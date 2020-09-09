package com.gppg.gppg.administrators.service;

import com.gppg.gppg.common.entity.BackUserDomain;

/**
 * @author: Yang
 * date: 2020/9/7 10:13
 * des: 
 */
public interface ManageInfoService {

    /**
     * asd
     * @param frontUserId
     * @param exchangeRecordsId
     * @param isApproved
     * @param backUser
     */
    void dealStudentApply(int frontUserId, int exchangeRecordsId, int isApproved, BackUserDomain backUser);
}
