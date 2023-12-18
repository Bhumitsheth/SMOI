package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlourishListResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("flourish_master")
    @Expose
    private List<FlourishMaster> flourishMaster = null;

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

    public List<FlourishMaster> getFlourishMaster() {
        return flourishMaster;
    }

    public void setFlourishMaster(List<FlourishMaster> flourishMaster) {
        this.flourishMaster = flourishMaster;
    }
    public static class FlourishMaster {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("topic")
        @Expose
        private String topic;
        @SerializedName("des")
        @Expose
        private String des;
        @SerializedName("document_type")
        @Expose
        private List<String> documentType = null;
        @SerializedName("event_place")
        @Expose
        private String eventPlace;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("Submissiontime")
        @Expose
        private String submissiontime;
        @SerializedName("chapter")
        @Expose
        private List<String> chapter = null;
        @SerializedName("scheduleddate")
        @Expose
        private String scheduleddate;
        @SerializedName("scheduledtime")
        @Expose
        private String scheduledtime;
        @SerializedName("status")
        @Expose
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public List<String> getDocumentType() {
            return documentType;
        }

        public void setDocumentType(List<String> documentType) {
            this.documentType = documentType;
        }

        public String getEventPlace() {
            return eventPlace;
        }

        public void setEventPlace(String eventPlace) {
            this.eventPlace = eventPlace;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSubmissiontime() {
            return submissiontime;
        }

        public void setSubmissiontime(String submissiontime) {
            this.submissiontime = submissiontime;
        }

        public List<String> getChapter() {
            return chapter;
        }

        public void setChapter(List<String> chapter) {
            this.chapter = chapter;
        }

        public String getScheduleddate() {
            return scheduleddate;
        }

        public void setScheduleddate(String scheduleddate) {
            this.scheduleddate = scheduleddate;
        }

        public String getScheduledtime() {
            return scheduledtime;
        }

        public void setScheduledtime(String scheduledtime) {
            this.scheduledtime = scheduledtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}

