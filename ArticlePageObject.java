package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE_TPL = "xpath://android.view.View[@content-desc='{TITLE}']",
    TITLE = "xpath://android.view.View[@content-desc='Nokia']",
    FOOTER_ELEMENT = "xpath://android.view.View[@content-desc='View article in browser']",
    OPTIONS_BUTTON_BOOKMARK = "id:org.wikipedia.beta:id/article_menu_bookmark",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@resource-id='org.wikipedia.beta:id/content']//*[@text='Add to another reading list']",
    OPTIONS_BUTTON_CREATE = "id:org.wikipedia.beta:id/create_button",
    OPTIONS_SAVES_TO_MY_LIST_BUTTON_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/lists_container']//*[@text='{FOLDER_NAME}']",
    MY_LIST_NAME_INPUT = "id:org.wikipedia.beta:id/text_input",
    MY_LIST_OK_BUTTON = "id:android:id/button1",
    CLOSE_ARTICLE_BUTTON1 = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
    CLOSE_ARTICLE_BUTTON2 = "xpath://android.widget.ImageButton",
    CLEAR_X_BUTTON = "id:org.wikipedia.beta:id/search_close_btn";


    private static String getSavedFolderXpath(String name_of_folder)
    {
        return OPTIONS_SAVES_TO_MY_LIST_BUTTON_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

    private static String getElementFound(String element_found)
    {
        return TITLE_TPL.replace("{TITLE}",element_found);
    }

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!",15);
    }

    public WebElement waitForTitleElement1()
    {
        return this.waitForElementPresent(TITLE_TPL, "Cannot find article title on page!",15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("content-desc");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find end of article",
                20
        );
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON_BOOKMARK,
                "Cannot find button to bookmark",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_BUTTON_BOOKMARK,
                "Cannot find button to bookmark",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add another reading list",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_BUTTON_CREATE,
                "Cannot find 'Create button' tip overlay",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticleMyList() {
        this.waitForElementAndClick(
                OPTIONS_BUTTON_BOOKMARK,
                "Cannot find button to bookmark",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_BUTTON_BOOKMARK,
                "Cannot find button to bookmark",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add another reading list",
                5
        );

    }

    public void savedArticleToMyList(String name_of_folder)
    {
        String folder_name = getSavedFolderXpath(name_of_folder);
        this.waitForElementAndClick (folder_name, "Cannot find 'Create button' tip overlay", 5);

    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON1,
                "Cannot find button to Navigate up",
                5
        );

        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON2,
                "Cannot find button to Navigate up",
                5
        );
    }

    public void closeArticleAndClearSearchLine()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON1,
                "Cannot find button to Navigate up",
                5
        );

        this.waitForElementAndClick(
                CLEAR_X_BUTTON,
                "Cannot find button to X button",
                5
        );
    }

    public int getAAmountOfElements(String element_found)
    {
        String element_by_found = getElementFound(element_found);
        List elements = driver.findElements(By.xpath(element_by_found));
        return elements.size();
    }

    public void assertElementPresent(String element_found)
    {
        int amount_of_elements = getAAmountOfElements(element_found);
        if (amount_of_elements == 0) {
            String default_message = "An element '" + element_found + "' supposed to be not present";
            throw new AssertionError(default_message );
        }
    }
    //" + by.toString() + "
}
