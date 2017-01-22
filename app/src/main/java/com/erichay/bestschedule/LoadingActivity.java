package com.erichay.bestschedule;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;

public class LoadingActivity extends Activity
{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                Intent mInHome = new Intent(LoadingActivity.this, MainActivity.class);
                LoadingActivity.this.startActivity(mInHome);
                LoadingActivity.this.finish();
            }
        }, 3000);
    }

}
