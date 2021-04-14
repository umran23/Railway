package com.railway.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Vagon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Vagon_id;
    private String Type;
    private int size;
}
