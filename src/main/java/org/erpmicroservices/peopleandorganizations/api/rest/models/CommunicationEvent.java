package org.erpmicroservices.peopleandorganizations.api.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "communication_event")
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
 private PartyRelationship partyRelationship;

 @ManyToOne
 @JoinColumn(name = "communication_event_status_type_id")
 private CommunicationEventStatusType statusType;

 @ManyToOne
 @JoinColumn(name = "communication_event_type_id")
 private CommunicationEventType type;

 @ManyToOne
 @JoinColumn(name = "case_id")
 private Case kase;
}
