package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvailableLabelResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("available_labels")
    @Expose
    private List<AvailableLabel> availableLabels = null;

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

    public List<AvailableLabel> getAvailableLabels() {
        return availableLabels;
    }

    public void setAvailableLabels(List<AvailableLabel> availableLabels) {
        this.availableLabels = availableLabels;
    }

    public static class AvailableLabel {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("series_id")
        @Expose
        private String seriesId;
        @SerializedName("type_of_label")
        @Expose
        private String typeOfLabel;
        @SerializedName("label_from")
        @Expose
        private String labelFrom;
        @SerializedName("label_to")
        @Expose
        private String labelTo;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("remaining_qty")
        @Expose
        private String remainingQty;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("series")
        @Expose
        private String series;

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

        public String getTypeOfLabel() {
            return typeOfLabel;
        }

        public void setTypeOfLabel(String typeOfLabel) {
            this.typeOfLabel = typeOfLabel;
        }

        public String getLabelFrom() {
            return labelFrom;
        }

        public void setLabelFrom(String labelFrom) {
            this.labelFrom = labelFrom;
        }

        public String getLabelTo() {
            return labelTo;
        }

        public void setLabelTo(String labelTo) {
            this.labelTo = labelTo;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getRemainingQty() {
            return remainingQty;
        }

        public void setRemainingQty(String remainingQty) {
            this.remainingQty = remainingQty;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

    }
}
