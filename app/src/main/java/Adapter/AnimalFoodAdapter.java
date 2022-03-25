package Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.customerinterface.R;
import com.example.customerinterface.ShowDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import Model.Products;

public class AnimalFoodAdapter extends FirebaseRecyclerAdapter<Products, AnimalFoodAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AnimalFoodAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Products products) {
        Glide.with(holder.DisPicPro.getContext()).load(products.getImageUrl()).into(holder.DisPicPro);
        holder.DisTitlePro.setText(products.getProductName());
        holder.DisPricePro.setText(products.getProductPrice());
        holder.DisShopPro.setText(products.getShopName());

        holder.addPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", products);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cate_item_adapter, parent, false);
        return new ViewHolder(inflate);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView DisPicPro;
        TextView DisTitlePro, DisPricePro, DisShopPro, addPro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DisPicPro = itemView.findViewById(R.id.pic_cate_item);
            DisTitlePro = itemView.findViewById(R.id.ct_title_item);
            DisPricePro = itemView.findViewById(R.id.ct_price_item);
            DisShopPro = itemView.findViewById(R.id.ct_shopName_item);
            addPro = itemView.findViewById(R.id.ct_addBtn_item);
        }
    }
}
