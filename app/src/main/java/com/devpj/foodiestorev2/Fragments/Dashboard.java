package com.devpj.foodiestorev2.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devpj.foodiestorev2.Activity.HomeActivity;
import com.devpj.foodiestorev2.Activity.LoginActivity;
import com.devpj.foodiestorev2.Adapter.PendingOrderAdapter;
import com.devpj.foodiestorev2.MainActivity;
import com.devpj.foodiestorev2.R;
import com.devpj.foodiestorev2.model.PendingOrder;
import com.devpj.foodiestorev2.model.PendingOrderItem;
import com.devpj.foodiestorev2.model.User;
import com.devpj.foodiestorev2.retrofit.APIClient;
import com.devpj.foodiestorev2.retrofit.GetResult;
import com.devpj.foodiestorev2.utils.CustProgressbar;
import com.devpj.foodiestorev2.utils.SessionManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dashboard extends Fragment implements PendingOrderAdapter.acceptOrderListener {

    RecyclerView pendingOrdersRecycler;
    RecyclerView.LayoutManager layoutManager;
    PendingOrderAdapter orderAdapter;
    HomeActivity homeActivity;
    //PendingOrder p;
    @BindView(R.id.recycle_pending)
    RecyclerView recyclePending;
    PendingOrder pendingOrder;
    SwipeRefreshLayout swipeRefreshLayout;


    CustProgressbar custPrograssbar;
    SessionManager sessionManager;
    User user;
    List<PendingOrderItem> pendinglistMain = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        pendingOrdersRecycler = view.findViewById(R.id.recycle_pending);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        custPrograssbar = new CustProgressbar();
        //homeActivity=new HomeActivity();
        pendingOrder = ((HomeActivity) getActivity()).getPendingOrder();
        if (pendingOrder == null) {
            getPendingOrder();
        } else {
            layoutManager = new GridLayoutManager(getContext(), 1);
            pendingOrdersRecycler.setLayoutManager(layoutManager);
            orderAdapter = new PendingOrderAdapter(getContext(), pendingOrder, Dashboard.this::onAcceptClick);
            pendingOrdersRecycler.setAdapter(orderAdapter);
            pendingOrdersRecycler.setHasFixedSize(false);

        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPendingOrder();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }

    private void getPendingOrder() {
        //custPrograssbar.PrograssCreate(getContext());
        Context context = getContext();
        custPrograssbar.PrograssCreate(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", "1");
            JsonParser jsonParser = new JsonParser();
            Call<PendingOrder> call = APIClient.getInterface().getpendingOrders((JsonObject) jsonParser.parse(jsonObject.toString()));

            call.enqueue(new Callback<PendingOrder>() {
                @Override
                public void onResponse(Call<PendingOrder> call, Response<PendingOrder> response) {

                    if (response.isSuccessful()) {

                        pendingOrder = response.body();
                        ((HomeActivity) getActivity()).setPendingOrder(pendingOrder);
                        layoutManager = new GridLayoutManager(getContext(), 1);
                        pendingOrdersRecycler.setLayoutManager(layoutManager);
                        orderAdapter = new PendingOrderAdapter(getContext(), pendingOrder, Dashboard.this::onAcceptClick);
                        pendingOrdersRecycler.setAdapter(orderAdapter);
                        pendingOrdersRecycler.setHasFixedSize(false);
                        custPrograssbar.ClosePrograssBar();

                    }


                }

                @Override
                public void onFailure(Call<PendingOrder> call, Throwable t) {
                    // throw  t.toString();
                    custPrograssbar.ClosePrograssBar();
                    Toast.makeText(getContext(), "Error Loading Data", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onAcceptClick(int position) {

        boolean res = ((HomeActivity) getActivity()).setPendingOrder(pendingOrder);
        boolean res2 = ((HomeActivity) getActivity()).setFrom("Pending");
        boolean res3 = ((HomeActivity) getActivity()).setSelected_id(pendingOrder.getData().get(position).getOid());


        if (res) {
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_frame, new ViewOrder(), "ViewOrder").addToBackStack("ViewOrder").commit();

        }

    }
}