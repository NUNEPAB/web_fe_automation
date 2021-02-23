package starter.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;
import org.json.simple.parser.ParseException;
import starter.navigation.NavigateTo;

import java.util.List;

public class BlazeStepDefinitions {

    @Steps
    NavigateTo navigateTo;

    @Given("Pablo is on the Blaze home page")
    public void pabloIsOnTheBlazeHomePage() {
        navigateTo.openBlazeHomePage();
    }

    @When("he navigates to {string} category and adds the following products to the cart")
    public void heNavigatesToCategoryAndAddsTheFollowingProductsToTheCart(
            String category, DataTable table) {

        List products = table.asList();
        for(Object product : products)
        {
            navigateTo.openCategories(category);
            try {
                navigateTo.addProductsToCart((String) product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @When("he navigates to Cart page and deletes the following products from the cart")
    public void heNavigatesToCartPageAndDeletesTheFollowingProductsFromTheCart(DataTable table) {
        navigateTo.openBlazeCartPage();

        List products = table.asList();
        for(Object product : products)
        {
            navigateTo.removeProductsFromCart((String) product);
        }
    }

    @And("he place the order")
    public void hePlaceTheOrder() throws ParseException {
        navigateTo.placeOrder();
    }

    @Then("receipt matches the order")
    public void receiptMatchesTheOrder() {
        navigateTo.checkReceiptMatchesOrder();
    }

    @And("accepts the receipt")
    public void acceptsTheReceipt() {
        navigateTo.acceptReceipt();
    }
}
