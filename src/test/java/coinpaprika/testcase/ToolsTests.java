package coinpaprika.testcase;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import coinpaprika.steps.ToolsSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class ToolsTests {
        
    HashMap<String,Object> params;
    String errorMessageInvalidParameter = "invalid parameters";
    String baseCurrencyId = "btc-bitcoin";
    String quoteCurrencyId = "usd-us-dollars";

    @Steps
    ToolsSteps toolsSteps;


    @Test
    public void search_Successfully(){
        params = new HashMap<>();
        params.put("q", "btc");

        toolsSteps
            .search(params)
            .verifyStatusCode(200)
            .verifySearchResponse();
    }

    @Test
    public void search_WithOutQParameter_BadRequest(){
        params = new HashMap<>();

        toolsSteps
        .search(params)
        .verifyStatusCode(400)
        .verifyErrorMessage(errorMessageInvalidParameter);
    }

    @Test
    public void search_WithLimitParameterExceed250_Successfully(){
        params = new HashMap<>();
        params.put("q", "btc");
        params.put("limit", 251);

        toolsSteps
        .search(params)
        .verifyStatusCode(400)
        .verifyErrorMessage(errorMessageInvalidParameter);
    }

    @Test
    public void getPriceConverter_Successfully(){
        params = new HashMap<>();
        params.put("base_currency_id", baseCurrencyId);
        params.put("quote_currency_id", quoteCurrencyId);
        
        toolsSteps
            .getPriceConverter(params)
            .verifyStatusCode(200)
            .verifyGetPriceConverter(baseCurrencyId, quoteCurrencyId);
    }

    @Test
    public void getPriceConverter_WithOutBaseCurrencyIdParameter_BadRequest(){
        params = new HashMap<>();
        params.put("base_currency_id", "");
        params.put("quote_currency_id", quoteCurrencyId);

        toolsSteps
            .getPriceConverter(params)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorMessageInvalidParameter);
    }

    
    @Test
    public void getPriceConverter_WithOutQuoteCurrencyIdParameter_BadRequest(){
        params = new HashMap<>();
        params.put("base_currency_id", baseCurrencyId);
        params.put("quote_currency_id", "");

        toolsSteps
            .getPriceConverter(params)
            .verifyStatusCode(400)
            .verifyErrorMessage(errorMessageInvalidParameter);
    }
}
