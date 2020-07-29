package com.wjk.foodnotemini.api;

import com.wjk.foodnotemini.bean.Bean;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * Created by wjk on 2018. 5. 14..
 */

public interface APIInterface {

//    @GET("api/unknown")
//    Call<MultipleResource> doGetListResources();
//
//    @POST("api/users")
//    Call<User> createUser(@Body User user);
//
//    @GET("api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);


    @GET("smart/v1/lists")
    Call<Bean> getData(@QueryMap Map<String, String> option);





    @Multipart
    @POST("/images/upload")
    Call<Bean> uploadImage(@Part MultipartBody.Part image);



}
