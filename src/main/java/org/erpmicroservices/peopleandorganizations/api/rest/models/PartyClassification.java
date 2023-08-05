package org.erpmicroservices.peopleandorganizations.api.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "party_classification")
@Table(name = "party_classification")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyClassification extends AbstractPersistable<UUID> {

 @ManyToOne
 @JoinColumn(name = "party_classification_type_id")
 @NotNull
 private PartyClassificationType type;

 @NotBlank
 @NotNull
 private String value;

 @Column(name = "from_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate fromDate;

 @Column(name = "thru_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate thruDate;

 public PartyClassificationType getType() {
	return type;
 }

 public void setType(PartyClassificationType type) {
	this.type = type;
 }

 public String getValue() {
	return value;
 }

 public void setValue(String value) {
	this.value = value;
 }

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
}
