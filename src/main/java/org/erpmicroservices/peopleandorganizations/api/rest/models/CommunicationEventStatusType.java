package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class CommunicationEventStatusType extends AbstractPersistable<UUID> {
    @NotBlank
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CommunicationEventStatusType parent;

    @OneToMany(mappedBy = "parent")
    @Builder.Default
    private List<CommunicationEventStatusType> children = new ArrayList<>();

    public boolean isAParent() {
        return !children.isEmpty();
    }

    public boolean isChild() {
        return parent != null;
    }


    @Override
    public @Nonnull String toString() {
        return "org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventStatusType{" + "description='" + getDescription() + '\'' +
                '}';
    }
}
