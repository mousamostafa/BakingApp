package com.example.mousa.baking.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mousa.baking.DetailActivity;
import com.example.mousa.baking.Models.Model;
import com.example.mousa.baking.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class Adapter extends RecyclerView.Adapter<Adapter.MovieHolder> {
    private Context context;
    private ArrayList<Model> itemlist = new ArrayList<Model>();
    public static String dd="";

    public Adapter(Context context, ArrayList<Model> itemlist) {
        this.context = context;
        this.itemlist = itemlist;

    }

    static class MovieHolder extends RecyclerView.ViewHolder {
       TextView itemname;
        LinearLayout linearLayout;
        ImageView imageView;
        MovieHolder(View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.ll);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            itemname = (TextView) itemView.findViewById(R.id.bakitem);
            imageView=(ImageView)itemView.findViewById(R.id.row);
        }
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemrow, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, final int position) {
      if(!itemlist.get(position).getImg().isEmpty()){
          Picasso.with(context).load(itemlist.get(position).getImg()).into(holder.imageView);
      }
        holder.itemname.setText(itemlist.get(position).getName());


        holder.itemname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                dd=itemlist.get(position).getJsonElements().toString();
                intent.putExtra("In",itemlist.get(position).getJsonElements().toString());
                intent.putExtra("St",itemlist.get(position).getJsonArray().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);




            }
        });

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

}


