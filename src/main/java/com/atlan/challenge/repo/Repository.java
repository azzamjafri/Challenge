package com.atlan.challenge.repo;

import com.atlan.challenge.model.Model;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface Repository extends JpaRepository<Model, String> {
    
}