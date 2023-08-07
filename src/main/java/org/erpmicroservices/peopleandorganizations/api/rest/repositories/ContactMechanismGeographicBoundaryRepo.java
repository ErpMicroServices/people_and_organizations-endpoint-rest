package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.ContactMechanismGeographicBoundary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface ContactMechanismGeographicBoundaryRepo extends JpaRepository<ContactMechanismGeographicBoundary, UUID> {

}
