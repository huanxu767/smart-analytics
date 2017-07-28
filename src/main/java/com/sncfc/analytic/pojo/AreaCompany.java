package com.sncfc.analytic.pojo;

/**
 * Created by xuhuan on 2017/6/1.
 * 大区指标
 */
public class AreaCompany {
    private Long id;
    private String areaCode;
    private String areaName;
    private int yearNum;
    private int quarterNum;
    private double loanShop;
    private double loanShopNewcust;
    private int shopNewcust;
    private double loanCash;
    private double loanCashself;
    private int cashselfNewcust;
    private double loanOther;
    private int otherNewcust;
    private int appCustNo;

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

    public double getLoanShop() {
        return loanShop;
    }

    public void setLoanShop(double loanShop) {
        this.loanShop = loanShop;
    }

    public double getLoanShopNewcust() {
        return loanShopNewcust;
    }

    public void setLoanShopNewcust(double loanShopNewcust) {
        this.loanShopNewcust = loanShopNewcust;
    }

    public int getShopNewcust() {
        return shopNewcust;
    }

    public void setShopNewcust(int shopNewcust) {
        this.shopNewcust = shopNewcust;
    }

    public double getLoanCash() {
        return loanCash;
    }

    public void setLoanCash(double loanCash) {
        this.loanCash = loanCash;
    }

    public double getLoanCashself() {
        return loanCashself;
    }

    public void setLoanCashself(double loanCashself) {
        this.loanCashself = loanCashself;
    }

    public int getCashselfNewcust() {
        return cashselfNewcust;
    }

    public void setCashselfNewcust(int cashselfNewcust) {
        this.cashselfNewcust = cashselfNewcust;
    }

    public double getLoanOther() {
        return loanOther;
    }

    public void setLoanOther(double loanOther) {
        this.loanOther = loanOther;
    }

    public int getOtherNewcust() {
        return otherNewcust;
    }

    public void setOtherNewcust(int otherNewcust) {
        this.otherNewcust = otherNewcust;
    }

    public int getAppCustNo() {
        return appCustNo;
    }

    public void setAppCustNo(int appCustNo) {
        this.appCustNo = appCustNo;
    }
}
