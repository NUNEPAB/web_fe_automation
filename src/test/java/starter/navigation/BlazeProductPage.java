package starter.navigation;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class BlazeProductPage extends PageObject {

    @FindBy(linkText= "Add to cart")
    private WebElement buttonAdd;

    @FindBy(partialLinkText= "Home")
    private WebElement homeLink;

    public void addProductToCart() throws InterruptedException {
        if(buttonAdd.isEnabled()){
            buttonAdd.click();
            Thread.sleep(5000);
            try
            {
                this.getDriver().switchTo().alert().accept();
            }   // try
            catch (NoAlertPresentException Ex)
            {
                new AssertionError("No alert has been generated");
            }   // catch

        }
        homeLink.click();
        waitABit(6000);
    }

}
