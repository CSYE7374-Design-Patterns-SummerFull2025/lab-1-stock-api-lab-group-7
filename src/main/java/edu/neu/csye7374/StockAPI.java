package edu.neu.csye7374;

import java.util.List;
import java.util.ArrayList;

public class StockAPI implements Tradable {

    private String name;
    private double price;
    private String description;
    private int metric;
    protected List<Double> previous_prices = new ArrayList<>();

    public StockAPI() {
        super();
    }

    public StockAPI(String name, double price, String description) {
        super();
        setName(name);
        setPrice(price);
        setDescription(description);
        addPreviousPrice(price);
    }

    private void addPreviousPrice(double price) {
        this.previous_prices.add(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Stock [name=" + name + ", price=" + price + ", description=" + description + "Metric : " + metric + "]";
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
        double dev = 0.0;
        for(double price : previous_prices) {
            dev = dev + (average - price);
        }
        dev = dev / count;
        if (dev > 0.0) {
            this.metric = 1;
        } else {
            this.metric = -1;
        }
        return this.metric;
    }

    @Override
    public void setBid(String bid) {
        previous_prices.add(Double.parseDouble(bid));
        double sum = 0.0;
        int count = 0;
        double average = 0.0;
        for(double price : previous_prices) {
            sum = sum + price;
            count = count + 1;
        }
        average = sum / count;
        setPrice(average);
    }

}
