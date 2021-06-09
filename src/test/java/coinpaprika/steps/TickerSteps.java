package coinpaprika.steps;

import java.util.HashMap;

import coinpaprika.WebUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;


public class TickerSteps {
    Response response;

    @Step
    public TickerSteps getTickersForAllCoins(HashMap<String, String> params){
        response = RestAssured
            .given().params(params)
            .get(WebUrl.Tickers);

        return this;
    }

    @Step
    public TickerSteps verifyStatusCode(int statusCode){
        response
            .then()
            .statusCode(statusCode);

        return this;
    }


    @Step
    public TickerSteps verifyGetTickerForAllCoinsResponse(){
        response
            .then()
            .body("rank[0]",greaterThan(0))
            .body("max_supply[0]",greaterThan(0));

        return this;
    }

    @Step
    public TickerSteps verifyErrorMessage(String errorMessage){
        response
            .then()
            .body("error", is(errorMessage));

        return this;
    }

    @Step
    public TickerSteps getTickerInformationForSpecificCoin(String coinId, HashMap<String, String> params){
        response = RestAssured
            .given().params(params)
            .when().get(WebUrl.TickerInformation, coinId);

        return this;
    }

    @Step
    public TickerSteps verifyGetTickerInformationForSpecifiCoinResponse(){
        response
            .then()
            .body("rank",greaterThan(0))
            .body("max_supply",greaterThan(0));

        return this;
    }


    
    @Step
    public TickerSteps getHistoricalTickersForSpecificCoin(String coinId, HashMap<String, Object> params){
        response = RestAssured
            .given().params(params)
            .when().get(WebUrl.HistoricalTicker, coinId);

        return this;
    }

    @Step
    public TickerSteps verifyGetHistoricalTickersForSpecificCoin(){
        response
            .then()
            .body("price[0]", greaterThan(0F));


        return this;
    }

}
