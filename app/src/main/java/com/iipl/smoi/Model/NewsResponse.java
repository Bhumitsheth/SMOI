package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("news")
    @Expose
    private List<News> news = null;

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

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }


    public static class News {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("tag")
        @Expose
        private String tag;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("news_title_name")
        @Expose
        private NewsTitleName newsTitleName;
        @SerializedName("news_title_name_en")
        @Expose
        private String newsTitleNameEn;
        @SerializedName("catagory")
        @Expose
        private String catagory;
        @SerializedName("ordering")
        @Expose
        private String ordering;
        @SerializedName("galleryimage")
        @Expose
        private String galleryimage;
        @SerializedName("news_description")
        @Expose
        private NewsDescription newsDescription;
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

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public NewsTitleName getNewsTitleName() {
            return newsTitleName;
        }

        public void setNewsTitleName(NewsTitleName newsTitleName) {
            this.newsTitleName = newsTitleName;
        }

        public String getNewsTitleNameEn() {
            return newsTitleNameEn;
        }

        public void setNewsTitleNameEn(String newsTitleNameEn) {
            this.newsTitleNameEn = newsTitleNameEn;
        }

        public String getCatagory() {
            return catagory;
        }

        public void setCatagory(String catagory) {
            this.catagory = catagory;
        }

        public String getOrdering() {
            return ordering;
        }

        public void setOrdering(String ordering) {
            this.ordering = ordering;
        }

        public String getGalleryimage() {
            return galleryimage;
        }

        public void setGalleryimage(String galleryimage) {
            this.galleryimage = galleryimage;
        }

        public NewsDescription getNewsDescription() {
            return newsDescription;
        }

        public void setNewsDescription(NewsDescription newsDescription) {
            this.newsDescription = newsDescription;
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

        public static class NewsDescription {

            @SerializedName("en")
            @Expose
            private String en;
            @SerializedName("hi")
            @Expose
            private String hi;

            public String getEn() {
                return en;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public String getHi() {
                return hi;
            }

            public void setHi(String hi) {
                this.hi = hi;
            }

        }

        public static class NewsTitleName {

            @SerializedName("en")
            @Expose
            private String en;
            @SerializedName("hi")
            @Expose
            private String hi;

            public String getEn() {
                return en;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public String getHi() {
                return hi;
            }

            public void setHi(String hi) {
                this.hi = hi;
            }

        }

    }



}
