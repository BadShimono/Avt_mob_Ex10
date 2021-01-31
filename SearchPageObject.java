package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_SKIP_BUTTON = "id:org.wikipedia.beta:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "id:org.wikipedia.beta:id/search_src_text",
            SEARCH_NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton",
            SEARCH_X_BUTTON = "id:org.wikipedia.beta:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/search_results_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_SUBSTRING1_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia.beta:id/search_results_list']/*[@class='android.view.ViewGroup']",
            SEARCH_EMPTY_RESULT_ELEMENT = "id:org.wikipedia.beta:id/results_text";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElement1(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING1_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchButton()
    {
        this.waitForElementAndClick(SEARCH_SKIP_BUTTON,"Cannot find and click 'SKIP' init button", 5);
        this.waitForElementNotPresent(SEARCH_SKIP_BUTTON,"Find 'SKIP' after clicking 'SKIP' init button",5);
    }

    public void waitForNavigateUpButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_NAVIGATE_UP_BUTTON, "Cannot find search Navigate Up button",5);
    }

    public void waitForNavigateUpButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_NAVIGATE_UP_BUTTON, "Search Navigate Up button is still present!",5);
    }

    public void clickNavigateUpButton()
    {
        this.waitForElementAndClick(SEARCH_NAVIGATE_UP_BUTTON, "Cannot find and click search Navigate Up button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element",5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element",5);
    }

    public void clearSearchLine()
    {
        this.waitForElementAndClick(SEARCH_X_BUTTON,"Cannot find and click search 'close button'",5);
        this.waitForElementPresent(SEARCH_INPUT,"Cannot find search input 'search init element'",5);
    }

    public void typeSearchLine(String name_of_search)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, name_of_search,"Cannot find adn type into search input",5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring " + substring,10);
    }

    public void waitForSearchResult1(String substring)
    {
        String search_result_xpath = getResultSearchElement1(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring " + substring,10);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring " + substring,10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Cannot find empty result element.", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

}
