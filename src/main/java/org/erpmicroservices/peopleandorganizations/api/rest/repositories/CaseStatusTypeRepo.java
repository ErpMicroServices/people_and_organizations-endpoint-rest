package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface CaseStatusTypeRepo extends JpaRepository<CaseStatusType, UUID> {

    CaseStatusType findByDescription( String description);
    Page<CaseStatusType> findCaseStatusTypeByParentIdIsNotNull(Pageable unpaged);
}
