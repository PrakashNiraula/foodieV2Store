package com.devpj.foodiestorev2.retrofit;


import com.devpj.foodiestorev2.model.AddressResponse;
import com.devpj.foodiestorev2.model.AppSettings;
import com.devpj.foodiestorev2.model.Broadcast;
import com.devpj.foodiestorev2.model.BroadcastResponse;
import com.devpj.foodiestorev2.model.DeliveredOrders;
import com.devpj.foodiestorev2.model.OrderDetails;
import com.devpj.foodiestorev2.model.PendingOrder;
import com.devpj.foodiestorev2.model.PendingOrderItem;
import com.devpj.foodiestorev2.model.RiderResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface UserService {




    @POST(APIClient.APPEND_URL + "admin_login.php")
    Call<JsonObject> getLogin(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "pending_orders.php")
    Call<PendingOrder> getpendingOrders(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "delivered_orders.php")
    Call<DeliveredOrders> getDelivered(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "order_by_id.php")
    Call<OrderDetails> getOrderbyId(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "update_order.php")
    Call<OrderDetails> Updateorder(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "riders.php")
    Call<RiderResponse> GetallRiders(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "getsettings.php")
    Call<AppSettings> getAppsettings(@Body JsonObject object);

    @POST("https://onesignal.com/api/v1/notifications")
    Call<BroadcastResponse> postToRider(@Header("Authorization") String token,@Body JsonObject object);

    @POST("https://onesignal.com/api/v1/notifications")
    Call<BroadcastResponse> postToUser(@Header("Authorization") String token,@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "address_by_id.php")
    Call<AddressResponse> getAddress(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "update_order.php")
    Call<List<PendingOrderItem>> updateOrder(@Body JsonObject object);




}
