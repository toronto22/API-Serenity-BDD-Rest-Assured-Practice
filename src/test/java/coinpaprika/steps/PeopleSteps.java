package coinpaprika.steps;

import coinpaprika.WebUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

public class PeopleSteps {
    Response response;

    @Step("Verify status code {0}")
    public PeopleSteps verifyStatusCode(int statusCode) {
        response.then().statusCode(statusCode);

        return this;
    }

    @Step("Get people by Id {0}")
    public PeopleSteps getPeopleById(String peopleId) {
        response = RestAssured.when().get(WebUrl.PeopleById, peopleId);

        return this;
    }

    @Step("Verify error message {0}")
    public PeopleSteps verifyErrorMessage(String errorMessage) {
        response.then().body("error", is(errorMessage));

        return this;
    }

    @Step("Verify get people by id response")
    public PeopleSteps verifyGetPeopleByIdResponse(String personId) {
        response.then().body("id", is(personId));

        return this;
    }
}
