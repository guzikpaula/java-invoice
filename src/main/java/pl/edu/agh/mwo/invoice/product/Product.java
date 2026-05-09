package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name missing");
        }
        this.name = name;
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price missing or negative");
        }
        this.price = price;


        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {

        return this.price;
    }

    public BigDecimal getTaxPercent() {

        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {

        return this.price.multiply(this.taxPercent).add(this.price);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return name.equals(product.name) && price.compareTo(product.price) == 0;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + price.stripTrailingZeros().hashCode();
    }
}
