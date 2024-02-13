package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventPurposeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface CommunicationEventPurposeTypeRepo extends JpaRepository<CommunicationEventPurposeType, UUID> {

    Page<CommunicationEventPurposeType> findCommunicationEventPurposeTypeByParentIdIsNotNull(Pageable pageable);
}
