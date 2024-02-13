package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseRole;
import org.erpmicroservices.peopleandorganizations.api.rest.models.FacitlityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface FacilityRoleRepo extends JpaRepository<FacitlityRole, UUID> {
}
