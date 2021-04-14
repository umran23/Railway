package com.railway.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Train_id;
    private String Name;
    private String Services;
    private String Speed;

    public Train(){

    }
}
