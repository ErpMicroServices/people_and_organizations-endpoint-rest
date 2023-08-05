package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity(name = "facility_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacitlityRole extends AbstractPersistable<UUID> {

 @ManyToOne
 @JoinColumn(name = "party_id")
 private Party party;

 @ManyToOne
 @JoinColumn(name = "facility_role_type_id")
 private FacilityRoleType type;

}
