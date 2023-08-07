package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity(name = "communication_event_work_effort")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationEventWorkEffort extends AbstractPersistable<UUID> {

 @Column(name = "work_effort_id")
 private UUID workEffortId;

}
