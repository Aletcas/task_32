import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

public class TestBase {
    protected static final String BASE_URL = "https://the-internet.herokuapp.com";

    static Playwright playwright;
    static Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected LoginPage loginPage;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
    }

    @AfterAll
    static void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate(BASE_URL);
        loginPage = new LoginPage(page);
    }

    @AfterEach
    void takeScreenshotOnFailure(TestInfo testInfo) {
        if (context != null && testInfo.getTags().contains("regression")) {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/" + testInfo.getDisplayName() + ".png"))
                    .setFullPage(true));
        }
    }

    void closeContext() {
        if (context != null) context.close();
    }
}
