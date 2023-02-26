package com.b902.watersupply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WaterRepository  extends JpaRepository<WaterUsers, Integer> {

    @Query("SELECT w FROM WaterUsers w WHERE noOfCyclesCompleted = (SELECT MIN(noOfCyclesCompleted) FROM WaterUsers)")
    public List<WaterUsers> findCurrentCycleUsers();

    public WaterUsers findByUsername(String username);

    @Modifying
    @Query("UPDATE WaterUsers set inQueue = :flag WHERE username = :username")
    public void setQueueValue(String username, char flag);

    @Modifying
    @Query("UPDATE WaterUsers set hasVoted = :flag WHERE username = :username")
    public void registerVote(String username, char flag);

    @Query("SELECT w FROM WaterUsers w WHERE inQueue = :inQueue")
    public List<WaterUsers> getCurrUsersList(char inQueue);

    @Modifying
    @Query("UPDATE WaterUsers set inQueue = :inQueue, noOfCyclesCompleted = :currCount WHERE inQueue = :currState")
    public void setCurrAssignee(char inQueue, char currState, int currCount);

    @Modifying
    @Query("UPDATE WaterUsers set hasVoted = :hasVoted")
    public void removeVotes(char hasVoted);
}
