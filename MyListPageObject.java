package lib.ui;

import io.appium.java_client.AppiumDriver;


public class MyListPageObject extends MainPageObject{

    private static final String
    FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/reading_list_list']//*[@text='{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/reading_list_contents']//*[@text='{TITLE}']";

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",article_title);
    }
    private static String getSavedArticleXpathByTitle1(String article_title1)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",article_title1);
    }

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                10
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToMyListAndClick(String article_title1)
    {
        String article_xpath = getSavedArticleXpathByTitle1(article_title1);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title1, 15);
        this.waitForElementAndClick(article_xpath, "Cannot find article and click", 10);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForArticleToAppearByTitle(article_title);

        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );

        this.waitForArticleToDisappearByTitle(article_title);
    }

}
