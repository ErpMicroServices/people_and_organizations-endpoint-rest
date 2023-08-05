package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity(name = "valid_contact_mechanism_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidContactMechanismRole extends AbstractPersistable<UUID> {

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_type_id")
 private ContactMechanismType contactMechanismType;

 @ManyToOne
 @JoinColumn(name = "communication_event_role_type_id")
 private CommunicationEventRoleType communicationEventRoleType;

}
