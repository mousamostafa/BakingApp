package com.example.mousa.baking;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.mousa.baking.Adapters.Adapter;
import com.example.mousa.baking.Models.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;


public class MainActivity extends AppCompatActivity {
    public static Bundle bundle;
    public static Context context;
    //---------Defination Attribute ----------------------------------------------

    private String name;
    public static ArrayList<Model> modelArrayList=new ArrayList<Model>();
    public RecyclerView MainList;
    public RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    JSONArray steps;
    JSONArray ingredients;
    String im;
  public static boolean isTablet;

//------------Method OnCreate---------------------------//--------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        bundle=savedInstanceState;
         isTablet = getResources().getBoolean(R.bool.isTablet); // Get from bool file
        MainList = (RecyclerView) findViewById(R.id.mainlist);


//---------------check device is tablet or phone-----------------------------
        if (isTablet) {
            layoutManager = new GridLayoutManager(getApplicationContext(),2);


        } else {
            layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);
        }
       // ------------------------------------------------//----------------------------

        MainList.setLayoutManager(layoutManager);
        MainList.setHasFixedSize(true);

        //------------------------for Rotation --------------------------------------------
        if(savedInstanceState==null){
            GetJson();
        }
       else {
            GetJson();
            Toast.makeText(getApplicationContext(),"savv!=null",Toast.LENGTH_SHORT).show();
       }
       //-------------------------------------------------------//----------------------------------


       }



///------------------------------interface For Method Get-------------------

  public interface GetData {
        @GET("baking.json")
        Call<ResponseBody> get();
    }

    //-----------------------GetData From ApI By Retrofit*-------------------------------------

    void GetJson() {
        modelArrayList.clear();// clear list because Redundancy----------------------------

            Retrofit retrofit = new Retrofit.Builder().baseUrl(getApplicationContext().getString(R.string.BaseUrl)).build();
            GetData getData = retrofit.create(GetData.class);
            Call<ResponseBody> call = getData.get();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        for (int i = 0 ; i < jsonArray.length(); i++) {
                             JSONObject jsonObject = jsonArray.getJSONObject(i);
                              name = jsonObject.optString("name");
                              im=jsonObject.optString("image");
                              ingredients = jsonObject.getJSONArray("ingredients");
                            steps = jsonObject.getJSONArray("steps");

                            modelArrayList.add(new Model(name,ingredients,steps,im));
                        }
                        //-----set Adapter For Recycleview (MainList) ---------------------------------
                        adapter=new Adapter(getApplicationContext(),modelArrayList);
                        MainList.setAdapter(adapter);
                    //-------------------------------------------------------------------

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                     t.printStackTrace();
                }
            });
        }
        //------------------------------------------------//----------------------------------------------------

}
