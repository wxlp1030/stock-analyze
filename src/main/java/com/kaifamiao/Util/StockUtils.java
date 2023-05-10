package com.kaifamiao.Util;

import com.csvreader.CsvReader;
import com.kaifamiao.domain.Stock;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockUtils {

    /**
     * 获取某只股票的所有历史数据
     */
    public static List<Stock> getStockHistory(String code) {
        // 解析文件
        try (InputStream inputStream = new FileInputStream(code);
             InputStreamReader reader = new InputStreamReader(inputStream, "GBK");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            CsvReader csvReader = new CsvReader(bufferedReader);
            csvReader.readHeaders(); // 跳过表头
            List<Stock> stocks = new ArrayList<>();
            while (csvReader.readRecord()) {
                // 读取一条记录
                String[] values = csvReader.getValues();
                // 过滤必要数据
                String[] data = new String[values.length - 3];
                System.arraycopy(values, 0, data, 0, 3);
                System.arraycopy(values, 6, data, 3, values.length - 6);
                // 获取对象的Class对象
                Stock stock = new Stock();
                Class<?> clazz = stock.getClass();
                Field[] declaredFields = clazz.getDeclaredFields(); // 获取对象的所有属性
                // 封装到Stock对象中
                for (int i = 0; i < data.length; i++) {
                    Field field = declaredFields[i];
                    field.setAccessible(true); // 设置可访问私有属性
                    if (data[i] == null || data[i].isBlank()) {
                        field.set(stock, null);
                    } else {
                        // 根据属性名设置属性值，注意需要根据属性类型进行转换
                        if (field.getType() == String.class) {
                            field.set(stock, data[i]);
                        } else if (field.getType() == Integer.class) {
                            field.setInt(stock, Integer.parseInt(data[i]));
                        } else if (field.getType() == Boolean.class) {
                            field.set(stock, Objects.equals(data[i], "1"));
                        } else if (field.getType() == Double.class) {
                            field.set(stock, Double.valueOf(data[i]));
                        }
                    }
                }
                stocks.add(stock);
            }
            return stocks;
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
