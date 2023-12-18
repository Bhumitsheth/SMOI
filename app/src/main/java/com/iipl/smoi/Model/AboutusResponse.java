package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AboutusResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("about_smoi")
    @Expose
    private List<AboutSmous> aboutSmoi = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AboutSmous> getAboutSmoi() {
        return aboutSmoi;
    }

    public void setAboutSmoi(List<AboutSmous> aboutSmoi) {
        this.aboutSmoi = aboutSmoi;
    }

    public static class AboutSmous {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("banner_image")
        @Expose
        private String bannerImage;
        @SerializedName("banner_title")
        @Expose
        private String bannerTitle;
        @SerializedName("banner_sub_title")
        @Expose
        private String bannerSubTitle;
        @SerializedName("about_title")
        @Expose
        private String aboutTitle;
        @SerializedName("about_img")
        @Expose
        private String aboutImg;
        @SerializedName("about_dec")
        @Expose
        private String aboutDec;
        @SerializedName("vission_mission_title")
        @Expose
        private String vissionMissionTitle;
        @SerializedName("vission_mission_dec")
        @Expose
        private String vissionMissionDec;
        @SerializedName("vission_mission_img")
        @Expose
        private String vissionMissionImg;
        @SerializedName("vission_mission_img_two")
        @Expose
        private String vissionMissionImgTwo;
        @SerializedName("vission_mission_img_three")
        @Expose
        private String vissionMissionImgThree;
        @SerializedName("title_one")
        @Expose
        private String titleOne;
        @SerializedName("dec_one")
        @Expose
        private String decOne;
        @SerializedName("title_two")
        @Expose
        private String titleTwo;
        @SerializedName("title_dec_two")
        @Expose
        private String titleDecTwo;
        @SerializedName("title_three")
        @Expose
        private String titleThree;
        @SerializedName("title_dec_three")
        @Expose
        private String titleDecThree;
        @SerializedName("title_for")
        @Expose
        private String titleFor;
        @SerializedName("title_dec_for")
        @Expose
        private String titleDecFor;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("updated_by")
        @Expose
        private String updatedBy;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_ip_address")
        @Expose
        private String createdIpAddress;
        @SerializedName("updated_ip_address")
        @Expose
        private String updatedIpAddress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public String getBannerTitle() {
            return bannerTitle;
        }

        public void setBannerTitle(String bannerTitle) {
            this.bannerTitle = bannerTitle;
        }

        public String getBannerSubTitle() {
            return bannerSubTitle;
        }

        public void setBannerSubTitle(String bannerSubTitle) {
            this.bannerSubTitle = bannerSubTitle;
        }

        public String getAboutTitle() {
            return aboutTitle;
        }

        public void setAboutTitle(String aboutTitle) {
            this.aboutTitle = aboutTitle;
        }

        public String getAboutImg() {
            return aboutImg;
        }

        public void setAboutImg(String aboutImg) {
            this.aboutImg = aboutImg;
        }

        public String getAboutDec() {
            return aboutDec;
        }

        public void setAboutDec(String aboutDec) {
            this.aboutDec = aboutDec;
        }

        public String getVissionMissionTitle() {
            return vissionMissionTitle;
        }

        public void setVissionMissionTitle(String vissionMissionTitle) {
            this.vissionMissionTitle = vissionMissionTitle;
        }

        public String getVissionMissionDec() {
            return vissionMissionDec;
        }

        public void setVissionMissionDec(String vissionMissionDec) {
            this.vissionMissionDec = vissionMissionDec;
        }

        public String getVissionMissionImg() {
            return vissionMissionImg;
        }

        public void setVissionMissionImg(String vissionMissionImg) {
            this.vissionMissionImg = vissionMissionImg;
        }

        public String getVissionMissionImgTwo() {
            return vissionMissionImgTwo;
        }

        public void setVissionMissionImgTwo(String vissionMissionImgTwo) {
            this.vissionMissionImgTwo = vissionMissionImgTwo;
        }

        public String getVissionMissionImgThree() {
            return vissionMissionImgThree;
        }

        public void setVissionMissionImgThree(String vissionMissionImgThree) {
            this.vissionMissionImgThree = vissionMissionImgThree;
        }

        public String getTitleOne() {
            return titleOne;
        }

        public void setTitleOne(String titleOne) {
            this.titleOne = titleOne;
        }

        public String getDecOne() {
            return decOne;
        }

        public void setDecOne(String decOne) {
            this.decOne = decOne;
        }

        public String getTitleTwo() {
            return titleTwo;
        }

        public void setTitleTwo(String titleTwo) {
            this.titleTwo = titleTwo;
        }

        public String getTitleDecTwo() {
            return titleDecTwo;
        }

        public void setTitleDecTwo(String titleDecTwo) {
            this.titleDecTwo = titleDecTwo;
        }

        public String getTitleThree() {
            return titleThree;
        }

        public void setTitleThree(String titleThree) {
            this.titleThree = titleThree;
        }

        public String getTitleDecThree() {
            return titleDecThree;
        }

        public void setTitleDecThree(String titleDecThree) {
            this.titleDecThree = titleDecThree;
        }

        public String getTitleFor() {
            return titleFor;
        }

        public void setTitleFor(String titleFor) {
            this.titleFor = titleFor;
        }

        public String getTitleDecFor() {
            return titleDecFor;
        }

        public void setTitleDecFor(String titleDecFor) {
            this.titleDecFor = titleDecFor;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedIpAddress() {
            return createdIpAddress;
        }

        public void setCreatedIpAddress(String createdIpAddress) {
            this.createdIpAddress = createdIpAddress;
        }

        public String getUpdatedIpAddress() {
            return updatedIpAddress;
        }

        public void setUpdatedIpAddress(String updatedIpAddress) {
            this.updatedIpAddress = updatedIpAddress;
        }

    }
}
