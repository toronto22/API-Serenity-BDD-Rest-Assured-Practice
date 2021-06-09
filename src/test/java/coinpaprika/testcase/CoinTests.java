package coinpaprika.testcase;

import coinpaprika.steps.CoinSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CoinTests {

    private final String errorIdNotFound = "id not found";
    String coinIdValid = "btc-bitcoin";
    String coinIdInvalid = "invalid-btc-bitcoin";

    @Steps
    CoinSteps coinSteps;

    @Test
    public void getListCoin_Successfully() {
        coinSteps
                .getListCoins()
                .verifyStatusCode(200)
                .verifyGetListCoinsResponse();
    }

    @Test
    public void getCoinById_Successfully() {
        coinSteps
                .getCoinById(coinIdValid)
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyGetCoinByIdResponse(coinIdValid);
    }

    @Test
    public void getCoinById_WithInvalidCoinId_NotFound() {
        coinSteps
                .getCoinById(coinIdInvalid)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyErrorMessage(errorIdNotFound);
    }

    @Test
    public void getTwitterTimelineForCoin_Successfully() {
        coinSteps
                .getTwitterTimelineForCoin(coinIdValid)
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyGetTwitterTimelineForCoinResponse();
    }

    @Test
    public void getTwitterTimelineForCoin_WithInvalidCoinId_NotFound() {
        coinSteps
                .getTwitterTimelineForCoin(coinIdInvalid)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyErrorMessage(errorIdNotFound);
    }

    @Test
    public void getCoinEventsByCoinId_Successfully() {
        coinSteps
                .getCoinEventsByCoinId(coinIdValid)
                .verifyCoinEventsByCoinIdResponse();
    }

    @Test
    public void GetCoinEventsByCoinId_WithInvalidCoinId_NotFound() {
        coinSteps
                .getCoinEventsByCoinId(coinIdInvalid)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyErrorMessage(errorIdNotFound);
    }

    @Test
    public void GetExchangesByCoinId_Successfully() {
        coinSteps
                .getExchangesByCoinId(coinIdValid)
                .verifyExchangesByCoinIdResponse();
    }

    
    @Test
    public void GetExchangesByCoinId_WithInvalidCoinId_NotFound() {
        coinSteps
                .getExchangesByCoinId(coinIdInvalid)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyErrorMessage(errorIdNotFound);
    }

    @Test
    public void GetMarketsByCoinId_Successfully() {
        coinSteps
                .getMarketByCoinId(coinIdValid)
                .verifyMarketsByCoinIdResponse();
    }

    @Test
    public void GetMarketsByCoinId_WithInvalidCoinId_NotFound() {
        coinSteps
                .getMarketByCoinId(coinIdInvalid)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyErrorMessage(errorIdNotFound);
    }

    @Test
    public void GetOhlcForLastFullDay_Successfully() {
        coinSteps
                .getOhlcForLastFullDay(coinIdValid)
                .verifyOhlcForLastFullDayByCoinIdResponse();
    }

    @Test
    public void GetOhlcForLastFullDay_WithInvalidCoinId_NotFound() {
        coinSteps
                .getOhlcForLastFullDay(coinIdInvalid)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyErrorMessage(errorIdNotFound);
    }

    
    @Test
    public void GetHistoricalOhlc_Successfully() {
        HashMap<String,String> params = new HashMap<>();
        params.put("start", "1518671700");
        params.put("limit", "12");
        params.put("quote", "btc");
        coinSteps
                .getHistoricalOhlc(coinIdValid,params)
                .verifyHistoricalOhlcByCoinIdResponse();
    }

    @Test
    public void GetHistoricalOhlc_WithInvalidCoinId_NotFound() {

        HashMap<String,String> params = new HashMap<>();
        params.put("start", "1518671700");
        params.put("limit", "12");
        params.put("quote", "btc");
        
        coinSteps
                .getHistoricalOhlc(coinIdInvalid,params)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyErrorMessage(errorIdNotFound);
    }

    @Test
    public void GetTodayOhlc_Successfully() {
        HashMap<String,String> params = new HashMap<>();
        params.put("quote", "btc");
        coinSteps
                .getTodayOhlc(coinIdValid,params)
                .verifyTodayOhlcResponse();
    }

    @Test
    public void GetTodayOhlc_WithInvalidCoinId_NotFound() {

        HashMap<String,String> params = new HashMap<>();
        params.put("quote", "btc");
        
        coinSteps
                .getTodayOhlc(coinIdInvalid,params)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyErrorMessage(errorIdNotFound);
    }
}