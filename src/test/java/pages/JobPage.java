package pages;

import com.codeborne.selenide.appium.AppiumScrollOptions;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import constants.JobStatus;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.AppiumSelectors.byText;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class JobPage {

    SelenideAppiumElement assignTechnician = $(accessibilityId("assign_teach_to_job"));

    SelenideAppiumElement status = $(accessibilityId("job_status_value"));

    SelenideAppiumElement sendJob = $(accessibilityId("menu_send_single_job"));

    public JobPage assignTechnician() {
        assignTechnician.scrollTo().shouldBe(visible).click();
        $(byText("Team")).shouldBe(visible);
        $(byText("Clear")).tap();
        $(accessibilityId("team_select")).tap();
        $(accessibilityId("save")).tap();
        assignTechnician.shouldBe(visible);
        assignTechnician.find(byText("Boris Zubkov")).shouldBe(visible);
        status.scroll(AppiumScrollOptions.up());
        return this;
    }

    public JobPage assignTechnicianIfUnassigned() {
        assignTechnician.scrollTo().shouldBe(visible);
        if (assignTechnician.find(byText("Assign Technician")).exists()) return assignTechnician();
        else {
            status.scroll(AppiumScrollOptions.up());
            return this;
        }
    }

    public JobPage clearAssignation() {
        assignTechnician.scrollTo().shouldBe(visible);
        if (!assignTechnician.find(byText("Assign Technician")).exists()) {
            assignTechnician.tap();
            $(byText("Team")).shouldBe(visible);
            $(byText("Clear")).tap();
            $(accessibilityId("save")).tap();
            assignTechnician.find(byText("Assign Technician")).shouldBe(visible);
        }
        status.scroll(AppiumScrollOptions.up());
        return this;
    }

    public JobPage changeStatusTo(JobStatus statusValue) {
        status.shouldBe(visible).click();
        $(byText("Job Status")).shouldBe(visible);
        $(byText(statusValue.getValue())).shouldBe(visible).click();

        return this;
    }

    public JobPage statusChangeNotificationIsPresent() {
        $(byText("Job status was changed successfully")).shouldBe(visible);

        return this;
    }

    public JobPage shouldHaveStatus(JobStatus status) {
        this.status.find(byText(status.getValue())).shouldBe(visible);

        return this;
    }

    public JobPage checkSendingOptionsArePresent() {
        sendJob.shouldBe(visible);
        sendJob.tap();
        $(byText("Send job to assigned techs")).shouldBe(visible);
        $(byText("By Text")).shouldBe(visible);
        $(byText("By Email")).shouldBe(visible);
        $(byText("In App")).shouldBe(visible);
        $(byText("By All")).shouldBe(visible);
        $(accessibilityId("modal_dismiss_button")).tap();

        return this;
    }

    public JobPage mustAssignTechnicianBannerIsPresent() {
        $(byText("Must assign technician to perform this action")).shouldBe(visible);
        $(accessibilityId("close")).shouldBe(visible).click();
        return this;
    }
}
