package com.example.smartclassapp;

import java.util.List;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.*;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    List<YouTubeVideos> youTubeVideoList;

    public VideoAdapter() {
    }

    public VideoAdapter(List<YouTubeVideos> youTubeVideoList) {
        this.youTubeVideoList = youTubeVideoList;
    }

    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_view,viewGroup,false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder videoViewHolder, int i) {
        videoViewHolder.videoWeb.loadData(youTubeVideoList.get(i).getVideoUrl(),"text/html","utf-8");
    }

    @Override
    public int getItemCount() {
        return youTubeVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        WebView videoWeb;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.videoWebView);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient(){


            });
        }
    }
    /*public class VideoViewHolder extends RecyclerView.ViewHolder{

        WebView videoWeb;
        public videoViewHolder(View itemView){
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.videoWebView);

            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient(){

            });
        }*/
    }


