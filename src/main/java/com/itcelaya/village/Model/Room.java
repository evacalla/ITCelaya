package com.itcelaya.village.Model;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Size(min = 3, max = 10, message = "Minimum name length: 3 characters")
    public String name;

}
