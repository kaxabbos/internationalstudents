package com.internationalstudents.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Employees {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String photo;
    private String surname;
    private String name;
    private String patronymic;
    private String faculty;
    private String tel;

    public Employees() {
        photo = "def.jpg";
        surname = "Фамилия";
        name = "Имя";
        patronymic = "Отчество";
        faculty = "Факультет";
        tel = "Контактный телефон";
    }

    public void set(String surname, String name, String patronymic, String faculty, String tel) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.faculty = faculty;
        this.tel = tel;
    }

    public String getFIO(){
        return surname + " " + name + " " + patronymic;
    }


}
