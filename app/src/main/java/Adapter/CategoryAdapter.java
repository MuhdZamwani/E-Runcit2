package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.customerinterface.R;

import java.util.ArrayList;

import Domain.CategoryDomain;
import UserCategoryInterface.BeverageInterface;
import UserCategoryInterface.RiceInterface;
import UserCategoryInterface.SnacksInterface;
import UserCategoryInterface.SpicesInterface;
import UserCategoryInterface.StationaryInterface;

public class  CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    ArrayList<CategoryDomain> categoryDomains;
    Context context;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains, Context context) {
        this.categoryDomains = categoryDomains;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.CategoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0:{
                picUrl="stationary";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background1));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, StationaryInterface.class));
                    }
                });
                break;
            }

            case 1:{
                picUrl="rice";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background2));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, RiceInterface.class));
                    }
                });
                break;
            }

            case 2:{
                picUrl="beverages";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background3));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, BeverageInterface.class));

                    }
                });
                break;
            }

            case 3:{
                picUrl="snack";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background4));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, SnacksInterface.class));
                    }
                });
                break;
            }

            case 4:{
                picUrl="spices";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background5));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, SpicesInterface.class));
                    }
                });
                break;
            }

        }

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.CategoryIcon);
    }

    @Override
    public int getItemCount(){

        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView CategoryName;
        ImageView CategoryIcon;
        ConstraintLayout MainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryName = itemView.findViewById(R.id.CategoryName);
            CategoryIcon = itemView.findViewById(R.id.CategoryIcon);
            MainLayout = itemView.findViewById(R.id.MainLayout);

        }
    }

}
