package coinpaprika.testcase;

import org.junit.Test;
import org.junit.runner.RunWith;

import coinpaprika.steps.PeopleSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class PeopleTests {
    
    String validPersonId = "vitalik-buterin";
    String invalidPersonId = "Invalid-String-buterin";
    String errorMessageIdNotFound = "id not found";

    @Steps
    PeopleSteps peopleSteps;
    
    @Test
    public void GetPeopleById_Successfully(){
        peopleSteps
            .getPeopleById(validPersonId)
            .verifyStatusCode(200)
            .verifyGetPeopleByIdResponse(validPersonId);
    }

    
    @Test
    public void GetPeopleById_WithInvalidPersonId_NotFound(){
        peopleSteps
            .getPeopleById(invalidPersonId)
            .verifyStatusCode(404)
            .verifyErrorMessage(errorMessageIdNotFound);
    }
}
