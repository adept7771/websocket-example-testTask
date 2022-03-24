package etc;

public class Settings {
    // данный класс хранит статичные настройки для запуска тестов. Можно все вынести в пропертис, что
    // более нравится проггерам и получить километровые многоэтажные конструкции с пропертис файлами,
    // можно прикрутить либу owner в которой свой логика раздачи пропертей. Сделал по топорному на
    // скорую руку :) можно доделать нормально в обычной обстановке.

    public final static String connectAddress = "wss://stream.binance.com:9443/stream?streams=ethbtc@depth";
    public final static long pollingResponseIntervalMs = 10;
    public final static long pollingResponseIntervalMaxTimeMs = 5000;
}
