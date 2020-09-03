package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.PointRecordsDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.exception.CommonException;
import com.gppg.gppg.student.service.IGetPointService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    IGetPointService getPointService;

    @RequestMapping(value = "/images/uploadImage", method = {RequestMethod.POST, RequestMethod.GET})
    public HttpResponse uploadImageForGetPoint(HttpServletRequest request,
                                               HttpServletResponse httpServletResponse
                                               ) {
        HttpResponse response = new HttpResponse();

        //获取上传文件
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = req.getFile("file");

        //保存图片
        try {
            saveProofImage(multipartFile);
        } catch (CommonException e) {
            response.setHttpResponse(e.getType(), e.getMessage());
            return response;
        }

        response.setHttpResponse(ResponseType.SUCCESS, null);
        return response;
    }

    private void saveProofImage(MultipartFile multipartFile) {
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        //路径，获取当前系统时间，之后再拼接到images后面
        String filepath = "/data/static/lhbb/";
        String realpath = filepath + "/" + df.format(new Date());
        try {
            //确认文件目录是否存在
            File dir = new File(filepath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            Thumbnails.of(multipartFile.getInputStream()).scale(1f).outputQuality(1f).outputFormat("jpg").toFile(realpath);
            //更新 运输批次表中的 证明项
            String name = realpath + ".jpg";
//            PointRecordsDomain pointRecordsDomain = getPointService.getById();
//            if (pointRecordsDomain == null) {
//                throw new CommonException();
//            }
            try {
                PointRecordsDomain domain = new PointRecordsDomain();

            } catch (Exception e) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
