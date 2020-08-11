package com.dbs.pageobjects.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {

	public WebDriver driver;

	public IndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[@class='dbs-group']/a/button[@class='btn btn-primary' and text()='Learn More']")
	public WebElement learnMoreButton;

	public void clickOnLearnMoreButton() {
		learnMoreButton.click();
	}

	@FindBy(xpath = "//div[@id='sideNav']/ul/li")
	public List<WebElement> sideBarLinks;

	public void clickOnsideBarLinks(String getlink) {

		for (int i = 0; i < sideBarLinks.size(); i++) {
			if (sideBarLinks.get(i).getText().equalsIgnoreCase(getlink)) {
				sideBarLinks.get(i).click();
				break;
			}
		}
	}

	@FindBy(xpath = "//table/tbody/tr")
	public List<WebElement> tableValues;

	public List<String> readTableValues() {
		List<String> writeData = new ArrayList<>();
		for (int i = 0; i < tableValues.size(); i++) {
			List<WebElement> columnSize = driver.findElements(By.xpath("//table/tbody/tr[" + i + "]/td"));
			for (int j = 1; j < columnSize.size(); j++) {
				System.out.println(columnSize.get(j).getText());
				writeData.add(columnSize.get(j).getText());
			}
		}
		return writeData;
	}

	@FindBy(xpath = "//div[@class='navbar-links-left']/ul/li/a")
	public List<WebElement> headerlinks;

	public void clickOnHeardLink(String headerLink) {
		for (WebElement ele : headerlinks) {
			if (ele.getText().equalsIgnoreCase(headerLink)) {
				ele.click();
				break;
			}
		}
	}

	@FindBy(xpath = "//div[@class='navbar-overflow-content']/div/ul/li/a")
	public List<WebElement> subTablinks;

	public void clickOnSubTabLink(String headerLink) {
		for (WebElement ele : subTablinks) {
			if (ele.getText().equalsIgnoreCase(headerLink)) {
				ele.click();
				break;
			}
		}
	}

	@FindBy(xpath = "//h3/a")
	public List<WebElement> whoAreWelinks;

	public void clickOnWhoAreWelinks(String subLinks) {
		for (WebElement ele : whoAreWelinks) {
			if (ele.getText().equalsIgnoreCase(subLinks)) {
				ele.click();
				break;
			}
		}
	}
	
	@FindBy(xpath = "//div[@class='col-md-8']/h3")
	public List<WebElement> awardsLinks;


}
