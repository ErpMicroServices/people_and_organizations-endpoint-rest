package org.erpmicroservices.peopleandorganizations.api.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseRoleType extends AbstractPersistable<UUID> {
	@NotBlank
	@NotNull
	private String description;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private CaseRoleType parent;

	@OneToMany(mappedBy = "parent")
	private List<CaseRoleType> children = new ArrayList<>();

	public boolean isAParent() {
		return !children.isEmpty();
	}

	public boolean isChild() {
		return parent != null;
	}

}
