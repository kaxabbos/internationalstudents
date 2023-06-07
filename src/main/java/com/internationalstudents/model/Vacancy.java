package com.internationalstudents.model;

import com.internationalstudents.model.enums.VacancyStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vacancy {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
    @Enumerated(EnumType.STRING)
    private VacancyStatus status;

    public Vacancy(Users user) {
        this.user = user;
        this.status = VacancyStatus.WAITING;
    }
}
