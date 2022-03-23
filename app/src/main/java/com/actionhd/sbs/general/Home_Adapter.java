package com.actionhd.sbs.general;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
//import android.support.p000v4.app.ActivityOptionsCompat;
//import android.support.p000v4.util.Pair;
//import android.support.p003v7.widget.RecyclerView.Adapter;
//import android.support.p003v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
//import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.actionhd.sbs.R;

import com.actionhd.sbs.utils.TypefaceManager;

import java.util.ArrayList;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.MyRestaurant_ViewHolder> implements Filterable {
    ArrayList<All_Home> FilteredList = new ArrayList<>();
    Activity activity;
    public ArrayList<All_Home> allVideo;
    ArrayList<All_Home> all_videohistoryArrayList;
    Context context;
    boolean isFilter = false;
    TypefaceManager typefaceManager;

    public class MyRestaurant_ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_home;
        RelativeLayout rl_home;
        TextView tv_title_home;

        public MyRestaurant_ViewHolder(View view) {
            super(view);

            this.tv_title_home.setTypeface(Home_Adapter.this.typefaceManager.getLight());
        }
    }

    public Home_Adapter(Activity activity2, ArrayList<All_Home> arrayList, Context context2) {
        this.activity = activity2;
        this.context = context2;
        this.all_videohistoryArrayList = arrayList;
        this.allVideo = arrayList;
        this.typefaceManager = new TypefaceManager(context2.getAssets(), context2);
    }

    @NonNull
    @Override
    public MyRestaurant_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindViewHolder(final MyRestaurant_ViewHolder myRestaurant_ViewHolder, int i) {
        try {
            myRestaurant_ViewHolder.tv_title_home.setText(((All_Home) this.all_videohistoryArrayList.get(i)).title);
//            GradientDrawable gradientDrawable = (GradientDrawable) myRestaurant_ViewHolder.rl_home.getBackground();
//            StringBuilder sb = new StringBuilder();
//            sb.append("#");
//            sb.append(((All_Home) this.all_videohistoryArrayList.get(i)).color);
//            gradientDrawable.setColor(Color.parseColor(sb.toString()));
            try {
                Resources resources = this.context.getResources();
                myRestaurant_ViewHolder.image_home.setImageDrawable(resources.getDrawable(resources.getIdentifier(((All_Home) this.all_videohistoryArrayList.get(i)).image, "drawable", this.context.getPackageName())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            myRestaurant_ViewHolder.rl_home.setTag(Integer.valueOf(i));
            myRestaurant_ViewHolder.rl_home.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    int i = ((All_Home) Home_Adapter.this.all_videohistoryArrayList.get(((Integer) view.getTag()).intValue())).f2614id;
                    if (i == 0) {

                    }

                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int getItemCount() {
        return this.all_videohistoryArrayList.size();
    }

    public Filter getFilter() {
        return new Filter() {

            public void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Home_Adapter.this.all_videohistoryArrayList = (ArrayList) filterResults.values;
                Home_Adapter.this.notifyDataSetChanged();
            }


            public FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence == null || charSequence.length() == 0) {
                    filterResults.values = Home_Adapter.this.allVideo;
                    filterResults.count = Home_Adapter.this.allVideo.size();
                    Home_Adapter.this.isFilter = false;
                } else {
                    Home_Adapter.this.FilteredList.clear();
                    Home_Adapter.this.isFilter = true;
                    for (int i = 0; i < Home_Adapter.this.allVideo.size(); i++) {
                        All_Home all_Home = (All_Home) Home_Adapter.this.allVideo.get(i);
                        if (all_Home.title.toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("pos->");
                            sb.append(String.valueOf(i));
                            Log.d("pos->", sb.toString());
                            Home_Adapter.this.FilteredList.add(all_Home);
                        }
                    }
                    filterResults.values = Home_Adapter.this.FilteredList;
                    filterResults.count = Home_Adapter.this.FilteredList.size();
                }
                return filterResults;
            }
        };
    }
}
