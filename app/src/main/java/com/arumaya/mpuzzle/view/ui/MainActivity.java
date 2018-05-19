package com.arumaya.mpuzzle.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

import com.arumaya.mpuzzle.R;
import com.arumaya.mpuzzle.BuildConfig;
import com.arumaya.mpuzzle.MemoryPuzzle;
import com.arumaya.mpuzzle.databinding.ActivityMainBinding;
import com.arumaya.mpuzzle.viewmodel.MainActivityVM;

public class MainActivity extends AppCompatActivity {

    private MemoryPuzzle app;
    private MainActivityVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer gridSize = BuildConfig.GRID_SIZE;
        app = (MemoryPuzzle) this.getApplication();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainActivityVM.Factory factory = new MainActivityVM.Factory(app);

        final MainActivityVM model = ViewModelProviders.of(this, factory)
                .get(MainActivityVM.class);

        viewModel = model;
        binding.setVm(viewModel);
        viewModel.onCreate();

        NumberPicker np = findViewById(R.id.np_gridsize);
        String[] gridValues = new String[gridSize];

        for (int i = 2; i <= (gridValues.length*2); i+=2) {
            gridValues[(i/2)-1] = i < 10 ? "0" + i : String.valueOf(i);
        }

        np.setMinValue(2);
        np.setMaxValue(gridSize+1);
        np.setDisplayedValues(gridValues);

        np = findViewById(R.id.np_player);
        np.setMinValue(1);
        np.setMaxValue(4);
        np.setWrapSelectorWheel(true);

    }
}
