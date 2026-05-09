import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.example.FormData;
import org.example.TestData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {
    @Test
    public void verifyGetMethod() {
        given()
                .log().body()
                .baseUri("http://postman-echo.com")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .log().all();
    }

    @Test
    public void verifyPostMethod() {
        TestData testData = new TestData("value");

        given()
                .log().body()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.JSON)
                .body(testData)
                .when()
                .post("/post")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("json.test", equalTo("value"))
                .log().all();
    }

    @Test
    public void verifyPostMethodFormD() {
        FormData formData = new FormData("bar1", "bar2");

        given()
                .log().body()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.JSON)
                .body(formData)
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"));
    }

    @Test
    public void verifyPutMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .log().body()
                .baseUri("https://postman-echo.com")
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void verifyPatchMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .log().body()
                .baseUri("https://postman-echo.com")
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void verifyDeleteMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .log().body()
                .baseUri("https://postman-echo.com")
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("data", equalTo(requestBody));
    }
}