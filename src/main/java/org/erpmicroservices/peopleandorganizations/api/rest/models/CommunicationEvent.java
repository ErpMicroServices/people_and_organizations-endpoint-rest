package org.erpmicroservices.peopleandorganizations.api.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "communication_event")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommunicationEvent extends AbstractPersistable<UUID> {
 @Column(name = "started", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 private ZonedDateTime started;

 @Column(name = "ended", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 private ZonedDateTime ended;

 private String note;

 @ManyToOne
 private ContactMechanismType contactMechanismType;

 @ManyToOne
 @JoinColumn(name = "party_relationship_id")
 private PartyRelationship relationship;

 @ManyToOne
 @JoinColumn(name = "communication_event_status_type_id")
 private CommunicationEventStatusType statusType;

 @ManyToOne
 @JoinColumn(name = "communication_event_type_id")
 private CommunicationEventType type;

 @ManyToOne
 @JoinColumn(name = "case_id")
 private Case kase;

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 @Builder.Default
 private List<CommunicationEventPurpose> purposes = new ArrayList<>();

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 @Builder.Default
 private List<CommunicationEventRole> roles = new ArrayList<>();

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 @Builder.Default
 private List<CommunicationEventWorkEffort> effort = new ArrayList<>();

}
