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

import Domain.CategoryDomain2;
import UserCategoryInterface.AnimalFoodInterface;
import UserCategoryInterface.BreadInterface;
import UserCategoryInterface.KitchenwareInterface;
import UserCategoryInterface.LaundryInterface;
import UserCategoryInterface.MedicineInterface;
import UserCategoryInterface.ToiletriesInterface;

public class  CategoryAdapter2 extends RecyclerView.Adapter<CategoryAdapter2.ViewHolder> {
    ArrayList<CategoryDomain2> categoryDomains2;
    Context context;

    public CategoryAdapter2(ArrayList<CategoryDomain2> categoryDomains2, Context context) {
        this.categoryDomains2 = categoryDomains2;
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
        holder.CategoryName.setText(categoryDomains2.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0:{
                picUrl="bread";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background1));
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, BreadInterface.class));
                    }
                });
                break;
            }

            case 1:{
                picUrl="medicine";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background2));
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, MedicineInterface.class));
                    }
                });
                break;
            }

            case 2:{
                picUrl="laundry";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background3));
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, LaundryInterface.class));
                    }
                });
                break;
            }

            case 3:{
                picUrl="toiletries";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background4));
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, ToiletriesInterface.class));
                    }
                });
                break;
            }

            case 4:{
                picUrl="kitchenware";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background5));
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, KitchenwareInterface.class));
                    }
                });
                break;
            }

            case 5:{
                picUrl="animalfood";
                holder.MainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background1));
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, AnimalFoodInterface.class));
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

        return categoryDomains2.size();
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
