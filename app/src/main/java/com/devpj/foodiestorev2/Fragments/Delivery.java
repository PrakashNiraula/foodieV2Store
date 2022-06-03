package com.devpj.foodiestorev2.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devpj.foodiestorev2.Activity.HomeActivity;
//import com.devpj.foodiestorev2.Manifest;
import com.devpj.foodiestorev2.Adapter.PendingOrderAdapter;
import com.devpj.foodiestorev2.R;
import com.devpj.foodiestorev2.model.AppSettings;
import com.devpj.foodiestorev2.model.Broadcast;
import com.devpj.foodiestorev2.model.BroadcastResponse;
import com.devpj.foodiestorev2.model.OrderDetails;
import com.devpj.foodiestorev2.model.PendingOrder;
import com.devpj.foodiestorev2.model.PendingOrderItem;
import com.devpj.foodiestorev2.model.RiderResponse;
import com.devpj.foodiestorev2.retrofit.APIClient;
import com.devpj.foodiestorev2.utils.CustProgressbar;
import com.devpj.foodiestorev2.utils.CustomAlert;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.$Gson$Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.*;
import static android.Manifest.permission.*;


public class Delivery extends Fragment {

    CustomAlert customAlert;
    CustProgressbar custProgressbar;
    TextView save, reject, makephonecall, orderId, status, address, txtPhone, txttotal, tax, paymentmethod;
    Spinner riderlist;
    String from;
    String selected_id;
    String[] ridernames;
    String[] riderid;
    OrderDetails orderDetails;
    PendingOrder pendingOrder;

