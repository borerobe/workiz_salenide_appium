package pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.codeborne.selenide.appium.selector.CombinedBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class MainPage {

    SelenideAppiumElement menu = $(CombinedBy.android(accessibilityId("menu_burger_home"))
            .ios(accessibilityId("ios_menu")));

    SelenideAppiumElement dashboard = $(accessibilityId("card"));

    SelenideAppiumElement timesheets  = $(byXpath("//android.widget.TextView[@text='Timesheets']"));

    SelenideAppiumElement jobs  = $(accessibilityId("jobs_report"));

    SelenideAppiumElement invoices  = $(accessibilityId("invoices_report"));

    SelenideAppiumElement estimates  = $(accessibilityId("estimates_report"));

    SelenideAppiumElement settings  = $(accessibilityId("settings"));

    SelenideAppiumElement getHelp  = $(accessibilityId("get_help"));

    SelenideAppiumElement logout  = $(accessibilityId("logout"));

    public MainPage(){
        menu.shouldBe(visible, Duration.ofSeconds(10));
    }


    public MainPage openMenu() {
        dashboard.shouldBe(visible, Duration.ofSeconds(5));
        menu.shouldBe(visible);
        menu.tap();
        return this;
    }

    public JobsPage openJobs() {
        jobs.click();
        return new JobsPage();
    }

}
