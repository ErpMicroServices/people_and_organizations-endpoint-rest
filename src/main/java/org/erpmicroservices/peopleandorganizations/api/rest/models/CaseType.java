package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CaseType extends AbstractPersistable<UUID> {

    @NotBlank
    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CaseType parent;
    @OneToMany(mappedBy = "parent")
    private List<CaseType> children = new ArrayList<>();

    @Builder
    public CaseType(UUID id, String description, CaseType parent, List<CaseType> children) {
        setId(id);
        this.description = description;
        this.parent = parent;
        this.children = children;
    }

    public CaseType() {

    }

    public boolean isAParent() {
        return !children.isEmpty();
    }

    public boolean isChild() {
        return parent != null;
    }
}