    AppSettings appSettings;
    TextInputEditText deliverycharge;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        customAlert = new CustomAlert();
        custProgressbar = new CustProgressbar();
        custProgressbar.PrograssCreate(getContext());
        riderlist = view.findViewById(R.id.riderlist);
        txttotal = view.findViewById(R.id.txtotal);
        save = view.findViewById(R.id.txtaccept);
        tax = view.findViewById(R.id.tax);
        deliverycharge = view.findViewById(R.id.txtdeliveryCharge);
        paymentmethod = view.findViewById(R.id.paymentmethod);
        txtPhone = view.findViewById(R.id.txtphone);
        status = view.findViewById(R.id.txtstatus);
        address = view.findViewById(R.id.txtaddress);
        orderDetails = ((HomeActivity) getActivity()).getOrderDetails();
        appSettings = ((HomeActivity) getActivity()).getAppSettings();
        if (orderDetails != null) {
            status.setText("Status: " + orderDetails.getData().get(0).getStatus());
            address.setText("Deliver to: " + (orderDetails.getData().get(0).getAddressId()));
            txtPhone.setText("Mob: " + orderDetails.getUser().getMobile());
            txttotal.setText("Total: " + orderDetails.getData().get(0).getTotal());
            paymentmethod.setText("Payment: " + orderDetails.getData().get(0).getpMethod());
        }
        if (appSettings != null) {
            tax.setText("Tax: " + appSettings.getData().getTax() + " %>> Nrs" + (Float.parseFloat(orderDetails.getData().get(0).getTotal()) * Float.parseFloat(appSettings.getData().getTax())) / 100);
            // deliverycharge.setText("Delivery Charge: Nrs" + appSettings.getData().getDeliveryCharge());
        }
        reject = view.findViewById(R.id.txt_reject);
        orderId = view.findViewById(R.id.orderId);
        makephonecall = view.findViewById(R.id.txt_call);
        makephonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] texts = txtPhone.getText().toString().split("Mob:");
                String phone = texts[1];
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                custProgressbar.PrograssCreate(getContext());
                String buyerid = orderDetails.getData().get(0).getUid();
                String riderid = riderlist.getSelectedItem().toString().split(">")[0];
                String dcharge = deliverycharge.getText().toString();
                updatestatus(riderid, orderDetails.getData().get(0).getOid().toString(), dcharge);
                  sendNotiftoUser(buyerid);
                sendNotiftoRider(riderid);
            }
        });


        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        from = ((HomeActivity) getActivity()).getFrom();
        selected_id = ((HomeActivity) getActivity()).getSelected_id();
        orderId.setText("Order: " + selected_id);
        loadriders();
        loadappsettings();


        return view;
    }

    public void updatestatus(String riderid, String orderid, String deliverycharge) {


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", "1");
            jsonObject.put("rid", riderid);
            jsonObject.put("delivery_charge", deliverycharge);
            jsonObject.put("oid", orderid);
            JsonParser jsonParser = new JsonParser();
            Call<List<PendingOrderItem>> call = APIClient.getInterface().updateOrder((JsonObject) jsonParser.parse(jsonObject.toString()));
            call.enqueue(new Callback<List<PendingOrderItem>>() {
                @Override
                public void onResponse(Call<List<PendingOrderItem>> call, Response<List<PendingOrderItem>> response) {
                    String name="";

                    Toast.makeText(getContext(), "Rider has been assigned successfully", Toast.LENGTH_SHORT).show();
                    getPendingOrder();


                }

                @Override
                public void onFailure(Call<List<PendingOrderItem>> call, Throwable t) {
                    Log.d("Error", t.toString());
                }
            });


        } catch (Exception ex) {
            Log.d("Error", ex.toString());
        }
    }

    public void loadappsettings() {
        custProgressbar.PrograssCreate(getContext());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", "1");
            JsonParser jsonParser = new JsonParser();
            Call<AppSettings> call = APIClient.getInterface().getAppsettings((JsonObject) jsonParser.parse(jsonObject.toString()));
            call.enqueue(new Callback<AppSettings>() {
                @Override
                public void onResponse(Call<AppSettings> call, Response<AppSettings> response) {
                    appSettings = response.body();
                    custProgressbar.ClosePrograssBar();
                    ((HomeActivity) getActivity()).setAppSettings(appSettings);
                    if (appSettings != null) {
                        tax.setText("Tax: " + appSettings.getData().getTax() + " %>> Nrs " + (Float.parseFloat(orderDetails.getData().get(0).getTotal()) * Float.parseFloat(appSettings.getData().getTax())) / 100);
                        //deliverycharge.setText("Delivery Charge: Nrs " + appSettings.getData().getDeliveryCharge());
                    }
                }

                @Override
                public void onFailure(Call<AppSettings> call, Throwable t) {
                    Log.d("Error:", t.toString());
                }
            });

        } catch (Exception ex) {
            Log.d("Error", ex.toString());
        }


    }


    public void sendNotiftoUser(String userid) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "\r\n{\"app_id\":\"9476ad49-bc00-4e08-b658-b0ddd6b42ddb\",\r\n\"filters\":[{\"field\":\"tag\", \"key\":\"userId\",\"relation\":\"=\",\"value\":\"" + userid + "\"}],\r\n\"contents\":{\"en\":\"Your order has been accepted\"}}\r\n\r\n");
        Request request = new Request.Builder()
                .url("https://onesignal.com/api/v1/notifications")
                .method("POST", body)
                .addHeader("Authorization", "Bearer YTRmN2VlZjktM2UyZS00OWRkLWFjNjItZGIzYzUzM2Y0MDUz")
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String name = "sdafa";
            }
        });
    }


    public void sendNotiftoRider(String riderid) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "\r\n{\"app_id\":\"cd8e610e-c6ed-43ca-9acd-43f322340784\",\r\n\"filters\":[{\"field\":\"tag\", \"key\":\"rider_id\",\"relation\":\"=\",\"value\":\"" + riderid + "\"}],\r\n\"contents\":{\"en\":\"Your have new order assigned.\"}}\r\n\r\n");
        Request request = new Request.Builder()
                .url("https://onesignal.com/api/v1/notifications")
                .method("POST", body)
                .addHeader("Authorization", "Bearer MGI0YTkzMmItNzBhNC00NDM3LThkZDktZTkzMTE3NzM2ZjIw")
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String name = "sdafa";
            }
        });
    }


    public void loadriders() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", "1");
            JsonParser jsonParser = new JsonParser();
            Call<RiderResponse> call = APIClient.getInterface().GetallRiders((JsonObject) jsonParser.parse(jsonObject.toString()));
            call.enqueue(new Callback<RiderResponse>() {
                @Override
                public void onResponse(Call<RiderResponse> call, Response<RiderResponse> response) {
                    if (response != null) {
                        custProgressbar.ClosePrograssBar();
                        ridernames = new String[response.body().getData().size()];
                        riderid = new String[response.body().getData().size()];

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            ridernames[i] = response.body().getData().get(i).getId() + "> " + response.body().getData().get(i).getName();
                            riderid[i] = response.body().getData().get(i).getId();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ridernames);
                        riderlist.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<RiderResponse> call, Throwable t) {
                    Log.d("Error", t.toString());

                }
            });

        } catch (Exception ex) {
            Log.d("1", ex.toString());
        }


    }

    private void getPendingOrder() {
        //custPrograssbar.PrograssCreate(getContext());
        Context context = getContext();
        custProgressbar.PrograssCreate(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", "1");
            JsonParser jsonParser = new JsonParser();
            Call<PendingOrder> call = APIClient.getInterface().getpendingOrders((JsonObject) jsonParser.parse(jsonObject.toString()));

            call.enqueue(new Callback<PendingOrder>() {
                @Override
                public void onResponse(Call<PendingOrder> call, Response<PendingOrder> response) {

                    if (response.isSuccessful()) {
                        custProgressbar.ClosePrograssBar();
                        pendingOrder = response.body();
                        ((HomeActivity) getActivity()).setPendingOrder(pendingOrder);
                        getParentFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Dashboard(), "Dashboard").commit();

                    }


                }

                @Override
                public void onFailure(Call<PendingOrder> call, Throwable t) {
                    // throw  t.toString();
                    custProgressbar.ClosePrograssBar();
                    Toast.makeText(getContext(), "Error Loading Data", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}