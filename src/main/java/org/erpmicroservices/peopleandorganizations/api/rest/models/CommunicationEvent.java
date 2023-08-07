package org.erpmicroservices.peopleandorganizations.api.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "communication_event")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationEvent extends AbstractPersistable<UUID> {
 @Column(name = "started", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
 private LocalDateTime started;

 @Column(name = "ended", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
 private LocalDateTime ended;

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
 private Case aCase;

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 private List<CommunicationEventPurpose> purposes = new ArrayList<>();

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 private List<CommunicationEventRole> roles = new ArrayList<>();

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 private List<CommunicationEventWorkEffort> effort = new ArrayList<>();

}
