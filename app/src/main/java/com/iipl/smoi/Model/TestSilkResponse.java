package com.iipl.smoi.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestSilkResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("how_to_test_silk")
    @Expose
    private List<HowToTestSilk> howToTestSilk = null;

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

    public List<HowToTestSilk> getHowToTestSilk() {
        return howToTestSilk;
    }

    public void setHowToTestSilk(List<HowToTestSilk> howToTestSilk) {
        this.howToTestSilk = howToTestSilk;
    }

    public class HowToTestSilk {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("how_to_test_silk")
        @Expose
        private String howToTestSilk;
        @SerializedName("how_to_test_silk_desc")
        @Expose
        private String howToTestSilkDesc;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHowToTestSilk() {
            return howToTestSilk;
        }

        public void setHowToTestSilk(String howToTestSilk) {
            this.howToTestSilk = howToTestSilk;
        }

        public String getHowToTestSilkDesc() {
            return howToTestSilkDesc;
        }

        public void setHowToTestSilkDesc(String howToTestSilkDesc) {
            this.howToTestSilkDesc = howToTestSilkDesc;
        }

    }

}
