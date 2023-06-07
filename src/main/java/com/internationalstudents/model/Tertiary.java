package com.internationalstudents.model;

import com.internationalstudents.model.enums.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Tertiary {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Marital marital;
    @Enumerated(EnumType.STRING)
    private Citizenship citizenship;
    @Enumerated(EnumType.STRING)
    private YesNo conscripted;

    public Tertiary() {
        marital = Marital.SINGLE;
        citizenship = Citizenship.BELARUS;
        conscripted = YesNo.NO;
    }

    public void set(Marital marital, Citizenship citizenship, YesNo conscripted) {
        this.marital = marital;
        this.citizenship = citizenship;
        this.conscripted = conscripted;
    }
}
