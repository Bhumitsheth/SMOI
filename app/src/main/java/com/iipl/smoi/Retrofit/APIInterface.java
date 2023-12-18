package com.iipl.smoi.Retrofit;

import com.iipl.smoi.Model.AboutusResponse;
import com.iipl.smoi.Model.AddExpoResponse;
import com.iipl.smoi.Model.AddStallResponse;
import com.iipl.smoi.Model.AdvertiseSliderResponse;
import com.iipl.smoi.Model.AvailableLabelResponse;
import com.iipl.smoi.Model.BlogResponse;
import com.iipl.smoi.Model.BuySilkResponse;
import com.iipl.smoi.Model.ChangePasswordResponse;
import com.iipl.smoi.Model.ChapterResponse;
import com.iipl.smoi.Model.CityResponse;
import com.iipl.smoi.Model.CompanyStatusResponse;
import com.iipl.smoi.Model.ContactListResponse;
import com.iipl.smoi.Model.CreateRequestLabelsResponse;
import com.iipl.smoi.Model.DeleteGalleryImageResponse;
import com.iipl.smoi.Model.DeleteGalleryResponse;
import com.iipl.smoi.Model.DistributeLabelResponse;
import com.iipl.smoi.Model.EventsResponse;
import com.iipl.smoi.Model.ExpoListResponse;
import com.iipl.smoi.Model.FaqsResponse;
import com.iipl.smoi.Model.FlourishListResponse;
import com.iipl.smoi.Model.ForgotPasswordResponse;
import com.iipl.smoi.Model.GalleryDetailsResponse;
import com.iipl.smoi.Model.GalleryResponse;
import com.iipl.smoi.Model.HelpSupportResponse;
import com.iipl.smoi.Model.LabelGstResponse;
import com.iipl.smoi.Model.LoginResponse;
import com.iipl.smoi.Model.MemberResponse;
import com.iipl.smoi.Model.NaturalTypeResponse;
import com.iipl.smoi.Model.NewsResponse;
import com.iipl.smoi.Model.NotificationsResponse;
import com.iipl.smoi.Model.RequestLabelResponse;
import com.iipl.smoi.Model.SilkCareResponse;
import com.iipl.smoi.Model.SilksResponse;
import com.iipl.smoi.Model.StallsBookResponse;
import com.iipl.smoi.Model.StateResponse;
import com.iipl.smoi.Model.TcResponse;
import com.iipl.smoi.Model.TestCenterResponse;
import com.iipl.smoi.Model.TestSilkResponse;
import com.iipl.smoi.Model.TypeOfLabelsResponse;
import com.iipl.smoi.Model.UpdateProfileResponse;
import com.iipl.smoi.Model.UploadGalleryResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {

    @POST("login")
    Call<LoginResponse> getLogin(@Body Map<String, String> body);

    @POST("Forgot_password")
    Call<ForgotPasswordResponse> getForgotPassword(@Body Map<String, String> body);

    @POST("changePwd")
    Call<ChangePasswordResponse> getChangePassword(@Body Map<String, String> body);

    @GET("blog")
    Call<BlogResponse> getBlog();

    @POST("members/memberSearchByStateCity")
    Call<MemberResponse> getMembers(@Body Map<String, String> body);

    @POST("members")
    Call<MemberResponse> getMembersLazyLoading(@Body Map<String, String> body);

    @GET("chapter")
    Call<ChapterResponse> getChapter();

    @POST("Faq")
    Call<FaqsResponse> getFaq(@Body Map<String, String> body);

    @GET("advertisement")
    Call<AdvertiseSliderResponse> getAdvertiseSlider();

    @POST("distribute_label/available_labels")
    Call<AvailableLabelResponse> getAvailableLabel(@Header("Authorization") String token, @Body Map<String, String> body);

    @POST("distribute_label/records_distributed")
    Call<DistributeLabelResponse> getDistributeLabel(@Header("Authorization") String token, @Body Map<String, String> body);

    @POST("terms_and_conditions")
    Call<TcResponse> getTermsConditions();

    @POST("help_and_support")
    Call<HelpSupportResponse> getHelpSupport();

    @GET("news")
    Call<NewsResponse> getNews();

    @GET("about_smoi")
    Call<AboutusResponse> getSmoi();

    @POST("contact_list")
    Call<ContactListResponse> getContactList(@Header("Authorization") String token, @Body Map<String, String> body);

    //Request Label
    @POST("request_labels")
    Call<RequestLabelResponse> getRequestLabels(@Header("Authorization") String token, @Body Map<String, String> body);

    //Flourish List
    @POST("flourish")
    Call<FlourishListResponse> getFlourishList(@Header("Authorization") String token, @Body Map<String, String> body);

    //List of Expo
    @POST("expo_bookings")
    Call<ExpoListResponse> getExpoList(@Header("Authorization") String token, @Body Map<String, String> body);

    @GET("gallery")
    Call<GalleryResponse> getGallery();


    @Multipart
    @POST("UpdateProfile")
    Call<UpdateProfileResponse> getUpdateProfile(@Header("Authorization") String token,
                                                 @Part("id") RequestBody id,
                                                 @Part("username") RequestBody username,
                                                 @Part("email") RequestBody email,
                                                 @Part("first_name") RequestBody first_name,
                                                 @Part("last_name") RequestBody last_name,
                                                 @Part("mobile_no") RequestBody mobile_no,
                                                 @Part("telephone1") RequestBody telephone1,
                                                 @Part("telephone2") RequestBody telephone2,
                                                 @Part("address") RequestBody address,
                                                 @Part("area") RequestBody area,
                                                 @Part("city") RequestBody city,
                                                 @Part("state") RequestBody state,
                                                 @Part("pincode") RequestBody pincode,
                                                 @Part("send_request") RequestBody sendRequest,
                                                 @Part MultipartBody.Part filepart);

    @POST("gallery/delete_gallery")
    Call<DeleteGalleryResponse> getDeleteGallery(@Header("Authorization") String token, @Body Map<String, String> body);

    @POST("gallery/gallery_by_id")
    Call<GalleryDetailsResponse> getGalleryDetails(@Header("Authorization") String token, @Body Map<String, String> body);

    @POST("gallery/remove_gallery_image")
    Call<DeleteGalleryImageResponse> getDeleteGalleryImage(@Header("Authorization") String token, @Body Map<String, String> body);

    @Multipart
    @POST("gallery/upload_gallery")
    Call<UploadGalleryResponse> getUploadGalleryImage(@Header("Authorization") String token,
                                                      @Part MultipartBody.Part[] parts,
                                                      @Part("id") RequestBody galleryId,
                                                      @Part("login_id") RequestBody UserId);

    @GET("test_center")
    Call<TestCenterResponse> getTestCenter();

    @GET("silk_info_master")
    Call<SilksResponse> getSilks();

    @GET("natural_type")
    Call<NaturalTypeResponse> getNaturalType();

    @GET("state")
    Call<StateResponse> getState();

    @Multipart
    @POST("city")
    Call<CityResponse> getCity(@Part("state_id") RequestBody stateId);

    @GET("events")
    Call<EventsResponse> getEvents();

    @GET("test_center")
    Call<SilkCareResponse> getSilkCare();

    @GET("buy_silk")
    Call<BuySilkResponse> getBuySilk();

    @GET("test_center/how_to_test_silk")
    Call<TestSilkResponse> getTestSilk();

    //Notification
    @POST("notifications")
    Call<List<NotificationsResponse>> getNotifications(@Body Map<String, String> body);

    //Diff type of Label
    @GET("type_of_label")
    Call<TypeOfLabelsResponse> getTypeOfLabels();

    //Book Expo
    @Multipart
    @POST("expo_bookings/add")
    Call<AddExpoResponse> AddExpo(@Header("Authorization") String token,
                                  @Part("chapter_id") RequestBody chapter_id,
                                  @Part("expo_name") RequestBody expo_name,
                                  @Part("expo_startdate") RequestBody expo_startdate,
                                  @Part("expo_enddate") RequestBody expo_enddate,
                                  @Part("available_stalls") RequestBody available_stalls,
                                  @Part("stall_size") RequestBody stall_size,
                                  @Part("description") RequestBody description,
                                  @Part("slug") RequestBody slug,
                                  @Part("address") RequestBody address,
                                  @Part("area") RequestBody area,
                                  @Part("state") RequestBody state,
                                  @Part("city") RequestBody city,
                                  @Part("pincode") RequestBody pincode,
                                  @Part("contact_person") RequestBody contact_person,
                                  @Part("contact_no") RequestBody contact_no,
                                  @Part("chapter_address") RequestBody chapter_address,
                                  @Part("chapter_area") RequestBody chapter_area,
                                  @Part("chapter_state") RequestBody chapter_state,
                                  @Part("chapter_city") RequestBody chapter_city,
                                  @Part("chapter_pincode") RequestBody chapter_pincode,
                                  @Part("smoi_letter_no") RequestBody smoi_letter_no,
                                  @Part("police_station_details") RequestBody police_station_details,
                                  @Part("fire_station_details") RequestBody fire_station_details,
                                  @Part("income_tax_office_details") RequestBody income_tax_office_details,
                                  @Part("local_municipal_corporation_details") RequestBody local_municipal_corporation_details,
                                  @Part("insurance_company_details") RequestBody insurance_company_details,
                                  // Start List Send
                                  @Part("stall_no_from") RequestBody stallNoFromList,
                                  @Part("stall_no_to") RequestBody stall_no_toList,
                                  @Part("stall_rent") RequestBody stall_rentList,
                                  @Part("gst") RequestBody gstList,
                                  @Part("gst_amount") RequestBody gst_amountList,
                                  @Part("total") RequestBody totalList,
                                  // End List Send
                                  @Part("login_id") RequestBody login_id,
                                  @Part("role_id") RequestBody role_id,
                                  @Part("user_type") RequestBody user_type,
                                  @Part("status") RequestBody status,
                                  @Part MultipartBody.Part image,
                                  @Part MultipartBody.Part floor_plan);


    //Stall Selection
    @POST("expo_bookings/booking_stall_by_expo_id")
    Call<StallsBookResponse> AddStallBooking(@Header("Authorization") String token, @Body Map<String, String> body);

    //Company List
    @GET("expo_bookings/company_status_list")
    Call<CompanyStatusResponse> getCompanyStatus();

    //Book Stall
    @Multipart
    @POST("Expo_booking_details/add")
    Call<AddStallResponse> AddStall(@Header("Authorization") String token,
                                    @Part("expo_id") RequestBody expo_id,
                                    @Part("login_id") RequestBody login_id,
                                    @Part("reference_no") RequestBody reference_no,
                                    @Part("name_of_owner") RequestBody name_of_owner,
                                    @Part("name_of_company") RequestBody name_of_company,
                                    @Part("door_no") RequestBody door_no,
                                    @Part("street_no") RequestBody street_no,
                                    @Part("location") RequestBody location,
                                    @Part("area") RequestBody area,
                                    @Part("city") RequestBody city,
                                    @Part("district") RequestBody district,
                                    @Part("state") RequestBody state,
                                    @Part("pincode") RequestBody pincode,
                                    @Part("contact_person") RequestBody contact_person,
                                    @Part("telephone1") RequestBody telephone1,
                                    @Part("mobile_no") RequestBody mobile_no,
                                    @Part("email") RequestBody email,
                                    @Part("company_status") RequestBody company_status,
                                    @Part("product_covered") RequestBody product_covered,
                                    @Part("product_specialty") RequestBody product_specialty,
                                    @Part("awards") RequestBody awards,
                                    @Part("information") RequestBody information,
                                    //List Data
                                    @Part("no_of_stall") RequestBody no_of_stall,
                                    @Part("preferred_stall") RequestBody preferred_stall,
                                    @Part("stall_rent") RequestBody stall_rent,
                                    @Part("gst") RequestBody gst,
                                    @Part("gst_amount") RequestBody gst_amount,
                                    @Part("total") RequestBody total,
                                    // / List Data
                                    @Part("total_payment") RequestBody total_payment,
                                    @Part("declared") RequestBody declared,
                                    @Part("currency") RequestBody currency,
                                    @Part("payment_description") RequestBody payment_description,
                                    @Part("payment_method") RequestBody payment_method,
                                    @Part MultipartBody.Part signature,
                                    @Part("transaction_id") RequestBody transaction_id,
                                    @Part MultipartBody.Part transaction_image);


    //Label GST Amount
    @Multipart
    @POST("Request_labels/gst")
    Call<LabelGstResponse> getLabelGstAmount(@Part("state2") RequestBody state2,
                                             @Part("login_id") RequestBody login_id,
                                             @Part("role_id") RequestBody role_id,
                                             @Part("amount") RequestBody amount,
                                             @Part("type_of_label") RequestBody type_of_label,
                                             @Part("requested_to") RequestBody requested_to);

    //Create Request Label
    @Multipart
    @POST("Request_labels/add")
    Call<CreateRequestLabelsResponse> getCreateRequestLabels(@Part("login_id") RequestBody login_id,
                                                             @Part("role_id") RequestBody role_id,
                                                             @Part("type_of_label") RequestBody type_of_label,
                                                             @Part("required_label") RequestBody required_label,

                                                             @Part("transaction_id") RequestBody transaction_id,
                                                             @Part("payment_method") RequestBody payment_method,
                                                             @Part("payable_amount") RequestBody payable_amount,
                                                             @Part("requested_to") RequestBody requested_to,
                                                             @Part MultipartBody.Part transaction_image);




}

