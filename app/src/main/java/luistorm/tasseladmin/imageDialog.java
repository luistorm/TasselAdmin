package luistorm.tasseladmin;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

/**
 * Created by luis on 06/07/17.
 */

public class imageDialog extends Dialog implements View.OnClickListener{
    public Activity c;
    private String path;
    private NetworkImageView nIV;
    private FloatingActionButton fAB,fAB2;

    public imageDialog(Activity a, String path) {
        super(a);
        this.c = a;
        this.path = path;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_dialog);
        nIV = (NetworkImageView) findViewById(R.id.photo4);
        nIV.setDefaultImageResId(R.drawable.loading);
        nIV.setErrorImageResId(R.drawable.error);
        ImageLoader.ImageCache imageCache = new BitmapLruCache();
        ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(this.getContext()), imageCache);
        nIV.setImageUrl(utilities.serverAddress + path, imageLoader);
        fAB = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fAB2 = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        fAB.setOnClickListener(this);
        fAB2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == fAB2.getId()) {
            this.hide();
        }
        if(view.getId() == fAB.getId()) {
            //Do image change
        }
    }
}
