package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.ContactMechanismGeographicBoundary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface ContactMechanismGeographicBoundaryRepo extends PagingAndSortingRepository<ContactMechanismGeographicBoundary, UUID> {

}
