package tests;

import static driver.EmulatorHelper.goBack;
import static helper.Constants.ACCESSIBILITY_ID_APP_PAGE;
import static helper.Constants.ACCESSIBILITY_ID_CUSTOM_PAGE;
import static helper.Constants.ACCESSIBILITY_ID_LOADER_PAGE;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jdk.jfr.Description;
import pages.AppPage;
import pages.LoaderPage;
import pages.MainPage;

public class CustomTest extends BaseTest {
    private static MainPage mainPage;

    @BeforeTest
    public static void init() {
        mainPage = new MainPage();
    }

    @DataProvider
    private Object[][] searchQuery() {
        return new Object[][]{
                {"Button"},
                {"Android"}
        };
    }

    @Description("Verify the functionality of the search feature and the behavior of the filter reset button.")
    @Test(dataProvider = "searchQuery")
    public void verifySearchFunctionalityAndFilterReset(String text) {
        int countAllElementsBeforeSearch;
        int countElementsWithTextInTheGeneralList;
        int countElementsWithSearchWord;
        int countAllElementsAfterClearSearchInput;

        pages.CustomPage customPage = mainPage.goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_APP_PAGE, AppPage.class)
                .goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_LOADER_PAGE, LoaderPage.class)
                .goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_CUSTOM_PAGE, pages.CustomPage.class);

        customPage.isCorrectNavigation(ACCESSIBILITY_ID_APP_PAGE + "/" + ACCESSIBILITY_ID_LOADER_PAGE + "/" + ACCESSIBILITY_ID_CUSTOM_PAGE);

        countAllElementsBeforeSearch = customPage.getCountElements();

        countElementsWithTextInTheGeneralList = customPage.getCountElementsByText(text);

        customPage.clickOnTheSearchInput()
                .enterTextInTheSearchInput(text);
        Assert.assertTrue(customPage.allElementsStartWith(text));

        countElementsWithSearchWord = customPage.getCountElements();
        Assert.assertEquals(countElementsWithTextInTheGeneralList ,countElementsWithSearchWord);

        customPage.clearSearchInput();
        goBack();

        countAllElementsAfterClearSearchInput = customPage.getCountElements();
        Assert.assertEquals(countAllElementsAfterClearSearchInput, countAllElementsBeforeSearch);
    }
}
