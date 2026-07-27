package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Calculation;
import com.example.demo.repository.CalculationRepository;

@Service
public class CalculatorService {

    @Autowired
    private CalculationRepository repository;

    public double calculate(double firstNumber,
                            double secondNumber,
                            String operation) {

        double result = 0;

        switch (operation) {

            case "+":
                result = firstNumber + secondNumber;
                break;

            case "-":
                result = firstNumber - secondNumber;
                break;

            case "*":
                result = firstNumber * secondNumber;
                break;

            case "/":
                result = firstNumber / secondNumber;
                break;

            default:
                throw new IllegalArgumentException("Invalid Operation");
        }

        Calculation calculation = new Calculation(
                firstNumber,
                secondNumber,
                operation,
                result);

        repository.save(calculation);

        return result;
    }
}