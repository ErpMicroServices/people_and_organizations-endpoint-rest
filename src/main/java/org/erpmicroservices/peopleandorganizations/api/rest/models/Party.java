package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Party extends AbstractPersistable<UUID> {

 @ManyToOne
 @JoinColumn(name = "party_type_id")
 private PartyType type;

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyClassification> classifications = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyRole> roles = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyContactMechanism> contactMechanisms = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyName> names = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyName> identifications = new ArrayList<>();

}
