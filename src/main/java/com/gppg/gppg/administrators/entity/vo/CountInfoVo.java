package com.gppg.gppg.administrators.entity.vo;

import com.gppg.gppg.administrators.entity.dto.ExchangedPointDto;
import com.gppg.gppg.administrators.entity.dto.SumPointDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/7 12:57
 * des:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountInfoVo {
    List<SumPointDto> sumPoint;
    List<ExchangedPointDto> exchangedPoint;
}
