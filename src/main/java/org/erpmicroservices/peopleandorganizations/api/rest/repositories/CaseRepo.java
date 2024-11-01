package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface CaseRepo extends JpaRepository<Case, UUID> {

    List<Case> findAllByType_Description(String caseTypeDescription);

    List<Case> findAllByCaseStatus_Description(String caseStatusDescription);
}
