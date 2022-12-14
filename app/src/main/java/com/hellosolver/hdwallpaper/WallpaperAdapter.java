package com.hellosolver.hdwallpaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import com.hellosolver.hdwallpaper.WallpaperAdapter.WallpaperViewHolder;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperViewHolder>{

    private Context context;
    List<WallpaperModel>wallpaperModels;

    public WallpaperAdapter(Context context, List<WallpaperModel> wallpaperModels) {
        this.context = context;
        this.wallpaperModels = wallpaperModels;
    }
    @NonNull
    @NotNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.wallpaper_list_item,parent,false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        Glide.with(context).load(wallpaperModels.get(position).getMediumUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,FullScreenActivity.class)
                        .putExtra("originalUrl",wallpaperModels.get(position).getOriginalUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperModels.size();
    }
    static class WallpaperViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewItem);
        }
    }
}
