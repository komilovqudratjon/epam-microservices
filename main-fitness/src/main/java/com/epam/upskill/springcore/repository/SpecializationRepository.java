package com.epam.upskill.authenticationservice.repository;

import com.epam.upskill.authenticationservice.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: TODO This class is used for SpecializationRepository
 * @date: 19 November 2023 $
 * @time: 2:48 AM 09 $
 * @author: Qudratjon Komilov
 */
@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

}