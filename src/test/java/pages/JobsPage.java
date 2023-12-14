package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import constants.JobStatus;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.appium.AppiumSelectors.byText;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static utils.Helper.getContainer;
import static utils.Helper.getContainerList;

public class JobsPage {

    SelenideAppiumElement back = $(accessibilityId("menu_back"));

    SelenideAppiumElement timePeriod  = $(byXpath("//android.view.ViewGroup[@content-desc='menu_back']/../following-sibling::android.widget.TextView"));

    SelenideAppiumElement title  = $(accessibilityId("page_title"));

    SelenideAppiumElement filter = $(byXpath("//android.widget.TextView[@text='\uEA92']"));

    SelenideAppiumElement newJob  = $(accessibilityId("menu_plus"));

    public ElementsCollection jobs = $$(accessibilityId("job_line"));

    public NewJobPage newJob(){
        newJob.shouldBe(visible).click();
        return new NewJobPage();
    }

    public JobPage openJob(int jobNumber){
        jobs.shouldHave(sizeGreaterThan(0)).get(jobNumber).click();

        return new JobPage();
    }

    public List<Job> getJobs(){
        jobs.shouldHave(sizeGreaterThan(0));
        return getContainerList(jobs, Job.class);
    }

    public Job getJob(int index){
        jobs.shouldHave(sizeGreaterThan(0));
        return getContainer(jobs.get(index), Job.class);
    }

    public JobsPage filterByStatus(JobStatus... jobStatus) {
        String xpath = "//*[lower-case(@text)='%s']";
        EnumSet<JobStatus> statuses = EnumSet.copyOf(Arrays.asList(jobStatus));

        filter.click();
        $(byText("Filter Results")).shouldBe(visible);
        $(byText("Deselect All")).click();
        statuses.forEach(status -> {
            String statusPath = String.format(xpath, status.getValue().toLowerCase());
            $(byXpath(statusPath)).tap();
        });
        $(byText(String.format("Status (%d)",statuses.size()))).shouldBe(visible);
        $(byText("Show Results")).tap();
        $(byText("Jobs")).shouldBe(visible);

        return this;
    }
}
