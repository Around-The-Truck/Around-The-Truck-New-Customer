package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sumin on 2015-01-17.
 */
//public class Map {
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//
//        LatLng seoul = new LatLng(UserSession.getInstance().getLatitude(), UserSession.getInstance().getLongitude());
//        map.setMyLocationEnabled(true);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 13));
//
//        TruckLoader.getLoader().getTruckListOnMap(UserSession.getInstance().getLatitude(), UserSession.getInstance().getLongitude(), TruckMapFragment.this);
//
//        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//            @Override
//            public View getInfoWindow(final Marker marker) {
//                View view = LayoutInflater.from(getActivity()).inflate(R.layout.info_window, null);
//
//                StringBuilder builder = new StringBuilder();
//
////                final ImageView iv = (ImageView) view.findViewById(R.id.info_window_iv);
//                TextView tv = (TextView) view.findViewById(R.id.info_window_tv);
//
//                TruckModel truck = getTruck(marker.getPosition());
//
//                if (null != truck) {
//                    if (null != truck.getName()) {
//                        builder.append(truck.getName());
//                    }
//
//                    if (0 == truck.getStatus()) {
//                        if (0 == builder.length()) {
//                            builder.append("영업 종료\r\n");
//                        } else {
//                            builder.append(" / 영업 종료\r\n");
//                        }
//                    } else {
//                        if (1 == truck.getStatus()) {
//                            if (0 == builder.length()) {
//                                builder.append("영업중\r\n");
//                            } else {
//                                builder.append(" / 영업 종료\r\n");
//                            }
//                        }
//                    }
//
//                    builder.append(Integer.toString(truck.getFollowCount()) + " 좋아요\r\n");
//
//                    if (null != truck.getPhoto()) {
////                        Picasso.with(getActivity())
////                                .setIndicatorsEnabled(true);
//
//                        final String url = URL.getApi("/upload/" + truck.getPhoto());
//
////                        Picasso.with(getActivity())
////                                .load(url)
////                                .fit().error(R.drawable.ic_launcher)
////                                .into(iv, new Callback() {
////                                    @Override
////                                    public void onSuccess() {
////                                        if (marker != null && marker.isInfoWindowShown()) {
////                                            marker.hideInfoWindow();
////                                            marker.showInfoWindow();
////                                        }
////                                    }
////
////                                    @Override
////                                    public void onError() {
////                                        iv.setVisibility(View.GONE);
////
////                                        Toast.makeText(getActivity(), "NOPE", Toast.LENGTH_SHORT).show();
////                                    }
////                                });
//                        Log.i(TAG, "URL : " + url);
////                        Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT).show();
//                    } else {
////                        iv.setVisibility(View.GONE);
//                    }
//
//                    tv.setText(builder.toString());
//                }
//
//                return view;
//            }
//
//            @Override
//            public View getInfoContents(final Marker marker) {
//                return null;
//            }
//        });
//    }
//
//
//
//
//}
