package org.erpmicroservices.peopleandorganizations.api.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "party_relationship")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PartyRelationship extends AbstractPersistable<UUID> {

 @Column(name = "from_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate fromDate;

 @Column(name = "thru_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate thruDate;

 private String comment;

 @ManyToOne
 private PartyRole fromPartyRole;

 @ManyToOne
 private PartyRole toPartyRole;

 @ManyToOne
 @JoinColumn(name = "party_relationship_type_id")
 private PartyRelationshipType type;

 @ManyToOne
 @JoinColumn(name = "priority_type_id")
 private PriorityType priority;

 @OneToMany
 @JoinColumn(name = "party_relationship_id")
 @Builder.Default
 private List<CommunicationEvent> communicationEvents = new ArrayList<>();

}
