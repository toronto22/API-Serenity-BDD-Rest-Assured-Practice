package coinpaprika.testcase;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import coinpaprika.steps.TickerSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class TickerTests {
    
    String errorMessageInvalidparameter = "invalid parameters";
    String errorMessageIdNotFound = "id not found";
    String validCoinId = "btc-bitcoin";
    String invalidCoinId = "invalid-BTC";

    @Steps
    TickerSteps tickerSteps;

    @Test
    public void getTickersForAllCoins_Succesfully(){
        HashMap<String, String> params = new HashMap<>();
        params.put("quotes", "USD,BTC");

        tickerSteps
            .getTickersForAllCoins(params)
            .verifyStatusCode(200)
            .verifyGetTickerForAllCoinsResponse();
    }

    @Test
    public void getTickersForAllCoins_WithInvalidQuotes_BadRequest(){
        HashMap<String, String> invalidParams = new HashMap<>();
        invalidParams.put("quotes", "Invalid-params");

        tickerSteps
            .getTickersForAllCoins(invalidParams)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorMessageInvalidparameter);
    }

    @Test
    public void getTickerInformationForSpecificCoin_Succesfully(){
        HashMap<String, String> params = new HashMap<>();
        params.put("quotes", "USD,BTC");

        tickerSteps
            .getTickerInformationForSpecificCoin(validCoinId, params)
            .verifyStatusCode(200)
            .verifyGetTickerInformationForSpecifiCoinResponse();
    }

    @Test
    public void GetTickerInformationForSpecificCoin_WithInvalidQuotes_BadRequest(){
        HashMap<String, String> invalidParams = new HashMap<>();
        invalidParams.put("quotes", "invalid-quotes");

        tickerSteps
            .getTickerInformationForSpecificCoin(validCoinId, invalidParams)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorMessageInvalidparameter);
    }

    @Test
    public void getTickerInformationForSpecificCoin_WithInvalidCoinId_NotFound(){
        HashMap<String, String> params = new HashMap<>();
        params.put("quotes", "USD,BTC");

        tickerSteps
            .getTickerInformationForSpecificCoin(invalidCoinId, params)
            .verifyStatusCode(404)
            .verifyErrorMessage(errorMessageIdNotFound);
    }

    @Test
    public void getHistoricalTickersForSpecificCoin_Successfully(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("start", "1518671700");
        params.put("limit", 5000);
        params.put("quotes", "usd");

        tickerSteps
            .getHistoricalTickersForSpecificCoin(validCoinId, params)
            .verifyStatusCode(200)
            .verifyGetHistoricalTickersForSpecificCoin();
    }

    @Test
    public void getHistoricalTickersForSpecificCoin_InvalidCoinId_NotFound(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("start", "1518671700");
        params.put("limit", 5000);
        params.put("quotes", "usd");

        tickerSteps
            .getHistoricalTickersForSpecificCoin(invalidCoinId, params)
            .verifyStatusCode(404)
            .verifyErrorMessage(errorMessageIdNotFound);
    }

    @Test
    public void getHistoricalTickersForSpecificCoin_WithOutStartParameter_BadRequest(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 5000);
        params.put("quotes", "usd");

        tickerSteps
            .getHistoricalTickersForSpecificCoin(validCoinId, params)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorMessageInvalidparameter);
    }

    @Test
    public void getHistoricalTickersForSpecificCoin_WithLimitParameterExceedLimit5000_BadRequest(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("start", "1518671700");
        params.put("limit", 5001);
        params.put("quotes", "usd");

        tickerSteps
            .getHistoricalTickersForSpecificCoin(validCoinId, params)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorMessageInvalidparameter);
    }

}
