package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.PointRecordsDomain;
import com.gppg.gppg.student.mapper.GetPointMapper;
import com.gppg.gppg.student.mapper.QueryPoint;
import com.gppg.gppg.student.service.IGetPointService;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * date: 2020/9/3 19:51
 * des:
 */
@Service
public class GetPointServiceImpl extends ServiceImpl<GetPointMapper, PointRecordsDomain> implements IGetPointService{

}
