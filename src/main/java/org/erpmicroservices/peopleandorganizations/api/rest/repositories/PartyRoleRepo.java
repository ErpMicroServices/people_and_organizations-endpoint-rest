package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.Case;
import org.erpmicroservices.peopleandorganizations.api.rest.models.Party;
import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRole;
import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface PartyRoleRepo extends JpaRepository<PartyRole, UUID> {

    Page<PartyRole> findAllByType_Description(String partyRoleTypeDescription, Pageable pageable);

}
