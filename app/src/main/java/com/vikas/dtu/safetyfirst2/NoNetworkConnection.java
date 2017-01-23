package com.vikas.dtu.safetyfirst2;

import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by viveksb007 on 10/9/16.
 */
public class NoNetworkConnection extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network);
        ImageView imgNoNetwork = (ImageView)findViewById(R.id.img_no_network);
        imgNoNetwork.setImageResource(R.drawable.no_internet_connection);
    }
}
