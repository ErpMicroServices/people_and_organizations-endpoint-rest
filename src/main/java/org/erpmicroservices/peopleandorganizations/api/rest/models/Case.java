package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.ZonedDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "kase")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Case extends AbstractPersistable<UUID> {
 private String description;

 private ZonedDateTime startedAt;

 @ManyToOne
 @JoinColumn(name = "case_type_id")
 private CaseType type;

 @ManyToOne
 @JoinColumn(name = "case_status_type_id")
 private CaseStatusType caseStatus;

}
