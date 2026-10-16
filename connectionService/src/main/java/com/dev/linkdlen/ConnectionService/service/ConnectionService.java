package com.dev.linkdlen.ConnectionService.service;

import com.dev.linkdlen.ConnectionService.entity.Person;
import com.dev.linkdlen.ConnectionService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionService {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnectionsOfUser(Long userId) {

        log.info("Getting first Degree connections of user with ID:{}", userId);

        return personRepository.getFirstDegreeConnection(userId);
    }

}
