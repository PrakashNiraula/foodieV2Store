package com.devpj.foodiestorev2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devpj.foodiestorev2.Activity.HomeActivity;
import com.devpj.foodiestorev2.Adapter.DeliveredOrderAdapter;
import com.devpj.foodiestorev2.Adapter.PendingOrderAdapter;
import com.devpj.foodiestorev2.R;
import com.devpj.foodiestorev2.model.DeliveredOrders;
import com.devpj.foodiestorev2.model.PendingOrder;
import com.devpj.foodiestorev2.retrofit.APIClient;
import com.devpj.foodiestorev2.utils.CustProgressbar;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Delivered extends Fragment implements DeliveredOrderAdapter.onViewListener {

     RecyclerView recycle_delivered;
    RecyclerView.LayoutManager layoutManager;
    DeliveredOrderAdapter deliveredOrderAdapter;
    DeliveredOrders deliveredOrders;
    CustProgressbar custPrograssbar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_delivered, container, false);
       recycle_delivered=view.findViewById(R.id.recycle_delivered);
       swipeRefreshLayout=view.findViewById(R.id.swiperefreshdelivered);
        custPrograssbar = new CustProgressbar();
      deliveredOrders=((HomeActivity) getActivity()).getDeliveredOrders();
      if(deliveredOrders==null){
          loaddelivered();
      }else{
          layoutManager=new GridLayoutManager(getContext(),1);
          recycle_delivered.setLayoutManager(layoutManager);
          //save for on click listener
          // allproducts=response.body();
          deliveredOrderAdapter=new DeliveredOrderAdapter(getContext(),deliveredOrders,Delivered.this::OnView);
          recycle_delivered.setAdapter(deliveredOrderAdapter);
          recycle_delivered.setHasFixedSize(false);
      }
      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
              swipeRefreshLayout.setRefreshing(true);
              loaddelivered();
              swipeRefreshLayout.setRefreshing(false);

          }
      });



       return view;
    }


    public void loaddelivered(){

        custPrograssbar.PrograssCreate(getContext());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", "1");
            JsonParser jsonParser = new JsonParser();
            Call<DeliveredOrders> call = APIClient.getInterface().getDelivered((JsonObject) jsonParser.parse(jsonObject.toString()));
            call.enqueue(new Callback<DeliveredOrders>() {
                @Override
                public void onResponse(Call<DeliveredOrders> call, Response<DeliveredOrders> response) {
                    if(response.isSuccessful()){
                        deliveredOrders=response.body();
                        ((HomeActivity) getActivity()).setDeliveredOrders(deliveredOrders);

                        layoutManager=new GridLayoutManager(getContext(),1);
                        recycle_delivered.setLayoutManager(layoutManager);
                        //save for on click listener
                        // allproducts=response.body();
                        deliveredOrderAdapter=new DeliveredOrderAdapter(getContext(),deliveredOrders,Delivered.this::OnView);
                        recycle_delivered.setAdapter(deliveredOrderAdapter);
                        recycle_delivered.setHasFixedSize(false);
                        custPrograssbar.ClosePrograssBar();



                    }
                }

                @Override
                public void onFailure(Call<DeliveredOrders> call, Throwable t) {

                }
            });

        }catch (Exception ex){

        }

    }

    @Override
    public void OnView(int position) {
        boolean res = ((HomeActivity) getActivity()).setDeliveredOrders(deliveredOrders);
        boolean res2 = ((HomeActivity) getActivity()).setFrom("Delivered");
        boolean res3 = ((HomeActivity) getActivity()).setSelected_id(deliveredOrders.getData().get(position).getOid());
        //boolean res = ((HomeActivity) getActivity()).se(d);
        if(res){
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_frame, new ViewOrder(), "ViewOrder").addToBackStack("ViewOrder").commit();

        }


    }
}