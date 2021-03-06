package org.erpmicroservices.peopleandorganizations.api.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "case_role")
public class CaseRole extends AbstractPersistable<UUID> {

 @Column(name = "from_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate fromDate;

 @Column(name = "thru_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate thruDate;

 @ManyToOne
 @JoinColumn(name = "case_id")
 private Case kase;

 @ManyToOne
 @JoinColumn(name = "case_role_type_id")
 private CaseRoleType type;

 @ManyToOne
 @JoinColumn(name = "party_id")
 private Party party;

 public LocalDate getFromDate() {
	return fromDate;
 }

 public void setFromDate(LocalDate fromDate) {
	this.fromDate = fromDate;
 }

 public LocalDate getThruDate() {
	return thruDate;
 }

 public void setThruDate(LocalDate thruDate) {
	this.thruDate = thruDate;
 }

 public Case getKase() {
	return kase;
 }

 public void setKase(Case kase) {
	this.kase = kase;
 }

 public CaseRoleType getType() {
	return type;
 }

 public void setType(CaseRoleType type) {
	this.type = type;
 }

 public Party getParty() {
	return party;
 }

 public void setParty(Party party) {
	this.party = party;
 }
}
