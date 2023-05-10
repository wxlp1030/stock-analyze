package com.kaifamiao.Util;

import com.kaifamiao.domain.Stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {


    /**
     * 计算某只股票的阳线统计
     *
     * @param code
     * @return
     */
    public static int upCount(String code) {
        // 获取所有历史数据
        List<Stock> stockHistory = StockUtils.getStockHistory(code);
        int upCount = 0;
        for (Stock stock : stockHistory) {
            if (stock.getClose() > stock.getOpen()) {
                upCount++;
            }
        }
        return upCount;
    }

    /**
     * 计算某只股票的阴线统计
     *
     * @param code
     * @return
     */
    public static int downCount(String code) {
        // 获取所有历史数据
        List<Stock> stockHistory = StockUtils.getStockHistory(code);
        int downCount = 0;
        for (Stock stock : stockHistory) {
            if (stock.getClose() < stock.getOpen()) {
                downCount++;
            }
        }
        return downCount;
    }

    /**
     * 比较两只股票的阳线阴线情况
     */
    public static void compare(String code1, String code2) {
        int upCount1 = upCount(code1);
        int downCount1 = downCount(code1);
        int count1 = StockUtils.getStockHistory(code1).size();
        int upCount2 = upCount(code2);
        int downCount2 = downCount(code2);
        int count2 = StockUtils.getStockHistory(code2).size();
        System.out.println("阳：" + upCount1 + "，阴：" + downCount1);
        System.out.println("阳：" + upCount2 + "，阴：" + downCount2);
        System.out.println("股票1阴阳比：" + Math.round((upCount1 * 1.0 / count1) * 100) + "%" + "，股票2阴阳比：" + Math.floor((upCount2 * 1.0 / count2) * 100) + "%");
    }

    /**
     * 查询某天某只股票的所有信息
     */
    public static Stock getStockByDate(String code, String date) {
        // 获取所有历史数据
        List<Stock> stockHistory = StockUtils.getStockHistory(code);
        // 根据日期查询
        for (Stock stock : stockHistory) {
            if (Objects.equals(stock.getDate(), date)) {
                return stock;
            }
        }
        return null;
    }

    /**
     * 计算某只股票涨的天数
     */
    public static int upDays(String code) {
        // 获取所有历史数据
        List<Stock> stockHistory = StockUtils.getStockHistory(code);
        int upDays = 0;
        for (Stock stock : stockHistory) {
            if (stock.getChange() > 0) {
                upDays++;
            }
        }
        return upDays;
    }

    /**
     * 查询某只股票某天开始一个月的数据
     */
    public static List<Stock> getStockByMonth(String code, String date) {
        // 获取所有历史数据
        List<Stock> stockHistory = StockUtils.getStockHistory(code);
        // 根据日期查询
        List<Stock> stocks = new ArrayList<>();
        for (Stock stock : stockHistory) {
            try {
                Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(stock.getDate());
                Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                Calendar instance = Calendar.getInstance();
                instance.setTime(parse);
                instance.add(Calendar.MONTH, 1);
                if (currentDate.compareTo(parse) >=0 && currentDate.compareTo(instance.getTime()) <= 0) {
                    stocks.add(stock);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return stocks;
    }


    /**
     * 某只股票在某天开始一个月连续三天上涨的股票所有信息
     */
    public static void upDays(String code, String date) {
        // 获取某天开始连续一个月的数据
        List<Stock> stockHistory = getStockByMonth(code, date);
        for (int i = stockHistory.size() - 1; i > 1; i--) {
            Stock stock = stockHistory.get(i);
            // 判断是否连续三天上涨
            if (stock.getChange() > 0) {
                // 判断前一天是否上涨
                Stock stock1 = getStockByDate(code, stockHistory.get(i - 1).getDate());
                if (stock1.getChange() > 0) {
                    // 判断前两天是否上涨
                    Stock stock2 = getStockByDate(code, stockHistory.get(i - 2).getDate());
                    if (stock2.getChange() > 0) {
                        System.out.println(stock);
                        System.out.println(stock1);
                        System.out.println(stock2);
                    }
                }
            }
        }
    }

    /**
     * 某只股票在某天开始一个月连续三天下跌的股票所有信息
     */
    public static void downDays(String code, String date) {
        // 获取某天开始连续一个月的数据
        List<Stock> stockHistory = getStockByMonth(code, date);
        for (int i = stockHistory.size() - 1; i > 1; i--) {
            Stock stock = stockHistory.get(i);
            // 判断是否连续三天上涨
            if (stock.getChange() < 0) {
                // 判断前一天是否上涨
                Stock stock1 = getStockByDate(code, stockHistory.get(i - 1).getDate());
                if (stock1.getChange() < 0) {
                    // 判断前两天是否上涨
                    Stock stock2 = getStockByDate(code, stockHistory.get(i - 2).getDate());
                    if (stock2.getChange() < 0) {
                        System.out.println(stock);
                        System.out.println(stock1);
                        System.out.println(stock2);
                    }
                }
            }
        }
    }

    /**
     * 某天买入某只股票，一个月后卖出，计算收益
     */
    public static void buyAndSell(String code, String date, int money) {
        // 获取某天开始连续一个月的数据
        List<Stock> stockHistory = getStockByMonth(code, date);
        // 获取买入价格
        Stock buyStock = stockHistory.get(stockHistory.size() - 1);
        // 获取卖出价格
        Stock sellStock = stockHistory.get(0);
        // 计算收益
        double profit = (sellStock.getOpen() - buyStock.getClose()) / buyStock.getOpen();
        System.out.println("买入：" + buyStock);
        System.out.println("卖出：" + sellStock);
        System.out.println("收益：" + profit * money );
    }

    /**
     * 小王和小明在某天分别买了两只股票，1000股，请计算一个月后他们两人的收益
     */
    public static void buyAndSell(String code1, String code2, String date, int money) {
        // 获取某天开始连续一个月的数据
        List<Stock> stockHistory1 = getStockByMonth(code1, date);
        List<Stock> stockHistory2 = getStockByMonth(code2, date);
        // 获取买入价格
        Stock buyStock1 = stockHistory1.get(stockHistory1.size() - 1);
        Stock buyStock2 = stockHistory2.get(stockHistory2.size() - 1);
        // 获取卖出价格
        Stock sellStock1 = stockHistory1.get(0);
        Stock sellStock2 = stockHistory2.get(0);
        // 计算收益
        double profit1 = (sellStock1.getOpen() - buyStock1.getClose()) / buyStock1.getOpen();
        double profit2 = (sellStock2.getOpen() - buyStock2.getClose()) / buyStock2.getOpen();
        System.out.println("小王买入：" + buyStock1);
        System.out.println("小王卖出：" + sellStock1);
        System.out.println("小王收益：" + profit1 * money);
        System.out.println("小明买入：" + buyStock2);
        System.out.println("小明卖出：" + sellStock2);
        System.out.println("小明收益：" + profit2 * money);
    }
}
