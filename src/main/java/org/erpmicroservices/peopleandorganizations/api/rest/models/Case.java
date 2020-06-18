package org.erpmicroservices.peopleandorganizations.api.rest.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Case extends AbstractPersistable<UUID> {
 private String description;

 private LocalDateTime startedAt;

 @ManyToOne
 @JoinColumn(name = "case_type_id")
 private CaseType type;

 @ManyToOne
 @JoinColumn(name = "case_status_type_id")
 private CaseStatusType caseStatus;
}
