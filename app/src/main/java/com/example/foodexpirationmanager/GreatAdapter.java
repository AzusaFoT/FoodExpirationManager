package com.example.foodexpirationmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//超偉大處理器
public class GreatAdapter extends RecyclerView.Adapter<GreatAdapter.DataViewHolder> {
    
    private Context context;
    private ArrayList ID,objType,name,tag,buyDate,expiration,num,ps,archived,timelimit;

    GreatAdapter(Context context,
                ArrayList ID,
                ArrayList objType,
                ArrayList name,
                ArrayList tag,
                ArrayList buyDate,
                ArrayList expiration,
                ArrayList num,
                ArrayList ps,
                ArrayList archived,
                ArrayList timelimit){
        this.context = context;
        this.ID = ID;
        this.objType = objType;
        this.name = name;
        this.tag = tag;
        this.buyDate = buyDate;
        this.expiration = expiration;
        this.num = num;
        this.ps = ps;
        this.archived = archived;
        this.timelimit = timelimit;
    }
    
    
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_page_recycler,parent,false);
        return new GreatAdapter.DataViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull GreatAdapter.DataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String photo;
        int[] sort_images;
        int photo_num=0;
        sort_images = new int[]{R.drawable.deli, R.drawable.sauce,
                R.drawable.freshfood, R.drawable.dessert,R.drawable.otherfoodicon,R.drawable.drink};

        photo=String.valueOf(objType.get(position));
        if(photo.equals("deli")) photo_num=0;
        if(photo.equals("sauce")) photo_num=1;
        if(photo.equals("freshfood"))photo_num=2;
        if(photo.equals("dessert"))photo_num=3;
        if(photo.equals("otherfoodicon"))photo_num=4;
        if(photo.equals("drink"))photo_num=5;


        holder.sortIdTextView.setBackground(ContextCompat.getDrawable(context.getApplicationContext(), sort_images[photo_num]));
        holder.itemNameTextView.setText(String.valueOf(name.get(position)));
        holder.dateTextView.setText(String.valueOf(expiration.get(position)));

        //item的點擊事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent ();
                i.setClass (view.getContext (),dataInsertPageActivity.class);
                i.putExtra("ID", String.valueOf(ID.get(position)));
                i.putExtra("objType", String.valueOf(objType.get(position)));
                i.putExtra("name",String.valueOf(name.get(position)));
                i.putExtra("tag",String.valueOf(tag.get(position)));
                i.putExtra("buyDate",String.valueOf(buyDate.get(position)));
                i.putExtra("expiration",String.valueOf(expiration.get(position)));
                i.putExtra("num",String.valueOf(num.get(position)));
                i.putExtra("ps",String.valueOf(ps.get(position)));
                i.putExtra("archived",String.valueOf(archived.get(position)));
                i.putExtra("timelimit","");
                view.getContext ().startActivity (i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView sortIdTextView,dateTextView,itemNameTextView;



        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            sortIdTextView = itemView.findViewById(R.id.sort_photo_TextView);
            dateTextView = itemView.findViewById(R.id.date_TextView);
            itemNameTextView = itemView.findViewById(R.id.item_Name_TextView);


        }
    }
}
