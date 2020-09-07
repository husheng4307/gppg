package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.ExchangeStrategyDomain;
import com.gppg.gppg.student.entity.dto.QueryStrategyDto;
import com.gppg.gppg.student.mapper.QueryStrategy;
import com.gppg.gppg.student.service.IQueryStrategyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 11:02
 * des:
 */

@Service
public class QueryStrategyServiceImpl extends ServiceImpl<QueryStrategy, ExchangeStrategyDomain> implements IQueryStrategyService {
    @Override
    public List<QueryStrategyDto> queryStrategy(int schoolId) {
        try {
            List<ExchangeStrategyDomain> list = this.baseMapper.queryStrategy(schoolId);
            List<QueryStrategyDto> res = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                QueryStrategyDto dto = new QueryStrategyDto();
                dto.setId(list.get(i).getId());
                dto.setStrategyName(list.get(i).getStrategyName());
                dto.setPointAccquired(list.get(i).getPointAccquired());
                dto.setStrategyDescription(list.get(i).getStrategyDescription());
                res.add(dto);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
