package coinpaprika.testcase;

import org.junit.Test;
import org.junit.runner.RunWith;

import coinpaprika.steps.GlobalSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class GlobalTests {
    @Steps
    GlobalSteps globalSteps;

    @Test
    public void getGlobalInformation_Successfully(){
        globalSteps
            .getGlobalInformation()
            .verifyStatusCode(200)
            .verifyGlobalInformationResponse();    
    }
}
