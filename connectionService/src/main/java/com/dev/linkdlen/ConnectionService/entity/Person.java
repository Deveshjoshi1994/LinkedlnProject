package com.dev.linkdlen.ConnectionService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.RelationshipId;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Node("Person")
@Data // This automatically generates getters, setters, toString, etc.
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;


    private Long userId;

    private String name;



}
