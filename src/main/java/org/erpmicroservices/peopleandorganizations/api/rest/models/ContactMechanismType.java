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
public class ContactMechanismType extends AbstractPersistable<UUID> {
    @NotBlank
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ContactMechanismType parent;

    @OneToMany
    @JoinColumn(name = "contact_mechanism_type_id")
    @Builder.Default
    private List<ValidContactMechanismRole> validContactMechanismRoles = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    @Builder.Default
    private List<ContactMechanismType> children = new ArrayList<>();

    public boolean isAParent() {
        return !children.isEmpty();
    }

    public boolean isChild() {
        return parent != null;
    }


    @Override
    public @Nonnull String toString() {
        return "org.erpmicroservices.peopleandorganizations.api.rest.models.ContactMechanismType{" + "description='" + description + '\'' +
                '}';
    }

}
