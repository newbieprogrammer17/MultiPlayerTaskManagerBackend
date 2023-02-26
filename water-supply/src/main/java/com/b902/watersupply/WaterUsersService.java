package com.b902.watersupply;

import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class WaterUsersService {

    @Autowired
    public WaterRepository waterRepository;

    @Transactional
    public List<WaterUsers> getCurrentUsers(){
        if(waterRepository.findAll().stream().filter(waterUsers -> waterUsers.getInQueue() == 'Y').count() == 0) {
            List<WaterUsers> allSampleUsers = waterRepository.findCurrentCycleUsers();
            List<WaterUsers> randomUsers = getRandomUsers(allSampleUsers);
            randomUsers.forEach(waterUsers -> waterRepository.setQueueValue(waterUsers.getUsername(), 'Y'));
            return randomUsers;
        }else{
             throw new UnableToProceedRequestException("WaterUsersService","Current Assignees Should Complete the Task First");
        }
    }

    public List<WaterUsers> getCurrentUsersList(){
        return waterRepository.getCurrUsersList('Y');
    }

    @Transactional
    public ResponseEntity<?> registerVote(String username, String password){
        Boolean isVerified = verifyUsername(username, password);
        List<WaterUsers> waterUsersList = getCurrentUsersList();
        Boolean areVotesDone = areAllVotedCompleted();
        if(waterUsersList.stream().count() > 0){
            if(isVerified && !areVotesDone){
                waterRepository.registerVote(username, 'Y');
            }
            if(isVerified && areVotesDone){
                int currCount = waterUsersList.get(0).getNoOfCyclesCompleted();
                waterRepository.setCurrAssignee('N','Y',currCount + 1);
                waterRepository.removeVotes('N');
                return ResponseEntity.ok().header("action","disable").body("Votes Registered Task Completed.");
            }
        }else {
            return ResponseEntity.badRequest().body("Minimum Required Users Have Already Voted !!");
        }

        return ResponseEntity.ok(isVerified);
    }


    public void addUser(WaterUsers waterUsers){
        String salt = BCrypt.gensalt();
        WaterUsers user = new WaterUsers();
        user.setHasVoted('N');
        user.setInQueue('N');
        user.setNoOfCyclesCompleted(0);
        user.setUsername(waterUsers.getUsername());
        user.setPassword(BCrypt.hashpw(waterUsers.getPassword(),salt));

        waterRepository.save(user);
    }

    public Boolean verifyUsername(String username, String password){
        WaterUsers currUser = waterRepository.findByUsername(username);
        if(currUser == null || currUser.equals("")) {
            throw new UserNotFoundException("WaterSupplyService", "User", username);
        }
            System.out.println(BCrypt.checkpw(password, currUser.getPassword()));
            Boolean isVerified =  BCrypt.checkpw(password, currUser.getPassword()) && currUser.getInQueue() == 'N' && currUser.getHasVoted() == 'N';
        return isVerified;
    }

    public ResponseEntity<?> isSpinAvailable(){
        List<WaterUsers> allUsers = waterRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        int avail = (int) allUsers.stream().filter(alluser -> alluser.getInQueue() == 'Y').count();
        if(avail == 0){
            stringBuilder.append(true);
            stringBuilder.append(" ");
            stringBuilder.append("proceed with spin.");
        }else{
            List<WaterUsers> YUsers = allUsers.stream().filter(alluser -> alluser.getInQueue() == 'Y').toList();
            stringBuilder.append(false);
            YUsers.forEach(user -> stringBuilder.append(" " +user.getUsername()));

        }
        return ResponseEntity.ok().body(stringBuilder.toString());
    }

    public Boolean areAllVotedCompleted(){
        List<WaterUsers> allUsers = waterRepository.findAll();

        int currVotes = (int) allUsers.stream()
                .filter(allUser -> allUser.getHasVoted() == 'Y')
                .count();

        System.out.println("currVotes :: "+currVotes);

        Boolean areVotesDone = currVotes >= 1;

        return areVotesDone;
    }


    public List<WaterUsers> getRandomUsers(List<WaterUsers> allSampleUsers){
        List<WaterUsers> randomlyChosen = new ArrayList<>();
        int firstRandom , secondRandom = 0;
        firstRandom = (int) Math.floor(Math.random() * allSampleUsers.size());
        do{
            secondRandom = (int) Math.floor(Math.random() * allSampleUsers.size());
        }while(firstRandom == secondRandom);
        randomlyChosen.add(allSampleUsers.get(firstRandom));
        randomlyChosen.add(allSampleUsers.get(secondRandom));

        return randomlyChosen;
    }

}
