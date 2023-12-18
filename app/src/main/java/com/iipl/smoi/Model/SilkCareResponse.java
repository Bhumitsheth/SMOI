package com.iipl.smoi.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class SilkCareResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Test Center")
    @Expose
    private List<TestCenter> testCenter = null;

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

    public List<TestCenter> getTestCenter() {
        return testCenter;
    }

    public void setTestCenter(List<TestCenter> testCenter) {
        this.testCenter = testCenter;
    }

    public class TestCenter {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("test_image_1")
        @Expose
        private String testImage1;
        @SerializedName("test_image_2")
        @Expose
        private String testImage2;
        @SerializedName("test_image_3")
        @Expose
        private String testImage3;
        @SerializedName("test_image_4")
        @Expose
        private String testImage4;
        @SerializedName("test_image_5")
        @Expose
        private String testImage5;
        @SerializedName("test_image_6")
        @Expose
        private String testImage6;
        @SerializedName("test_image_7")
        @Expose
        private String testImage7;
        @SerializedName("test_image_8")
        @Expose
        private String testImage8;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

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

        public String getTestImage1() {
            return testImage1;
        }

        public void setTestImage1(String testImage1) {
            this.testImage1 = testImage1;
        }

        public String getTestImage2() {
            return testImage2;
        }

        public void setTestImage2(String testImage2) {
            this.testImage2 = testImage2;
        }

        public String getTestImage3() {
            return testImage3;
        }

        public void setTestImage3(String testImage3) {
            this.testImage3 = testImage3;
        }

        public String getTestImage4() {
            return testImage4;
        }

        public void setTestImage4(String testImage4) {
            this.testImage4 = testImage4;
        }

        public String getTestImage5() {
            return testImage5;
        }

        public void setTestImage5(String testImage5) {
            this.testImage5 = testImage5;
        }

        public String getTestImage6() {
            return testImage6;
        }

        public void setTestImage6(String testImage6) {
            this.testImage6 = testImage6;
        }

        public String getTestImage7() {
            return testImage7;
        }

        public void setTestImage7(String testImage7) {
            this.testImage7 = testImage7;
        }

        public String getTestImage8() {
            return testImage8;
        }

        public void setTestImage8(String testImage8) {
            this.testImage8 = testImage8;
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

    }

}
