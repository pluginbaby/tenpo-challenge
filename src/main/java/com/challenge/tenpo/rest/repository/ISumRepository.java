package com.challenge.tenpo.rest.repository;

import java.util.Optional;

public interface ISumRepository {

    Optional<Double> getPercentage();
    void setPercentage(Double percentage);

}
