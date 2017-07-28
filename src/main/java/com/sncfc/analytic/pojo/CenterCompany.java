package com.sncfc.analytic.pojo;

/**
 * 总部季度指标
 */
public class CenterCompany {
    /**编号*/
    private Long id;
    /**大区编码*/
    private String areaCode = "";
    /**大区名称*/
    private String areaName;
    /**年度*/
    private int yearNum;
    /**季度*/
    private int quarterNum;
    /**总投放*/
    private double loanAll;
    /** 购物贷贷投放*/
    private double loanBuy;
    /** 现金贷投放*/
    private int loanCash;
    /** 门店购物贷*/
    private double loanShop;
    /** 线上购物贷*/
    private double loanOnline;
    /** 第三方*/
    private int loanOther;
    /** 随借随还*/
    private double loanAny;
    /** 现金分期*/
    private int loanInstall;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getYearNum() {
        return yearNum;
    }

    public void setYearNum(int yearNum) {
        this.yearNum = yearNum;
    }

    public int getQuarterNum() {
        return quarterNum;
    }

    public void setQuarterNum(int quarterNum) {
        this.quarterNum = quarterNum;
    }

    public double getLoanAll() {
        return loanAll;
    }

    public void setLoanAll(double loanAll) {
        this.loanAll = loanAll;
    }

    public double getLoanBuy() {
        return loanBuy;
    }

    public void setLoanBuy(double loanBuy) {
        this.loanBuy = loanBuy;
    }

    public int getLoanCash() {
        return loanCash;
    }

    public void setLoanCash(int loanCash) {
        this.loanCash = loanCash;
    }

    public double getLoanShop() {
        return loanShop;
    }

    public void setLoanShop(double loanShop) {
        this.loanShop = loanShop;
    }

    public double getLoanOnline() {
        return loanOnline;
    }

    public void setLoanOnline(double loanOnline) {
        this.loanOnline = loanOnline;
    }

    public int getLoanOther() {
        return loanOther;
    }

    public void setLoanOther(int loanOther) {
        this.loanOther = loanOther;
    }

    public double getLoanAny() {
        return loanAny;
    }

    public void setLoanAny(double loanAny) {
        this.loanAny = loanAny;
    }

    public int getLoanInstall() {
        return loanInstall;
    }

    public void setLoanInstall(int loanInstall) {
        this.loanInstall = loanInstall;
    }
}
