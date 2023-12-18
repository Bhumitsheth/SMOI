package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestLabelResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("request_labels")
    @Expose
    private List<RequestLabel> requestLabels = null;

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

    public List<RequestLabel> getRequestLabels() {
        return requestLabels;
    }

    public void setRequestLabels(List<RequestLabel> requestLabels) {
        this.requestLabels = requestLabels;
    }
    public static class RequestLabel {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("requested_to")
        @Expose
        private String requestedTo;
        @SerializedName("type_of_label")
        @Expose
        private String typeOfLabel;
        @SerializedName("required_label")
        @Expose
        private String requiredLabel;
        @SerializedName("requested_by")
        @Expose
        private String requestedBy;
        @SerializedName("request_status")
        @Expose
        private String requestStatus;
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
        @SerializedName("requested_to_name")
        @Expose
        private String requestedToName;
        @SerializedName("requested_by_name")
        @Expose
        private String requestedByName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRequestedTo() {
            return requestedTo;
        }

        public void setRequestedTo(String requestedTo) {
            this.requestedTo = requestedTo;
        }

        public String getTypeOfLabel() {
            return typeOfLabel;
        }

        public void setTypeOfLabel(String typeOfLabel) {
            this.typeOfLabel = typeOfLabel;
        }

        public String getRequiredLabel() {
            return requiredLabel;
        }

        public void setRequiredLabel(String requiredLabel) {
            this.requiredLabel = requiredLabel;
        }

        public String getRequestedBy() {
            return requestedBy;
        }

        public void setRequestedBy(String requestedBy) {
            this.requestedBy = requestedBy;
        }

        public String getRequestStatus() {
            return requestStatus;
        }

        public void setRequestStatus(String requestStatus) {
            this.requestStatus = requestStatus;
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

        public String getRequestedToName() {
            return requestedToName;
        }

        public void setRequestedToName(String requestedToName) {
            this.requestedToName = requestedToName;
        }

        public String getRequestedByName() {
            return requestedByName;
        }

        public void setRequestedByName(String requestedByName) {
            this.requestedByName = requestedByName;
        }

    }

}