package org.erpmicroservices.peopleandorganizations.api.rest.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "communication_event_role")
@Data
@Builder

public class CommunicationEventRole extends AbstractPersistable<UUID> {
 @ManyToOne
 @JoinColumn(name = "communication_event_role_type_id")
 private CommunicationEventRoleType type;

 @ManyToOne
 @JoinColumn(name = "party_id")
 private Party party;

}
