package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface CommunicationEventRepo extends JpaRepository<CommunicationEvent, UUID> {

     List<CommunicationEvent> findAllByKase_Id(UUID ACase_id);
}
