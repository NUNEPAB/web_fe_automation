package starter.utils;

import java.util.ArrayList;
import java.util.List;

public class BlazeCustomerCart {

    List<BlazeProduct> customerSelection = new ArrayList<>();

    public void addProduct(BlazeProduct product) {
        customerSelection.add(customerSelection.size(),product);
    }
}
