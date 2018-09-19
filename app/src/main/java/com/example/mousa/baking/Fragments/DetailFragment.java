package com.example.mousa.baking.Fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.mousa.baking.Adapters.IngreditAdapter;
import com.example.mousa.baking.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class DetailFragment extends Fragment {
    JSONArray ingerdientList;
    ArrayList<String> ingrdlist=new ArrayList<String>();



    //----------Make Constructor --------------------
    public DetailFragment(){}
    //---------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.detail,container,false);


        //---------Defination LayoutManager---------------------------------
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,false);

        //--------------------------------------------------------------------------------------

        RecyclerView mIngreditList=(RecyclerView)view.findViewById(R.id.ingrdt);// Recycleview For Ingredient---------------
        mIngreditList.setLayoutManager(layoutManager);
        mIngreditList.setHasFixedSize(true);

//-----------------------------------------//-----------------------------------

        try {


            if (this.getArguments() != null) {
                ingerdientList = new JSONArray(this.getArguments().getString("Ing"));

            }

            for (int i=0;i<ingerdientList.length();i++){
                JSONObject jsonObject=ingerdientList.getJSONObject(i);
                int quantity=jsonObject.optInt("quantity");
                String measure=jsonObject.optString("measure");
                String ingredient=jsonObject.optString("ingredient");
                String word="";
                word=String.valueOf(quantity)+" "+measure+"\n"+ingredient;
                ingrdlist.add(word);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    //---------------SetAdapter For RecycleView for Ingredient//----------------------------------

        IngreditAdapter ingreditAdapter=new IngreditAdapter(getActivity(),ingrdlist);
        mIngreditList.setAdapter(ingreditAdapter);
        //-------------------------------------------------------

        return view;

    }
}
