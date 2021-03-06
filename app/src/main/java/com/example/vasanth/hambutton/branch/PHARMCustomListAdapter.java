package com.example.vasanth.hambutton.branch;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vasanth.hambutton.R;
import com.example.vasanth.hambutton.pharmaBranchIndividualActivities.OralPresentationPharma;
import com.example.vasanth.hambutton.pharmaBranchIndividualActivities.PharmaQuizPharma;
import com.example.vasanth.hambutton.pharmaBranchIndividualActivities.PosterPresentationPharma;
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

public class PHARMCustomListAdapter extends ArrayAdapter<Card> {

    private static final String TAG = "PersonListAdapterPHARM";

    private Context mContextPHARM;
    private int mResourcePHARM;
    private int lastPositionPHARM = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {

        TextView titlePHARM;
        ImageView imagePHARM;
    }


    public PHARMCustomListAdapter(Context context, int resource, ArrayList<Card> objects) {
        super(context, resource, objects);
        mContextPHARM = context;
        mResourcePHARM = resource;
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
            LayoutInflater inflater = LayoutInflater.from(mContextPHARM);
            convertView = inflater.inflate(mResourcePHARM, parent, false);
            holder= new ViewHolder();
            holder.titlePHARM= (TextView) convertView.findViewById(R.id.cardTitleInPHARM);
            holder.imagePHARM = (ImageView) convertView.findViewById(R.id.cardImageInPHARM);
            result = convertView;

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Button readMoreInPHARM = (Button)convertView.findViewById(R.id.readMoreInPHARM);
        readMoreInPHARM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0)
                   mContextPHARM.startActivity(new Intent(mContextPHARM, OralPresentationPharma.class));
                else if (position == 1)
                    mContextPHARM.startActivity(new Intent(mContextPHARM, PosterPresentationPharma.class));
                else if (position == 2)
                    mContextPHARM.startActivity(new Intent(mContextPHARM, PharmaQuizPharma.class));
            }
        });
        Button registerInPHARM = (Button)convertView.findViewById(R.id.registerInPHARM);
        registerInPHARM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0)
                    goToChrome("https://docs.google.com/forms/d/e/1FAIpQLScj8tnDtN6zDHOmW-IbSx6KEArOiiG0awMfNsj8ycQX_Tq7Qg/viewform");
         else if (position == 1)
                    goToChrome("https://docs.google.com/forms/d/e/1FAIpQLScj8tnDtN6zDHOmW-IbSx6KEArOiiG0awMfNsj8ycQX_Tq7Qg/viewform");
                else if (position == 2)
                    goToChrome("https://docs.google.com/forms/d/e/1FAIpQLScj8tnDtN6zDHOmW-IbSx6KEArOiiG0awMfNsj8ycQX_Tq7Qg/viewform");


            }
        });




        //    Animation animation = AnimationUtils.loadAnimation(mContext,
     //           (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
      //  result.startAnimation(animation);
       // lastPosition = position;

        holder.titlePHARM.setText(title);


        //create the imageloader object
        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage = mContextPHARM.getResources().getIdentifier("@drawable/image_failed",null,mContextPHARM.getPackageName());

        //create display options
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //download and display image from url
        imageLoader.displayImage(imgUrl, holder.imagePHARM, options);

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
                mContextPHARM)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }

    public void goToChrome(String docsLink) {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(docsLink));
            mContextPHARM.startActivity(i);
        } catch (ActivityNotFoundException e) {
            // Chrome is not installed
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(docsLink));
            mContextPHARM.startActivity(i);
        }
    }

}