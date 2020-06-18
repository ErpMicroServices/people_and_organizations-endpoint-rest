package org.erpmicroservices.peopleandorganizations.api.rest.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Party extends AbstractPersistable<UUID> {

 @ManyToOne
 private PartyType type;

 @OneToMany
 private List<PartyClassification> classifications = new ArrayList<PartyClassification>();
}
