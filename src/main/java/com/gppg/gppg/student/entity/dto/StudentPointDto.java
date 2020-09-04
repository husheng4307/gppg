package com.gppg.gppg.student.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * date: 2020/9/3 10:11
 * des:
 */
@Data
public class StudentPointDto {
    Integer sumPoint;
    Integer usedPoint;
    Integer nowPoint;

    public StudentPointDto() {

    }

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
}
