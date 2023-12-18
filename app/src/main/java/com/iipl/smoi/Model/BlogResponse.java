package com.iipl.smoi.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BlogResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("blogs")
    @Expose
    private List<Blog> blogs = null;

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

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }


    public static class Blog {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("blog_title")
        @Expose
        private BlogTitle blogTitle;
        @SerializedName("catagory_id")
        @Expose
        private Object catagoryId;
        @SerializedName("catagory_name")
        @Expose
        private Object catagoryName;
        @SerializedName("blog_title_en")
        @Expose
        private String blogTitleEn;
        @SerializedName("ordering")
        @Expose
        private String ordering;
        @SerializedName("blog_description")
        @Expose
        private BlogDescription blogDescription;
        @SerializedName("blog_image")
        @Expose
        private String blogImage;
        @SerializedName("blog_published_date")
        @Expose
        private String blogPublishedDate;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("intro_content")
        @Expose
        private String introContent;
        @SerializedName("blog_like")
        @Expose
        private String blogLike;
        @SerializedName("blog_view")
        @Expose
        private String blogView;
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
        @SerializedName("blog_comments")
        @Expose
        private Integer blogComments;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public BlogTitle getBlogTitle() {
            return blogTitle;
        }

        public void setBlogTitle(BlogTitle blogTitle) {
            this.blogTitle = blogTitle;
        }

        public Object getCatagoryId() {
            return catagoryId;
        }

        public void setCatagoryId(Object catagoryId) {
            this.catagoryId = catagoryId;
        }

        public Object getCatagoryName() {
            return catagoryName;
        }

        public void setCatagoryName(Object catagoryName) {
            this.catagoryName = catagoryName;
        }

        public String getBlogTitleEn() {
            return blogTitleEn;
        }

        public void setBlogTitleEn(String blogTitleEn) {
            this.blogTitleEn = blogTitleEn;
        }

        public String getOrdering() {
            return ordering;
        }

        public void setOrdering(String ordering) {
            this.ordering = ordering;
        }

        public BlogDescription getBlogDescription() {
            return blogDescription;
        }

        public void setBlogDescription(BlogDescription blogDescription) {
            this.blogDescription = blogDescription;
        }

        public String getBlogImage() {
            return blogImage;
        }

        public void setBlogImage(String blogImage) {
            this.blogImage = blogImage;
        }

        public String getBlogPublishedDate() {
            return blogPublishedDate;
        }

        public void setBlogPublishedDate(String blogPublishedDate) {
            this.blogPublishedDate = blogPublishedDate;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getIntroContent() {
            return introContent;
        }

        public void setIntroContent(String introContent) {
            this.introContent = introContent;
        }

        public String getBlogLike() {
            return blogLike;
        }

        public void setBlogLike(String blogLike) {
            this.blogLike = blogLike;
        }

        public String getBlogView() {
            return blogView;
        }

        public void setBlogView(String blogView) {
            this.blogView = blogView;
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

        public Integer getBlogComments() {
            return blogComments;
        }

        public void setBlogComments(Integer blogComments) {
            this.blogComments = blogComments;
        }


        public class BlogDescription {

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

        public class BlogTitle {

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