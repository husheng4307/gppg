package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.PointRecordsDomain;
import com.gppg.gppg.student.mapper.PointRecordsMapper;
import com.gppg.gppg.student.service.IGetPointService;
import org.springframework.stereotype.Service;

@Service
public class IGetPointServiceImpl extends ServiceImpl<PointRecordsMapper, PointRecordsDomain> implements IGetPointService {
}
