package com.dev.linkdlen.ConnectionService.repository;

import com.dev.linkdlen.ConnectionService.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {


    Optional<Person> findByUserId(Long aLong);


    @Query("MATCH (personA:Person)-[:CONNECTED_TO]->(personB:Person) " +
            "WHERE personA.userId = $userId " +
            "RETURN personB;")
    List<Person> getFirstDegreeConnection(Long userId);
}
