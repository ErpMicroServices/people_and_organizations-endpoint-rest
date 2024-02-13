package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Party extends AbstractPersistable<UUID> {

 @NotNull
 @NotBlank
 private String comment;

 @ManyToOne
 @JoinColumn(name = "party_type_id")
 private PartyType type;

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 @Builder.Default
 private List<PartyClassification> classifications = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 @Builder.Default
 private List<PartyRole> roles = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 @Builder.Default
 private List<PartyContactMechanism> contactMechanisms = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 @Builder.Default
 private List<PartyName> names = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 @Builder.Default
 private List<PartyName> identifications = new ArrayList<>();

}
