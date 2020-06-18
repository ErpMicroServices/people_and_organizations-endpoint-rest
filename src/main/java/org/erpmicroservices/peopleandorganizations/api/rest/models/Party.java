package org.erpmicroservices.peopleandorganizations.api.rest.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Party extends AbstractPersistable<UUID> {

 @ManyToOne
 private PartyType type;

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 private List<PartyClassification> classifications = new ArrayList<PartyClassification>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 private List<PartyRole> roles = new ArrayList<PartyRole>();
}
