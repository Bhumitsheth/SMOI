package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TypeOfLabelsResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("type_of_label")
    @Expose
    private List<TypeOfLabelsResponse.TypeOfLabel> typeOfLabel = null;

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

    public List<TypeOfLabelsResponse.TypeOfLabel> getTypeOfLabel() {
        return typeOfLabel;
    }

    public void setTypeOfLabel(List<TypeOfLabelsResponse.TypeOfLabel> typeOfLabel) {
        this.typeOfLabel = typeOfLabel;
    }

    public static class TypeOfLabel {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type_of_label")
        @Expose
        private String typeOfLabel;
        @SerializedName("hsn_code")
        @Expose
        private String hsnCode;
        @SerializedName("unit_cost")
        @Expose
        private String unitCost;
        @SerializedName("sgst")
        @Expose
        private String sgst;
        @SerializedName("cgst")
        @Expose
        private String cgst;
        @SerializedName("igst")
        @Expose
        private String igst;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_by")
        @Expose
        private Object createdBy;
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
        private Object createdIpAddress;
        @SerializedName("updated_ip_address")
        @Expose
        private String updatedIpAddress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeOfLabel() {
            return typeOfLabel;
        }

        public void setTypeOfLabel(String typeOfLabel) {
            this.typeOfLabel = typeOfLabel;
        }

        public String getHsnCode() {
            return hsnCode;
        }

        public void setHsnCode(String hsnCode) {
            this.hsnCode = hsnCode;
        }

        public String getUnitCost() {
            return unitCost;
        }

        public void setUnitCost(String unitCost) {
            this.unitCost = unitCost;
        }

        public String getSgst() {
            return sgst;
        }

        public void setSgst(String sgst) {
            this.sgst = sgst;
        }

        public String getCgst() {
            return cgst;
        }

        public void setCgst(String cgst) {
            this.cgst = cgst;
        }

        public String getIgst() {
            return igst;
        }

        public void setIgst(String igst) {
            this.igst = igst;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
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

        public Object getCreatedIpAddress() {
            return createdIpAddress;
        }

        public void setCreatedIpAddress(Object createdIpAddress) {
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
