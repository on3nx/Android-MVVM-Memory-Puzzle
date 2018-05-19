package com.arumaya.mpuzzle.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arumaya.mpuzzle.MemoryPuzzle;
import com.arumaya.mpuzzle.R;
import com.arumaya.mpuzzle.databinding.FragmentGameBinding;
import com.arumaya.mpuzzle.view.adapter.CardAdapter;
import com.arumaya.mpuzzle.view.adapter.PlayerAdapter;
import com.arumaya.mpuzzle.viewmodel.GameFragmentVM;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {
    public static final String TAG = "GameFragment";

    private MemoryPuzzle app;

    private GameFragmentVM mViewModel;
    private GameFragmentVM getViewModel(){ return mViewModel; }
    private void setViewModel(GameFragmentVM viewModel){ mViewModel = viewModel; }

    private FragmentGameBinding mBinding;

    private PlayerAdapter mPlayerAdapter;
    private CardAdapter mCardAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GameFragment() { /* Required empty public constructor */ }

    public static GameFragment newInstance(/*String param1, String param2*/) {
        Log.d(TAG, "DEBUG: NewInstance");
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "DEBUG: onCreate");

        app = (MemoryPuzzle) getActivity().getApplication();
        GameFragmentVM.Factory factory = new GameFragmentVM.Factory(app);
        setViewModel(ViewModelProviders.of(this, factory) .get(GameFragmentVM.class));

        //if (getArguments() != null) {
        //    mParam1 = getArguments().getString(ARG_PARAM1);
        //    mParam2 = getArguments().getString(ARG_PARAM2);
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "DEBUG: onCreateView");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false);

        LinearLayoutManager linearLayout = new LinearLayoutManager(mBinding.getRoot().getContext(), LinearLayout.HORIZONTAL, false);
        mBinding.playerList.setLayoutManager(linearLayout);

        mPlayerAdapter = new PlayerAdapter();
        mBinding.playerList.setAdapter(mPlayerAdapter);

        GridLayoutManager gridLayout = new GridLayoutManager(mBinding.getRoot().getContext(), 4);
        gridLayout.setOrientation(GridLayoutManager.VERTICAL);
        mBinding.cardList.setLayoutManager(gridLayout);

        mCardAdapter = new CardAdapter(mCardClickCallback);
        mBinding.cardList.setAdapter(mCardAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "DEBUG: onActivityCreated");

        //viewModel = model;
        //binding.setVm(viewModel);
        getViewModel().onCreate();

        subscribeUi(getViewModel());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void subscribeUi(GameFragmentVM viewModel) {
        Log.d(TAG, "DEBUG: subscribeUi");
        // Update the list when the data changes
        observePlayer(viewModel);
        observeCard(viewModel);
    }

    private void observePlayer(GameFragmentVM viewModel) {
        viewModel.getPlayers().observe(this, players -> {
            Log.d(TAG, "DEBUG: VM getPlayers() observe: onChanged");
            if (players != null) {
                mBinding.setIsLoading(false);
                mPlayerAdapter.setPlayerList(players);
            } else {
                mBinding.setIsLoading(true);
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            mBinding.executePendingBindings();
        });
    }

    private void observeCard(GameFragmentVM viewModel) {
        viewModel.getCards().observe(this, myCards -> {
            Log.d(TAG, "DEBUG: VM getCards() observe: onChanged");
            if (myCards != null) {
                mBinding.setIsLoading(false);
                mCardAdapter.setCards(myCards);
            } else {
                mBinding.setIsLoading(true);
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            mBinding.executePendingBindings();
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private final CardClickCallback mCardClickCallback = card -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            Log.d(TAG, "DEBUG: CardClickCallback -> onClick");
            //card.setScore(card.getScore()-1);
            //Log.d(TAG, "DEBUG: CardPoint: " + card.getScore());

            getViewModel().move(card);

            //GameFragmentVM vm = mCardAdapter.getViewModel();
            //vm.setTest(vm.getTest()-1);

            //Log.d(TAG, "DEBUG: TEST CLICK" + mCardAdapter.getViewModel().getTest());


            //((GameActivity) getActivity()).show(card);
        }
    };
}
