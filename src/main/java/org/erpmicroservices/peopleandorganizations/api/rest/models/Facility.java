package org.erpmicroservices.peopleandorganizations.api.rest.models;

import lombok.*;
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

@Entity(name = "facility")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Facility extends AbstractPersistable<UUID> {
	@NotBlank
	@NotNull
	private String description;

	private Long squareFootage;

	@ManyToOne
	@JoinColumn(name = "part_of_id")
	private Facility partOf;

	@OneToMany
	@JoinColumn(name = "part_of_id")
	private List<Facility> contains = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "facility_type_id")
	private FacilityType type;

	@OneToMany
	@JoinColumn(name = "facility_id")
	private List<FacitlityRole> roles = new ArrayList<>();

}
