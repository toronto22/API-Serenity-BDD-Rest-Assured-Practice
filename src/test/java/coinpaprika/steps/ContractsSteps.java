package coinpaprika.steps;

import coinpaprika.WebUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class ContractsSteps{

    private Response response;
    
    @Step("Get list contracts platforms")
    public ContractsSteps getListContractsPlatforms(){
        response = RestAssured.when().get(WebUrl.ListContractsPlatforms);

        return this;
    }

    @Step("Get contract addresses for platform {0}")
    public ContractsSteps getContractAddressesForPlatform(String platformId){
        response = RestAssured.when().get(WebUrl.ContractAddresses,platformId);

        return this;
    }

    @Step("Get contract addresses for platform {0}")
    public ContractsSteps redirectToTickerByContractAddress(String platformId, String contractAddress){
        response = RestAssured.when().get(WebUrl.RedirectToTickerByContractAddress ,platformId,contractAddress);

        return this;
    }

    @Step("Get contract addresses for platform {0}")
    public ContractsSteps RedirectToTickerHistoricalValuesByContractAddress(String platformId, String contractAddress,HashMap<String,String> params){
        response = RestAssured
            .given().params(params)
            .when().get(WebUrl.RedirectToTickerHistorical ,platformId,contractAddress);

        return this;
    }


    
    @Step("Verify list contracts platforms response")
    public ContractsSteps verifyListContractsPlatformsResponse(){
        response.then().body("size()", greaterThan(0));

        return this;
    }

    @Step("Verify list contracts address for platforms response")
    public ContractsSteps verifyListContractsAddressesForPlatformsResponse(){
        response.then().body("size()", greaterThan(0));

        return this;
    }

    @Step("Verify status code {0}")
    public ContractsSteps verifyStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);

        return this;
    }

}