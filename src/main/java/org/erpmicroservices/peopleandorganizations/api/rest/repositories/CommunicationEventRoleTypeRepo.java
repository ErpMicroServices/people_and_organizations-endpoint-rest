package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Arrays;
import java.util.UUID;

@RepositoryRestResource
public interface CommunicationEventRoleTypeRepo extends JpaRepository<CommunicationEventRoleType, UUID> {

    Page<CommunicationEventRoleType> findCommunicationEventRoleTypeByParentIdIsNotNull(Pageable pageable);
}
