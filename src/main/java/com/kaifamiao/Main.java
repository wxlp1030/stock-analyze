package com.kaifamiao;

import com.kaifamiao.Util.Test;

public class Main {
    public static void main(String[] args) {
        String code = "E:\\Temp\\overview-data-sh.20230508\\sh600000.csv";
        String code1 = "E:\\Temp\\overview-data-sh.20230508\\sh600004.csv";
//        System.out.println("涨：" + Test.upCount(code));
//        System.out.println("跌：" + Test.downCount(code));
//        Test.compare(code,code1);
//        Test.downDays(code, "2023-03-06");
        Test.buyAndSell(code, code1 ,"2023-04-06", 10000);
//        Test.getStockByMonth(code, "2023-04-06").forEach(System.out::println);
//        System.out.println(Test.getStockByMonth(code, "2023-04-06"));
//        System.out.println(Test.upDays(code, "2023-04-06"));
//        System.out.println(Test.upDays(code1));
    }
}
