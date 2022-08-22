package com.challenge.tenpo.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SumRepositoryImpl implements ISumRepository{

    private Double percentage = null;

    @Override
    public Optional<Double> getPercentage() {
        return Optional.ofNullable(percentage);
    }

    @Override
    public void setPercentage(final Double percentage) {
        this.percentage = percentage;
    }


}
