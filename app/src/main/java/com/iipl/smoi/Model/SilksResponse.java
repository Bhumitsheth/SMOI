package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SilksResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("info")
    @Expose
    private List<Info> info = null;

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

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public static class Info {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("top_banner")
        @Expose
        private String topBanner;
        @SerializedName("top_banner_title")
        @Expose
        private String topBannerTitle;
        @SerializedName("top_banner_des_one")
        @Expose
        private String topBannerDesOne;
        @SerializedName("advantages_title")
        @Expose
        private String advantagesTitle;
        @SerializedName("objective_title_one")
        @Expose
        private String objectiveTitleOne;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("updated_by")
        @Expose
        private String updatedBy;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTopBanner() {
            return topBanner;
        }

        public void setTopBanner(String topBanner) {
            this.topBanner = topBanner;
        }

        public String getTopBannerTitle() {
            return topBannerTitle;
        }

        public void setTopBannerTitle(String topBannerTitle) {
            this.topBannerTitle = topBannerTitle;
        }

        public String getTopBannerDesOne() {
            return topBannerDesOne;
        }

        public void setTopBannerDesOne(String topBannerDesOne) {
            this.topBannerDesOne = topBannerDesOne;
        }

        public String getAdvantagesTitle() {
            return advantagesTitle;
        }

        public void setAdvantagesTitle(String advantagesTitle) {
            this.advantagesTitle = advantagesTitle;
        }

        public String getObjectiveTitleOne() {
            return objectiveTitleOne;
        }

        public void setObjectiveTitleOne(String objectiveTitleOne) {
            this.objectiveTitleOne = objectiveTitleOne;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

    }

}