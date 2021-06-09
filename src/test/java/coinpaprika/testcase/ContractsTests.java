package coinpaprika.testcase;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import coinpaprika.steps.ContractsSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class ContractsTests {

    String platformId = "eth-ethereum";
    String contractAddress = "0xd26114cd6ee289accf82350c8d8487fedb8a0c07";

    @Steps
    ContractsSteps contractsSteps;

    @Test
    public void GetListContractsPlatforms_Successfully(){
        contractsSteps
            .getListContractsPlatforms()
            .verifyListContractsPlatformsResponse();

    }

    @Test
    public void GetAllContractAddressesForPlatform_Successfully(){
        contractsSteps
            .getContractAddressesForPlatform(platformId)
            .verifyListContractsAddressesForPlatformsResponse();
    }

    @Test
    public void RedirectToTickerByContractAddress_Successfully(){
        contractsSteps
            .redirectToTickerByContractAddress(platformId,contractAddress)
            .verifyStatusCode(200);
    }

    @Test
    public void RedirectToTickerHistoricalValuesByContractAddress_Successfully(){
        HashMap<String,String> params = new HashMap<>();
        params.put("start", "1518671700");

        contractsSteps
            .RedirectToTickerHistoricalValuesByContractAddress(platformId,contractAddress,params)
            .verifyStatusCode(200);
    }
}