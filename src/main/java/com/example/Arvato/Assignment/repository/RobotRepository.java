package com.example.Arvato.Assignment.repository;

import com.example.Arvato.Assignment.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {
}
