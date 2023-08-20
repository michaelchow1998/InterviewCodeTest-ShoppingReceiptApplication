import config.Config;
import dto.Receipt;
import model.Order;
import model.Product;
import model.Task;
import service.TaxCounter;

import java.util.ArrayList;

public class ReceiptApplication {


    public Receipt run(Task task){
        Receipt receipt = new Receipt();
        addReceiptDetails(receipt, task);
        return receipt;
    }

    public void addReceiptDetails(Receipt receipt, Task task){
        Config config = task.getConfig();
        Order order = task.getOrder();
        ArrayList<Product> productList = order.getPurchasedProducts();
        for (Product product: productList) {
            receipt.addSubtotal(TaxCounter.getSubTotal(product));
            receipt.addTax(TaxCounter.countTax(config,product));
            receipt.addPurchasedProducts(product);
        }
    }



}
