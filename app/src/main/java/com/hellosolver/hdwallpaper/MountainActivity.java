package com.hellosolver.hdwallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MountainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    WallpaperAdapter wallpaperAdapter;
    List<WallpaperModel> wallpaperModelList;
    Toolbar toolbar;


    int pageNumber=1;
    Boolean isScrolling=false;
    int currentItem,totalItem,scrollOutItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpaper_list);

        recyclerView=findViewById(R.id.recyclerview);
        wallpaperModelList=new ArrayList<>();
        wallpaperAdapter=new WallpaperAdapter(this,wallpaperModelList);

        recyclerView.setAdapter(wallpaperAdapter);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Objects.requireNonNull(getSupportActionBar()).setTitle("LAND WALLPAPERS");

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true;
                }
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem=gridLayoutManager.getChildCount();
                totalItem=gridLayoutManager.getItemCount();
                scrollOutItem=gridLayoutManager.findFirstVisibleItemPosition();
                if(isScrolling &&(currentItem+scrollOutItem==totalItem)){
                    isScrolling=false;
                    fetchWallpaper();
                }
            }
        });
        fetchWallpaper();
    }



    private void fetchWallpaper() {
        String url="https://pixabay.com/api/?key=22301803-79762318050f3ddfca57eff66&q=Mountain&image_type=photo&pretty=true&page=\" + pageNumber + \"&per_page=80\"";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,url,null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("hits");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject hit = jsonArray.getJSONObject(i);
                            String mediumUrl = hit.getString("webformatURL");
                            String originalUrl = hit.getString("largeImageURL");
                            int id = hit.getInt("id");
                            wallpaperModelList.add(new WallpaperModel(id, originalUrl, mediumUrl));

                        }
                        wallpaperAdapter.notifyDataSetChanged();
                        pageNumber++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace());
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onShare(MenuItem item) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareLink="GET HD Wallpapers for free from this HD WALLPAPERS APP"
                +"\n "+
                "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
        intent.putExtra(Intent.EXTRA_TEXT,shareLink);
        startActivity(Intent.createChooser(intent,"Share Using"));
    }

    public void onRate(MenuItem item) {
        Uri uri=Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

}