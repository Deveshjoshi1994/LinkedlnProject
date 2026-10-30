package com.dev.linkdlen.ConnectionService.controller;

import com.dev.linkdlen.ConnectionService.entity.Person;
import com.dev.linkdlen.ConnectionService.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
@Slf4j
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/core/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId) {
        log.info("User id is {}", userId);
        List<Person> personList = connectionService.getFirstDegreeConnectionsOfUser(userId);
        return ResponseEntity.ok(personList); // Return the list directly without casting!
    }


    @PostMapping("/request/{userId}")
    public ResponseEntity<Void> sendConnectionRequest(@PathVariable Long userId) {

        connectionService.sendConnectionRequest(userId);

        return ResponseEntity.noContent().build();

    }

    @PostMapping("/accept/{userId}")
    public ResponseEntity<Void> acceptConnectionRequest(@PathVariable Long userId) {
        connectionService.acceptConnectionRequest(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reject/{userId}")
    public ResponseEntity<Void> rejectConnectionRequest(@PathVariable Long userId) {
        connectionService.rejectConnectionRequest(userId);
        return ResponseEntity.noContent().build();
    }


}
