package com.iipl.smoi.Model.OtherModel;

public class StallSelectionModel {

    String noOfStall,preferredStallNo,stallRent,gst,gstAmount,total;

    public StallSelectionModel(String noOfStall, String preferredStallNo, String stallRent, String gst, String gstAmount, String total) {
        this.noOfStall = noOfStall;
        this.preferredStallNo = preferredStallNo;
        this.stallRent = stallRent;
        this.gst = gst;
        this.gstAmount = gstAmount;
        this.total = total;
    }

    public String getNoOfStall() {
        return noOfStall;
    }

    public void setNoOfStall(String noOfStall) {
        this.noOfStall = noOfStall;
    }

    public String getPreferredStallNo() {
        return preferredStallNo;
    }

    public void setPreferredStallNo(String preferredStallNo) {
        this.preferredStallNo = preferredStallNo;
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
