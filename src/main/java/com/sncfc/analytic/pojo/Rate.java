package com.sncfc.analytic.pojo;

/**
 * Created by 123 on 2017/5/26.
 */
public class Rate {

    private String kpiCode;
    private String kpiName;
    private Double weight;
    private Double kpiUp;
    private Double kpiDown;

    public String getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getKpiUp() {
        return kpiUp;
    }

    public void setKpiUp(Double kpiUp) {
        this.kpiUp = kpiUp;
    }

    public Double getKpiDown() {
        return kpiDown;
    }

    public void setKpiDown(Double kpiDown) {
        this.kpiDown = kpiDown;
    }

    @Override
    public String toString() {
        return "Rate{" + "kpiCode='" + kpiCode + '\'' + ", kpiName='" + kpiName + '\'' + ", weight=" + weight + ", kpiUp=" + kpiUp + ", kpiDown=" + kpiDown + '}';
    }
}
