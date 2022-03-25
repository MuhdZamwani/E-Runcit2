package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import Model.Cart;

public class CartViewAdapter extends FirebaseRecyclerAdapter<Cart,CartViewAdapter.ViewHolder> {
    Context context;
    private List<Cart> cartList;

    public CartViewAdapter(FirebaseRecyclerOptions<Cart> options) { super(options); }

//    public CartViewAdapter(Context context, List<Cart> cart) {
//        super();
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card, parent, false);
//        return new ViewHolder(inflate);
        return null;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Cart cart) {
//        double OverPrice =0;
//        DecimalFormat dfZero = new DecimalFormat("0.00");
//
//        Glide.with(holder.pic.getContext()).load(cart.getImageUrl()).into(holder.pic);
//        holder.title.setText(cart.getProductName());
//        holder.feeEachItem.setText(new StringBuilder().append(cart.getProductPrice()));
//        holder.num.setText(new StringBuilder().append(cart.getQuantity()));
////        holder.totalEachItem.setText(new StringBuilder().append(cart.getTotalPrice() * cart.getQuantity()));
//        holder.totalEachItem.setText(String.valueOf(cart.getTotalPrice()));
//
//        OverPrice =+ cart.getTotalPrice();
//        Intent intent = new Intent("MyOverPrice");
//        intent.putExtra("OverPrice", (OverPrice));
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
//        holder.minusItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cart.setQuantity(cart.getQuantity()-1);
//                if(cart.getQuantity() >= 1){
////                    cart.setQuantity(cart.getQuantity()-1);
//                    cart.setTotalPrice(cart.getQuantity() * Float.parseFloat(cart.getProductPrice()));
//
//                    holder.num.setText(Integer.toString(cart.getQuantity()));
//
//                    double newValue = Double.valueOf(dfZero.format(cart.getTotalPrice()));
//                    Map<String, Object> UpData = new HashMap<>();
//                    UpData.put("Quantity",Integer.valueOf(cart.getQuantity()));
//                    UpData.put("TotalPrice",newValue);
//
//                    FirebaseDatabase.getInstance().getReference().child("Cart List")
//                            .child(cart.getUID())
//                            .updateChildren(UpData)
//                            .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
//                }else{
//                    FirebaseDatabase.getInstance().getReference().child("Cart List")
//                            .child(cart.getUID()).removeValue();
//                }
//            }
//        });
//
//        holder.plusItem.setOnClickListener(v -> {
//            cart.setQuantity(cart.getQuantity()+1);
//            cart.setTotalPrice(cart.getQuantity() * Float.parseFloat(cart.getProductPrice()));
//
//            holder.num.setText(Integer.toString(cart.getQuantity()));
//
//            double newValue = Double.valueOf(dfZero.format(cart.getTotalPrice()));
//            Map<String, Object> UpData = new HashMap<>();
//            UpData.put("Quantity",Integer.valueOf(cart.getQuantity()));
//            UpData.put("TotalPrice",newValue);
//
//            FirebaseDatabase.getInstance().getReference().child("Cart List")
//                    .child(cart.getUID())
//                    .updateChildren(UpData)
//                    .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
//        });


    }
    private void updateFirebase(Cart cart) {
//        FirebaseDatabase.getInstance().getReference("Cart List")
//                .child(cart.getUID())
//                .setValue(cart)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//
//                    }
//                });
//        Map<String, Object> UpData = new HashMap<>();
//        UpData.put("quantity",Integer.valueOf(cart.getQuantity()));
//        UpData.put("totalPrice",Double.valueOf(cart.getTotalPrice()));
//
//        FirebaseDatabase.getInstance().getReference().child("Cart List")
//                .orderByChild("CusUserName")
//                .equalTo(GlobalOnline.currentOnline.getUsername())
//                .updateChildren()

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView title, feeEachItem;
//        public ImageView pic, plusItem, minusItem;
//        public TextView totalEachItem, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            title = itemView.findViewById(R.id.title2Txt);
//            feeEachItem = itemView.findViewById(R.id.feeEachItem);
//            pic = itemView.findViewById(R.id.picCard);
//            totalEachItem = itemView.findViewById(R.id.totalEachItem);
//            num = itemView.findViewById(R.id.numberItemTxt);
//            plusItem = itemView.findViewById(R.id.plusCardBtn);
//            minusItem = itemView.findViewById(R.id.minusCardBtn);
        }
    }
}
