package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Calculation;

public interface CalculationRepository
        extends MongoRepository<Calculation, String> {

}