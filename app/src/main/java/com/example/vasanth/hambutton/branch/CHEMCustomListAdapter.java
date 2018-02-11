package com.example.vasanth.hambutton.branch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vasanth.hambutton.R;
import com.example.vasanth.hambutton.civilBranchIndividualActivities.InnovativeIdeaPresentationCIV;
import com.example.vasanth.hambutton.civilBranchIndividualActivities.PosterPresentationCIV;
import com.example.vasanth.hambutton.civilBranchIndividualActivities.ProjectPresentationCIV;
import com.example.vasanth.hambutton.cseBranchIndividualActivities.CseActivity1;
import com.example.vasanth.hambutton.cseBranchIndividualActivities.CseActivity2;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by mravi on 14-01-2018.
 */

public class CHEMCustomListAdapter extends ArrayAdapter<Card> {

    private static final String TAG = "PersonListAdapterCHEM";

    private Context mContextCHEM;
    private int mResourceCHEM;
    private int lastPositionCHEM = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {

        TextView titleCHEM;
        ImageView imageCHEM;
    }


    public CHEMCustomListAdapter(Context context, int resource, ArrayList<Card> objects) {
        super(context, resource, objects);
        mContextCHEM = context;
        mResourceCHEM = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //sets up the image loader library
        setupImageLoader();

        //get the persons information
        final String title= getItem(position).getTitle();

        String imgUrl = getItem(position).getImgUrl();

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;



        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContextCHEM);
            convertView = inflater.inflate(mResourceCHEM, parent, false);
            holder= new ViewHolder();
            holder.titleCHEM= (TextView) convertView.findViewById(R.id.cardTitleInCHEM);
            holder.imageCHEM = (ImageView) convertView.findViewById(R.id.cardImageInCHEM);
            result = convertView;

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Button readMoreInCHEM = (Button)convertView.findViewById(R.id.readMoreInCHEM);
        readMoreInCHEM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0)
                   mContextCHEM.startActivity(new Intent(mContextCHEM, ProjectPresentationCIV.class));
                else if (position == 1)
                    mContextCHEM.startActivity(new Intent(mContextCHEM, InnovativeIdeaPresentationCIV.class));
                else if (position == 2)
                    mContextCHEM.startActivity(new Intent(mContextCHEM, PosterPresentationCIV.class));

            }
        });




        //    Animation animation = AnimationUtils.loadAnimation(mContext,
     //           (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
      //  result.startAnimation(animation);
       // lastPosition = position;

        holder.titleCHEM.setText(title);


        //create the imageloader object
        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage = mContextCHEM.getResources().getIdentifier("@drawable/image_failed",null,mContextCHEM.getPackageName());

        //create display options
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //download and display image from url
        imageLoader.displayImage(imgUrl, holder.imageCHEM, options);

        return convertView;


    }

    /**
     * Required for setting up the Universal Image loader Library
     */
    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContextCHEM)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}