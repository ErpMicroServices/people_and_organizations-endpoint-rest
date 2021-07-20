package org.erpmicroservices.peopleandorganizations.api.rest.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "contact_mechanism")
@Data
@Builder

public class ContactMechanism extends AbstractPersistable<UUID> {
 @NotBlank
 @NotNull
 private String endPoint;

 private String directions;

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_type_id")
 private ContactMechanismType type;

}
