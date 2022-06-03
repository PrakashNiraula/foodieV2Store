package com.devpj.foodiestorev2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.devpj.foodiestorev2.R;
import com.devpj.foodiestorev2.model.DeliveredOrders;
import com.devpj.foodiestorev2.model.OrderDetails;
import com.devpj.foodiestorev2.retrofit.APIClient;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    public OrderDetails orderDetails;
    public Context Context;
    String[] quantities,pids,ptype,price,pname;

    public ProductsAdapter(OrderDetails orderDetails, Context context) {
        this.orderDetails = orderDetails;
        this.Context = context;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products, parent, false);
        ProductsAdapter.ProductViewHolder productViewHolder = new ProductsAdapter.ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

//        EditText txtProductName,txt_dateandqty,txt_pricetotl,txt_type;
//        ImageView productimage;

        holder.txtProductName.setText(orderDetails.getProducts().get(position).getPname());

        String url = APIClient.baseUrl + "/" + orderDetails.getProducts().get(position).getPimg();
        Glide.with(Context).load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.productimage);

        quantities=orderDetails.getData().get(0).getQty().split("\\$;");
       pids=orderDetails.getData().get(0).getPid().split("\\$;");
       ptype=orderDetails.getData().get(0).getPtype().split("\\$;");
        price=orderDetails.getData().get(0).getPprice().split("\\$;");
        pname=orderDetails.getData().get(0).getPname().split("\\$;");

        holder.txt_dateandqty.setText("Rate : Nrs "+price[position]+":: Qty :"+quantities[position]);

        float totalPrice=Float.parseFloat(price[position])*Float.parseFloat(quantities[position]);
holder.txt_type.setText("Nrs "+totalPrice+"");


    }

    @Override
    public int getItemCount() {
        return orderDetails.getProducts().size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductName, txt_dateandqty, txt_pricetotal, txt_type;
        ImageView productimage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txtProductName);
            txt_dateandqty = itemView.findViewById(R.id.txt_dateandqty);
            txt_pricetotal = itemView.findViewById(R.id.txt_pricetotla);
            txt_type = itemView.findViewById(R.id.txt_type);
            productimage = itemView.findViewById(R.id.productimage);


        }
    }


    public interface onViewListener {
        void OnView(int position);
    }

}
