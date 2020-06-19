# people_and_organizations-endpoint-rest
Restful endpoint for People and Organizations services.

 A restful HATEOAS based API for handling data about People and Organizations. The people and organizations could be employees,
 customers, rivals, partners, suppliers or anyone, or any organization with which the business needs to communicate, classify or 
 otherwise deal with.


# Build & Push to docker
 
    ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=erpmicroservices/people_and_organizations_endpoint_rest
    
    docker push erpmicroservices/people_and_organizations_endpoint_rest:latest
