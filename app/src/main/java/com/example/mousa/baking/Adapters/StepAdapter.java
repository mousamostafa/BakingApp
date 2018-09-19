package com.example.mousa.baking.Adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
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
import com.example.mousa.baking.DetailActivity;
import com.example.mousa.baking.Fragments.ShowDetail;
import com.example.mousa.baking.MainActivity;
import com.example.mousa.baking.Models.Model;
import com.example.mousa.baking.R;
import com.example.mousa.baking.ShowActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.MovieHolder> {
private Context context;
private ArrayList<Model> list = new ArrayList<Model>();
 public static ArrayList<Model> arrayList=new ArrayList<Model>();
public static int po=0;
public static boolean bt;

    public StepAdapter(Context context, ArrayList<Model> list) {
        this.context = context;
        this.list = list;
        arrayList=list;

    }

    static class MovieHolder extends RecyclerView.ViewHolder {
        TextView itemname;
        ImageView imageView;
        LinearLayout linearLayout;

        MovieHolder(View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.ll);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            imageView=itemView.findViewById(R.id.row);
            itemname = (TextView) itemView.findViewById(R.id.bakitem);

        }
}

    @NonNull
    @Override
    public StepAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemrow, parent, false);
        return new StepAdapter.MovieHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull StepAdapter.MovieHolder holder, final int position) {
        holder.itemname.setText(list.get(position).getShortDescription());
        if(!list.get(position).getThumbnailURL().isEmpty()){
            holder.imageView.setVisibility(View.VISIBLE);
            Picasso.with(context).load(list.get(position).getThumbnailURL()).into(holder.imageView);
        }
        else {
            holder.imageView.setVisibility(View.INVISIBLE);
        }




        holder.itemname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!MainActivity.isTablet){
                    Intent intent = new Intent(context, ShowActivity.class);
                    po=position;
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("des",list.get(position).getDescription());
                    intent.putExtra("Vid",list.get(position).getVideoURL());
                    intent.putExtra("Th",list.get(position).getThumbnailURL());
                    context.startActivity(intent);
                }
                else if(MainActivity.isTablet && DetailActivity.IsPort) {
                    Intent intent = new Intent(context, ShowActivity.class);
                    po=position;
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("des",list.get(position).getDescription());
                    intent.putExtra("Vid",list.get(position).getVideoURL());
                    intent.putExtra("Th",list.get(position).getThumbnailURL());
                    context.startActivity(intent);
                }
                else {

                    po=position;
                    ShowDetail showDetail=new ShowDetail();
                    android.app.FragmentManager manager=((Activity)context).getFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.replace(R.id.ShowContainer,showDetail);
                    transaction.commit();

                }



            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}



