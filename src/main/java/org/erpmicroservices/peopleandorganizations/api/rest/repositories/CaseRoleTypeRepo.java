package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "caseRoleTypes", path = "caseRoleTypes")
public interface CaseRoleTypeRepo extends JpaRepository<CaseRoleType, UUID> {


	Page<CaseRoleType> findByParentIsNull(Pageable pageable);

	Page<CaseRoleType> findByDescriptionContaining(String description, Pageable pageable);

	Page<CaseRoleType> findCaseRoleTypeByParentIdIsNotNull(final Pageable pageable);

	Page<CaseRoleType> findCaseStatusTypeByParentIdIsNotNull(final Pageable pageable);
}
