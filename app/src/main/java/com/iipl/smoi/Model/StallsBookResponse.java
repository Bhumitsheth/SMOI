package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StallsBookResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("expo_bookings")
    @Expose
    private List<ExpoBooking> expoBookings;

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

    public List<ExpoBooking> getExpoBookings() {
        return expoBookings;
    }

    public void setExpoBookings(List<ExpoBooking> expoBookings) {
        this.expoBookings = expoBookings;
    }

    public static class ExpoBooking {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title_en")
        @Expose
        private String titleEn;
        @SerializedName("available_stalls")
        @Expose
        private String availableStalls;
        @SerializedName("remaining_stalls")
        @Expose
        private List<RemainingStall> remainingStalls;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public String getAvailableStalls() {
            return availableStalls;
        }

        public void setAvailableStalls(String availableStalls) {
            this.availableStalls = availableStalls;
        }

        public List<RemainingStall> getRemainingStalls() {
            return remainingStalls;
        }

        public void setRemainingStalls(List<RemainingStall> remainingStalls) {
            this.remainingStalls = remainingStalls;
        }


        public static class RemainingStall {

            @SerializedName("stall_no")
            @Expose
            private String stallNo;
            @SerializedName("stall_rent")
            @Expose
            private String stallRent;
            @SerializedName("gst")
            @Expose
            private String gst;
            @SerializedName("gst_amount")
            @Expose
            private String gstAmount;
            @SerializedName("total")
            @Expose
            private String total;

            public String getStallNo() {
                return stallNo;
            }

            public void setStallNo(String stallNo) {
                this.stallNo = stallNo;
            }

            public String getStallRent() {
                return stallRent;
            }

            public void setStallRent(String stallRent) {
                this.stallRent = stallRent;
            }

            public String getGst() {
                return gst;
            }

            public void setGst(String gst) {
                this.gst = gst;
            }

            public String getGstAmount() {
                return gstAmount;
            }

            public void setGstAmount(String gstAmount) {
                this.gstAmount = gstAmount;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

        }


    }

}
