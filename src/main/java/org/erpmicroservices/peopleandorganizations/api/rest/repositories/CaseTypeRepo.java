package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface CaseTypeRepo extends JpaRepository<CaseType, UUID> {

    Page<CaseType> findCaseTypeByParentIdIsNotNull(Pageable unpaged);
}
