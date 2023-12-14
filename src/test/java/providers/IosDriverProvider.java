package providers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.URL;

public class IosDriverProvider implements WebDriverProvider {
    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("Ios")
                .setPlatformVersion("17")
                .setUdid("")
                .setBundleId("com.workiz")
                .setNoReset(true);

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), options);

        driver.activateApp("com.workiz");

        return driver;
    }
}
