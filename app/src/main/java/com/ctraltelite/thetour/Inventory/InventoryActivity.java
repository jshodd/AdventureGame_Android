package com.ctraltelite.thetour.Inventory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.ctraltelite.thetour.Display.DisplayFragment;
import com.ctraltelite.thetour.R;

/**
 * Created by jshodd on 4/11/16.
 */
public class InventoryActivity extends FragmentActivity{

    private FragmentManager fm = getSupportFragmentManager();

    public static Intent newIntent(Context packageContext){
        Intent i = new Intent(packageContext,InventoryActivity.class);
        return i;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_container);

                fm.beginTransaction()
                        .add(R.id.fragment_inventory_container, new InventoryFragment()).commit();



    }
}
