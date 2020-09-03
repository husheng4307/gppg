package com.gppg.gppg.student.entity.dto;

/**
 * @author: Yang
 * date: 2020/9/3 10:58
 * des: 
 */
public class QueryStrategyDto {
    String strategyName;
    String strategyDescription;
    int pointAccquired;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyDescription() {
        return strategyDescription;
    }

    public void setStrategyDescription(String strategyDescription) {
        this.strategyDescription = strategyDescription;
    }

    public int getPointAccquired() {
        return pointAccquired;
    }

    public void setPointAccquired(int pointAccquired) {
        this.pointAccquired = pointAccquired;
    }
}
