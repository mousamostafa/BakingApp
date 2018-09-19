package com.example.mousa.baking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mousa.baking.R;
import java.util.ArrayList;

public class IngreditAdapter extends RecyclerView.Adapter<IngreditAdapter.MovieHolder> {
    private Context context;
    private ArrayList<String> ingredlist = new ArrayList<String>();
    public IngreditAdapter(Context context, ArrayList<String> itemlist) {
        this.context = context;
        this.ingredlist = itemlist;
    }

    static class MovieHolder extends RecyclerView.ViewHolder {
        TextView itemname;
        ImageView imageView;
        MovieHolder(View itemView) {
            super(itemView);
            itemname = (TextView) itemView.findViewById(R.id.bakitem);
            imageView=(ImageView)itemView.findViewById(R.id.row);
            imageView.setVisibility(View.VISIBLE);



        }
    }

    @NonNull
    @Override
    public IngreditAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemrow, parent, false);
        return new IngreditAdapter.MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngreditAdapter.MovieHolder holder, final int position) {
      holder.itemname.setText(ingredlist.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredlist.size();
    }

}




