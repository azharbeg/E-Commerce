package com.example.productservicenov24.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // tell please auto generate a new ID
    private long id;
    private Date createdAt;
    private Date lastModifindAt;
    private boolean isDeleted;

}

/*
        Serializable is just an interface--
        If you want an object to be convert to a
        json to be transferd over the network  you have to
        implement serializable interface in that class
 */