package com.arumaya.mpuzzle.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arumaya.mpuzzle.DataRepository;
import com.arumaya.mpuzzle.MemoryPuzzle;
import com.arumaya.mpuzzle.model.entity.Card;
import com.arumaya.mpuzzle.model.entity.Player;

import java.util.List;

/**
 * Created by andre on 7/2/2018.
 */

public class GameFragmentVM extends AndroidViewModel implements ViewModel {

    private static final String TAG = "GameFragmentViewModel";
    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Player>> mObservablePlayers;
    public LiveData<List<Player>> getPlayers() { return mObservablePlayers; }

    private final MediatorLiveData<List<Card>> mObservableCards;
    public LiveData<List<Card>> getCards() { return mObservableCards; }

    public GameFragmentVM(Application application) {
        super(application);

        Log.d(TAG, "DEBUG: Constructor GameFragmentVM");

        mObservablePlayers = new MediatorLiveData<>();
        mObservablePlayers.setValue(null);

        LiveData<List<Player>> players = ((MemoryPuzzle) application).getRepository()
                .getPlayers();
        mObservablePlayers.addSource(players, mObservablePlayers::setValue);


        mObservableCards = new MediatorLiveData<>();
        mObservableCards.setValue(null);

        LiveData<List<Card>> cards = ((MemoryPuzzle) application).getRepository()
                .getCards();
        mObservableCards.addSource(cards, mObservableCards::setValue);
    }

    public void move(Card card){

        if(card.getStatus()){
            ((MemoryPuzzle) getApplication()).getDatabase().closeCard(card);
        }else{
            ((MemoryPuzzle) getApplication()).getDatabase().openCard(card);
        }

        //card.setStatus(!card.getStatus());
        //card.setScore(card.getScore()-1);
        //if(card.getScore() < 90) card.setOwner(new Player());
        //if(card.getScore() < 80) card.setOwner(null);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final MemoryPuzzle mApplication;
        private final DataRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = (MemoryPuzzle) application;
            mRepository = mApplication.getRepository();
        }

        @Override
        public <T extends android.arch.lifecycle.ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            GameFragmentVM vm = new GameFragmentVM(mApplication);
            return (T) vm;
        }
    }

}
