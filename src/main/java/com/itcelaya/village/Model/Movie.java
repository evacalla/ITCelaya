package com.itcelaya.village.Model;

import com.itcelaya.village.Model.Enum.Classification;
import com.itcelaya.village.Model.Enum.Gender;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Enumerated(EnumType.STRING)
    public Gender gender;

    @Size(max = 255)
    public String name;

    @Enumerated(EnumType.STRING)
    public Classification classification;

    public Integer duration;

    @Size(max = 255)
    public String description;

    @Size(max = 255)
    public String director;

}

