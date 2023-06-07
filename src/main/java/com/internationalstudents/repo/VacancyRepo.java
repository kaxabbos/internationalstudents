package com.internationalstudents.repo;

import com.internationalstudents.model.Vacancy;
import com.internationalstudents.model.enums.VacancyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepo extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findAllByStatus(VacancyStatus status);
}
