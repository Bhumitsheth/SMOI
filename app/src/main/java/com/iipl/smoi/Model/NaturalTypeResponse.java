package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NaturalTypeResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("natural")
    @Expose
    private List<Natural> natural = null;

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

    public List<Natural> getNatural() {
        return natural;
    }

    public void setNatural(List<Natural> natural) {
        this.natural = natural;
    }

    public static class Natural {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type_id")
        @Expose
        private String typeId;
        @SerializedName("dep_name")
        @Expose
        private String depName;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("des")
        @Expose
        private String des;
        @SerializedName("galleryimage")
        @Expose
        private String galleryimage;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getGalleryimage() {
            return galleryimage;
        }

        public void setGalleryimage(String galleryimage) {
            this.galleryimage = galleryimage;
        }

    }

}
