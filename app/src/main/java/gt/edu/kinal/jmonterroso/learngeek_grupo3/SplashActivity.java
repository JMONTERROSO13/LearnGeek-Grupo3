package gt.edu.kinal.jmonterroso.learngeek_grupo3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends ActionBarActivity {
    private final int SPLASH_TIME= 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = SplashActivity.this.getSharedPreferences(getString(R.string.sharedClass), Context.MODE_PRIVATE);
                Boolean isRemembered = pref.getBoolean(getString(R.string.isRemembered), false);
                Intent intentLog;
                if(isRemembered){
                    intentLog = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intentLog = new Intent(SplashActivity.this, LoginActivity.class);
                }
                SplashActivity.this.startActivity(intentLog);
                SplashActivity.this.finish();
            }
        },SPLASH_TIME);
    }

}
