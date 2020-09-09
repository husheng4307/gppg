package com.gppg.gppg.student.entity.dto;

import lombok.Data;

/**
 * @author: Yang
 * date: 2020/9/3 10:58
 * des: 
 */
@Data
public class QueryStrategyDto {
    /**
     * 策略ID
     */
    int id;
    /**
     * 策略名称
     */
    String strategyName;
    /**
     * 策略描述
     */
    String strategyDescription;
    /**
     * 需要积分
     */
    int pointAccquired;

    String pictureUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
