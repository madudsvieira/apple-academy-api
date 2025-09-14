package br.senac.sp.appleacademyapi.cohort;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortRepository extends JpaRepository<Cohort, UUID>{

    
} 
