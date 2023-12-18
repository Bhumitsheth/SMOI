package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("member")
    @Expose
    private List<Member> member = null;

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

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }


    public static class Member {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("middle_name")
        @Expose
        private String middleName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("role_id")
        @Expose
        private String roleId;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("verify_mob")
        @Expose
        private String verifyMob;
        @SerializedName("telephone1")
        @Expose
        private String telephone1;
        @SerializedName("telephone2")
        @Expose
        private String telephone2;
        @SerializedName("payment_type")
        @Expose
        private String paymentType;
        @SerializedName("payment_value")
        @Expose
        private String paymentValue;
        @SerializedName("area")
        @Expose
        private String area;
        @SerializedName("received_date")
        @Expose
        private String receivedDate;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("profile_image")
        @Expose
        private String profileImage;
        @SerializedName("status")
        @Expose
        private String status;
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
        @SerializedName("chapter_id")
        @Expose
        private String chapterId;
        @SerializedName("group_id")
        @Expose
        private String groupId;
        @SerializedName("product_category")
        @Expose
        private String productCategory;
        @SerializedName("trade_partner_id")
        @Expose
        private String tradePartnerId;
        @SerializedName("registration_date")
        @Expose
        private String registrationDate;
        @SerializedName("pincode")
        @Expose
        private String pincode;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("next_renewal_date")
        @Expose
        private String nextRenewalDate;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("member_type")
        @Expose
        private String memberType;
        @SerializedName("type_of_business")
        @Expose
        private String typeOfBusiness;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("assign_user")
        @Expose
        private String assignUser;
        @SerializedName("fax")
        @Expose
        private String fax;
        @SerializedName("designation_id")
        @Expose
        private String designationId;
        @SerializedName("contact_person")
        @Expose
        private String contactPerson;
        @SerializedName("contact_designation_id")
        @Expose
        private String contactDesignationId;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("silk_product")
        @Expose
        private String silkProduct;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("mr_title")
        @Expose
        private String mrTitle;
        @SerializedName("product_dealt")
        @Expose
        private String productDealt;
        @SerializedName("session_login")
        @Expose
        private String sessionLogin;
        @SerializedName("approved_status")
        @Expose
        private String approvedStatus;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("organization")
        @Expose
        private String organization;
        @SerializedName("reference_no")
        @Expose
        private String referenceNo;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("approved_date")
        @Expose
        private String approvedDate;
        @SerializedName("email_otp")
        @Expose
        private String emailOtp;
        @SerializedName("payment_status")
        @Expose
        private String paymentStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getVerifyMob() {
            return verifyMob;
        }

        public void setVerifyMob(String verifyMob) {
            this.verifyMob = verifyMob;
        }

        public String getTelephone1() {
            return telephone1;
        }

        public void setTelephone1(String telephone1) {
            this.telephone1 = telephone1;
        }

        public String getTelephone2() {
            return telephone2;
        }

        public void setTelephone2(String telephone2) {
            this.telephone2 = telephone2;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getPaymentValue() {
            return paymentValue;
        }

        public void setPaymentValue(String paymentValue) {
            this.paymentValue = paymentValue;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getReceivedDate() {
            return receivedDate;
        }

        public void setReceivedDate(String receivedDate) {
            this.receivedDate = receivedDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getChapterId() {
            return chapterId;
        }

        public void setChapterId(String chapterId) {
            this.chapterId = chapterId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(String productCategory) {
            this.productCategory = productCategory;
        }

        public String getTradePartnerId() {
            return tradePartnerId;
        }

        public void setTradePartnerId(String tradePartnerId) {
            this.tradePartnerId = tradePartnerId;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getNextRenewalDate() {
            return nextRenewalDate;
        }

        public void setNextRenewalDate(String nextRenewalDate) {
            this.nextRenewalDate = nextRenewalDate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMemberType() {
            return memberType;
        }

        public void setMemberType(String memberType) {
            this.memberType = memberType;
        }

        public String getTypeOfBusiness() {
            return typeOfBusiness;
        }

        public void setTypeOfBusiness(String typeOfBusiness) {
            this.typeOfBusiness = typeOfBusiness;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAssignUser() {
            return assignUser;
        }

        public void setAssignUser(String assignUser) {
            this.assignUser = assignUser;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getDesignationId() {
            return designationId;
        }

        public void setDesignationId(String designationId) {
            this.designationId = designationId;
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public String getContactDesignationId() {
            return contactDesignationId;
        }

        public void setContactDesignationId(String contactDesignationId) {
            this.contactDesignationId = contactDesignationId;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getSilkProduct() {
            return silkProduct;
        }

        public void setSilkProduct(String silkProduct) {
            this.silkProduct = silkProduct;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getMrTitle() {
            return mrTitle;
        }

        public void setMrTitle(String mrTitle) {
            this.mrTitle = mrTitle;
        }

        public String getProductDealt() {
            return productDealt;
        }

        public void setProductDealt(String productDealt) {
            this.productDealt = productDealt;
        }

        public String getSessionLogin() {
            return sessionLogin;
        }

        public void setSessionLogin(String sessionLogin) {
            this.sessionLogin = sessionLogin;
        }

        public String getApprovedStatus() {
            return approvedStatus;
        }

        public void setApprovedStatus(String approvedStatus) {
            this.approvedStatus = approvedStatus;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getReferenceNo() {
            return referenceNo;
        }

        public void setReferenceNo(String referenceNo) {
            this.referenceNo = referenceNo;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getApprovedDate() {
            return approvedDate;
        }

        public void setApprovedDate(String approvedDate) {
            this.approvedDate = approvedDate;
        }

        public String getEmailOtp() {
            return emailOtp;
        }

        public void setEmailOtp(String emailOtp) {
            this.emailOtp = emailOtp;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

    }
}