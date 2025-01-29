package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface CommunicationEventRepo extends JpaRepository<CommunicationEvent, UUID> {

    List<CommunicationEvent> findAllByKase_Id(UUID aCaseId);

    Page<CommunicationEvent> findCommunicationEventsByEndedBetweenOrStartedBetween(
            @Param("endedFrom") ZonedDateTime endedFrom
            , @Param("endedThru") ZonedDateTime endedThru
            , @Param("startedFrom") ZonedDateTime startedFrom
            , @Param("startedThru") ZonedDateTime startedThru
            , Pageable pageable);
}
