package com.android.qbpeopledirectory.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.qbpeopledirectory.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by qbuser on 3/7/14.
 */
public class QBurstLocations extends Fragment {

    // Google Map
    private GoogleMap googleMap;

    // latitude and longitude


    double cokLat=10.009198;
    double cokLon=76.359765;

    double polandLat= 53.128655;
    double polandLon=23.150037;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.burst_locations_layout,
                container, false);

        try {
            // Loading map
            initilizeMap();



// create marker
            MarkerOptions marker1 = new MarkerOptions().position(new LatLng(cokLat, cokLon)).title("QBurst"
            ).snippet("Infopark\n" +
                    "Kakkanadu, Kerala, India\n");


            MarkerOptions marker2 = new MarkerOptions().position(new LatLng(polandLat, polandLon)).title("QBurst"
            ).snippet("ul. Legionowa 28, 3rd Floor\n" +
                    "15-281 Bialystokn");

            double arabLat=25.617410;
            double arabLon=55.598175;
            MarkerOptions marker3 = new MarkerOptions().position(new LatLng(arabLat, arabLon)).title("QBurst"
            ).snippet("PO Box: 17084\n" +
                    "Ras al-Khaimah");







            MarkerOptions marker4 = new MarkerOptions().position(new LatLng(12.964285, 80.241569)).title("QBurst"
            ).snippet("Campus 4B, 6th Floor, Phase 2\n" +
                    "Regus, Millenia Business Park\n" +
                    "Chennai - 600096");




            MarkerOptions marker5 = new MarkerOptions().position(new LatLng(10.267134, 76.347797)).title("QBurst"
            ).snippet("Nisagandhi, Infopark\n" +
                    "Koratty\n" +
                    "Trissur - 680308");




            MarkerOptions marker6 = new MarkerOptions().position(new LatLng(8.5263984, 76.935062)).title("QBurst"
            ).snippet("C22, Thejaswini\n" +
                    "Technopark\n" +
                    "Trivandrum - 695581");


            MarkerOptions marker7 = new MarkerOptions().position(new LatLng(8.526727, 76.935244)).title("QBurst"
            ).snippet("3rd Floor, Leela Towers\n" +
                    "Pattom\n" +
                    " Trivandrum - 695004") ;


// adding marker
            googleMap.addMarker(marker1);
            googleMap.addMarker(marker2);
            googleMap.addMarker(marker3);
            googleMap.addMarker(marker4);
            googleMap.addMarker(marker5);
            googleMap.addMarker(marker6);
            googleMap.addMarker(marker7);






        } catch (Exception e) {
            e.printStackTrace();
        }


 return view;
    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity().getBaseContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initilizeMap();
    }


}
