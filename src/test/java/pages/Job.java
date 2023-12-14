package pages;

import com.codeborne.selenide.Container;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;

public class Job implements Container {

    @Getter
    private SelenideElement root;

    By date = byXpath("*/android.view.ViewGroup/android.widget.TextView[1]");
    By time = byXpath("*/android.view.ViewGroup/android.widget.TextView[2]");
    By status = byXpath("*/android.view.ViewGroup/android.widget.TextView[3]");
    By clientName = byXpath("*/android.view.ViewGroup/android.widget.TextView[4]");
    By clientCompanyName = byXpath("*/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
    By jobType = byXpath("*/android.view.ViewGroup/android.widget.TextView[6]");
    By address = byXpath("*/android.view.ViewGroup/android.widget.TextView[8]");

    public Job(SelenideElement el) {
        root = el;
    }

    public SelenideElement getDate() {
        return root.$(date);
    }

    public SelenideElement getTime() {
        return root.$(time);
    }

    public SelenideElement getStatus() {
        return root.$(status);
    }

    public SelenideElement getClientName() {
        return root.$(clientName);
    }

    public SelenideElement getClientCompanyName() {
        return root.$(clientCompanyName);
    }

    public SelenideElement getJobType() {
        return root.$(jobType);
    }

    public SelenideElement getAddress() {
        return root.$(address);
    }

    public Job hasDate(String value) {
        getDate().shouldHave(text(value));
        return this;
    }

    public Job hasTime(String value) {
        getTime().shouldHave(text(value));
        return this;
    }

    public Job hasStatus(String value) {
        getStatus().shouldHave(text(value));
        return this;
    }

    public Job hasClientName(String value) {
        getClientName().shouldHave(text(value));
        return this;
    }

    public Job hasClientCompanyName(String value) {
        getClientCompanyName().shouldHave(text(value));
        return this;
    }

    public Job hasJobType(String value) {
        getJobType().shouldHave(text(value));
        return this;
    }

    public Job hasAddress(String value) {
        getAddress().shouldHave(text(value));
        return this;
    }
}
