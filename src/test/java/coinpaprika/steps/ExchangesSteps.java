package coinpaprika.steps;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import coinpaprika.WebUrl;
import coinpaprika.common.DateHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

public class ExchangesSteps {
    Response response;

    @Step("Get list exchanges")
    public ExchangesSteps getListExchanges(HashMap<String,String> params) {

        response = RestAssured
                    .given().params(params)
                    .when().get(WebUrl.Exchanges);

        return this;
    }

    @Step("Verify list exchanges response")
    public ExchangesSteps verifyListExchangesResponse() {
        String dateTimeNow = DateHelper.getDateTimeNow(DateTimeFormatter.ISO_INSTANT);
        response.then()
                .body("last_updated[0]", lessThan(dateTimeNow) );

        return this;
    }

    @Step("Verify status code {0}")
    public ExchangesSteps verifyStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);

        return this;
    }

    @Step("Verify error message")
    public ExchangesSteps verifyErrorMessage(String expectedErrorMessage) {
        response.then()
                .body("error", is(expectedErrorMessage));
        return this;
    }

    @Step("Get exchange by Id")
    public ExchangesSteps getExchangeById(String exchangeId, HashMap<String,String> params) {

        response = RestAssured
                    .given().params(params)
                    .when().get(WebUrl.ExchangesById,exchangeId);

        return this;
    }

    @Step("Get markets exchange Id")
    public ExchangesSteps GetMarketsByExchangeId(String exchangeId, HashMap<String,String> params) {

        response = RestAssured
                    .given().params(params)
                    .when().get(WebUrl.MarketsByExchangeId,exchangeId);

        return this;
    }
}
