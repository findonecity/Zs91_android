package com.example.mainmodule.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zj on 2019/4/8.
 * is use for:
 */
public class HomeMessageBean implements Serializable {
    private Object onenewslist;
    private Object pricelist;
    private int qiandao;
    private String zxinfo;
    private String pcount;
    private int is5zheflag;
    private String hotnews;
    private Object onehuzhulist;
    private int zxclose;
    private List<AdlistallBean> adlistall;
    private List<MidadlistBean> midadlist;
    private List<TopadlistBean> topadlist;
    private List<YjzdListBean> yjzdList;

    public Object getOnenewslist() {
        return onenewslist;
    }

    public void setOnenewslist(Object onenewslist) {
        this.onenewslist = onenewslist;
    }

    public Object getPricelist() {
        return pricelist;
    }

    public void setPricelist(Object pricelist) {
        this.pricelist = pricelist;
    }

    public int getQiandao() {
        return qiandao;
    }

    public void setQiandao(int qiandao) {
        this.qiandao = qiandao;
    }

    public String getZxinfo() {
        return zxinfo;
    }

    public void setZxinfo(String zxinfo) {
        this.zxinfo = zxinfo;
    }

    public String getPcount() {
        return pcount;
    }

    public void setPcount(String pcount) {
        this.pcount = pcount;
    }

    public int getIs5zheflag() {
        return is5zheflag;
    }

    public void setIs5zheflag(int is5zheflag) {
        this.is5zheflag = is5zheflag;
    }

    public String getHotnews() {
        return hotnews;
    }

    public void setHotnews(String hotnews) {
        this.hotnews = hotnews;
    }

    public Object getOnehuzhulist() {
        return onehuzhulist;
    }

    public void setOnehuzhulist(Object onehuzhulist) {
        this.onehuzhulist = onehuzhulist;
    }

    public int getZxclose() {
        return zxclose;
    }

    public void setZxclose(int zxclose) {
        this.zxclose = zxclose;
    }

    public List<AdlistallBean> getAdlistall() {
        return adlistall;
    }

    public void setAdlistall(List<AdlistallBean> adlistall) {
        this.adlistall = adlistall;
    }

    public List<MidadlistBean> getMidadlist() {
        return midadlist;
    }

    public void setMidadlist(List<MidadlistBean> midadlist) {
        this.midadlist = midadlist;
    }

    public List<TopadlistBean> getTopadlist() {
        return topadlist;
    }

    public void setTopadlist(List<TopadlistBean> topadlist) {
        this.topadlist = topadlist;
    }

    public List<YjzdListBean> getYjzdList() {
        return yjzdList;
    }

    public void setYjzdList(List<YjzdListBean> yjzdList) {
        this.yjzdList = yjzdList;
    }

    public static class AdlistallBean implements Serializable{
        private String ad_title;
        private String picaddress;
        private String target_url;
        private String ad_target_url;

        public String getAd_title() {
            return ad_title;
        }

        public void setAd_title(String ad_title) {
            this.ad_title = ad_title;
        }

        public String getPicaddress() {
            return picaddress;
        }

        public void setPicaddress(String picaddress) {
            this.picaddress = picaddress;
        }

        public String getTarget_url() {
            return target_url;
        }

        public void setTarget_url(String target_url) {
            this.target_url = target_url;
        }

        public String getAd_target_url() {
            return ad_target_url;
        }

        public void setAd_target_url(String ad_target_url) {
            this.ad_target_url = ad_target_url;
        }
    }

    public static class TopadlistBean extends SimpleBannerInfo implements Serializable{
        private String ad_title;
        private String picaddress;
        private String target_url;
        private String ad_target_url;

        public String getAd_title() {
            return ad_title;
        }

        public void setAd_title(String ad_title) {
            this.ad_title = ad_title;
        }

        public String getPicaddress() {
            return picaddress;
        }

        public void setPicaddress(String picaddress) {
            this.picaddress = picaddress;
        }

        public String getTarget_url() {
            return target_url;
        }

        public void setTarget_url(String target_url) {
            this.target_url = target_url;
        }

        public String getAd_target_url() {
            return ad_target_url;
        }

        public void setAd_target_url(String ad_target_url) {
            this.ad_target_url = ad_target_url;
        }

        @Override
        public String getXBannerUrl() {
            return null;
        }
    }

    public static class MidadlistBean implements Serializable{
        private String ad_title;
        private String picaddress;
        private String target_url;
        private String ad_target_url;

        public String getAd_title() {
            return ad_title;
        }

        public void setAd_title(String ad_title) {
            this.ad_title = ad_title;
        }

        public String getPicaddress() {
            return picaddress;
        }

        public void setPicaddress(String picaddress) {
            this.picaddress = picaddress;
        }

        public String getTarget_url() {
            return target_url;
        }

        public void setTarget_url(String target_url) {
            this.target_url = target_url;
        }

        public String getAd_target_url() {
            return ad_target_url;
        }

        public void setAd_target_url(String ad_target_url) {
            this.ad_target_url = ad_target_url;
        }
    }

    public static class YjzdListBean implements Serializable, MultiItemEntity {
        private String keywords;
        private int companyId;
        public static final int GREEN = 1;
        public static final int RED = 2;
        public static final int BLUE = 3;
        private int itemType;

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
