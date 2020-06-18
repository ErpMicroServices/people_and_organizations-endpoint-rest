package org.erpmicroservices.peopleandorganizations.api.rest.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "facility_contact_mechanism")
public class FacilitiyContactMechanism extends AbstractPersistable<UUID> {

 @ManyToOne
 @JoinColumn(name = "facility_id")
 private Facility facility;

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_id")
 private ContactMechanism contactMechanism;

}
