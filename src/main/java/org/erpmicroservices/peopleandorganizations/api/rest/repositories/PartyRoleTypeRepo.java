package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Arrays;
import java.util.UUID;

@RepositoryRestResource
public interface PartyRoleTypeRepo extends JpaRepository<PartyRoleType, UUID> {

    Page<PartyRoleType> findPartyRoleTypesByParentIdIsNull(Pageable page);

    Page <PartyRoleType> findPartyRoleTypesByParentId(UUID id, Pageable unpaged);
}
