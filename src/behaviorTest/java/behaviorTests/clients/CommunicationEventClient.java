package behaviorTests.clients;

import behaviorTests.models.CommunicationEventEntityModel;
import behaviorTests.steps.StepContext;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEvent;
import org.junit.Assert;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

@Lazy
@Component
public class CommunicationEventClient extends BaseHATEOASClient {

    public CommunicationEventClient(RestTemplate RestTemplate, StepContext stepContext) {
        super(RestTemplate, stepContext);
    }

    protected URI communicationEventUrl() throws URISyntaxException {
        return (new URI(url())).resolve("communicationEvents");
    }

    public ResponseEntity<CommunicationEventEntityModel> create(CommunicationEvent communicationEvent) {
        try {
            return template.postForEntity(communicationEventUrl(), convertCommunicationEventToHttpStringEntity(communicationEvent), CommunicationEventEntityModel.class);
        } catch (URISyntaxException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    private HttpEntity<String> convertCommunicationEventToHttpStringEntity(CommunicationEvent communicationEvent) {
        String stupidJson = """
                  {
                  "started": "%s",
                  "ended": "%s",
                  "note": "%s",
                  "roles": %s,
                  "type": "http://localhost:8080/communicationEventTypes/%s",
                  "purposes": %s,
                  "statusType": "http://localhost:8080/communicationEventStatusTypes/%s",
                  "contactMechanismType": "http://localhost:8080/contactMechanismTypes/%s",
                  "relationship": "http://localhost:8080/relationship/%s"
                  %s
                }
                """.formatted(
                communicationEvent.getStarted(),
                communicationEvent.getEnded(),
                communicationEvent.getNote(),
                "[%s]".formatted(communicationEvent.getRoles().stream()
                        .map(role -> "http://localhost:8080/communicationEventRole/%s".formatted(role.getId()))
                        .collect(Collectors.joining(","))),
                communicationEvent.getType().getId(),
                "[%s]".formatted(communicationEvent.getPurposes().stream()
                        .map(purpose -> "http://localhost:8080/communicationEventPurposeType/%s".formatted(purpose.getId()))
                        .collect(Collectors.joining(","))),
                communicationEvent.getStatusType().getId(),
                communicationEvent.getContactMechanismType().getId(),
                communicationEvent.getRelationship().getId(),
                communicationEvent.getKase() == null ? "" : """ 
                        ", kase": "http://localhost:8080/cases/%s"
                        """.formatted(communicationEvent.getKase().getId())
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(stupidJson, headers);
    }
}
