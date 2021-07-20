package org.erpmicroservices.peopleandorganizations.api.rest.repositories;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseRoleType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "caseRoleTypes", path = "caseRoleTypes")
public interface CaseRoleTypeRepo extends PagingAndSortingRepository<CaseRoleType, UUID> {

	@RestResource(exported = true, path = "/roots", rel = "roots")
	List<CaseRoleType> findAllByParentIsNull();

}
