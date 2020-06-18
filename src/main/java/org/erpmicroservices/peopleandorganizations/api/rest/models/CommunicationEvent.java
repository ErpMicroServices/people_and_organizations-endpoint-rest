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
 private Case aCase;

 public LocalDateTime getStarted() {
  return started;
 }

 public void setStarted(LocalDateTime started) {
  this.started = started;
 }

 public LocalDateTime getEnded() {
  return ended;
 }

 public void setEnded(LocalDateTime ended) {
  this.ended = ended;
 }

 public String getNote() {
  return note;
 }

 public void setNote(String note) {
  this.note = note;
 }

 public ContactMechanismType getContactMechanismType() {
  return contactMechanismType;
 }

 public void setContactMechanismType(ContactMechanismType contactMechanismType) {
  this.contactMechanismType = contactMechanismType;
 }

 public PartyRelationship getPartyRelationship() {
  return partyRelationship;
 }

 public void setPartyRelationship(PartyRelationship partyRelationship) {
  this.partyRelationship = partyRelationship;
 }

 public CommunicationEventStatusType getStatusType() {
  return statusType;
 }

 public void setStatusType(CommunicationEventStatusType statusType) {
  this.statusType = statusType;
 }

 public CommunicationEventType getType() {
  return type;
 }

 public void setType(CommunicationEventType type) {
  this.type = type;
 }


 public Case getaCase() {
  return aCase;
 }

 public void setaCase(Case aCase) {
  this.aCase = aCase;
 }
}
