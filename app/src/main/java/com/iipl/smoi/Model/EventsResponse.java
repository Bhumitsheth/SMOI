package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;

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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    public static class Event {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("title_en")
        @Expose
        private String titleEn;
        @SerializedName("cat_order")
        @Expose
        private String catOrder;
        @SerializedName("event_date")
        @Expose
        private String eventDate;
        @SerializedName("event_status")
        @Expose
        private String eventStatus;
        @SerializedName("galleryimage")
        @Expose
        private String galleryimage;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("desc")
        @Expose
        private String desc;
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
        @SerializedName("date")
        @Expose
        private Object date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public String getCatOrder() {
            return catOrder;
        }

        public void setCatOrder(String catOrder) {
            this.catOrder = catOrder;
        }

        public String getEventDate() {
            return eventDate;
        }

        public void setEventDate(String eventDate) {
            this.eventDate = eventDate;
        }

        public String getEventStatus() {
            return eventStatus;
        }

        public void setEventStatus(String eventStatus) {
            this.eventStatus = eventStatus;
        }

        public String getGalleryimage() {
            return galleryimage;
        }

        public void setGalleryimage(String galleryimage) {
            this.galleryimage = galleryimage;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
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

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

    }

}
