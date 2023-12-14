package pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.appium.AppiumSelectors.*;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class NewJobPage {

    SelenideAppiumElement selectClient = $(accessibilityId("Select existing client"));

    SelenideAppiumElement selectJobType = $(accessibilityId("job_type"));

    SelenideAppiumElement selectAdSource = $(accessibilityId("adgroup_id"));

    SelenideAppiumElement jobDescription  = $(byXpath("//android.widget.EditText[@content-desc='set_price']"));


    public NewJobPage selectClient(String name){
        selectClient.tap();
        $(accessibilityId("list_search")).shouldBe(visible).setValue(name);
        $$(accessibilityId("client"))
                .shouldHave(sizeGreaterThan(0))
                .get(0).click();
        $(byText("Client Info")).shouldBe(visible);
        return this;
    }

    public NewJobPage selectJobType(String jobType){
        selectJobType.tap();
        $(byText("Job Type")).shouldBe(visible);
        $(byTagAndText("android.widget.TextView", jobType)).click();
        selectJobType.shouldBe(visible);
        $(withContentDescription(jobType)).shouldBe(visible);
        return this;
    }

    public NewJobPage selectAdSource(String adSource){
        selectAdSource.tap();
        $(byText("Ad Source")).shouldBe(visible);
        $(byTagAndText("android.widget.TextView", adSource)).tap();
        selectAdSource.shouldBe(visible);
        $(withContentDescription(adSource)).shouldBe(visible);
        return this;
    }

    public NewJobPage addJobDescription(String jobDesc){
        jobDescription.setValue(jobDesc);
        return this;
    }

    public NewJobPage clearAdSource(){
        selectAdSource.tap();
        $(byText("Job Type")).shouldBe(visible);
        $(byTagAndText("android.widget.TextView", "Clear")).click();
        selectAdSource.shouldBe(visible).shouldBe(empty);
        return this;
    }

    public JobPage createJob(){
        $(accessibilityId("save_job")).click();
        return new JobPage();
    }

}
