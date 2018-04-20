package org.erpmicroservices.peopleandorganizations.service.endpoints.rest.repositories;


import org.erp_microservices.people_and_organizations.models.party.model.Party;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PartyRepository extends PagingAndSortingRepository<Party, UUID> {
}
