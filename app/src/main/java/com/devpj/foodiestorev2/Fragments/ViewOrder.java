package com.devpj.foodiestorev2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devpj.foodiestorev2.Activity.HomeActivity;
import com.devpj.foodiestorev2.Adapter.PendingOrderAdapter;
import com.devpj.foodiestorev2.Adapter.ProductsAdapter;
import com.devpj.foodiestorev2.R;
import com.devpj.foodiestorev2.model.AddressResponse;
import com.devpj.foodiestorev2.model.DeliveredOrders;
import com.devpj.foodiestorev2.model.OrderDetails;
import com.devpj.foodiestorev2.model.PendingOrder;
import com.devpj.foodiestorev2.model.PendingOrderItem;
import com.devpj.foodiestorev2.retrofit.APIClient;
import com.devpj.foodiestorev2.utils.CustProgressbar;
import com.devpj.foodiestorev2.utils.CustomAlert;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrder extends Fragment implements CustomAlert.onAcceptClickListener {

    PendingOrder pendingOrder;
    DeliveredOrders deliveredOrders;

    String from;
    String selected_id;
    TextView accept, reject;
    CustProgressbar custProgressbar;
    OrderDetails orderDetails;
    RecyclerView.LayoutManager layoutManager;
    ProductsAdapter productsAdapter;
    RecyclerView recyclerView;
    CustomAlert customAlert;
    TextView orderid;
    TextView total;
    TextView address, mobile, username;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_order, container, false);
        recyclerView = view.findViewById(R.id.productsinOrder);
        orderid = view.findViewById(R.id.orderId);
        total = view.findViewById(R.id.txttotal);
        username = view.findViewById(R.id.txtusername);
        custProgressbar = new CustProgressbar();
        customAlert = new CustomAlert();
        accept = view.findViewById(R.id.txtaccept);
        mobile = view.findViewById(R.id.txtphone);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customAlert.show_alert(getContext(), ViewOrder.this::onAcceptClick);
            }


        });
        reject = view.findViewById(R.id.txtrrject);
        address = view.findViewById(R.id.txtaddress);

        from = ((HomeActivity) getActivity()).getFrom();
        selected_id = ((HomeActivity) getActivity()).getSelected_id();
        if (from.equals("Pending")) {
            pendingOrder = ((HomeActivity) getActivity()).getPendingOrder();
            orderid.setText("Order: " + selected_id);
//            total.setText("Total: Nrs "+pendingOrder.getData().get(0).getTotal());
//            address.setText("Deliver to : " +pendingOrder.getData().get(0).getAddressId());
            //mobile.setText(pendingOrder.g().get(0).get);

        } else if (from.equals("Delivered")) {
            deliveredOrders = ((HomeActivity) getActivity()).getDeliveredOrders();
            accept.setVisibility(View.INVISIBLE);
            reject.setVisibility(View.INVISIBLE);
            orderid.setText("Order: " + selected_id);
//            total.setText("Total: Nrs "+deliveredOrders.getData().get(0).getTotal());
//            address.setText("Deliver to : " +deliveredOrders.getData().get(0).getAddressId());
        }


        loadOrderDetails();


        return view;
    }

    public void loadOrderDetails() {
        custProgressbar.PrograssCreate(getContext());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", "1");
            jsonObject.put("oid", selected_id);
            JsonParser jsonParser = new JsonParser();
            Call<OrderDetails> call = APIClient.getInterface().getOrderbyId((JsonObject) jsonParser.parse(jsonObject.toString()));
            call.enqueue(new Callback<OrderDetails>() {
                @Override
                public void onResponse(Call<OrderDetails> call, Response<OrderDetails> response) {
                    orderDetails = response.body();

                    ((HomeActivity) getActivity()).setOrderDetails(orderDetails);
                    layoutManager = new GridLayoutManager(getContext(), 1);
                    recyclerView.setLayoutManager(layoutManager);
                    productsAdapter = new ProductsAdapter(orderDetails, getContext());
                    recyclerView.setAdapter(productsAdapter);
                    recyclerView.setHasFixedSize(false);
                    custProgressbar.ClosePrograssBar();
                    if(orderDetails.getData().get(0).getStatus().equals("pending")){

                    }else{
                        accept.setVisibility(View.INVISIBLE);
                        reject.setVisibility(View.INVISIBLE);
                    }

                    total.setText("Total: Nrs " + orderDetails.getData().get(0).getTotal()+" Status : "+orderDetails.getData().get(0).getStatus());
                    //address.setText("Deliver to : " +orderDetails.getData().get(0).getAddressId());
                    mobile.setText("Phone: " + orderDetails.getUser().getMobile());
                    username.setText("Username: "+orderDetails.getUser().getName());
                    loaduserAddress();

                }

                @Override
                public void onFailure(Call<OrderDetails> call, Throwable t) {
                    Log.d(" Call Failed ", t.toString() + "");
                    Toast.makeText(getContext(), "Error loading data", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public void loaduserAddress() {
        custProgressbar.PrograssCreate(getContext());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", "1");
            String check = orderDetails.getData().get(0).getAddressId();
            jsonObject.put("aid", check);
            JsonParser jsonParser = new JsonParser();
            Call<AddressResponse> call = APIClient.getInterface().getAddress((JsonObject) jsonParser.parse(jsonObject.toString()));
            call.enqueue(new Callback<AddressResponse>() {
                @Override
                public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                    //address.setText(response.body().getData().getSociety())+""+response.body().getData().getSociety();
                    address.setText("Deliver to: " + response.body().getData().getSociety() + " " + response.body().getData().getArea() + " " + response.body().getData().getLandmark());
                    custProgressbar.ClosePrograssBar();


                }

                @Override
                public void onFailure(Call<AddressResponse> call, Throwable t) {
                    Log.d("Error==>", t.getMessage());

                }
            });


        } catch (Exception ex) {
            Log.d("Error==>", ex.getMessage().toString());
        }


    }


    @Override
    public void onAcceptClick(String orderid) {
        customAlert.dismiss_alert(getContext());
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Delivery(), "Delivery").commit();


    }
}