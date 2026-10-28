package com.dev.linkdlen.ConnectionService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Person")
@Data // This automatically generates getters, setters, toString, etc.
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;


    private Long userId;

    private String name;


}
