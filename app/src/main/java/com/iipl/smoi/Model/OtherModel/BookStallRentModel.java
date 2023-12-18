package com.iipl.smoi.Model.OtherModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookStallRentModel implements Serializable {

    String stallNoFrom,getStallNoTo,gstPer,stallRent,gstAmount,total;

    public BookStallRentModel(String stallNoFrom, String getStallNoTo, String gstPer, String stallRent, String gstAmount, String total) {
        this.stallNoFrom = stallNoFrom;
        this.getStallNoTo = getStallNoTo;
        this.gstPer = gstPer;
        this.stallRent = stallRent;
        this.gstAmount = gstAmount;
        this.total = total;
    }

    public String getStallNoFrom() {
        return stallNoFrom;
    }

    public void setStallNoFrom(String stallNoFrom) {
        this.stallNoFrom = stallNoFrom;
    }

    public String getGetStallNoTo() {
        return getStallNoTo;
    }

    public void setGetStallNoTo(String getStallNoTo) {
        this.getStallNoTo = getStallNoTo;
    }

    public String getGstPer() {
        return gstPer;
    }

    public void setGstPer(String gstPer) {
        this.gstPer = gstPer;
    }

    public String getStallRent() {
        return stallRent;
    }

    public void setStallRent(String stallRent) {
        this.stallRent = stallRent;
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
