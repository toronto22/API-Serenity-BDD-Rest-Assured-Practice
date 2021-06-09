package coinpaprika.testcase;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import coinpaprika.steps.TagSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class TagTests {

    String validTagId = "blockchain-service";
    String invalidTagId = "Invalid--blockchain-service";
    String errorMessageInvalidParameter = "invalid parameters";
    String errorMessageIdNotFound = "id not found";

    @Steps
    TagSteps tagSteps;

    @Test
    public void getTagById_Successfully(){
        HashMap<String, String> params = new HashMap<>();
        params.put("additional_fields", "coins,icos");
        tagSteps  
            .getTagById(validTagId, params)
            .verifyStatusCode(200)
            .verifyGetTagByIdResponse(validTagId);
    }

    @Test
    public void getTagById_WithInvalidAdditionalFields_BadRequest(){
        HashMap<String, String> params = new HashMap<>();
        params.put("additional_fields", "invalid0-item");

        tagSteps
            .getTagById(invalidTagId, params)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorMessageInvalidParameter);
    }


    @Test
    public void getTagById_WithInvalidTagId_NotFound(){
        HashMap<String, String> params = new HashMap<>();
        params.put("additional_fields", "coins,icos");
        tagSteps  
            .getTagById(invalidTagId, params)
            .verifyStatusCode(404)
            .verifyErrorMessage(errorMessageIdNotFound);
    }

    @Test
    public void GetListTags_Successfully(){
        HashMap<String, String> params = new HashMap<>();
        params.put("additional_fields", "coins,icos");

        tagSteps
            .getListTags(params)
            .verifyStatusCode(200)
            .verifyGetListTags();
    }

    @Test
    public void GetListTags_WithInvalidAdditionalFields_NotFound(){
        HashMap<String, String> invalidParam = new HashMap<>();
        invalidParam.put("additional_fields", "invalid-field");

        tagSteps
            .getListTags(invalidParam)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorMessageInvalidParameter);
    }
}
