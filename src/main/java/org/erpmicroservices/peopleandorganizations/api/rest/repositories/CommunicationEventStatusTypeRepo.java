package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface CommunicationEventStatusTypeRepo extends JpaRepository<CommunicationEventStatusType, UUID> {

    Page<CommunicationEventStatusType> findCommunicationEventStatusTypeByParentIdIsNull(Pageable page);


    Page<CommunicationEventStatusType> findCommunicationEventStatusTypeByParentId(UUID parent_id, Pageable pageable);

    CommunicationEventStatusType findByDescription( String description );
}
