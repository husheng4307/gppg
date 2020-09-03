package com.gppg.gppg.student.entity.dto;

import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * date: 2020/9/3 10:11
 * des:
 */
public class StudentPointDto {
    Integer sumPoint;
    Integer usedPoint;
    Integer nowPoint;

    public StudentPointDto(Integer sumPoint, Integer usedPoint, Integer nowPoint) {
        this.sumPoint = sumPoint;
        this.usedPoint = usedPoint;
        this.nowPoint = nowPoint;
    }

    @Override
    public String toString() {
        return "StudentPointDto{" +
                "sumPoint=" + sumPoint +
                ", usedPoint=" + usedPoint +
                ", nowPoint=" + nowPoint +
                '}';
    }

    public Integer getSumPoint() {
        return sumPoint;
    }

    public void setSumPoint(Integer sumPoint) {
        this.sumPoint = sumPoint;
    }

    public Integer getUsedPoint() {
        return usedPoint;
    }

    public void setUsedPoint(Integer usedPoint) {
        this.usedPoint = usedPoint;
    }

    public Integer getNowPoint() {
        return nowPoint;
    }

    public void setNowPoint(Integer nowPoint) {
        this.nowPoint = nowPoint;
    }
}
