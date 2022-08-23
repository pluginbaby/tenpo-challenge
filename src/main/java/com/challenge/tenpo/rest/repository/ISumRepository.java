package com.challenge.tenpo.repository;

import java.util.Optional;

public interface ISumRepository {

    Optional<Double> getPercentage();
    void setPercentage(Double percentage);

}
