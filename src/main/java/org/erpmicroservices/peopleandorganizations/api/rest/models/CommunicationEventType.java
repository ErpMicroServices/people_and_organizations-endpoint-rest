package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommunicationEventType extends AbstractPersistable<UUID> {

    @NotBlank
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CommunicationEventType parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CommunicationEventType> children = new ArrayList<>();

    public boolean isAParent() {
        return !children.isEmpty();
    }

    public boolean isChild() {
        return parent != null;
    }

    @Override
    public @Nonnull String toString() {
        return "org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventType{" + "description='" + getDescription() + '\'' +
                '}';
    }
}
