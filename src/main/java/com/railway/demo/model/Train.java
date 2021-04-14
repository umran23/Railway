package com.railway.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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
