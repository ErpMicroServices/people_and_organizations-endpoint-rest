package org.erpmicroservices.peopleandorganizations.api.rest.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "contact_mechanism_geographic_boundary")
@Data
@Builder

public class ContactMechanismGeographicBoundary extends AbstractPersistable<UUID> {
 @ManyToOne
 @JoinColumn(name = "contact_mechanism_id")
 private ContactMechanism contactMechanism;

 @ManyToOne
 @JoinColumn(name = "geographic_boundary_id")
 private GeographicBoundary geographBoundary;

}
