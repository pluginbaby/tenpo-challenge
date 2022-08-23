package com.challenge.tenpo.rest.repository;

import com.challenge.tenpo.rest.entities.HistoryEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryEndpointsRepository extends JpaRepository<HistoryEndpoint, Long> {

}
