package org.csu.mypetstore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sequence {
    
    private  String name;

    
    private int nextId;

    public Sequence(String name,int nextId){
        this.name = name;
        this.nextId = nextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNextId() {
        return nextId;
    }
}