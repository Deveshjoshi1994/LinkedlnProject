package com.dev.linkdlen.ConnectionService.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.RelationshipId;

@Node
public class Person {

    @Id
    @GeneratedValue
    private Long id;


    private Long userId;

    private String name;
}
