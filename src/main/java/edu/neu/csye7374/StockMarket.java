package edu.neu.csye7374;

import java.util.List;
import java.util.ArrayList;

public class StockMarket {

    private static StockMarket stock_market_instance;

    private List<StockAPI> stocks = new ArrayList<>();

    private StockMarket() {
        stock_market_instance = null;
    }

    public static synchronized StockMarket getStockMarketInstance() {
        if(stock_market_instance == null) {
            stock_market_instance = new StockMarket();
        }
        return stock_market_instance;
    }

    public void addStock(StockAPI stock) {
        stocks.add(stock);
    }

    public void deleteStock(StockAPI stock) {
        boolean deleted = this.stocks.remove(stock);
        if(deleted == false) {
            System.out.println("Stock Not Found");
        }
    }

    public void displayStocks() {
        for(StockAPI stock : this.stocks) {
            System.out.println(stock.toString());
        }
        System.out.println();
    }

    public static void displayBidsVsMetrics(String[] bids, StockAPI stock) {
        System.out.println("Stock: " + stock.getName() + " current Price : " + stock.getPrice());
        System.out.println("---------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-10s |%n", "Bid", "New Price", "Metric");
        System.out.println("---------------------------------------------");
        for(String bid : bids) {
            stock.setBid(bid);
            double price = stock.getPrice();
            double metric = stock.getMetric();
            System.out.printf("| %-10.2f | %-15.2f | %-10.2f |%n", Double.parseDouble(bid), price, metric);
        }
        System.out.println("---------------------------------------------");
        System.out.println();
    }

    public static void demo() {
        IntelStock intel_stock = new IntelStock(59);
        MicrosoftStock microsoft_stock = new MicrosoftStock(7);
        StockMarket.getStockMarketInstance().addStock(intel_stock);
        StockMarket.getStockMarketInstance().addStock(microsoft_stock);
        displayBidsVsMetrics(new String[] {"17","41","62","83","91","97"},intel_stock);
        displayBidsVsMetrics(new String[] {"85","12","8","94","35","54"}, microsoft_stock);
        StockMarket.getStockMarketInstance().displayStocks();
    }

}
