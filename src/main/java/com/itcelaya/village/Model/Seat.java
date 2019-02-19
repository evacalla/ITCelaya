package com.itcelaya.village.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itcelaya.village.Model.Enum.Status;

import javax.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Character row;

    public Integer number;

    @OneToOne
    @JoinColumn(name = "id")
    public Room room;

    @Enumerated(EnumType.STRING)
    public Status status;

}
