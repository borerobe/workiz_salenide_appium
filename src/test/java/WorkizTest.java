import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import constants.JobStatus;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.*;
import providers.AndroidDriverProvider;
import providers.IosDriverProvider;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.appium.AppiumDriverRunner.*;
import static constants.JobStatus.IN_PROGRESS;
import static constants.JobStatus.SUBMITTED;

public class WorkizTest {

    @BeforeAll
    static void setUp() {
        String platform = System.getProperty("platform", "Android").toLowerCase();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().savePageSource(false));

        Configuration.browser = platform.equals("ios") ? IosDriverProvider.class.getName() : AndroidDriverProvider.class.getName();

        SelenideAppium.launchApp();
    }

    @BeforeEach
    void launchApp() {
        if (isAndroidDriver()) {
            getAndroidDriver().activateApp("com.workiz");
        } else {
            getIosDriver().activateApp("com.workiz");
        }
    }

    @Test
    void checkJobDisplayOnJobsPage() {
        MainPage main = new MainPage();

        JobsPage jobsPage = main.openMenu().openJobs();
        Job job = jobsPage.getJob(0);
        job.hasClientCompanyName("Acme Inc")
                .hasAddress("5520 Ruffin Road, San Diego, California 92123, San Diego,  92123")
                .hasStatus(SUBMITTED.getValue());
    }

    @Test
    void checkJobFilterOnJobsPage() {
        MainPage main = new MainPage();

        JobsPage jobsPage = main.openMenu().openJobs();
        jobsPage.filterByStatus(SUBMITTED)
                .getJobs()
                .forEach(job -> job.getStatus().shouldHave(text(SUBMITTED.getValue())));
    }

    @Test
    void createNewJobTest() {

        MainPage main = new MainPage();

        NewJobPage newJobPage = main.openMenu().openJobs().newJob();
        JobPage jobPage = newJobPage.selectClient("Sample Client")
                .selectJobType("Example job type")
                .selectAdSource("Google")
                .addJobDescription("New testing job")
                .createJob();
        jobPage.shouldHaveStatus(SUBMITTED)
                .shouldHaveClient("Sample Client")
                .shouldHaveDetails("Example job type", "Google", "New testing job");
    }

    @Test
    void assignJobTest() {
        MainPage main = new MainPage();

        JobPage jobPage = main.openMenu().openJobs().openJob(0);

        jobPage.clearAssignation().assignTechnician();
        jobPage.checkSendingOptionsArePresent();
    }

    @Test
    void changeStatusWithoutAssigningTechnician() {
        MainPage main = new MainPage();

        JobPage jobPage = main.openMenu().openJobs().openJob(0);

        jobPage.clearAssignation()
                .shouldHaveStatus(SUBMITTED)
                .changeStatusTo(IN_PROGRESS)
                .mustAssignTechnicianBannerIsPresent();
    }

    @ParameterizedTest
    @EnumSource
    void changeStatus(JobStatus status) {
        MainPage main = new MainPage();

        JobPage jobPage = main.openMenu().openJobs().openJob(0);

        jobPage.assignTechnicianIfUnassigned()
                .changeStatusTo(status)
                .statusChangeNotificationIsPresent();

        jobPage.changeStatusTo(SUBMITTED).shouldHaveStatus(SUBMITTED);

    }

    @AfterEach
    void closeApp() {
        Boolean isAppRunning =
                isAndroidDriver() ? getAndroidDriver().terminateApp("com.workiz") : getIosDriver().terminateApp("com.workiz");
    }
}
