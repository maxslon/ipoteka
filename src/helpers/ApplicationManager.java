package helpers;

/**
 * Вспомогательный менеджер
 */
public class ApplicationManager {

    private helpers.WebDriverHelper webDriverHelper;
    private IpotekaHelper ipotekaHelper;

    public void stop() {
        if (webDriverHelper != null) {
            webDriverHelper.stop();
        }
    }

    public WebDriverHelper getWebDriverHelper() {
        if (webDriverHelper == null) {
            webDriverHelper = new WebDriverHelper(this);
        }
        return webDriverHelper;
    }

    public IpotekaHelper getIpotekaHelper() {
        if (ipotekaHelper == null) {
            ipotekaHelper = new IpotekaHelper(this);
        }
        return ipotekaHelper;
    }
}
