package ru.innopolis.stc21.med.service;

import lombok.Data;

@Data
public class EntityForJSONofNN {
    long id;
    String accuracy;
    String result;

    public EntityForJSONofNN(long id, String accuracy, String result) {
        this.id = id;
        this.result = result;
        this.accuracy = accuracy;
    }

    public EntityForJSONofNN() {
    }
}