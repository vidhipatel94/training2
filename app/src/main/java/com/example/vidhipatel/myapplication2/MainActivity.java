package com.example.vidhipatel.myapplication2;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private CancelableRunnable mCancelRunnable;
    private Handler mHandler = new Handler();
    private static int DURATION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = (EditText) findViewById(R.id.edit_query);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mCancelRunnable != null && System.currentTimeMillis() - mCancelRunnable.time < DURATION)
                    mCancelRunnable.isCancelled = true;

                mCancelRunnable = new CancelableRunnable(s.toString());
                mHandler.postDelayed(mCancelRunnable, DURATION);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CancelableRunnable implements Runnable {
        String s;
        long time;
        boolean isCancelled;

        CancelableRunnable(String s) {
            this.s = s;
            time = System.currentTimeMillis();
        }

        @Override
        public void run() {
            if (!isCancelled)
                Log.d("Mainactivity", s);
        }
    }


}
