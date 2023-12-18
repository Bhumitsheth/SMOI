package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpoListResponse {

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
        @SerializedName("title")
        @Expose
        private Title title;
        @SerializedName("title_en")
        @Expose
        private String titleEn;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("cat_order")
        @Expose
        private String catOrder;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("desc")
        @Expose
        private Desc desc;
        @SerializedName("stall_no")
        @Expose
        private String stallNo;
        @SerializedName("booking_date")
        @Expose
        private String bookingDate;
        @SerializedName("contact_person")
        @Expose
        private String contactPerson;
        @SerializedName("contact_no")
        @Expose
        private String contactNo;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("area")
        @Expose
        private String area;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("pincode")
        @Expose
        private String pincode;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("approved_by")
        @Expose
        private String approvedBy;
        @SerializedName("approved_status")
        @Expose
        private String approvedStatus;
        @SerializedName("approved_date")
        @Expose
        private String approvedDate;
        @SerializedName("reason")
        @Expose
        private String reason;
        @SerializedName("pay_status")
        @Expose
        private String payStatus;
        @SerializedName("pay_date")
        @Expose
        private String payDate;
        @SerializedName("initial_amount")
        @Expose
        private String initialAmount;
        @SerializedName("final_amount")
        @Expose
        private String finalAmount;
        @SerializedName("expo_startdate")
        @Expose
        private String expoStartdate;
        @SerializedName("expo_enddate")
        @Expose
        private String expoEnddate;
        @SerializedName("available_stalls")
        @Expose
        private String availableStalls;
        @SerializedName("stall_size")
        @Expose
        private String stallSize;
        @SerializedName("chapter_address")
        @Expose
        private String chapterAddress;
        @SerializedName("chapter_area")
        @Expose
        private String chapterArea;
        @SerializedName("chapter_country")
        @Expose
        private String chapterCountry;
        @SerializedName("chapter_state")
        @Expose
        private String chapterState;
        @SerializedName("chapter_city")
        @Expose
        private String chapterCity;
        @SerializedName("chapter_pincode")
        @Expose
        private String chapterPincode;
        @SerializedName("smoi_letter_no")
        @Expose
        private String smoiLetterNo;
        @SerializedName("stall_no_from")
        @Expose
        private String stallNoFrom;
        @SerializedName("stall_no_to")
        @Expose
        private String stallNoTo;
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
        @SerializedName("floor_plan")
        @Expose
        private String floorPlan;
        @SerializedName("police_station_details")
        @Expose
        private String policeStationDetails;
        @SerializedName("fire_station_details")
        @Expose
        private String fireStationDetails;
        @SerializedName("income_tax_office_details")
        @Expose
        private String incomeTaxOfficeDetails;
        @SerializedName("local_municipal_corporation_details")
        @Expose
        private String localMunicipalCorporationDetails;
        @SerializedName("insurance_company_details")
        @Expose
        private String insuranceCompanyDetails;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("updated_by")
        @Expose
        private String updatedBy;
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

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCatOrder() {
            return catOrder;
        }

        public void setCatOrder(String catOrder) {
            this.catOrder = catOrder;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Desc getDesc() {
            return desc;
        }

        public void setDesc(Desc desc) {
            this.desc = desc;
        }

        public String getStallNo() {
            return stallNo;
        }

        public void setStallNo(String stallNo) {
            this.stallNo = stallNo;
        }

        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(String approvedBy) {
            this.approvedBy = approvedBy;
        }

        public String getApprovedStatus() {
            return approvedStatus;
        }

        public void setApprovedStatus(String approvedStatus) {
            this.approvedStatus = approvedStatus;
        }

        public String getApprovedDate() {
            return approvedDate;
        }

        public void setApprovedDate(String approvedDate) {
            this.approvedDate = approvedDate;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getInitialAmount() {
            return initialAmount;
        }

        public void setInitialAmount(String initialAmount) {
            this.initialAmount = initialAmount;
        }

        public String getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(String finalAmount) {
            this.finalAmount = finalAmount;
        }

        public String getExpoStartdate() {
            return expoStartdate;
        }

        public void setExpoStartdate(String expoStartdate) {
            this.expoStartdate = expoStartdate;
        }

        public String getExpoEnddate() {
            return expoEnddate;
        }

        public void setExpoEnddate(String expoEnddate) {
            this.expoEnddate = expoEnddate;
        }

        public String getAvailableStalls() {
            return availableStalls;
        }

        public void setAvailableStalls(String availableStalls) {
            this.availableStalls = availableStalls;
        }

        public String getStallSize() {
            return stallSize;
        }

        public void setStallSize(String stallSize) {
            this.stallSize = stallSize;
        }

        public String getChapterAddress() {
            return chapterAddress;
        }

        public void setChapterAddress(String chapterAddress) {
            this.chapterAddress = chapterAddress;
        }

        public String getChapterArea() {
            return chapterArea;
        }

        public void setChapterArea(String chapterArea) {
            this.chapterArea = chapterArea;
        }

        public String getChapterCountry() {
            return chapterCountry;
        }

        public void setChapterCountry(String chapterCountry) {
            this.chapterCountry = chapterCountry;
        }

        public String getChapterState() {
            return chapterState;
        }

        public void setChapterState(String chapterState) {
            this.chapterState = chapterState;
        }

        public String getChapterCity() {
            return chapterCity;
        }

        public void setChapterCity(String chapterCity) {
            this.chapterCity = chapterCity;
        }

        public String getChapterPincode() {
            return chapterPincode;
        }

        public void setChapterPincode(String chapterPincode) {
            this.chapterPincode = chapterPincode;
        }

        public String getSmoiLetterNo() {
            return smoiLetterNo;
        }

        public void setSmoiLetterNo(String smoiLetterNo) {
            this.smoiLetterNo = smoiLetterNo;
        }

        public String getStallNoFrom() {
            return stallNoFrom;
        }

        public void setStallNoFrom(String stallNoFrom) {
            this.stallNoFrom = stallNoFrom;
        }

        public String getStallNoTo() {
            return stallNoTo;
        }

        public void setStallNoTo(String stallNoTo) {
            this.stallNoTo = stallNoTo;
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

        public String getFloorPlan() {
            return floorPlan;
        }

        public void setFloorPlan(String floorPlan) {
            this.floorPlan = floorPlan;
        }

        public String getPoliceStationDetails() {
            return policeStationDetails;
        }

        public void setPoliceStationDetails(String policeStationDetails) {
            this.policeStationDetails = policeStationDetails;
        }

        public String getFireStationDetails() {
            return fireStationDetails;
        }

        public void setFireStationDetails(String fireStationDetails) {
            this.fireStationDetails = fireStationDetails;
        }

        public String getIncomeTaxOfficeDetails() {
            return incomeTaxOfficeDetails;
        }

        public void setIncomeTaxOfficeDetails(String incomeTaxOfficeDetails) {
            this.incomeTaxOfficeDetails = incomeTaxOfficeDetails;
        }

        public String getLocalMunicipalCorporationDetails() {
            return localMunicipalCorporationDetails;
        }

        public void setLocalMunicipalCorporationDetails(String localMunicipalCorporationDetails) {
            this.localMunicipalCorporationDetails = localMunicipalCorporationDetails;
        }

        public String getInsuranceCompanyDetails() {
            return insuranceCompanyDetails;
        }

        public void setInsuranceCompanyDetails(String insuranceCompanyDetails) {
            this.insuranceCompanyDetails = insuranceCompanyDetails;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
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

        public class Desc {

            @SerializedName("en")
            @Expose
            private String en;
            @SerializedName("hi")
            @Expose
            private String hi;

            public String getEn() {
                return en;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public String getHi() {
                return hi;
            }

            public void setHi(String hi) {
                this.hi = hi;
            }

        }
        public class Title {

            @SerializedName("en")
            @Expose
            private String en;
            @SerializedName("hi")
            @Expose
            private String hi;

            public String getEn() {
                return en;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public String getHi() {
                return hi;
            }

            public void setHi(String hi) {
                this.hi = hi;
            }

        }

    }
}
