package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity(name = "communication_event_purpose")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationEventPurpose extends AbstractPersistable<UUID> {
 @NotBlank
 @NotNull
 private String description;

 @ManyToOne
 @JoinColumn(name = "communication_event_purpose_type_id")
 private CommunicationEventPurposeType type;

}
