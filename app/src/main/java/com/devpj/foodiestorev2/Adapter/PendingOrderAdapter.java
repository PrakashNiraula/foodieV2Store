package com.devpj.foodiestorev2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpj.foodiestorev2.R;
import com.devpj.foodiestorev2.model.PendingOrder;
import com.devpj.foodiestorev2.model.PendingOrderItem;
import com.kofigyan.stateprogressbar.StateProgressBar;


public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.MyViewHolder> {
    public Context context;
    public PendingOrder myorders;
    acceptOrderListener acceptOrderListener;

    public PendingOrderAdapter(Context context, PendingOrder order,acceptOrderListener acceptOrderListener) {
        this.context = context;
        this.myorders = order;
        this.acceptOrderListener=acceptOrderListener;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_item, parent, false);
        MyViewHolder myviewHolder = new MyViewHolder(view,acceptOrderListener);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PendingOrderItem row = myorders.getData().get(position);
        holder.txt_oderid.setText(row.getOid());
        holder.txt_dateandstatus.setText(row.getOrderDate()+" Status: " +row.getStatus());
        holder.txt_pricetotla.setText(row.getPprice());
        holder.txt_type.setText(row.getPtype());
        String[] descriptionData = {"Pending", "Accepted","Processing", "Delivering", "Delivered"};
        holder.progressBar.setStateDescriptionData(descriptionData);
        //StateProgressBar.StateNumber sn;
        StateProgressBar.StateNumber sn = null;
        if(row.getStatus().toString()=="cancelled"){

            holder.progressBar.setVisibility(View.INVISIBLE);
            return;
      }
        else{
            if (row.getStatus().toString().equals("pending")) {
                sn = StateProgressBar.StateNumber.ONE;
            } else if (row.getStatus().toString().equals("accepted")) {
                sn = StateProgressBar.StateNumber.TWO;
            } else if (row.getStatus().toString().equals("processing")) {
                sn = StateProgressBar.StateNumber.THREE;
            } else if (row.getStatus().toString().equals("delivering")) {
                sn = StateProgressBar.StateNumber.FOUR;
            }else if(row.getStatus().toString().equals("delivered")){
                sn = StateProgressBar.StateNumber.FIVE;
            }

            holder.progressBar.setCurrentStateNumber(sn);

        }

        // holder.progressBar


    }

    @Override
    public int getItemCount() {
        return myorders.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_oderid;
        TextView txt_dateandstatus;
        TextView txt_pricetotla;
        TextView txt_type;
        ImageView img_right;
        StateProgressBar progressBar;
        acceptOrderListener acceptOrderListener;

        public MyViewHolder(@NonNull View itemView,acceptOrderListener acceptOrderListener) {
            super(itemView);
            txt_oderid = itemView.findViewById(R.id.txt_oderid);
            txt_dateandstatus = itemView.findViewById(R.id.txt_dateandstatus);
            txt_pricetotla = itemView.findViewById(R.id.txt_pricetotla);
            txt_type = itemView.findViewById(R.id.txt_type);
            img_right = itemView.findViewById(R.id.img_right);

            progressBar = itemView.findViewById(R.id.time_view);
            img_right.setOnClickListener(this);
            this.acceptOrderListener=acceptOrderListener;
        }

        @Override
        public void onClick(View view) {

            // Toast.makeText(context, "sdkfskfsdf", Toast.LENGTH_SHORT).show();
            //acceptOrderListener.onAcceptClick
            acceptOrderListener.onAcceptClick(getAdapterPosition());


        }
    }


    public interface acceptOrderListener {
        void onAcceptClick(int position);
    }


    public void filterList(PendingOrder newlist){
        myorders=newlist;
        notifyDataSetChanged();
    }

}
