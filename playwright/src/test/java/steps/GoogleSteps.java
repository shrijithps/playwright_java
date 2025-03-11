package steps;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class GoogleSteps {
	private Browser browser;
	private Page page;
	private BrowserContext context;

	@Given("I open the saucedemo page")
	public void iOpenTheGoogleHomepage() {
		Playwright playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	}

	@And("Enter the credentials")
	public void iSearchFor() {
		page.waitForTimeout(5000);
		page.locator("input[id='user-name']").fill("standard_user");
		page.locator("input[id='password']").fill("secret_sauce");
		page.locator("input[id='login-button']").click();
		page.waitForTimeout(3000); // Wait for results to load
	}

	@Then("Dashboard should be displayed")
    public void dashboard() {
    	Locator swagLabs = page.locator("//div[text()='Swag Labs')");
    	PlaywrightAssertions.assertThat(swagLabs).isVisible();
    }
}
