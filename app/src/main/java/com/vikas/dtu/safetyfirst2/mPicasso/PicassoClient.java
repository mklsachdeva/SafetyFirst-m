package com.vikas.dtu.safetyfirst2.mPicasso;

/**
 * Created by Vikas on 10-07-2016.
 */
import android.content.Context;
import android.widget.ImageView;

import com.vikas.dtu.safetyfirst2.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Oclemmy on 4/12/2016 for ProgrammingWizards Channel.
 */
public class PicassoClient {



    public static void downloadImage(Context c,String url,ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.with(c).load(url)
            .resize(600, 200) // resizes the image to these dimensions (in pixel)
                .centerCrop() .placeholder(R.drawable.placeholder).into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }
}