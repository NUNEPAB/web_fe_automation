package starter.navigation;

import net.serenitybdd.core.pages.PageObject;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class BlazeCartPage extends PageObject {

    @FindBy(linkText = "Add to cart")
    private WebElement buttonAdd;

    @FindBy(partialLinkText = "Home")
    private WebElement homeLink;

    @FindBy(className = "table-responsive")
    private WebElement productsSaved;

    @FindBy(id = "page-wrapper")
    private WebElement pageWrapper;

    @FindBy(id = "orderModal")
    private WebElement orderModal;

    @FindBy(className = "lead text-muted")
    HashMap<String, String> formValues;

    @FindBy(className = "sweet-alert")
    private WebElement confirmationAlert;

    @FindBy(className = "sa-confirm-button-container")
    private WebElement okButtonContainer;

    public void removeProductFromCart(String product)  {
        List<WebElement> productsSavedList = productsSaved.findElements(By.className("success"));
        for (WebElement prod : productsSavedList) {
            List<WebElement> prodCells = prod.findElements(By.tagName("td"));
            for (WebElement cell : prodCells) {
                if (cell.getText().equalsIgnoreCase(product)) {
                    prod.findElement(By.linkText("Delete")).click();
                    waitABit(6000);
                    return;
                }
            }
        }
    }

    public void fillOrderForm(List<WebElement> forms, HashMap<String, String> formValues) {
        String label = "";
        for (WebElement form : forms) {
            label = form.findElement(By.tagName("label")).getAttribute("for");
            if (label != null && formValues.containsKey(label)) {
                form.findElement(By.tagName("input")).sendKeys(formValues.get(label));
            }
        }
    }

    public void placeOrder(JSONObject customer) {
        pageWrapper.findElement(By.tagName("button")).click();

        if (orderModal.isEnabled()) {
            List<WebElement> orderForms = orderModal.findElements(By.className("form-group"));
            fillOrderForm(orderForms, customer);
            orderModal.findElement(By.className("modal-footer")).
                    findElement(By.xpath("//button[text()='Purchase']")).click();
        }
    }

    public HashMap<String, String> getTotalFromReceipt(){
        int cartTotalAmount = 0;
        String sAmount[];
        int count;
        List<String> items =
                Arrays.asList(confirmationAlert.findElement(By.tagName("p")).getText().split("\\s*\n\\s*"));
        HashMap<String,String> receipt = new HashMap<>();
        for (String item : items) {
            //if (item.contains("Amount")) {
                String itemData[] = item.split(":");
                /*sAmount = itemData[1].split(" ");
                count = 0;
                while (count<sAmount.length){
                    try{
                        cartTotalAmount =+ Integer.parseInt(sAmount[count].trim());
                        break;
                    }catch(NumberFormatException e){
                        ++count;
                    }
                }*/
            receipt.put(itemData[0].toString(),itemData[1].toString());
            //}
        }
        
        //return cartTotalAmount;
        return receipt;
    }

    public void acceptReceipt(){
        WebElement okButton = okButtonContainer.findElement(By.tagName("button"));
        okButton.click();
    }

    public int getTotalAmountProductsCheckOut(){
        int total = 0;
        List<WebElement> productsSavedList = productsSaved.findElements(By.className("success"));
        for (WebElement prod : productsSavedList) {
            List<WebElement> prodCells = prod.findElements(By.tagName("td"));
            total = Integer.parseInt(prodCells.get(2).getText());
        }
        return total;
    }
}
