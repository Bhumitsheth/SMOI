package com.iipl.smoi.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChapterResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("chapter")
    @Expose
    private List<Chapter> chapter = null;

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

    public List<Chapter> getChapter() {
        return chapter;
    }

    public void setChapter(List<Chapter> chapter) {
        this.chapter = chapter;
    }


    public static class Chapter {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("middle_name")
        @Expose
        private String middleName;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("role_id")
        @Expose
        private String roleId;
        @SerializedName("module_permission")
        @Expose
        private String modulePermission;
        @SerializedName("last_name")
        @Expose
        private String lastName;
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
        private Object telephone1;
        @SerializedName("telephone2")
        @Expose
        private Object telephone2;
        @SerializedName("payment_type")
        @Expose
        private Object paymentType;
        @SerializedName("payment_value")
        @Expose
        private Object paymentValue;
        @SerializedName("area")
        @Expose
        private String area;
        @SerializedName("received_date")
        @Expose
        private Object receivedDate;
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
        private Object createdBy;
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
        private Object chapterId;
        @SerializedName("group_id")
        @Expose
        private Object groupId;
        @SerializedName("product_category")
        @Expose
        private Object productCategory;
        @SerializedName("trade_partner_id")
        @Expose
        private Object tradePartnerId;
        @SerializedName("registration_date")
        @Expose
        private Object registrationDate;
        @SerializedName("pincode")
        @Expose
        private String pincode;
        @SerializedName("website")
        @Expose
        private Object website;
        @SerializedName("next_renewal_date")
        @Expose
        private Object nextRenewalDate;
        @SerializedName("amount")
        @Expose
        private Object amount;
        @SerializedName("member_type")
        @Expose
        private Object memberType;
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
        private Object fax;
        @SerializedName("designation_id")
        @Expose
        private String designationId;
        @SerializedName("contact_person")
        @Expose
        private Object contactPerson;
        @SerializedName("contact_designation_id")
        @Expose
        private Object contactDesignationId;
        @SerializedName("country")
        @Expose
        private Object country;
        @SerializedName("branch_name")
        @Expose
        private Object branchName;
        @SerializedName("branch_address")
        @Expose
        private Object branchAddress;
        @SerializedName("branch_area")
        @Expose
        private Object branchArea;
        @SerializedName("branch_country")
        @Expose
        private Object branchCountry;
        @SerializedName("branch_state")
        @Expose
        private Object branchState;
        @SerializedName("branch_city")
        @Expose
        private Object branchCity;
        @SerializedName("branch_pincode")
        @Expose
        private Object branchPincode;
        @SerializedName("silk_product")
        @Expose
        private String silkProduct;
        @SerializedName("unit_branch_name")
        @Expose
        private Object unitBranchName;
        @SerializedName("unit_address")
        @Expose
        private Object unitAddress;
        @SerializedName("unit_area")
        @Expose
        private Object unitArea;
        @SerializedName("unit_country")
        @Expose
        private Object unitCountry;
        @SerializedName("unit_state")
        @Expose
        private Object unitState;
        @SerializedName("unit_city")
        @Expose
        private Object unitCity;
        @SerializedName("unit_pincode")
        @Expose
        private Object unitPincode;
        @SerializedName("unit_mobile_no")
        @Expose
        private Object unitMobileNo;
        @SerializedName("unit_telephone1")
        @Expose
        private Object unitTelephone1;
        @SerializedName("unit_type_of_business")
        @Expose
        private Object unitTypeOfBusiness;
        @SerializedName("unit_silk_product")
        @Expose
        private Object unitSilkProduct;
        @SerializedName("turn_over_in_production")
        @Expose
        private Object turnOverInProduction;
        @SerializedName("turnover_details")
        @Expose
        private Object turnoverDetails;
        @SerializedName("turnover")
        @Expose
        private Object turnover;
        @SerializedName("details_of_sourcing")
        @Expose
        private Object detailsOfSourcing;
        @SerializedName("for_manufactures_facilities")
        @Expose
        private Object forManufacturesFacilities;
        @SerializedName("quality_control")
        @Expose
        private String qualityControl;
        @SerializedName("specify_the_items_proposed")
        @Expose
        private Object specifyTheItemsProposed;
        @SerializedName("do_you_propose")
        @Expose
        private Object doYouPropose;
        @SerializedName("turn_over_of_silk_exports")
        @Expose
        private Object turnOverOfSilkExports;
        @SerializedName("rules_and_regulations")
        @Expose
        private Object rulesAndRegulations;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("submit_date")
        @Expose
        private String submitDate;
        @SerializedName("name")
        @Expose
        private Object name;
        @SerializedName("password_reset_code")
        @Expose
        private Object passwordResetCode;
        @SerializedName("last_login")
        @Expose
        private Object lastLogin;
        @SerializedName("login_ip")
        @Expose
        private Object loginIp;
        @SerializedName("is_approve")
        @Expose
        private String isApprove;
        @SerializedName("tender_type")
        @Expose
        private Object tenderType;
        @SerializedName("company_name")
        @Expose
        private Object companyName;
        @SerializedName("sub_category_id")
        @Expose
        private Object subCategoryId;
        @SerializedName("kyc_document")
        @Expose
        private Object kycDocument;
        @SerializedName("bank_document")
        @Expose
        private Object bankDocument;
        @SerializedName("category_id")
        @Expose
        private Object categoryId;
        @SerializedName("signature")
        @Expose
        private String signature;
        @SerializedName("aadhar_card")
        @Expose
        private String aadharCard;
        @SerializedName("gst_certificate")
        @Expose
        private String gstCertificate;
        @SerializedName("agreement_stamp")
        @Expose
        private String agreementStamp;
        @SerializedName("document")
        @Expose
        private String document;
        @SerializedName("landmark")
        @Expose
        private String landmark;
        @SerializedName("mr_title")
        @Expose
        private String mrTitle;
        @SerializedName("name_signature")
        @Expose
        private String nameSignature;
        @SerializedName("galleryImage")
        @Expose
        private String galleryImage;
        @SerializedName("product_dealt")
        @Expose
        private String productDealt;
        @SerializedName("turn_over")
        @Expose
        private String turnOver;
        @SerializedName("raw_materials")
        @Expose
        private String rawMaterials;
        @SerializedName("no_of_looms")
        @Expose
        private String noOfLooms;
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
        @SerializedName("annual_magazine")
        @Expose
        private String annualMagazine;
        @SerializedName("edit_aproved")
        @Expose
        private String editAproved;
        @SerializedName("silk_goods")
        @Expose
        private Object silkGoods;
        @SerializedName("financial_year")
        @Expose
        private Object financialYear;
        @SerializedName("fax_number")
        @Expose
        private Object faxNumber;
        @SerializedName("pincode_manufacturing")
        @Expose
        private Object pincodeManufacturing;
        @SerializedName("area_manufacturing")
        @Expose
        private Object areaManufacturing;
        @SerializedName("city_manufacturing")
        @Expose
        private Object cityManufacturing;
        @SerializedName("state_manufacturing")
        @Expose
        private Object stateManufacturing;
        @SerializedName("address_manufacturing")
        @Expose
        private Object addressManufacturing;
        @SerializedName("landmark_manufacturing")
        @Expose
        private Object landmarkManufacturing;
        @SerializedName("telephone_manufacturing")
        @Expose
        private Object telephoneManufacturing;
        @SerializedName("total_capacity")
        @Expose
        private Object totalCapacity;
        @SerializedName("organization")
        @Expose
        private String organization;
        @SerializedName("reference_no")
        @Expose
        private String referenceNo;
        @SerializedName("user_id")
        @Expose
        private Object userId;
        @SerializedName("approved_date")
        @Expose
        private String approvedDate;

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

        public String getModulePermission() {
            return modulePermission;
        }

        public void setModulePermission(String modulePermission) {
            this.modulePermission = modulePermission;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
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

        public Object getTelephone1() {
            return telephone1;
        }

        public void setTelephone1(Object telephone1) {
            this.telephone1 = telephone1;
        }

        public Object getTelephone2() {
            return telephone2;
        }

        public void setTelephone2(Object telephone2) {
            this.telephone2 = telephone2;
        }

        public Object getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(Object paymentType) {
            this.paymentType = paymentType;
        }

        public Object getPaymentValue() {
            return paymentValue;
        }

        public void setPaymentValue(Object paymentValue) {
            this.paymentValue = paymentValue;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Object getReceivedDate() {
            return receivedDate;
        }

        public void setReceivedDate(Object receivedDate) {
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

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
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

        public Object getChapterId() {
            return chapterId;
        }

        public void setChapterId(Object chapterId) {
            this.chapterId = chapterId;
        }

        public Object getGroupId() {
            return groupId;
        }

        public void setGroupId(Object groupId) {
            this.groupId = groupId;
        }

        public Object getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(Object productCategory) {
            this.productCategory = productCategory;
        }

        public Object getTradePartnerId() {
            return tradePartnerId;
        }

        public void setTradePartnerId(Object tradePartnerId) {
            this.tradePartnerId = tradePartnerId;
        }

        public Object getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(Object registrationDate) {
            this.registrationDate = registrationDate;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public Object getWebsite() {
            return website;
        }

        public void setWebsite(Object website) {
            this.website = website;
        }

        public Object getNextRenewalDate() {
            return nextRenewalDate;
        }

        public void setNextRenewalDate(Object nextRenewalDate) {
            this.nextRenewalDate = nextRenewalDate;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getMemberType() {
            return memberType;
        }

        public void setMemberType(Object memberType) {
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

        public Object getFax() {
            return fax;
        }

        public void setFax(Object fax) {
            this.fax = fax;
        }

        public String getDesignationId() {
            return designationId;
        }

        public void setDesignationId(String designationId) {
            this.designationId = designationId;
        }

        public Object getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(Object contactPerson) {
            this.contactPerson = contactPerson;
        }

        public Object getContactDesignationId() {
            return contactDesignationId;
        }

        public void setContactDesignationId(Object contactDesignationId) {
            this.contactDesignationId = contactDesignationId;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getBranchName() {
            return branchName;
        }

        public void setBranchName(Object branchName) {
            this.branchName = branchName;
        }

        public Object getBranchAddress() {
            return branchAddress;
        }

        public void setBranchAddress(Object branchAddress) {
            this.branchAddress = branchAddress;
        }

        public Object getBranchArea() {
            return branchArea;
        }

        public void setBranchArea(Object branchArea) {
            this.branchArea = branchArea;
        }

        public Object getBranchCountry() {
            return branchCountry;
        }

        public void setBranchCountry(Object branchCountry) {
            this.branchCountry = branchCountry;
        }

        public Object getBranchState() {
            return branchState;
        }

        public void setBranchState(Object branchState) {
            this.branchState = branchState;
        }

        public Object getBranchCity() {
            return branchCity;
        }

        public void setBranchCity(Object branchCity) {
            this.branchCity = branchCity;
        }

        public Object getBranchPincode() {
            return branchPincode;
        }

        public void setBranchPincode(Object branchPincode) {
            this.branchPincode = branchPincode;
        }

        public String getSilkProduct() {
            return silkProduct;
        }

        public void setSilkProduct(String silkProduct) {
            this.silkProduct = silkProduct;
        }

        public Object getUnitBranchName() {
            return unitBranchName;
        }

        public void setUnitBranchName(Object unitBranchName) {
            this.unitBranchName = unitBranchName;
        }

        public Object getUnitAddress() {
            return unitAddress;
        }

        public void setUnitAddress(Object unitAddress) {
            this.unitAddress = unitAddress;
        }

        public Object getUnitArea() {
            return unitArea;
        }

        public void setUnitArea(Object unitArea) {
            this.unitArea = unitArea;
        }

        public Object getUnitCountry() {
            return unitCountry;
        }

        public void setUnitCountry(Object unitCountry) {
            this.unitCountry = unitCountry;
        }

        public Object getUnitState() {
            return unitState;
        }

        public void setUnitState(Object unitState) {
            this.unitState = unitState;
        }

        public Object getUnitCity() {
            return unitCity;
        }

        public void setUnitCity(Object unitCity) {
            this.unitCity = unitCity;
        }

        public Object getUnitPincode() {
            return unitPincode;
        }

        public void setUnitPincode(Object unitPincode) {
            this.unitPincode = unitPincode;
        }

        public Object getUnitMobileNo() {
            return unitMobileNo;
        }

        public void setUnitMobileNo(Object unitMobileNo) {
            this.unitMobileNo = unitMobileNo;
        }

        public Object getUnitTelephone1() {
            return unitTelephone1;
        }

        public void setUnitTelephone1(Object unitTelephone1) {
            this.unitTelephone1 = unitTelephone1;
        }

        public Object getUnitTypeOfBusiness() {
            return unitTypeOfBusiness;
        }

        public void setUnitTypeOfBusiness(Object unitTypeOfBusiness) {
            this.unitTypeOfBusiness = unitTypeOfBusiness;
        }

        public Object getUnitSilkProduct() {
            return unitSilkProduct;
        }

        public void setUnitSilkProduct(Object unitSilkProduct) {
            this.unitSilkProduct = unitSilkProduct;
        }

        public Object getTurnOverInProduction() {
            return turnOverInProduction;
        }

        public void setTurnOverInProduction(Object turnOverInProduction) {
            this.turnOverInProduction = turnOverInProduction;
        }

        public Object getTurnoverDetails() {
            return turnoverDetails;
        }

        public void setTurnoverDetails(Object turnoverDetails) {
            this.turnoverDetails = turnoverDetails;
        }

        public Object getTurnover() {
            return turnover;
        }

        public void setTurnover(Object turnover) {
            this.turnover = turnover;
        }

        public Object getDetailsOfSourcing() {
            return detailsOfSourcing;
        }

        public void setDetailsOfSourcing(Object detailsOfSourcing) {
            this.detailsOfSourcing = detailsOfSourcing;
        }

        public Object getForManufacturesFacilities() {
            return forManufacturesFacilities;
        }

        public void setForManufacturesFacilities(Object forManufacturesFacilities) {
            this.forManufacturesFacilities = forManufacturesFacilities;
        }

        public String getQualityControl() {
            return qualityControl;
        }

        public void setQualityControl(String qualityControl) {
            this.qualityControl = qualityControl;
        }

        public Object getSpecifyTheItemsProposed() {
            return specifyTheItemsProposed;
        }

        public void setSpecifyTheItemsProposed(Object specifyTheItemsProposed) {
            this.specifyTheItemsProposed = specifyTheItemsProposed;
        }

        public Object getDoYouPropose() {
            return doYouPropose;
        }

        public void setDoYouPropose(Object doYouPropose) {
            this.doYouPropose = doYouPropose;
        }

        public Object getTurnOverOfSilkExports() {
            return turnOverOfSilkExports;
        }

        public void setTurnOverOfSilkExports(Object turnOverOfSilkExports) {
            this.turnOverOfSilkExports = turnOverOfSilkExports;
        }

        public Object getRulesAndRegulations() {
            return rulesAndRegulations;
        }

        public void setRulesAndRegulations(Object rulesAndRegulations) {
            this.rulesAndRegulations = rulesAndRegulations;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getSubmitDate() {
            return submitDate;
        }

        public void setSubmitDate(String submitDate) {
            this.submitDate = submitDate;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getPasswordResetCode() {
            return passwordResetCode;
        }

        public void setPasswordResetCode(Object passwordResetCode) {
            this.passwordResetCode = passwordResetCode;
        }

        public Object getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(Object lastLogin) {
            this.lastLogin = lastLogin;
        }

        public Object getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(Object loginIp) {
            this.loginIp = loginIp;
        }

        public String getIsApprove() {
            return isApprove;
        }

        public void setIsApprove(String isApprove) {
            this.isApprove = isApprove;
        }

        public Object getTenderType() {
            return tenderType;
        }

        public void setTenderType(Object tenderType) {
            this.tenderType = tenderType;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public Object getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(Object subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public Object getKycDocument() {
            return kycDocument;
        }

        public void setKycDocument(Object kycDocument) {
            this.kycDocument = kycDocument;
        }

        public Object getBankDocument() {
            return bankDocument;
        }

        public void setBankDocument(Object bankDocument) {
            this.bankDocument = bankDocument;
        }

        public Object getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAadharCard() {
            return aadharCard;
        }

        public void setAadharCard(String aadharCard) {
            this.aadharCard = aadharCard;
        }

        public String getGstCertificate() {
            return gstCertificate;
        }

        public void setGstCertificate(String gstCertificate) {
            this.gstCertificate = gstCertificate;
        }

        public String getAgreementStamp() {
            return agreementStamp;
        }

        public void setAgreementStamp(String agreementStamp) {
            this.agreementStamp = agreementStamp;
        }

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getMrTitle() {
            return mrTitle;
        }

        public void setMrTitle(String mrTitle) {
            this.mrTitle = mrTitle;
        }

        public String getNameSignature() {
            return nameSignature;
        }

        public void setNameSignature(String nameSignature) {
            this.nameSignature = nameSignature;
        }

        public String getGalleryImage() {
            return galleryImage;
        }

        public void setGalleryImage(String galleryImage) {
            this.galleryImage = galleryImage;
        }

        public String getProductDealt() {
            return productDealt;
        }

        public void setProductDealt(String productDealt) {
            this.productDealt = productDealt;
        }

        public String getTurnOver() {
            return turnOver;
        }

        public void setTurnOver(String turnOver) {
            this.turnOver = turnOver;
        }

        public String getRawMaterials() {
            return rawMaterials;
        }

        public void setRawMaterials(String rawMaterials) {
            this.rawMaterials = rawMaterials;
        }

        public String getNoOfLooms() {
            return noOfLooms;
        }

        public void setNoOfLooms(String noOfLooms) {
            this.noOfLooms = noOfLooms;
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

        public String getAnnualMagazine() {
            return annualMagazine;
        }

        public void setAnnualMagazine(String annualMagazine) {
            this.annualMagazine = annualMagazine;
        }

        public String getEditAproved() {
            return editAproved;
        }

        public void setEditAproved(String editAproved) {
            this.editAproved = editAproved;
        }

        public Object getSilkGoods() {
            return silkGoods;
        }

        public void setSilkGoods(Object silkGoods) {
            this.silkGoods = silkGoods;
        }

        public Object getFinancialYear() {
            return financialYear;
        }

        public void setFinancialYear(Object financialYear) {
            this.financialYear = financialYear;
        }

        public Object getFaxNumber() {
            return faxNumber;
        }

        public void setFaxNumber(Object faxNumber) {
            this.faxNumber = faxNumber;
        }

        public Object getPincodeManufacturing() {
            return pincodeManufacturing;
        }

        public void setPincodeManufacturing(Object pincodeManufacturing) {
            this.pincodeManufacturing = pincodeManufacturing;
        }

        public Object getAreaManufacturing() {
            return areaManufacturing;
        }

        public void setAreaManufacturing(Object areaManufacturing) {
            this.areaManufacturing = areaManufacturing;
        }

        public Object getCityManufacturing() {
            return cityManufacturing;
        }

        public void setCityManufacturing(Object cityManufacturing) {
            this.cityManufacturing = cityManufacturing;
        }

        public Object getStateManufacturing() {
            return stateManufacturing;
        }

        public void setStateManufacturing(Object stateManufacturing) {
            this.stateManufacturing = stateManufacturing;
        }

        public Object getAddressManufacturing() {
            return addressManufacturing;
        }

        public void setAddressManufacturing(Object addressManufacturing) {
            this.addressManufacturing = addressManufacturing;
        }

        public Object getLandmarkManufacturing() {
            return landmarkManufacturing;
        }

        public void setLandmarkManufacturing(Object landmarkManufacturing) {
            this.landmarkManufacturing = landmarkManufacturing;
        }

        public Object getTelephoneManufacturing() {
            return telephoneManufacturing;
        }

        public void setTelephoneManufacturing(Object telephoneManufacturing) {
            this.telephoneManufacturing = telephoneManufacturing;
        }

        public Object getTotalCapacity() {
            return totalCapacity;
        }

        public void setTotalCapacity(Object totalCapacity) {
            this.totalCapacity = totalCapacity;
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

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getApprovedDate() {
            return approvedDate;
        }

        public void setApprovedDate(String approvedDate) {
            this.approvedDate = approvedDate;
        }

    }
}