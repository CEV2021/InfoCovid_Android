package com.example.infocovid.datalayer.model;

/* public class ConnectionManager extends AsyncTask<ArrayList<Region>, Void, ArrayList<Region>> {

    RegionService regionService =  ApiClient.getClient().create(RegionService.class);
    ArrayList<Region> regionsList;
    Activity activity;

    public ConnectionManager(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Region> doInBackground(ArrayList<Region>... regions) {

        regionsList = new ArrayList<Region>();

        Call<String> call = regionService.getRegions();

        call.enqueue(new Callback<ArrayList<Region>>() {
            @Override
            public void onResponse(Call<ArrayList<Region>> call, Response<ArrayList<Region>> response) {
                Log.e("onResponse", "Response -->" + response.body().toString());

                if(!response.body().isEmpty()) {
                    for(int region = 0 ; region < response.body().size(); region ++) {
                            regionsList.add(region, response.body().get(region));
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Region>> call, Throwable t) {
                Log.e("onFailure", "Error " + t.getLocalizedMessage());
            }
        });

        if (!regionsList.isEmpty()) {
            return regionsList;
        }
         return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Region> regions) {
        super.onPostExecute(regions);
        Log.e("onPostExecute", "REGIONS 1 " + regions.get(0).getName());
        Log.e("onPostExecute", "REGIONS LIST" + regionsList.get(0).getName());

        MainActivity mainActivity = (MainActivity) activity;

        if (!(regions == null)) {
           // ((MainActivity) activity).setText(regions);
        }

    }
}


 */