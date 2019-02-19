package com.itcelaya.village.Model;

import javax.persistence.*;

@Entity
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name = "id")
    public Room room;

    @OneToOne
    @JoinColumn(name = "id")
    public Version version;
}
