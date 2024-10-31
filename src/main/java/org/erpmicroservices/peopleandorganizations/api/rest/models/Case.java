package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
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
    @JoinColumn(name = "case_type_id", nullable = false)
    @NotNull
    private CaseType type;
    @ManyToOne
    @JoinColumn(name = "case_status_type_id", nullable = false)
    @NotNull
    private CaseStatusType caseStatus;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "case_id")
    private List<CommunicationEvent> communicationEvents = new ArrayList<>();

    @Builder
    public Case(UUID id, String description, ZonedDateTime startedAt, CaseType type, CaseStatusType caseStatus, List<CommunicationEvent> communicationEvents) {
        setId(id);
        this.description = description;
        this.startedAt = startedAt;
        this.type = type;
        this.caseStatus = caseStatus;
        this.communicationEvents = new ArrayList<>();
        if (communicationEvents != null) {
            this.communicationEvents.addAll(communicationEvents);
        }

    }

    public void addCommunicationEvent(CommunicationEvent event) {
        this.communicationEvents.add(event);
        event.setKase(this);
    }


    @Override
    public @Nonnull String toString() {
        return "org.erpmicroservices.peopleandorganizations.api.rest.models.Case{" + "caseStatus=" + getCaseStatus() +
                ", description='" + getDescription() + '\'' +
                ", startedAt=" + getStartedAt() +
                ", type=" + getType().getDescription() +
                '}';
    }
}
