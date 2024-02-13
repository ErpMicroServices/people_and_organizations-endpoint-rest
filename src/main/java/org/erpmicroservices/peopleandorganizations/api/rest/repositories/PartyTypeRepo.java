package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PartyTypeRepo extends JpaRepository<PartyType, UUID> {

    Page<PartyType> findPartyTypesByParentIdIsNull(Pageable page);

    Page<PartyType> findPartyTypesByParentId(UUID parent_id, Pageable pageable);
}
