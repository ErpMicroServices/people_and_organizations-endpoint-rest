package org.erpmicroservices.peopleandorganizations.api.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity(name = "contact_mechanism")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactMechanism extends AbstractPersistable<UUID> {
 @NotBlank
 @NotNull
 private String endPoint;

 private String directions;

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_type_id")
 private ContactMechanismType type;

}
