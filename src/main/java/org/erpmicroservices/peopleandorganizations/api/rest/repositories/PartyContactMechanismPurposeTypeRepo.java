package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyContactMechanismPurposeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PartyContactMechanismPurposeTypeRepo extends JpaRepository<PartyContactMechanismPurposeType, UUID> {
}
