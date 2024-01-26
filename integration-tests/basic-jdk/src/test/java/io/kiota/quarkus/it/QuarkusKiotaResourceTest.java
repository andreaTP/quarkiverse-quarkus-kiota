package io.kiota.quarkus.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.apisdk.example.yaml.ApiClient;
import io.apisdk.example.yaml.models.Greeting;
import io.kiota.http.jdk.JDKRequestAdapter;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class QuarkusKiotaResourceTest {
    @Test
    public void testHelloEndpoint() {
        given().when()
                .get("/quarkus-kiota")
                .then()
                .statusCode(200)
                .body(is("{\"value\":\"Hello quarkus-kiota\"}"));
    }

    @Test
    public void testHelloEndpointUsingTheKiotaClient() throws Exception {
        // Arrange
        var adapter = new JDKRequestAdapter();
        adapter.setBaseUrl("http://localhost:8081");
        ApiClient client = new ApiClient(adapter);

        // Act
        Greeting result = client.quarkusKiota().get();

        // Assert
        assertEquals("Hello quarkus-kiota", result.getValue());
    }
}
