package com.example.demo2.beans;

import java.io.Serializable;

/**
 *
 * 网页采集数据的bean类
 * Created by kangkai on 2018/1/31.
 */
public class JsData implements Serializable{


    /**
     * ai : 6
     * dm : localhost
     * ul : http://localhost:63344/xla_sdk/example.html?_ijt=5ajbueb4atq937i355lg54qv3o
     * tt : XLA Demo
     * sn : 1280x800
     * cd : 24
     * rr :
     * ua : Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36
     * lg : zh-CN
     * ci : f6d405d3b5199672899c726c01f4eb1c
     * ed : {"key":"value","page":"example"}
     * ii : pv
     * tm : 1517368081
     */

    private int ai;
    private String dm;
    private String ul;
    private String tt;
    private String sn;
    private int cd;
    private String rr;
    private String ua;
    private String lg;
    private String ci;
    private EdBean ed;
    private String ii;
    private int tm;

    public int getAi() {
        return ai;
    }

    public void setAi(int ai) {
        this.ai = ai;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getUl() {
        return ul;
    }

    public void setUl(String ul) {
        this.ul = ul;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public EdBean getEd() {
        return ed;
    }

    public void setEd(EdBean ed) {
        this.ed = ed;
    }

    public String getIi() {
        return ii;
    }

    public void setIi(String ii) {
        this.ii = ii;
    }

    public int getTm() {
        return tm;
    }

    public void setTm(int tm) {
        this.tm = tm;
    }

    public static class EdBean {
        /**
         * key : value
         * page : example
         */

        private String key;
        private String page;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }
    }
}
