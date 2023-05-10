package com.kaifamiao.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Stock implements Serializable {

    /**
     * 股票代码
     */
    private String code;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 日期
     */
    private String date;
    /**
     * 开盘价
     */
    private Double open;
    /**
     * 最高价
     */
    private Double high;
    /**
     * 最低价
     */
    private Double low;
    /**
     * 收盘价
     */
    private Double close;
    /**
     * 后复权价
     */
    private Double adjClose;
    /**
     * 前复权价
     */
    private Double preClose;
    /**
     * 涨跌幅
     */
    private Double change;
    /**
     * 成交量
     */
    private Double volume;
    /**
     * 成交额
     */
    private Double amount;
    /**
     * 换手率
     */
    private Double turn;
    /**
     * 流通市值
     */
    private Double mktCap;
    /**
     * 总市值
     */
    private Double mktCapFloat;
    /**
     * 是否涨停
     */
    private Boolean isLimitUp;
    /**
     * 是否跌停
     */
    private Boolean isLimitDown;
    /**
     * 市盈率
     */
    private Double pe;
    /**
     * 市销率
     */
    private Double ps;
    /**
     * 市现率
     */
    private Double pc;
    /**
     * 市净率
     */
    private Double pb;
    /**
     * MA5
     */
    private Double ma5;
    /**
     * MA10
     */
    private Double ma10;
    /**
     * MA20
     */
    private Double ma20;
    /**
     * MA30
     */
    private Double ma30;
    /**
     * MA60
     */
    private Double ma60;
    /**
     * MA金叉死叉
     */
    private String maStatus;
    /**
     * MACD_DIF
     */
    private Double macdDif;
    /**
     * MACD_DEA
     */
    private Double macdDea;
    /**
     * MACD_MACD
     */
    private Double macdMacd;
    /**
     * MACD金叉死叉
     */
    private String macdStatus;
    /**
     * KDJ_K
     */
    private Double kdjK;
    /**
     * KDJ_D
     */
    private Double kdjD;
    /**
     * KDJ_J
     */
    private Double kdjJ;
    /**
     * KDJ金叉死叉
     */
    private String kdjStatus;
    /**
     * 布林线中轨
     */
    private Double bollMid;
    /**
     * 布林线上轨
     */
    private Double bollUp;
    /**
     * 布林线下轨
     */
    private Double bollDown;
    /**
     * psy
     */
    private Double psy;
    /**
     * psyma
     */
    private Double psyMa;
    /**
     * rsi1
     */
    private Double rsi1;
    /**
     * rsi2
     */
    private Double rsi2;
    /**
     * rsi3
     */
    private Double rsi3;
    /**
     * 振幅
     */
    private Double amplitude;
    /**
     * 量比
     */
    private Double volumeRatio;



}
