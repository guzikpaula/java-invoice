package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {


    private Map<Product, Integer>
    products = new HashMap<>();

public void addProduct(Product product ){
  this.addProduct(product, 1);
       }

   public void addProduct(Product product, Integer quantity) {
       if (quantity <= 0) {
           throw new IllegalArgumentException("Quantity must be positive");
       }

       if (product == null ) {
           throw new IllegalArgumentException("Product cannot be empty");
       }

    this.products.put(product, quantity);
      }

    public BigDecimal getNetValue() {
        BigDecimal value = BigDecimal.ZERO;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            BigDecimal price = product.getPrice()
                    .multiply(BigDecimal.valueOf(quantity));

            value = value.add(price);
        }
        return value;
    }

    public BigDecimal getTax() {
    return getGrossValue().subtract(getNetValue());
    }

    public BigDecimal getGrossValue() {
        BigDecimal value = BigDecimal.ZERO;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            BigDecimal grossPrice = product.getPriceWithTax()
                    .multiply(BigDecimal.valueOf(quantity));

            value = value.add(grossPrice);
        }
        return value;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Faktura nr: ").append(this.number).append("\n");

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            BigDecimal grossPrice = product.getPriceWithTax().multiply(BigDecimal.valueOf(quantity));

            sb.append(product.getName())
                    .append(", ")
                    .append(quantity)
                    .append(", ")
                    .append(grossPrice)
                    .append("\n");
        }

        sb.append("Liczba pozycji: ").append(products.size());
        return sb.toString();
    }


    private static int counter = 0;
    private int number;

    public Invoice() {
        this.number = ++counter;
    }

    public int getNumber() {
        return this.number;
    }
}
