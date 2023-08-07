package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity(name = "contact_mechanism_geographic_boundary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContactMechanismGeographicBoundary extends AbstractPersistable<UUID> {
 @ManyToOne
 @JoinColumn(name = "contact_mechanism_id")
 private ContactMechanism contactMechanism;

 @ManyToOne
 @JoinColumn(name = "geographic_boundary_id")
 private GeographicBoundary geographBoundary;

}
