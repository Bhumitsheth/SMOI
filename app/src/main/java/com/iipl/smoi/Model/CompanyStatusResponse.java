package com.iipl.smoi.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyStatusResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("type_of_business")
    @Expose
    private List<TypeOfBusiness> typeOfBusiness;

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

    public List<TypeOfBusiness> getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(List<TypeOfBusiness> typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }
    public static class TypeOfBusiness {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type_of_business")
        @Expose
        private String typeOfBusiness;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeOfBusiness() {
            return typeOfBusiness;
        }

        public void setTypeOfBusiness(String typeOfBusiness) {
            this.typeOfBusiness = typeOfBusiness;
        }

    }
}
