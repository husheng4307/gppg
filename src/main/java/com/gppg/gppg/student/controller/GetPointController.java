package com.gppg.gppg.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.entity.PointRecordsDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.service.FrontUserPointService;
import com.gppg.gppg.student.entity.exception.CommonException;
import com.gppg.gppg.student.service.IGetPointService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/3 19:08
 * des: 上传图片获取积分
 */

@RestController
@RequestMapping("/student")
public class GetPointController {

    private static final int LAUNCH_START = 1000;
    private static final int LAUNCH_END = 1400;
    private static final int DINNER_START = 1600;
    private static final int DINNER_END = 2000;
    private static final int POINT = 1;

    @Autowired
    IGetPointService getPointService;

    @Autowired
    FrontUserPointService iFrontUserPointService;

    @RequestMapping(value = "/uploadImage", method = {RequestMethod.POST, RequestMethod.GET})
    public HttpResponse uploadImageForGetPoint(HttpServletRequest request,
                                               HttpServletResponse httpServletResponse
    ) {
        HttpResponse response = new HttpResponse();
        //获取前端用户信息
        Subject subject = SecurityUtils.getSubject();
        FrontUserDomain frontUser = (FrontUserDomain) subject.getPrincipal();

        if (frontUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST, "学生信息不存在");
            return response;
        }

        //获取上传文件
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = req.getFile("file");

        //保存图片
        try {
            saveProofImage(frontUser, multipartFile);
        } catch (CommonException e) {
            response.setHttpResponse(e.getType(), e.getMessage());
            return response;
        }

        response.setHttpResponse(ResponseType.SUCCESS, null);
        return response;
    }

    private void saveProofImage(FrontUserDomain frontUser, MultipartFile multipartFile) {
        String addEndTime = "";
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df1 = new SimpleDateFormat("HHmm");
        String time = df1.format(new Date());
        int timeCompare = Integer.valueOf(time);
        if (timeCompare > LAUNCH_START && timeCompare < LAUNCH_END) {
            addEndTime = df.format(new Date()) + "01";
        } else if (timeCompare > DINNER_START && timeCompare < DINNER_END) {
            addEndTime = df.format(new Date()) + "02";
        } else {
            // 不属于上传时间段

        }
        //路径，获取当前系统时间，之后再拼接到images后面
        String filepath = "/data/static/lhbb/";
        String realpath = filepath + "/" + addEndTime;
        try {
            //确认文件目录是否存在
            File dir = new File(filepath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            // 确认照片是否存在
            File file = new File(realpath);
            if (!file.exists()) {
                Thumbnails.of(multipartFile.getInputStream()).scale(1f).outputQuality(1f).outputFormat("jpg").toFile(realpath);
            } else {
                // 文件已经存在
            }
            // 更新 积分记录表中的 图片存放位置
            // 更新 用户积分表中的 用户总积分
            String path = realpath + ".jpg";
//            PointRecordsDomain pointRecordsDomain = getPointService.getById();
//            if (pointRecordsDomain == null) {
//                throw new CommonException();
//            }
            try {
                // 新增记录：积分记录表
                PointRecordsDomain domain = new PointRecordsDomain();
                domain.setFrontUserId(frontUser.getId());
                domain.setImagePath(path);
                domain.setCreateTime(new Date());
                getPointService.save(domain);

                // 更新用户积分表
                QueryWrapper<FrontUserPointsDomain> wrapper = new QueryWrapper<>();
                wrapper.eq("front_user_id", frontUser.getId());
                FrontUserPointsDomain domain1 = iFrontUserPointService.getOne(wrapper);
                domain1.setPoint(domain1.getPoint() + 1 * POINT);
                iFrontUserPointService.updateById(domain1);

            } catch (Exception e) {
                throw new CommonException(ResponseType.FAILED_UPLOAD_IMAGES);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
