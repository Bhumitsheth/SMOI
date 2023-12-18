package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactListResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("contact")
    @Expose
    private List<Contact> contact = null;

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

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    public static class Contact {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("member_au_name")
        @Expose
        private String memberAuName;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;
        @SerializedName("office_phone")
        @Expose
        private String officePhone;
        @SerializedName("other_phone")
        @Expose
        private String otherPhone;
        @SerializedName("fax")
        @Expose
        private String fax;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("assign_user")
        @Expose
        private String assignUser;
        @SerializedName("support_start_date")
        @Expose
        private String supportStartDate;
        @SerializedName("support_end_date")
        @Expose
        private String supportEndDate;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("area")
        @Expose
        private String area;
        @SerializedName("pincode")
        @Expose
        private String pincode;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("contact_image")
        @Expose
        private String contactImage;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("updated_by")
        @Expose
        private String updatedBy;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getMemberAuName() {
            return memberAuName;
        }

        public void setMemberAuName(String memberAuName) {
            this.memberAuName = memberAuName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getOfficePhone() {
            return officePhone;
        }

        public void setOfficePhone(String officePhone) {
            this.officePhone = officePhone;
        }

        public String getOtherPhone() {
            return otherPhone;
        }

        public void setOtherPhone(String otherPhone) {
            this.otherPhone = otherPhone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAssignUser() {
            return assignUser;
        }

        public void setAssignUser(String assignUser) {
            this.assignUser = assignUser;
        }

        public String getSupportStartDate() {
            return supportStartDate;
        }

        public void setSupportStartDate(String supportStartDate) {
            this.supportStartDate = supportStartDate;
        }

        public String getSupportEndDate() {
            return supportEndDate;
        }

        public void setSupportEndDate(String supportEndDate) {
            this.supportEndDate = supportEndDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContactImage() {
            return contactImage;
        }

        public void setContactImage(String contactImage) {
            this.contactImage = contactImage;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

    }
}