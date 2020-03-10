package com.jidu.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    public static String app_id = "2019092767835641";

    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSEUix78VIC68KmvQkzrwk5DWevkzN3o3tYVK6rJ+d91e7bpsPrZ0Cm6y78JFpJ2ex9aIO7eEEr+jqr7mV97xZOvQPJdZCWs8P0vBA3wMeSanmvFqTYzstfH9YiHMXa610nZmxmk9oCTXqGC4tScopKPaD2sKyb538imenuQDBcnppmfXcxISrHVOpuH6JChoQA3SwiQfC0hseds+T3Qn3NgmOT/Fko7tOHZ9Tb4/yK+S15/bx+mosQAzuwSp3I65LpGoIGwpH+ijT38/BSLzVS5bC9PYcfyGImekYQR7G+ucFtSAD/iSrL2gqdXXNQ5jyUhgCP+jsDlwZ95R6oD59AgMBAAECggEBAI+/UrtKK+xrEyhstjhC/AWSUPKYdR1lGGe0j6YGnE7pS/25LACR6yLV8XhDl9bUYMhR49qtX/Us6RuNNCbSVxm5WtZNdj52/6+i963fy0HTXNEmLD1AOszY6LpIDnvODDXno4mVQvAvoE9w3IDVTY6UMYYxk8nm7qPJ0ZjOCGBWs1WxaZyO7uQz3IxNDUiXqOftDDg7qfAlPKTbLXe6AAqZTCzSHxcZHxEOmNPC6y3utFtmN0j+ykfh4piOXzuEo06w+jPM4ghTv0sa7YEVSOCu6HH3zgzYtFfo0rIVu9vBOJj+I1+yyKFKsn09XLkKTtGDpzeOJRnkFbOMhAnG7gECgYEA8GzVIjfnVlyPUEs55+ZapOeKbnfsy1dS+ACMILhSO9PTNWQmL/JRNfIWfWfD+Ss8xQnKguRLuTVfSaXXeu98x5jScm00Cqg92in7OxGDtFjhB1cpQu1c5vXYxJ38HFzn0MtvZXtRlyS4B8wpmd/A3Z39D6UWTFetOr+KwxpfxOkCgYEAm4ekP/8THfkP8nmzhBGdKVTXUSOvV982DWuuyTOpUlfetKP6VOAIAHXyxVEb8EGXiZAxcpiMw9z7ZP9uMhANJ0XS4nAiKidNQ+909qGo7FQO0Ze9+3ADCq+ZJ2tP0F1B8DWcDNk9stoHhi//7X54mlJqx/xrOn3cds3v3i35QHUCgYAXuXWSqdePIxUVREcThhydtydm3TilnBlY1Zz+QZIDy1RFKXvHMW8oFpp3h3zEIqsdemjcX7DFNuoPN2k6/VTf0Um69uGyx4VK+OeMnZ7UdgzGj6hHrWqEc/AO/tP47IYHiXuVlQSpGHt8cX4NbBH5DHUDTTQVFnXGnU4REJIwuQKBgBLOq0EF4lTsik4jA19EFgIqfUMVkp2Io2uqCt/PvHj1oTus3Jeo467SlIHh1gQOmnmLEz+tTNBeh5PmA3hQUpbeLasuLiN0zzYY3cb5M4kofQZBWDrwh52iMF4A7wzeShv3D6DKIFjhXqmOYl9gvMrEjp8SWhEfqSpAqvHuE0cFAoGBANji0nMWP6CfylXmhLKrNtiFTi8lNdsPGs+bOLItFe9J7cJkVqZ+5jySUROIr/ZPbJbya56WPa2H0JeiyfHT1+8Ab5VHpYXGppxtliQnBFf0h27EbeNkoblV5tqeWIzD3uoTTe3b6q79JP+hafv8AyUoGUXGw23V70oqp4wlr3+s";

    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkhFIse/FSAuvCpr0JM68JOQ1nr5Mzd6N7WFSuqyfnfdXu26bD62dApusu/CRaSdnsfWiDu3hBK/o6q+5lfe8WTr0DyXWQlrPD9LwQN8DHkmp5rxak2M7LXx/WIhzF2utdJ2ZsZpPaAk16hguLUnKKSj2g9rCsm+d/Ipnp7kAwXJ6aZn13MSEqx1Tqbh+iQoaEAN0sIkHwtIbHnbPk90J9zYJjk/xZKO7Th2fU2+P8ivktef28fpqLEAM7sEqdyOuS6RqCBsKR/oo09/PwUi81UuWwvT2HH8hiJnpGEEexvrnBbUgA/4kqy9oKnV1zUOY8lIYAj/o7A5cGfeUeqA+fQIDAQAB";

    public static String notify_url = "http://39.96.95.40:9005/pay/alipayNotify";

    public static String return_url = "http://39.96.95.40:9005/pay/alipayReturn";

    public static String sign_type = "RSA2";

    public static String charset = "utf-8";

    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    public static String log_path = "C:\\";

    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}