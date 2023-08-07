package org.erpmicroservices.peopleandorganizations.api.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "party_contact_mechanism")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyContactMechanism extends AbstractPersistable<UUID> {

 @Column(name = "from_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate fromDate;

 @Column(name = "thru_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate thruDate;

 private boolean doNotSolicitIndicator = true;

 private String comment;

 @ManyToOne
 @JoinColumn(name = "party_contact_mechanism_purpose_id")
 private PartyContactMechanismPurpose purpose;

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_id")
 private ContactMechanism contactMechanism;

}
