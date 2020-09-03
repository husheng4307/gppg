package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.student.entity.QueryStrategyDomain;
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
public class QueryStrategyServiceImpl extends ServiceImpl<QueryStrategy, QueryStrategyDomain> implements IQueryStrategyService {
    @Override
    public List<QueryStrategyDto> queryStrategy(int schoolId) {
        try {
            List<QueryStrategyDomain> list = this.baseMapper.queryStrategy(schoolId);
            List<QueryStrategyDto> res = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                res.get(i).setStrategyName(list.get(i).getStrategyName());
                res.get(i).setPointAccquired(list.get(i).getPointAccquired());
                res.get(i).setStrategyDescription(list.get(i).getStrategyDescription());
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
