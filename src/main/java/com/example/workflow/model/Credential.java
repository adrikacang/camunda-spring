package com.example.workflow.model;

import jakarta.persistence.*;

@Entity
public class Credential {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String value;

    protected Credential() {}

    public Credential(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, key='%s', value='%s']",
                id, name, value);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
