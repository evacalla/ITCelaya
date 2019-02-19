package com.itcelaya.village.Model;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name =  "id")
    public Function function;

    @OneToOne
    @JoinColumn(name = "id")
    public User user;

    @OneToOne
    @JoinColumn(name = "id")
    public Seat seat;
}
