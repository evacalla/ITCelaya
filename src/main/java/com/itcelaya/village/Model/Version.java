package com.itcelaya.village.Model;

import com.itcelaya.village.Model.Enum.Language;

import javax.persistence.*;

@Entity
@Table(name = "Version")
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name = "id")
    public Movie movie;

    @Enumerated(EnumType.STRING)
    public Language language;

}
