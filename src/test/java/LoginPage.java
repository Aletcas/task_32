import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {
    private final Page page;
    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator flashMessage;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameField = page.locator("#username");
        this.passwordField = page.locator("#password");
        this.loginButton = page.locator("button[type='submit']");
        this.flashMessage = page.locator("#flash");
    }

    public void open() {
        // Используем полный путь
        page.navigate("https://the-internet.herokuapp.com/login");
        waitForPageToLoad();
    }

    private void waitForPageToLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        usernameField.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }

    public void login(String username, String password) {
        usernameField.fill(username);
        passwordField.fill(password);
        loginButton.click();
        page.waitForLoadState();
    }

    public boolean isLoggedIn() {
        return page.locator("a[href='/logout']").isVisible();
    }

    public String getFlashMessageText() {
        return flashMessage.textContent().trim();
    }

    public boolean isFlashMessageVisible() {
        return flashMessage.isVisible();
    }
}