package com.internationalstudents.repo;

import com.internationalstudents.model.Apps;
import com.internationalstudents.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppsRepo extends JpaRepository<Apps, Long> {
    List<Apps> findAllByOwner(Users owner);

}
