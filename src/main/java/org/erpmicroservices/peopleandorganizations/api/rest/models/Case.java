package org.erpmicroservices.peopleandorganizations.api.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
