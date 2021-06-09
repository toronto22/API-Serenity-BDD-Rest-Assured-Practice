package coinpaprika.steps;

import coinpaprika.WebUrl;
import coinpaprika.common.DateHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class CoinSteps {
    Response response;


    @Step("Get list coins")
    public CoinSteps getListCoins() {
        response = RestAssured.when().get(WebUrl.Coins);

        return this;
    }

    @Step("Get coin by Id: {0}")
    public CoinSteps getCoinById(String coinId) {
        response = RestAssured.when().get(WebUrl.CoinById, coinId);

        return this;
    }

    @Step("Get Twitter timeline for coin: {0}")
    public CoinSteps getTwitterTimelineForCoin(String coinId) {
        response = RestAssured.when().get(WebUrl.TwitterTimelineCoin, coinId);

        return this;
    }

    @Step("Get coin events by CoinId {0}")
    public CoinSteps getCoinEventsByCoinId(String coinId) {
        response = RestAssured.when().get(WebUrl.CoinEventsById, coinId);

        return this;
    }

    @Step("Get exchanges by CoinId {0}")
    public CoinSteps getExchangesByCoinId(String coinId) {
        response = RestAssured.when().get(WebUrl.ExchangesCoinById, coinId);

        return this;
    }

    @Step("Get markets by CoinId {0}")
    public CoinSteps getMarketByCoinId(String coinId) {
        response = RestAssured.when().get(WebUrl.MarketsByCoinId, coinId);

        return this;
    }

    @Step("Verify coin events by coinId response")
    public CoinSteps verifyCoinEventsByCoinIdResponse() {
        String dateTimeNow = DateHelper.getDateTimeNow(DateTimeFormatter.ISO_INSTANT);

        response.then()
                .body("date[0]", lessThan(dateTimeNow))
                .body("id[0]", is(notNullValue()))
                .body("size()", greaterThan(0));

        return this;
    }

    @Step("Get ohlc for last full day by CoinId {0}")
    public CoinSteps getOhlcForLastFullDay(String coinId) {
        response = RestAssured.when().get(WebUrl.OhlcForLastFullDay, coinId);

        return this;
    }
    

    @Step("Verify Ohlc for last full day by coinId response")
    public CoinSteps verifyOhlcForLastFullDayByCoinIdResponse() {
        String dateTimeNow = DateHelper.getDateTimeNow(DateTimeFormatter.ISO_INSTANT);

        response.then()
                .body("time_open[0]", lessThan(dateTimeNow))
                .body("time_close[0]", lessThan(dateTimeNow))
                .body("size()", greaterThan(0));

        return this;
    }

    @Step("Get historical Ohlc by CoinId {0}")
    public CoinSteps getHistoricalOhlc(String coinId, Map<String,String> params) {
        response = RestAssured
                .given().params(params)        
                .when().get(WebUrl.OhlcForLastFullDay, coinId);

        return this;
    }

    
    @Step("Verify historical Ohlc by coinId response")
    public CoinSteps verifyHistoricalOhlcByCoinIdResponse() {
        String dateTimeNow = DateHelper.getDateTimeNow(DateTimeFormatter.ISO_INSTANT);

        response.then()
                .body("time_open[0]", lessThan(dateTimeNow))
                .body("time_close[0]", lessThan(dateTimeNow))
                .body("size()", greaterThan(0));

        return this;
    }

    @Step("Get today Ohlc {0}")
    public CoinSteps getTodayOhlc(String coinId, Map<String,String> params) {
        response = RestAssured
                .given().params(params)        
                .when().get(WebUrl.OhlcForLastFullDay, coinId);

        return this;
    }

    @Step("Verify today Ohlc response")
    public CoinSteps verifyTodayOhlcResponse() {
        String dateTimeNow = DateHelper.getDateTimeNow(DateTimeFormatter.ISO_INSTANT);

        response.then()
                .body("time_open[0]", lessThan(dateTimeNow))
                .body("time_close[0]", lessThan(dateTimeNow))
                .body("size()", greaterThan(0));

        return this;
    }

    @Step("Verify exchanges by coinId response")
    public CoinSteps verifyExchangesByCoinIdResponse() {
        response.then()
                .body("adjusted_volume_24h_share[0]", greaterThan(0F))
                .body("id[0]", is(notNullValue()))
                .body("size()", greaterThan(0));

        return this;
    }

    @Step("Verify markets by coinId response")
    public CoinSteps verifyMarketsByCoinIdResponse() {
        response.then()
                .body("adjusted_volume_24h_share[0]", greaterThan(0F))
                .body("exchange_id[0]", is(notNullValue()))
                .body("exchange_name[0]", is(notNullValue()))
                .body("base_currency_id[0]", is(notNullValue()))
                .body("base_currency_name[0]", is(notNullValue()))
                .body("size()", greaterThan(0));

        return this;
    }

    @Step("Verify status code {0}")
    public CoinSteps verifyStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);

        return this;
    }

    @Step("Verify get list coins response")
    public CoinSteps verifyGetListCoinsResponse() {
        response.then()
                .body("is_active[0]", is(notNullValue()))
                .body("is_new[0]", is(notNullValue()))
                .body("name[0]", is(notNullValue()))
                .body("symbol[0]", is(notNullValue()))
                .body("rank[0]", is(notNullValue()))
                .body("size()", greaterThan(0));

        return this;
    }

    @Step("Verify get Twitter timeline for coin response")
    public CoinSteps verifyGetTwitterTimelineForCoinResponse() {
        String dateTimeNow = DateHelper.getDateTimeNow(DateTimeFormatter.ISO_INSTANT);

        response.then()
                .body("date[0]", lessThan(dateTimeNow))
                .body("user_name[0]", is(notNullValue()))
                .body("size()", greaterThan(0));

        return this;
    }


    @Step("Verify error message")
    public CoinSteps verifyErrorMessage(String expectedErrorMessage) {
        response.then()
                .body("error", is(expectedErrorMessage));
        return this;
    }

    @Step("Verify get coin by id response with coinId: {0}")
    public CoinSteps verifyGetCoinByIdResponse(String coinId) {
        response.then()
                .body("id", is(coinId));
        return this;
    }
}
