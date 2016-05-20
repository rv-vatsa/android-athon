package com.example.rvijaykumarshahi.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private SeekBar selectTimeBar = null;
    private CircularProgress circularProgressBar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        selectTimeBar = (SeekBar) findViewById(R.id.selectTimeBar);
        circularProgressBar = (CircularProgress) findViewById(R.id.circularProgress);

        selectTimeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                circularProgressBar.setProgress(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "seek bar progress:" + progressChanged,
                        Toast.LENGTH_SHORT).show();
            }
        });

        Switch seekNotificationPermissionButton = (Switch) findViewById(R.id.notificationSwitch);
        seekNotificationPermissionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Context context = getApplicationContext();
            Toast toast;
            int duration = Toast.LENGTH_SHORT;
            CharSequence text;

            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {
                    text = "Switch is on";
                } else {
                    text = "Switch is off";
                    int duration = Toast.LENGTH_SHORT;
                }
                toast = Toast.makeText(context, text, duration);
                toast.show();
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
}
