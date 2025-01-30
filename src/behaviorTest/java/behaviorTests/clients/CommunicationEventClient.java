package behaviorTests.clients;

import behaviorTests.models.CommunicationEventCollectionEntityModel;
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
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Lazy
@Component
public class CommunicationEventClient extends BaseHATEOASClient {

    public CommunicationEventClient(RestTemplate RestTemplate, StepContext stepContext) {
        super(RestTemplate, stepContext);
    }

    protected UriComponentsBuilder communicationEventUrl() throws MalformedURLException {
        return url()
                .pathSegment("communicationEvents");
    }

    public ResponseEntity<CommunicationEventEntityModel> create(CommunicationEvent communicationEvent) {
        try {
            return template.postForEntity(communicationEventUrl().build(false).toString().replaceAll("/+$", ""), convertCommunicationEventToHttpStringEntity(communicationEvent), CommunicationEventEntityModel.class);
        } catch (MalformedURLException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    public ResponseEntity<CommunicationEventCollectionEntityModel> findAllEventsBetween(ZonedDateTime fromTimestamp, ZonedDateTime thruTimestamp) {
        try {
            params.put("fromTimestamp", fromTimestamp);
            params.put("thruTimestamp", thruTimestamp);
            UriComponents uri =communicationEventUrl()
                    .pathSegment("search")
                    .pathSegment("findCommunicationEventsByEndedBetweenOrStartedBetween")
                    .queryParam("endedFrom", "{fromTimestamp}")
                    .queryParam("endedThru", "{thruTimestamp}")
                    .queryParam("startedFrom", "{fromTimestamp}")
                    .queryParam("startedThru", "{thruTimestamp}")
                    .query(paginationStringTemplate)
                    .build(false);


            return template.getForEntity(uri.toString()
                    , CommunicationEventCollectionEntityModel.class
                    , params
            );
        } catch (MalformedURLException e) {
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
