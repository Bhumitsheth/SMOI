package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DistributeLabelResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("distributed_labels")
    @Expose
    private List<DistributedLabel> distributedLabels = null;

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

    public List<DistributedLabel> getDistributedLabels() {
        return distributedLabels;
    }

    public void setDistributedLabels(List<DistributedLabel> distributedLabels) {
        this.distributedLabels = distributedLabels;
    }


    public static class DistributedLabel {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("series_id")
        @Expose
        private String seriesId;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("chapter_user_list")
        @Expose
        private String chapterUserList;
        @SerializedName("user_list")
        @Expose
        private String userList;
        @SerializedName("label_ids")
        @Expose
        private String labelIds;
        @SerializedName("distributed_label_id")
        @Expose
        private String distributedLabelId;
        @SerializedName("total_qty")
        @Expose
        private String totalQty;
        @SerializedName("req_label")
        @Expose
        private String reqLabel;
        @SerializedName("distributed_label_from")
        @Expose
        private String distributedLabelFrom;
        @SerializedName("distributed_label_to")
        @Expose
        private String distributedLabelTo;
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
        @SerializedName("series_name")
        @Expose
        private String seriesName;
        @SerializedName("distributed_to")
        @Expose
        private String distributedTo;
        @SerializedName("distributed_from")
        @Expose
        private String distributedFrom;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("distributed_on")
        @Expose
        private String distributedOn;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(String seriesId) {
            this.seriesId = seriesId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getChapterUserList() {
            return chapterUserList;
        }

        public void setChapterUserList(String chapterUserList) {
            this.chapterUserList = chapterUserList;
        }

        public String getUserList() {
            return userList;
        }

        public void setUserList(String userList) {
            this.userList = userList;
        }

        public String getLabelIds() {
            return labelIds;
        }

        public void setLabelIds(String labelIds) {
            this.labelIds = labelIds;
        }

        public String getDistributedLabelId() {
            return distributedLabelId;
        }

        public void setDistributedLabelId(String distributedLabelId) {
            this.distributedLabelId = distributedLabelId;
        }

        public String getTotalQty() {
            return totalQty;
        }

        public void setTotalQty(String totalQty) {
            this.totalQty = totalQty;
        }

        public String getReqLabel() {
            return reqLabel;
        }

        public void setReqLabel(String reqLabel) {
            this.reqLabel = reqLabel;
        }

        public String getDistributedLabelFrom() {
            return distributedLabelFrom;
        }

        public void setDistributedLabelFrom(String distributedLabelFrom) {
            this.distributedLabelFrom = distributedLabelFrom;
        }

        public String getDistributedLabelTo() {
            return distributedLabelTo;
        }

        public void setDistributedLabelTo(String distributedLabelTo) {
            this.distributedLabelTo = distributedLabelTo;
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

        public String getSeriesName() {
            return seriesName;
        }

        public void setSeriesName(String seriesName) {
            this.seriesName = seriesName;
        }

        public String getDistributedTo() {
            return distributedTo;
        }

        public void setDistributedTo(String distributedTo) {
            this.distributedTo = distributedTo;
        }

        public String getDistributedFrom() {
            return distributedFrom;
        }

        public void setDistributedFrom(String distributedFrom) {
            this.distributedFrom = distributedFrom;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getDistributedOn() {
            return distributedOn;
        }

        public void setDistributedOn(String distributedOn) {
            this.distributedOn = distributedOn;
        }

    }

}
