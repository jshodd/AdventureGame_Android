package com.ctraltelite.thetour;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ctraltelite.thetour.Display.DisplayFragment;
import com.ctraltelite.thetour.Game.TheTour;
import com.ctraltelite.thetour.Inventory.InventoryActivity;
import com.ctraltelite.thetour.Inventory.InventoryFragment;

public class MainActivity extends AppCompatActivity {
    private static TheTour game = new TheTour();
    private FragmentManager fm = getSupportFragmentManager();
    private Button mButton;

    public static TheTour getGame(){
        return game;
    }

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, MainActivity.class);
        return i;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.w("config Change","checking orientation");
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.w("landscape switch","adding inv");
            setContentView(R.layout.activity_main);
                fm.beginTransaction()
                        .add(R.id.fragment_inventory_container_landscape, new InventoryFragment()).commit();
                fm.beginTransaction()
                        .replace(R.id.fragment_display_container, new DisplayFragment()).commit();
        } else {
            setContentView(R.layout.activity_main);
            fm.beginTransaction().replace(R.id.fragment_display_container, new DisplayFragment()).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            if (savedInstanceState == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_inventory_container_landscape, new InventoryFragment()).commit();
                fm.beginTransaction()
                        .add(R.id.fragment_display_container, new DisplayFragment()).commit();
            }
        } else {
            if (savedInstanceState == null) {
                fm.beginTransaction().add(R.id.fragment_display_container, new DisplayFragment()).commit();
                mButton = (Button) findViewById(R.id.inventory_button);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = InventoryActivity.newIntent(MainActivity.this);
                        startActivity(i);
                    }
                });
            }
        }


    }
}