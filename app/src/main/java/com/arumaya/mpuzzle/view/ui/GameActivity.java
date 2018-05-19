package com.arumaya.mpuzzle.view.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arumaya.mpuzzle.MemoryPuzzle;
import com.arumaya.mpuzzle.R;

/**
 * Created by Owner on 7/2/2018.
 */

public class GameActivity extends AppCompatActivity implements GameFragment.OnFragmentInteractionListener{
    private MemoryPuzzle app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        app = (MemoryPuzzle) this.getApplication();

        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            GameFragment fragment = GameFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, GameFragment.TAG).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }

    /** Shows the product detail fragment */
    /*public void show(Card product) {

        ProductFragment productFragment = ProductFragment.forProduct(product.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container,
                        productFragment, null).commit();
    }*/
}
