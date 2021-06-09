package coinpaprika.steps;

import coinpaprika.WebUrl;
import coinpaprika.common.DateHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;



import static org.hamcrest.Matchers.*;

import java.time.format.DateTimeFormatter;

public class GlobalSteps {
    Response response;

    @Step("Get global information")
    public GlobalSteps getGlobalInformation() {
        response = RestAssured.when().get(WebUrl.Global);

        return this;
    }

    @Step("Verify global information response")
    public GlobalSteps verifyGlobalInformationResponse(){
        response.then().body("market_cap_usd", greaterThan(0L));
        response.then().body("last_updated", greaterThan(0));
        response.then().body("volume_24h_usd", greaterThan(0L));
        response.then().body("bitcoin_dominance_percentage", greaterThan(0F));
        response.then().body("cryptocurrencies_number", greaterThan(0));
        response.then().body("market_cap_ath_value", greaterThan(0L));
        response.then().body("market_cap_ath_date", lessThan(DateHelper.getDateTimeNow(DateTimeFormatter.ISO_INSTANT)));
        response.then().body("volume_24h_ath_value", greaterThan(0L));
        response.then().body("volume_24h_ath_date", lessThan(DateHelper.getDateTimeNow(DateTimeFormatter.ISO_INSTANT)));
        response.then().body("market_cap_change_24h", is(notNullValue()));
        response.then().body("volume_24h_change_24h", is(notNullValue()));

        return this;
    }

    @Step("Verify status code {0}")
    public GlobalSteps verifyStatusCode(int statusCode){
        response.then().statusCode(statusCode);

        return this;
    }
}
