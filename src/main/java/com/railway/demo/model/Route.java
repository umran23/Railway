package com.railway.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Route_id;
    private String Dep_City;
    private String Arvl_City;
    private Time Arvl_Time;
    private Time Dep_Time;
    private Time Duration;
    private Date Dep_date;
    private Date Arvl_date;
    private int price;
}
