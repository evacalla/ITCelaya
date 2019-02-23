package com.itcelaya.village.Model;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name =  "function")
    public Function function;

    @OneToOne
    @JoinColumn(name = "user")
    public User user;

    @OneToOne
    @JoinColumn(name = "seat")
    public Seat seat;

}
