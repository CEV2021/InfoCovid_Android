package com.example.infocovid.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infocovid.InfoCovidMiniWidget;
import com.example.infocovid.datalayer.model.Point;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.RegionList;
import com.example.infocovid.datalayer.model.SearchData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeneralHelper {

    public static Region getCurrentRegionByGPS(Context context) {
        Region gpsRegion = null;

        // create class object
        GPSTracker gpsTracker = new GPSTracker(context);

        // check if GPS enabled
        if(gpsTracker.canGetLocation()){

            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());
            ArrayList<Point> coordinatesList = new ArrayList<Point>();

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                                String city = addresses.get(0).getLocality();
                String regionName = addresses.get(0).getAdminArea();
//
                RegionList regionList = new RegionList();
                regionList.regions = PreferencesManager.getRegions(context);
                gpsRegion = regionList.getRegionByName(regionName);

                if (gpsRegion != null) {
                    // setting the id to -1 so we now it's not a regular region
                    gpsRegion.setId(-1);
                    PreferencesManager.setCurrentRegion(context, gpsRegion);
                    // setting the gps region as the favorite region
                    SearchData searchData = PreferencesManager.getSearchData(context);
                    if (searchData == null) {
                        searchData = new SearchData();
                    }
                    searchData.setMyFavoriteRegion(gpsRegion);
                    PreferencesManager.setSearchData(context, searchData);
                } else {
                    // debug message for when it fails
                    Log.e("GPS Region", "Error: could not set region as >> " + address);
                }

                // debug message to show when the location is calculated
                Toast.makeText(context, "Your Location is " + regionName, Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsTracker.showSettingsAlert();
        }

        return gpsRegion;
    }

    public static Region removeCurrentRegionByGPS(Context context) {
        Region gpsRegion = null;
        PreferencesManager.setCurrentRegion(context, gpsRegion);
        SearchData searchData = PreferencesManager.getSearchData(context);
        if (searchData == null) {
            searchData = new SearchData();
        }
        searchData.setMyFavoriteRegion(gpsRegion);
        PreferencesManager.setSearchData(context, searchData);

        return gpsRegion;
    }

    public static void coordinatesTester(View view) {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(view.getContext(), Locale.getDefault());
        ArrayList<Point> coordinatesList = new ArrayList<Point>();

        try {
            Point coordenadas = new Point(37.3828300, -5.9731700);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            String comunidad = addresses.get(0).getAdminArea();
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(41.6560600, -0.8773400);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(43.3602900, -5.8447600);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(39.5693900, 2.6502400);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(39.4697500, -0.3773900);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(28.0997300, -15.4134300);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(43.4647200, -3.8044400);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(39.8581000, -4.0226300);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(41.6551800, -4.7237200);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(41.3887900, 2.1589900);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(35.8902800, -5.3075000);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(39.1666700, -6.1666700);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(42.2328200, -8.7226400);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(42.4666700, -2.4500000);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(40.4165000, -3.7025600);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(35.2936900, -2.9383300);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(37.9870400, -1.1300400);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(42.8168700, -1.6432300);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);

            coordenadas = new Point(43.2627100, -2.9252800);
            addresses = geocoder.getFromLocation(coordenadas.latitude, coordenadas.longitude, 1);
            comunidad = addresses.get(0).getAdminArea();
            Log.e("Comunidad ", comunidad);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Normalizes the region name based on google data.
     * Method to convert region names data to google location standard so we don't have issues when using GPS to set the current location.
     * @param regionName
     * @return
     */
    public static String normalizeRegionName(String regionName) {
        // this is the code to get the current locale
        // String currentLocale = Locale.getDefault().getLanguage();

        // this is in case we want to control the names based on the locale
//        if (Locale.getDefault().getLanguage().equals(new Locale("es").getLanguage())) {
//
//        }

        String nameToReturn = "";
         switch (regionName) {
             case "Andalusia":
                 nameToReturn = "Andalucía";
                 break;
             case "Aragon":
                 nameToReturn = "Aragón";
                 break;
             case "Asturias":
                 nameToReturn = "Principado de Asturias";
                 break;
             case "Baleares":
                 nameToReturn = "Islas Baleares";
                 break;
             case "C. Valenciana":
                 nameToReturn = "Comunidad Valenciana";
                 break;
             case "Canarias":
                 nameToReturn = "Canarias";
                 break;
             case "Cantabria":
                 nameToReturn = "Cantabria";
                 break;
             case "Castilla - La Mancha":
                 nameToReturn = "Castilla-La Mancha";
                 break;
             case "Castilla y Leon":
                 nameToReturn = "Castilla y León";
                 break;
             case "Catalonia":
                 nameToReturn = "Catalunya";
                 break;
             case "Ceuta":
                 nameToReturn = "Ceuta";
                 break;
             case "Extremadura":
                 nameToReturn = "Extremadura";
                 break;
             case "Galicia":
                 nameToReturn = "Galicia";
                 break;
             case "La Rioja":
                 nameToReturn = "La Rioja";
                 break;
             case "Madrid":
                 nameToReturn = "Comunidad de Madrid";
                 break;
             case "Melilla":
                 nameToReturn = "Melilla";
                 break;
             case "Murcia":
                 nameToReturn = "Región de Murcia";
                 break;
             case "Navarra":
                 nameToReturn = "Navarra";
                 break;
             case "Pais Vasco":
                 nameToReturn = "Euskadi";
                 break;
         }


        return nameToReturn;
    }

    public static void updateAllWidgets(View view){
        // Getting context
        Context context = view.getContext();
        // Extracting the widget IDs to update
        ComponentName name = new ComponentName(context, InfoCovidMiniWidget.class);
        int [] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(name);
        // Creating the intent to trigger the update
        Intent intentUpdate = new Intent(context, InfoCovidMiniWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Adding the ids
//        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        // Wrapping the intent in a pending
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(
                context, 1, intentUpdate,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

//    Activity myActivity;
//
//    public void setupUI(View view) {
//
//        // Set up touch listener for non-text box views to hide keyboard.
//        if (!(view instanceof EditText)) {
//            view.setOnTouchListener(new OnTouchListener() {
//                public boolean onTouch(View v, MotionEvent event) {
//                    hideSoftKeyboard(MyActivity.this);
//                    return false;
//                }
//            });
//        }
//
//        //If a layout container, iterate over children and seed recursion.
//        if (view instanceof ViewGroup) {
//            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
//                View innerView = ((ViewGroup) view).getChildAt(i);
//                setupUI(innerView);
//            }
//        }
//    }
//
//    public static void hideSoftKeyboard(Activity activity) {
//        InputMethodManager inputMethodManager =
//                (InputMethodManager) activity.getSystemService(
//                        Activity.INPUT_METHOD_SERVICE);
//        if(inputMethodManager.isAcceptingText()){
//            inputMethodManager.hideSoftInputFromWindow(
//                    activity.getCurrentFocus().getWindowToken(),
//                    0
//            );
//        }
//    }
}
