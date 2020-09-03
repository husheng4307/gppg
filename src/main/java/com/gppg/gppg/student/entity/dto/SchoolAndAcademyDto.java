package com.gppg.gppg.student.entity.dto;

import com.gppg.gppg.common.entity.AcademyDomain;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 17:13
 * des:
 */
public class SchoolAndAcademyDto {
    private int xxid;
    private String xxmc;
    private List<Academy> xy;

    public static class Academy {
         int xyid;
         String xymc;

        public int getXyid() {
            return xyid;
        }

        public void setXyid(int xyid) {
            this.xyid = xyid;
        }

        public String getXymc() {
            return xymc;
        }

        public void setXymc(String xymc) {
            this.xymc = xymc;
        }
    }

    public int getXxid() {
        return xxid;
    }

    public void setXxid(int xxid) {
        this.xxid = xxid;
    }

    public String getXxmc() {
        return xxmc;
    }

    public void setXxmc(String xxmc) {
        this.xxmc = xxmc;
    }

    public List<Academy> getXy() {
        return xy;
    }

    public void setXy(List<Academy> xy) {
        this.xy = xy;
    }
}
