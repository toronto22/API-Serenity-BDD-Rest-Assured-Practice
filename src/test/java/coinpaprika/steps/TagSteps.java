package coinpaprika.steps;

import coinpaprika.WebUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class TagSteps {

    Response response;

    @Step ("Get tag by Id {0}")
    public TagSteps getTagById(String tagId, HashMap<String,String> params){
        response = RestAssured
            .given().params(params)
            .when().get(WebUrl.TagById,tagId);

        return this;
    }

    @Step 
    public TagSteps verifyStatusCode(int statusCode){
        response.then().statusCode(statusCode);

        return this;
    }

    @Step
    public TagSteps verifyGetTagByIdResponse(String tagId){
        response
            .then()
            .body("id", is(tagId))
            .body("coin_counter",greaterThan(0));
            
        return this;
    }

    @Step
    public TagSteps verifyErrorMessage(String errorMessage){
        response
            .then()
            .body("error", is(errorMessage));
            
        return this;
    }

    @Step
    public TagSteps getListTags(HashMap<String,String> params){
        response = RestAssured.given().params(params).get(WebUrl.ListTags);
        return this;
    }

    @Step
    public TagSteps verifyGetListTags(){
        response
            .then()
            .body("id[0]", is(notNullValue()))
            .body("coin_counter[0]",greaterThan(0));
            
        return this;
    }

}
