package com.example.jandi.bolaapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class hasilPertandingan extends AppCompatActivity {
    private static final String Url_Data = "http://installin.000webhostapp.com/public/index.php/bola/getBola";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<modelBola> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_pertandingan);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        loadRecyclerViewData();
    }
    public void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data ...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url_Data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("data");
                            for(int i=0; i<array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                modelBola item = new modelBola(
                                        o.getString("id"),
                                        o.getString("nama"),
                                        o.getString("poin1"),
                                        o.getString("gambar1"),
                                        o.getString("poin2"),
                                        o.getString("gambar2"),
                                        o.getString("link")
                                );
                                listItems.add(item);
                            }
                            adapter = new bolaAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}