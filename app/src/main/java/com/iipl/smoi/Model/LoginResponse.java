package com.iipl.smoi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LoginResponse {

    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_info")
    @Expose
    private List<UserInfo> userInfo = null;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    public class UserInfo {

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
        @SerializedName("branch_name")
        @Expose
        private String branchName;
        @SerializedName("branch_address")
        @Expose
        private String branchAddress;
        @SerializedName("branch_area")
        @Expose
        private String branchArea;
        @SerializedName("branch_country")
        @Expose
        private String branchCountry;
        @SerializedName("branch_state")
        @Expose
        private String branchState;
        @SerializedName("branch_city")
        @Expose
        private String branchCity;
        @SerializedName("branch_pincode")
        @Expose
        private String branchPincode;
        @SerializedName("silk_product")
        @Expose
        private String silkProduct;
        @SerializedName("unit_branch_name")
        @Expose
        private String unitBranchName;
        @SerializedName("unit_address")
        @Expose
        private String unitAddress;
        @SerializedName("unit_area")
        @Expose
        private String unitArea;
        @SerializedName("unit_country")
        @Expose
        private String unitCountry;
        @SerializedName("unit_state")
        @Expose
        private String unitState;
        @SerializedName("unit_city")
        @Expose
        private String unitCity;
        @SerializedName("unit_pincode")
        @Expose
        private String unitPincode;
        @SerializedName("unit_mobile_no")
        @Expose
        private String unitMobileNo;
        @SerializedName("unit_telephone1")
        @Expose
        private String unitTelephone1;
        @SerializedName("unit_type_of_business")
        @Expose
        private String unitTypeOfBusiness;
        @SerializedName("unit_silk_product")
        @Expose
        private String unitSilkProduct;
        @SerializedName("turn_over_in_production")
        @Expose
        private String turnOverInProduction;
        @SerializedName("turnover_details")
        @Expose
        private String turnoverDetails;
        @SerializedName("turnover")
        @Expose
        private String turnover;
        @SerializedName("details_of_sourcing")
        @Expose
        private String detailsOfSourcing;
        @SerializedName("for_manufactures_facilities")
        @Expose
        private String forManufacturesFacilities;
        @SerializedName("quality_control")
        @Expose
        private String qualityControl;
        @SerializedName("specify_the_items_proposed")
        @Expose
        private String specifyTheItemsProposed;
        @SerializedName("do_you_propose")
        @Expose
        private String doYouPropose;
        @SerializedName("turn_over_of_silk_exports")
        @Expose
        private String turnOverOfSilkExports;
        @SerializedName("rules_and_regulations")
        @Expose
        private String rulesAndRegulations;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("submit_date")
        @Expose
        private String submitDate;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("password_reset_code")
        @Expose
        private String passwordResetCode;
        @SerializedName("last_login")
        @Expose
        private String lastLogin;
        @SerializedName("login_ip")
        @Expose
        private String loginIp;
        @SerializedName("is_approve")
        @Expose
        private String isApprove;
        @SerializedName("tender_type")
        @Expose
        private String tenderType;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("sub_category_id")
        @Expose
        private String subCategoryId;
        @SerializedName("kyc_document")
        @Expose
        private String kycDocument;
        @SerializedName("bank_document")
        @Expose
        private String bankDocument;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
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
        private String silkGoods;
        @SerializedName("financial_year")
        @Expose
        private String financialYear;
        @SerializedName("fax_number")
        @Expose
        private String faxNumber;
        @SerializedName("pincode_branches")
        @Expose
        private String pincodeBranches;
        @SerializedName("area_branches")
        @Expose
        private String areaBranches;
        @SerializedName("city_branches")
        @Expose
        private String cityBranches;
        @SerializedName("state_branches")
        @Expose
        private String stateBranches;
        @SerializedName("pincode_manufacturing")
        @Expose
        private String pincodeManufacturing;
        @SerializedName("area_manufacturing")
        @Expose
        private String areaManufacturing;
        @SerializedName("city_manufacturing")
        @Expose
        private String cityManufacturing;
        @SerializedName("state_manufacturing")
        @Expose
        private String stateManufacturing;
        @SerializedName("address_manufacturing")
        @Expose
        private String addressManufacturing;
        @SerializedName("landmark_manufacturing")
        @Expose
        private String landmarkManufacturing;
        @SerializedName("telephone_manufacturing")
        @Expose
        private String telephoneManufacturing;
        @SerializedName("total_capacity")
        @Expose
        private String totalCapacity;
        @SerializedName("organization")
        @Expose
        private String organization;
        @SerializedName("reference_no")
        @Expose
        private String referenceNo;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("session_token")
        @Expose
        private String sessionToken;
        @SerializedName("login_status")
        @Expose
        private String loginStatus;

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

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getBranchAddress() {
            return branchAddress;
        }

        public void setBranchAddress(String branchAddress) {
            this.branchAddress = branchAddress;
        }

        public String getBranchArea() {
            return branchArea;
        }

        public void setBranchArea(String branchArea) {
            this.branchArea = branchArea;
        }

        public String getBranchCountry() {
            return branchCountry;
        }

        public void setBranchCountry(String branchCountry) {
            this.branchCountry = branchCountry;
        }

        public String getBranchState() {
            return branchState;
        }

        public void setBranchState(String branchState) {
            this.branchState = branchState;
        }

        public String getBranchCity() {
            return branchCity;
        }

        public void setBranchCity(String branchCity) {
            this.branchCity = branchCity;
        }

        public String getBranchPincode() {
            return branchPincode;
        }

        public void setBranchPincode(String branchPincode) {
            this.branchPincode = branchPincode;
        }

        public String getSilkProduct() {
            return silkProduct;
        }

        public void setSilkProduct(String silkProduct) {
            this.silkProduct = silkProduct;
        }

        public String getUnitBranchName() {
            return unitBranchName;
        }

        public void setUnitBranchName(String unitBranchName) {
            this.unitBranchName = unitBranchName;
        }

        public String getUnitAddress() {
            return unitAddress;
        }

        public void setUnitAddress(String unitAddress) {
            this.unitAddress = unitAddress;
        }

        public String getUnitArea() {
            return unitArea;
        }

        public void setUnitArea(String unitArea) {
            this.unitArea = unitArea;
        }

        public String getUnitCountry() {
            return unitCountry;
        }

        public void setUnitCountry(String unitCountry) {
            this.unitCountry = unitCountry;
        }

        public String getUnitState() {
            return unitState;
        }

        public void setUnitState(String unitState) {
            this.unitState = unitState;
        }

        public String getUnitCity() {
            return unitCity;
        }

        public void setUnitCity(String unitCity) {
            this.unitCity = unitCity;
        }

        public String getUnitPincode() {
            return unitPincode;
        }

        public void setUnitPincode(String unitPincode) {
            this.unitPincode = unitPincode;
        }

        public String getUnitMobileNo() {
            return unitMobileNo;
        }

        public void setUnitMobileNo(String unitMobileNo) {
            this.unitMobileNo = unitMobileNo;
        }

        public String getUnitTelephone1() {
            return unitTelephone1;
        }

        public void setUnitTelephone1(String unitTelephone1) {
            this.unitTelephone1 = unitTelephone1;
        }

        public String getUnitTypeOfBusiness() {
            return unitTypeOfBusiness;
        }

        public void setUnitTypeOfBusiness(String unitTypeOfBusiness) {
            this.unitTypeOfBusiness = unitTypeOfBusiness;
        }

        public String getUnitSilkProduct() {
            return unitSilkProduct;
        }

        public void setUnitSilkProduct(String unitSilkProduct) {
            this.unitSilkProduct = unitSilkProduct;
        }

        public String getTurnOverInProduction() {
            return turnOverInProduction;
        }

        public void setTurnOverInProduction(String turnOverInProduction) {
            this.turnOverInProduction = turnOverInProduction;
        }

        public String getTurnoverDetails() {
            return turnoverDetails;
        }

        public void setTurnoverDetails(String turnoverDetails) {
            this.turnoverDetails = turnoverDetails;
        }

        public String getTurnover() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover = turnover;
        }

        public String getDetailsOfSourcing() {
            return detailsOfSourcing;
        }

        public void setDetailsOfSourcing(String detailsOfSourcing) {
            this.detailsOfSourcing = detailsOfSourcing;
        }

        public String getForManufacturesFacilities() {
            return forManufacturesFacilities;
        }

        public void setForManufacturesFacilities(String forManufacturesFacilities) {
            this.forManufacturesFacilities = forManufacturesFacilities;
        }

        public String getQualityControl() {
            return qualityControl;
        }

        public void setQualityControl(String qualityControl) {
            this.qualityControl = qualityControl;
        }

        public String getSpecifyTheItemsProposed() {
            return specifyTheItemsProposed;
        }

        public void setSpecifyTheItemsProposed(String specifyTheItemsProposed) {
            this.specifyTheItemsProposed = specifyTheItemsProposed;
        }

        public String getDoYouPropose() {
            return doYouPropose;
        }

        public void setDoYouPropose(String doYouPropose) {
            this.doYouPropose = doYouPropose;
        }

        public String getTurnOverOfSilkExports() {
            return turnOverOfSilkExports;
        }

        public void setTurnOverOfSilkExports(String turnOverOfSilkExports) {
            this.turnOverOfSilkExports = turnOverOfSilkExports;
        }

        public String getRulesAndRegulations() {
            return rulesAndRegulations;
        }

        public void setRulesAndRegulations(String rulesAndRegulations) {
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPasswordResetCode() {
            return passwordResetCode;
        }

        public void setPasswordResetCode(String passwordResetCode) {
            this.passwordResetCode = passwordResetCode;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getIsApprove() {
            return isApprove;
        }

        public void setIsApprove(String isApprove) {
            this.isApprove = isApprove;
        }

        public String getTenderType() {
            return tenderType;
        }

        public void setTenderType(String tenderType) {
            this.tenderType = tenderType;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(String subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public String getKycDocument() {
            return kycDocument;
        }

        public void setKycDocument(String kycDocument) {
            this.kycDocument = kycDocument;
        }

        public String getBankDocument() {
            return bankDocument;
        }

        public void setBankDocument(String bankDocument) {
            this.bankDocument = bankDocument;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
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

        public String getSilkGoods() {
            return silkGoods;
        }

        public void setSilkGoods(String silkGoods) {
            this.silkGoods = silkGoods;
        }

        public String getFinancialYear() {
            return financialYear;
        }

        public void setFinancialYear(String financialYear) {
            this.financialYear = financialYear;
        }

        public String getFaxNumber() {
            return faxNumber;
        }

        public void setFaxNumber(String faxNumber) {
            this.faxNumber = faxNumber;
        }

        public String getPincodeBranches() {
            return pincodeBranches;
        }

        public void setPincodeBranches(String pincodeBranches) {
            this.pincodeBranches = pincodeBranches;
        }

        public String getAreaBranches() {
            return areaBranches;
        }

        public void setAreaBranches(String areaBranches) {
            this.areaBranches = areaBranches;
        }

        public String getCityBranches() {
            return cityBranches;
        }

        public void setCityBranches(String cityBranches) {
            this.cityBranches = cityBranches;
        }

        public String getStateBranches() {
            return stateBranches;
        }

        public void setStateBranches(String stateBranches) {
            this.stateBranches = stateBranches;
        }

        public String getPincodeManufacturing() {
            return pincodeManufacturing;
        }

        public void setPincodeManufacturing(String pincodeManufacturing) {
            this.pincodeManufacturing = pincodeManufacturing;
        }

        public String getAreaManufacturing() {
            return areaManufacturing;
        }

        public void setAreaManufacturing(String areaManufacturing) {
            this.areaManufacturing = areaManufacturing;
        }

        public String getCityManufacturing() {
            return cityManufacturing;
        }

        public void setCityManufacturing(String cityManufacturing) {
            this.cityManufacturing = cityManufacturing;
        }

        public String getStateManufacturing() {
            return stateManufacturing;
        }

        public void setStateManufacturing(String stateManufacturing) {
            this.stateManufacturing = stateManufacturing;
        }

        public String getAddressManufacturing() {
            return addressManufacturing;
        }

        public void setAddressManufacturing(String addressManufacturing) {
            this.addressManufacturing = addressManufacturing;
        }

        public String getLandmarkManufacturing() {
            return landmarkManufacturing;
        }

        public void setLandmarkManufacturing(String landmarkManufacturing) {
            this.landmarkManufacturing = landmarkManufacturing;
        }

        public String getTelephoneManufacturing() {
            return telephoneManufacturing;
        }

        public void setTelephoneManufacturing(String telephoneManufacturing) {
            this.telephoneManufacturing = telephoneManufacturing;
        }

        public String getTotalCapacity() {
            return totalCapacity;
        }

        public void setTotalCapacity(String totalCapacity) {
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSessionToken() {
            return sessionToken;
        }

        public void setSessionToken(String sessionToken) {
            this.sessionToken = sessionToken;
        }

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
            this.loginStatus = loginStatus;
        }

    }

}

