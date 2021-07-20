package org.erpmicroservices.peopleandorganizations.api.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "geographic_boundary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeographicBoundary extends AbstractPersistable<UUID> {

 private String geoCode;

 private String name;

 private String abbreviation;

 @ManyToOne
 @JoinColumn(name = "geographic_boundary_type_id")
 private GeographicBoundaryType type;

 @ManyToMany
 @JoinTable(
   name = "geographic_boundary_association",
	 joinColumns = @JoinColumn(name = "in_boundary"),
	 inverseJoinColumns = @JoinColumn(name = "within_boundary"))
 private List<GeographicBoundary> inside = new ArrayList<>();

 @ManyToMany
 @JoinTable(
	 name = "geographic_boundary_association",
	 joinColumns = @JoinColumn(name = "within_boundary"),
	 inverseJoinColumns = @JoinColumn(name = "in_boundary"))
 private List<GeographicBoundary> within = new ArrayList<>();

}

