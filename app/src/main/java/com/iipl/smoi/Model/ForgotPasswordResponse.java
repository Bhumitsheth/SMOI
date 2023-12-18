package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ForgotPasswordResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("otp_info")
    @Expose
    private List<OtpInfo> otpInfo;

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

    public List<OtpInfo> getOtpInfo() {
        return otpInfo;
    }

    public void setOtpInfo(List<OtpInfo> otpInfo) {
        this.otpInfo = otpInfo;
    }

    public class OtpInfo {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("forgot_otp")
        @Expose
        private String forgotOtp;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getForgotOtp() {
            return forgotOtp;
        }

        public void setForgotOtp(String forgotOtp) {
            this.forgotOtp = forgotOtp;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

    }
}

