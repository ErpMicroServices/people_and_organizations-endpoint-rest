package org.erpmicroservices.peopleandorganizations.api.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "party_relationship")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PartyRelationship extends AbstractPersistable<UUID> {

    @Column(name = "from_date", columnDefinition = "DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Column(name = "thru_date", columnDefinition = "DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate thruDate;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "from_party_role_id", nullable = false, updatable = false)
    @NotNull
    private PartyRole fromPartyRole;

    @ManyToOne
    @JoinColumn(name = "to_party_role_id", nullable = false, updatable = false)
    @NotNull
    private PartyRole toPartyRole;

    @ManyToOne
    @JoinColumn(name = "party_relationship_type_id", nullable = false)
    @NotNull
    private PartyRelationshipType type;

    @ManyToOne
    @JoinColumn(name = "priority_type_id", nullable = false)
    @NotNull
    private PriorityType priority;

    @OneToMany
    @JoinColumn(name = "party_relationship_id")
    @Builder.Default
    private List<CommunicationEvent> communicationEvents = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "party_relationship_status_type_id", nullable = false)
    @NotNull
    private PartyRelationshipStatusType status;


    @Override
    public @Nonnull String toString() {
        return "org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRelationship{" + "comment='" + getComment() + '\'' +
                ", communicationEvents=" + getCommunicationEvents() +
                ", fromDate=" + getFromDate() +
                ", fromPartyRole=" + getFromPartyRole().getType().getDescription() +
                ", priority=" + getPriority().getDescription() +
                ", status=" + getStatus().getDescription() +
                ", thruDate=" + getThruDate() +
                ", toPartyRole=" + getToPartyRole().getType().getDescription() +
                ", type=" + getType().getDescription() +
                '}';
    }
}
