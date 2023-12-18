package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaqsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("faq")
    @Expose
    private List<Faq> faq = null;

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

    public List<Faq> getFaq() {
        return faq;
    }

    public void setFaq(List<Faq> faq) {
        this.faq = faq;
    }


    public static class Faq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private Title title;
        @SerializedName("title_en")
        @Expose
        private String titleEn;
        @SerializedName("description")
        @Expose
        private Description description;
        @SerializedName("ordering")
        @Expose
        private String ordering;
        @SerializedName("slug")
        @Expose
        private String slug;
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

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public Description getDescription() {
            return description;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public String getOrdering() {
            return ordering;
        }

        public void setOrdering(String ordering) {
            this.ordering = ordering;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
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


        public class Description {

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

        public class Title {

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
