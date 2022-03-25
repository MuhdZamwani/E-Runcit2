package Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customerinterface.R;

import Interface.ItemClickListener;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView title, feeEachItem;
    public ImageView pic, plusItem, minusItem;
    public TextView totalEachItem, num;
    private ItemClickListener itemClickListener;
    public Button picintent;


    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title2Txt);
        feeEachItem = itemView.findViewById(R.id.feeEachItem);
        pic = itemView.findViewById(R.id.picCard);
        totalEachItem = itemView.findViewById(R.id.totalEachItem);
        num = itemView.findViewById(R.id.numberItemTxt);
        minusItem = itemView.findViewById(R.id.minusCardBtn);
        plusItem = itemView.findViewById(R.id.plusCardBtn);
        picintent = itemView.findViewById(R.id.checkOutViewBtn);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
