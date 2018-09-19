package com.example.mousa.baking.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


import com.example.mousa.baking.Adapters.Adapter;
import com.example.mousa.baking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
   public static String x="ffff";
   static String word ;
    static  JSONArray ingerdientList;
    {

        try {
            ingerdientList = new JSONArray(Adapter.dd);
            word="";
            for (int i = 0; i < ingerdientList.length(); i++) {
                JSONObject jsonObject = ingerdientList.getJSONObject(i);
                int quantity = jsonObject.getInt("quantity");
                String measure = jsonObject.getString("measure");
                String ingredient = jsonObject.getString("ingredient");

                word  += String.valueOf(quantity)+" "+measure+"\n"+ingredient+"\n";
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

   static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId){

       CharSequence widgetText = context.getString(R.string.appwidget_text);
       // Construct the RemoteViews object
       RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
       views.setTextViewText(R.id.appwidget_text, word);
       // Instruct the widget manager to update the widget
       //Create an Intent with the AppWidgetManager.ACTION_APPWIDGET_UPDATE action//

       Intent intentUpdate = new Intent(context, NewAppWidget.class);
       intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

//Update the current widget instance only, by creating an array that contains the widget’s unique ID//

       int[] idArray = new int[]{appWidgetId};
       intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

//Wrap the intent as a PendingIntent, using PendingIntent.getBroadcast()//

       PendingIntent pendingUpdate = PendingIntent.getBroadcast(
               context, appWidgetId, intentUpdate,
               PendingIntent.FLAG_UPDATE_CURRENT);
       views.setOnClickPendingIntent(R.id.appwidget_text, pendingUpdate);
       appWidgetManager.updateAppWidget(appWidgetId, views);
   }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

