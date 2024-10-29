package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "kase")
@Data
@NoArgsConstructor
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
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "case_id")
    private List<CommunicationEvent> communicationEvents;

    @Builder
    public Case(UUID id, String description, ZonedDateTime startedAt, CaseType type, CaseStatusType caseStatus, List<CommunicationEvent> communicationEvents) {
        setId(id);
        this.description = description;
        this.startedAt = startedAt;
        this.type = type;
        this.caseStatus = caseStatus;
        this.communicationEvents = communicationEvents;
    }

}
