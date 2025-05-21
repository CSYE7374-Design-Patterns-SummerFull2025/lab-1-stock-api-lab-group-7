package edu.neu.csye7374;

public class IntelStock extends StockAPI implements Tradable {

    public IntelStock(double price) {
        super("Intel Stock", price, "Semiconductor Industry");
    }

    @Override
    public int getMetric() {
        double sum = 0.0;
        double average = 0.0;
        int count = 0;
        for(double price : previous_prices) {
            sum = sum + price;
            count = count + 1;
        }
        average = sum / count;
        double var = 0.0;
        for(double price : previous_prices) {
            var = var + Math.pow(price - average, 2);
        }
        var = var / count;
        return (int) Math.sqrt(var);
    }

    @Override
    public String toString() {
        return "Stock [name=" + this.getName() + ", price=" + this.getPrice() + ", description=" + this.getDescription() + "Metric : " + this.getMetric() + "]";
    }

}
