package com.b902.watersupply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WaterSupplyController {

    @Autowired
    public WaterUsersService waterUsersService;
    @Autowired
    private WaterRepository waterRepository;

    @GetMapping("/spin")
    @CrossOrigin(origins = "*")
    public List<WaterUsers> getCurrentTaskPerformers(){
        return waterUsersService.getCurrentUsers();
    }

    @GetMapping("/ping")
    public ResponseEntity<?> isSpinAvailable(){
        return waterUsersService.isSpinAvailable();
    }

    @PostMapping("/add")
    public ResponseEntity registerUser(@RequestBody WaterUsers waterUsers){
        waterUsersService.addUser(waterUsers);
        return ResponseEntity.ok("User Added");
    }



    /*@GetMapping("/voter")
    public List<WaterUsers> getVotersList(){
        return  waterUsersService.getVotersList();
    }*/

    @PostMapping("/verify")
    public ResponseEntity<?> verifyVote(@RequestBody UsernamePassword usernamePassword){
        return waterUsersService.registerVote(usernamePassword.getUsername(), usernamePassword.getPassword());
    }
}
