package starter.navigation;

import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import starter.utils.Util;

import java.util.HashMap;
import org.slf4j.Logger;

import static org.junit.Assert.assertEquals;

public class NavigateTo {

    BlazeHomePage blazeHomePage;
    BlazeCartPage blazeCartPage;

    Util util;

    int totalAmountCart;
    int totalProductsCheckOut;

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigateTo.class);

    @Step("Open the Blaze home page")
    public void openBlazeHomePage() {
        blazeHomePage.open();
    }

    @Step("Open Laptop category at home page")
    public void openCategories(String category) {
        blazeHomePage.clickOnCategory(category);
    }

    @Step("Add products to the cart")
    public void addProductsToCart(String product) throws InterruptedException {
        blazeHomePage.addProductToCart(product);
    }

    @Step("Open the Blaze Cart page")
    public void openBlazeCartPage() {
        blazeHomePage.goToCart();
    }

    @Step("Remove products from the cart")
    public void removeProductsFromCart(String product) {
        blazeCartPage.removeProductFromCart(product);
    }

    @Step("Place order")
    public void placeOrder() throws ParseException {
        JSONObject jsonCustomer = util.getJsonFromFile();
        totalProductsCheckOut = blazeCartPage.getTotalAmountProductsCheckOut();
        blazeCartPage.placeOrder(jsonCustomer);
    }

    @Step("Verify checkout order")
    public void checkReceiptMatchesOrder(){
        HashMap<String,String> receipt = blazeCartPage.getTotalFromReceipt();
        totalAmountCart = util.formatAmount(receipt.get("Amount"));
        LOGGER.info("Receipt ID: "+receipt.get("Id"));
        LOGGER.info("Receipt Amount: "+receipt.get("Amount"));
        assertEquals(totalProductsCheckOut,totalAmountCart);
    }

    @Step("Close receipt by accepting")
    public void acceptReceipt(){
        blazeCartPage.acceptReceipt();
    }


}
