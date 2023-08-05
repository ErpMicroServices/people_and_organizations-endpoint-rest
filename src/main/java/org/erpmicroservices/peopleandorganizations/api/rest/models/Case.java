package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "kase")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
