package starter.navigation;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DefaultUrl("https://www.demoblaze.com")
class BlazeHomePage extends PageObject {
    @FindAll(@FindBy(id="itemc"))
    private List<WebElement> categories;

    @FindBy(className="card-title")
    private List<WebElement> products;

    @FindBy(id="next2")
    private WebElement buttonNext;

    @FindBy(id="navbarExample")
    private WebElement navBar;

    @FindBy(partialLinkText= "Cart")
    private WebElement cartLink;

    BlazeProductPage blazeProductPage;

    public void goToCart(){
        cartLink.click();
    }

    public void clickOnCategory(String category) {
        for (WebElement cat : categories) {
            if (cat.getText().equalsIgnoreCase(category)) {
                cat.click();
                break;
            }
        }
    }

    public void addProductToCart(String product) throws InterruptedException {

        boolean productAdded = false;

        waitABit(6000);

        while  (!productAdded){
            for (WebElement prod : products) {
                WebElement prodLinkText = prod.findElement(By.className("hrefch"));
                if (product.equalsIgnoreCase(prodLinkText.getText())) {
                    prodLinkText.click();
                    blazeProductPage.addProductToCart();
                    productAdded = true;
                    break;
                }
            }
            if(!productAdded){ buttonNext.click(); }
        }
    }
}
