package coinpaprika;

public class WebUrl {

    public static String BaseUrl = "https://api.coinpaprika.com/v1";
    public static String Global = BaseUrl + "/global";
    public static String Coins = BaseUrl + "/coins";
    public static String CoinById = BaseUrl + "/coins/{coin_id}";
    public static String TwitterTimelineCoin = BaseUrl + "/coins/{coin_id}/twitter";
    public static String CoinEventsById = BaseUrl + "/coins/{coin_id}/events";
    public static String ExchangesCoinById = BaseUrl + "/coins/{coin_id}/exchanges";
    public static String MarketsByCoinId = BaseUrl + "/coins/{coin_id}/markets";
    public static String OhlcForLastFullDay = BaseUrl + "/coins/{coin_id}/ohlcv/latest/";
    public static String HistoricalOhlc = BaseUrl + "/coins/{coin_id}/ohlcv/historical";
    public static String TodayOhlc = BaseUrl + "/coins/{coin_id}/ohlcv/today/";
    public static String PeopleById = BaseUrl + "/people/{person_id}";
    public static String TagById = BaseUrl + "/tags/{tag_id}";
    public static String ListTags = BaseUrl + "/tags";
    public static String Tickers = BaseUrl + "/tickers";
    public static String TickerInformation = BaseUrl + "/tickers/{coin_id}";
    public static String HistoricalTicker = BaseUrl + "/tickers/{coin_id}/historical";
    public static String Exchanges = BaseUrl + "/exchanges";
    public static String ExchangesById = BaseUrl + "/exchanges/{exchange_id}";
    public static String MarketsByExchangeId = BaseUrl + "/exchanges/{exchange_id}/markets";
    public static String Search = BaseUrl + "/search";
    public static String PriceConverter = BaseUrl + "/price-converter";
    public static String ListContractsPlatforms = BaseUrl + "/contracts";
    public static String ContractAddresses = BaseUrl + "/contracts/{platform_id}";
    public static String RedirectToTickerHistorical = BaseUrl + "/contracts/{platform_id}/{contract_address}/historical";
    public static String RedirectToTickerByContractAddress = BaseUrl + "/contracts/{platform_id}/{contract_address}";
}
