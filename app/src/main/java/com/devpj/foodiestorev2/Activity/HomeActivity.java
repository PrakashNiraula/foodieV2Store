package com.devpj.foodiestorev2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.devpj.foodiestorev2.Fragments.Dashboard;
import com.devpj.foodiestorev2.Fragments.Delivered;
import com.devpj.foodiestorev2.Fragments.Notification;
import com.devpj.foodiestorev2.R;
import com.devpj.foodiestorev2.model.AppSettings;
import com.devpj.foodiestorev2.model.DeliveredOrders;
import com.devpj.foodiestorev2.model.OrderDetails;
import com.devpj.foodiestorev2.model.PendingOrder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    @BindView(R.id.fragment_frame)
    FrameLayout fragmentFrame;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    FragmentManager fragmentManager;
    DeliveredOrders deliveredOrders;
    PendingOrder pendingOrder;
    private String from;
    private  String selected_id;
    OrderDetails orderDetails;
    AppSettings appSettings;

    public AppSettings getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getSelected_id() {
        return selected_id;
    }

    public boolean setSelected_id(String selected_id) {
        this.selected_id = selected_id;
        return true;
    }

    public DeliveredOrders getDeliveredOrders() {
        return deliveredOrders;
    }

    public boolean setDeliveredOrders(DeliveredOrders deliveredOrders) {
        this.deliveredOrders = deliveredOrders;
        return true;
    }

    public PendingOrder getPendingOrder() {
        return pendingOrder;
    }

    public boolean setPendingOrder(PendingOrder pendingOrder) {
        this.pendingOrder = pendingOrder;
        return true;
    }

    public String getFrom() {
        return from;
    }

    public boolean setFrom(String from) {
        this.from = from;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        getSupportActionBar().hide();
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        bottomNavigation.setSelectedItemId(R.id.navigation_pendding);




    }

    public boolean callFragment(Fragment fragmentClass) {
        if (fragmentClass != null) {
            fragmentManager= getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentClass).addToBackStack(null).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_pendding:
                fragment = new Dashboard();
                break;

            case R.id.navigation_delivery:
                fragment = new Delivered();
                break;

            case R.id.navigation_notifications:
                fragment = new Notification();
                break;

           // case R.id.navigation_profile:
              //  fragment = new ProfileFragment();
                //break;
            default:
                break;
        }
        return callFragment(fragment);
    }
}