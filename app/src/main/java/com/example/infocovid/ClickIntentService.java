package com.example.infocovid;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;

public class ClickIntentService extends IntentService {
    public static final String ACTION_CLICK = "com.thetehnocafe.gurleensethi.widgets.click";

    public ClickIntentService() {
        super("ClickIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            if (ACTION_CLICK.equals(action)) {
                handleClick();
            }
        }
    }

    private void handleClick() {
        int clicks = getSharedPreferences("Preferences", MODE_PRIVATE).getInt("clicks", 0);
        clicks++;
        getSharedPreferences("Preferences", MODE_PRIVATE)
                .edit()
                .putInt("clicks", clicks)
                .commit();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, InfoCovidMiniWidget.class));
        for (int appWidgetId : widgetIds) {
            InfoCovidMiniWidget.updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetId);
        }
    }
}
