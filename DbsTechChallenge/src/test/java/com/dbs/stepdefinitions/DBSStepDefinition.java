package com.dbs.stepdefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.dbs.library.ReadXlsxExcelFile;
import com.dbs.pageobjects.pages.IndexPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DBSStepDefinition {

	RemoteWebDriver driver = Hooks.driver;
	IndexPage indexPage;
	LinkedHashMap<String, String> getMyGrantPageValues = new LinkedHashMap<>();

	public DBSStepDefinition() {
		indexPage = new IndexPage(driver);
	}

	@Given("I launch DBS application for entity")
	public void i_launch_DBS_application_for_entity() throws InterruptedException, IOException {

		FileReader reader = new FileReader(
				System.getProperty("user.dir") + "/src/test/java/com/DBS/config/config.properties");
		Properties p = new Properties();
		p.load(reader);

		String url = p.getProperty("url");
		driver.get(url);
		Thread.sleep(1000);
		driver.get(url);
	}

	@When("I click on the Learn More Button")
	public void i_click_on_the_Learn_More_Button() {
		indexPage.clickOnLearnMoreButton();
	}

	@When("I click on {string} country in the left panel")
	public void i_click_on_country_in_the_left_panel(String sideBarOptions) {
		indexPage.clickOnsideBarLinks(sideBarOptions);
	}

	@When("I read and write the Meals businesses")
	public void i_read_and_write_the_Meals_businesses() {
		ReadXlsxExcelFile.writeExcel(System.getProperty("user.dir") + "/src/test/resources/Files/Test.xlsx", "Data",
				indexPage.readTableValues());
	}

	@When("I click on {string} link in the header tabs")
	public void i_click_on_link_in_the_header_tabs(String headerLink) {
		indexPage.clickOnHeardLink(headerLink);
	}

	@When("I click on {string} in the sub tab")
	public void i_click_on_in_the_sub_tab(String subTabLinks) {
		indexPage.clickOnSubTabLink(subTabLinks);
	}

	@When("I click on {string} link")
	public void i_click_on_link(String subLinks) {
		indexPage.clickOnWhoAreWelinks(subLinks);
	}

	@Then("I should see {int} award links in the page")
	public void i_should_see_award_links_in_the_page(int int1) {
		int awardLinkssize = indexPage.awardsLinks.size();
		Assert.assertEquals(int1, awardLinkssize);
	}

	@Then("I should see {string} as award name and {string} as caption of the award is matched with table")
	public void i_should_see_and_is_matched_with_table(String awardName, String captionOftheAward) {
		List<WebElement> awardNameListInPage = driver.findElements(By.xpath("//div[@class='col-md-8']/h3/strong"));
		List<WebElement> captionofTheAwardListInPage = driver.findElements(By.xpath("//div[@class='col-md-8']/p"));

		boolean allMatch = false;
		for (WebElement ele : awardNameListInPage) {
			if (ele.getText().equalsIgnoreCase(awardName)) {
				System.out.println("Actual award name " + ele.getText() + " :   expected award name  " + awardName);
				allMatch = true;
				break;
			}
		}

		boolean allMatch1 = false;
		for (WebElement ele : captionofTheAwardListInPage) {
			if (ele.getText().equalsIgnoreCase(captionOftheAward)) {
				System.out.println("Actual caption of the award" + ele.getText() + " :   expected caption of the award "
						+ captionOftheAward);
				allMatch1 = true;
				break;
			}
		}
		Assert.assertTrue(allMatch, awardName + " text Not found");
		Assert.assertTrue(allMatch1, captionOftheAward + " text Not found");
	}

}
