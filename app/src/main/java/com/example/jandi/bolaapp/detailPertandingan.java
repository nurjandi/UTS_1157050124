package com.example.jandi.bolaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detailPertandingan extends AppCompatActivity {
    String Url_Data;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<modelBola> listItems;
    private TextView textView1, textView2, textView3;
    private ImageView imageView1, imageView2;
    private String linkbrowser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pertandingan);
        Url_Data = "http://installin.000webhostapp.com/public/index.php/bola/getBola";
        textView1 = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.nama);
        imageView1 = (ImageView) findViewById(R.id.gambar1);
        imageView2 = (ImageView) findViewById(R.id.gambar2);
        String sessionId= getIntent().getStringExtra("ID");
        loadRecyclerViewData(sessionId);

    }

    public void loadRecyclerViewData(final String id){
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
                            int ID = Integer.parseInt(id);
                            JSONObject o = array.getJSONObject(ID-1);
                            String id = o.getString("id");
                            String nama = o.getString("nama");
                            String poin1 = o.getString("poin1");
                            String gambar1 = o.getString("gambar1");
                            String poin2 = o.getString("poin2");
                            String gambar2 = o.getString("gambar2");
                            linkbrowser = o.getString("link");
                            textView1.setText(poin1);
                            textView2.setText(poin2);
                            Picasso.get().load(gambar1).into(imageView1);
                            Picasso.get().load(gambar2).into(imageView2);
                            textView3.setText(nama);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.openBrowser:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(linkbrowser));
                startActivity(browserIntent);

                return true;

        }
        return false;
    }
}
