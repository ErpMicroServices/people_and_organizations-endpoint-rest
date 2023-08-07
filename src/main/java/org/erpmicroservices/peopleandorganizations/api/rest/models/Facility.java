package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

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
    @Builder.Default
	private List<Facility> contains = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "facility_type_id")
	private FacilityType type;

	@OneToMany
    @JoinColumn(name = "facility_id")
    @Builder.Default
	private List<FacitlityRole> roles = new ArrayList<>();

}
