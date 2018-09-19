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
import com.example.mousa.baking.Adapters.StepAdapter;
import com.example.mousa.baking.Models.Model;
import com.example.mousa.baking.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Frgmentt extends Fragment {
    JSONArray Steplist;
    ArrayList<Model> liststep = new ArrayList<Model>();
  static LinearLayoutManager layoutManagerr;
  static StepAdapter stepAdapter;
    public Frgmentt() {

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail, container, false);
        RecyclerView mStepsList=(RecyclerView)view.findViewById(R.id.steps);// RecycleView For Steps-------------------
         layoutManagerr= new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);

        mStepsList.setLayoutManager(layoutManagerr);
        mStepsList.setHasFixedSize(true);
        try {
            Steplist = new JSONArray(this.getArguments().getString("Ste"));
            for (int x=0;x<Steplist.length();x++){
                JSONObject object=Steplist.getJSONObject(x);
                int id=object.optInt("id");
                String shortDescription=object.optString("shortDescription");
                String description=object.optString("description");
                String videoURL=object.optString("videoURL");
                if(videoURL==null){
                    videoURL="";
                }

                String thumbnailURL=object.optString("thumbnailURL");
                if(thumbnailURL==null){
                    thumbnailURL="";
                }
                liststep.add(new Model(shortDescription,description,videoURL,thumbnailURL));
            }


//-------------------SetAdapter For RecycleSteps-----------------
            stepAdapter=new StepAdapter(getActivity(),liststep);
            mStepsList.setAdapter(stepAdapter);
            //-------------------------------------------------------------

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}