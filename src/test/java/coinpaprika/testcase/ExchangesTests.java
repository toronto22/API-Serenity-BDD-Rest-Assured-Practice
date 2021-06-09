package coinpaprika.testcase;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import coinpaprika.steps.ExchangesSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class ExchangesTests {
    String errorInvalidParameter = "invalid parameters";
    String validExchangeId = "binance";
    String invalidQuote = "invalid-quote";

    @Steps
    ExchangesSteps exchangesSteps;

    @Test
    public void getListExchanges_Successfully(){
        exchangesSteps
            .getListExchanges(new HashMap<>())
            .verifyStatusCode(200)
            .verifyListExchangesResponse();
    }

    @Test
    public void getListExchanges_WithInvalidQuote_BadRequest(){
        HashMap<String,String> params = new HashMap<>();
        params.put("quotes", invalidQuote);

        exchangesSteps
            .getListExchanges(params)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorInvalidParameter);
    }

    @Test
    public void GetExchangeById_Successfully(){
        exchangesSteps
            .getExchangeById(validExchangeId, new HashMap<>())
            .verifyStatusCode(200)
            .verifyListExchangesResponse();
    }

    @Test
    public void GetExchangeById_WithInvalidQuote_BadRequest(){
        HashMap<String,String> params = new HashMap<>();
        params.put("quotes", invalidQuote);
        exchangesSteps
            .getExchangeById(validExchangeId, params)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorInvalidParameter);
    }

    @Test
    public void GetMarketsByExchangeId_Successfully(){
        exchangesSteps
            .GetMarketsByExchangeId(validExchangeId, new HashMap<>())
            .verifyStatusCode(200)
            .verifyListExchangesResponse();
    }

    @Test
    public void GetMarketsByExchangeId_WithInvalidQuote_BadRequest(){
        HashMap<String,String> params = new HashMap<>();
        params.put("quotes", invalidQuote);
        exchangesSteps
            .GetMarketsByExchangeId(validExchangeId, params)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorInvalidParameter);
    }
}
