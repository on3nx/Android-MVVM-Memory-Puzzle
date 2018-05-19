package com.arumaya.mpuzzle.viewmodel;

/**
 * Created by andre on 19/1/2018.
 */

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.arumaya.mpuzzle.MemoryPuzzle;
import com.arumaya.mpuzzle.view.ui.GameActivity;

public class MainActivityVM extends AndroidViewModel implements ViewModel {

    public final ObservableField<Boolean> btStartenb = new ObservableField<>();
    public final ObservableField<String> btStartLbl = new ObservableField<>();
    public final ObservableField<Integer> gridSize = new ObservableField<>();
    public final ObservableField<Integer> noPlayer = new ObservableField<>();

    public MainActivityVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        noPlayer.set(1);
        gridSize.set(2);
        btStartLbl.set("Start Game");
        btStartenb.set(true);
    }

    @Override
    public void onPause() { }

    @Override
    public void onResume() { }

    @Override
    public void onDestroy() { }

    public void onClickedStart() {
        Context c = this.getApplication();
        if(gridSize.get() % 2 == 0 && noPlayer.get() > 0){
            MemoryPuzzle app = getApplication();
            app.setCardQty(gridSize.get());
            app.setNoPlayer(noPlayer.get());

            Intent intent = new Intent(c, GameActivity.class);
            c.startActivity(intent);
        }else{
            Toast.makeText(c, "Input not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    public void validateGrid(int newVal){
        gridSize.set(((newVal-1)*2));
        btStartenb.set(gridSize.get() % 2 == 0);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application mApplication;

        public Factory(@NonNull Application application) {
            mApplication = application;
        }

        @Override
        public <T extends android.arch.lifecycle.ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MainActivityVM(mApplication);
        }
    }

}
