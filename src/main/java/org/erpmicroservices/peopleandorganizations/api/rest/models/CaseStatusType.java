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
public class CaseStatusType extends AbstractPersistable<UUID> {

    @NotBlank
    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CaseStatusType parent;
    @OneToMany(mappedBy = "parent")
    private List<CaseStatusType> children = new ArrayList<>();

    @Builder
    public CaseStatusType(UUID id, String description, CaseStatusType parent, List<CaseStatusType> children) {
        setId(id);
        this.description = description;
        this.parent = parent;
        this.children = children;
    }

    public CaseStatusType() {

    }

    public boolean isAParent() {
        return !children.isEmpty();
    }

    public boolean isChild() {
        return parent != null;
    }
}
