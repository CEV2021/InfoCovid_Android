package com.example.infocovid;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;

/**
 * Implementation of App Widget functionality.
 */
public class InfoCovidMiniWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {



        // Creating the Intent for the MainActivity
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingMainActivityIntent = PendingIntent.getActivity(context, 0, mainActivityIntent, 0);

        // Construct the RemoteViews object
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.info_covid_mini_widget);

        // This code launches the app when clicking on the widget
        remoteView.setOnClickPendingIntent(R.id.coordinatorLayout, pendingMainActivityIntent);
        // This code updates the content of the widget
        Region currentRegion = PreferencesManager.getCurrentRegion(context);

        if (currentRegion != null) {
            // Here we get the latest data set
            int latest = currentRegion.getData().size() - 1;
            // Now we set the image depending on the incident
            if (currentRegion.getData().get(latest).getIncidentRate() < 50) {
                remoteView.setImageViewResource(R.id.appwidget_image_covid_status, R.mipmap.ic_greencovid);
            } else if (currentRegion.getData().get(latest).getIncidentRate() < 150) {
                remoteView.setImageViewResource(R.id.appwidget_image_covid_status, R.mipmap.ic_yellowcovid);
            } else {
                remoteView.setImageViewResource(R.id.appwidget_image_covid_status, R.mipmap.ic_redcovid);
            }
            // And here we set the values for the textViews for Region name and the number on incidence rate
            remoteView.setTextViewText(R.id.appwidget_region_name, currentRegion.getName());
            remoteView.setTextViewText(R.id.appwidget_region_ia_value, String.format("%.2f", currentRegion.getData().get(latest).getIncidentRate()));
        } else {

        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteView);

        // ---------------------------------


//        Intent clickServiceIntent = new Intent(context, ClickIntentService.class);
//        clickServiceIntent.setAction(ClickIntentService.ACTION_CLICK);
//
//        PendingIntent pendingClickServiceIntent = PendingIntent.getService(context, 0, clickServiceIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        remoteView.setOnClickPendingIntent(R.id.appwidget_text, pendingClickServiceIntent);
//
//        int clicks = context.getSharedPreferences("Preferences", context.MODE_PRIVATE).getInt("clicks", 0);
//
//        remoteView.setTextViewText(R.id.appwidget_text, String.valueOf(clicks));
//
//        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, remoteView);


        // ---------------------------------


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