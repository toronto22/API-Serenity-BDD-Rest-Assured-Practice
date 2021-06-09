package coinpaprika.steps;

import java.util.HashMap;

import coinpaprika.WebUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

public class ToolsSteps {
    
    Response response;

    @Step
    public ToolsSteps search(HashMap<String,Object> params){
        response = RestAssured
            .given().params(params)
            .when().get(WebUrl.Search);

        return this;
    }

    @Step
    public ToolsSteps verifyStatusCode(int statusCode){
        response
            .then().statusCode(statusCode);
    
        return this;
    }

    @Step
    public ToolsSteps verifySearchResponse(){
        response
            .then()
            .body("currencies.size()", greaterThan(0));

        return this;
    }

    @Step
    public ToolsSteps verifyErrorMessage(String errorMessage){
        response
            .then()
            .body("error", is(errorMessage));
            
        return this;
    }

    @Step
    public ToolsSteps getPriceConverter(HashMap<String,Object> params){
        response = RestAssured
            .given()
            .params(params)
            .when()
            .get(WebUrl.PriceConverter);

        return this;
    }

    @Step
    public ToolsSteps verifyGetPriceConverter(String baseCurrencyId, String quoteCurrencyId){
        response
            .then()
            .body("base_currency_id", is(baseCurrencyId))
            .body("quote_currency_id", is(quoteCurrencyId));

        return this;
    }
}
