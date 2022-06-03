package com.devpj.foodiestorev2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpj.foodiestorev2.R;
import com.devpj.foodiestorev2.model.DeliveredOrders;
import com.devpj.foodiestorev2.model.PendingOrder;
import com.devpj.foodiestorev2.model.PendingOrderItem;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class DeliveredOrderAdapter extends RecyclerView.Adapter<DeliveredOrderAdapter.deliveredViewholder> {

    DeliveredOrders deliveredOrders;
    Context context;
    onViewListener onViewListener;

    public DeliveredOrderAdapter(Context context, DeliveredOrders deliveredOrders, onViewListener onViewListener) {
        this.deliveredOrders = deliveredOrders;
        this.context = context;
        this.onViewListener = onViewListener;

    }

    @NonNull
    @Override
    public deliveredViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_item, parent, false);
        // MyviewHolder myviewHolder = new MyviewHolder(view, productListener, onAddtocartListener);
        //myviewHolder = new PendingOrderAdapter.MyViewHolder(view);
        deliveredViewholder deliveredViewholder = new deliveredViewholder(view, onViewListener);
        return deliveredViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull deliveredViewholder holder, int position) {
        PendingOrderItem row = deliveredOrders.getData().get(position);
        holder.txt_oderid.setText(row.getOid());
        holder.txt_dateandstatus.setText(row.getOrderDate());
        holder.txt_pricetotla.setText(row.getPprice());
        holder.txt_type.setText(row.getPtype());
        String[] descriptionData = {"Accepted","Processing", "Delivering", "Delivered"};
        holder.progressBar.setStateDescriptionData(descriptionData);
        holder.progressBar.setVisibility(View.INVISIBLE);





//        StateProgressBar.StateNumber sn=null;
//        if (row.getStatus().toString().equals("accepted")) {
//            sn = StateProgressBar.StateNumber.ONE;
//        } else if (row.getStatus().toString().equals("processing")) {
//            sn = StateProgressBar.StateNumber.TWO;
//        } else if (row.getStatus().toString().equals("delivering")) {
//            sn = StateProgressBar.StateNumber.THREE;
//        } else if (row.getStatus().toString().equals("delivered")) {
//            sn = StateProgressBar.StateNumber.FOUR;
//        }
//       holder.progressBar.setCurrentStateNumber(sn);
        // holder.progressBar


    }

    @Override
    public int getItemCount() {
        return deliveredOrders.getData().size();
    }

    public class deliveredViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_oderid;
        TextView txt_dateandstatus;
        TextView txt_pricetotla;
        TextView txt_type;
        ImageView img_right;
        StateProgressBar progressBar;
        onViewListener onViewListener;


        public deliveredViewholder(@NonNull View itemView, onViewListener onViewListener) {
            super(itemView);
            txt_oderid = itemView.findViewById(R.id.txt_oderid);
            txt_dateandstatus = itemView.findViewById(R.id.txt_dateandstatus);
            txt_pricetotla = itemView.findViewById(R.id.txt_pricetotla);
            txt_type = itemView.findViewById(R.id.txt_type);
            img_right = itemView.findViewById(R.id.img_right);
            img_right.setOnClickListener(this);
            progressBar = itemView.findViewById(R.id.time_view);
            img_right.setOnClickListener(this::onClick);
            this.onViewListener = onViewListener;
        }

        @Override
        public void onClick(View view) {

            onViewListener.OnView(getAdapterPosition());
        }


    }


    public interface onViewListener {
        void OnView(int position);
    }


    public void filterList(DeliveredOrders newlist) {
        deliveredOrders = newlist;
        notifyDataSetChanged();
    }
}
