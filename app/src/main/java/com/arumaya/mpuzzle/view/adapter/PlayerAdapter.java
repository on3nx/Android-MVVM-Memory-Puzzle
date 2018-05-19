package com.arumaya.mpuzzle.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arumaya.mpuzzle.BR;
import com.arumaya.mpuzzle.R;
import com.arumaya.mpuzzle.databinding.PlayerItemBinding;
import com.arumaya.mpuzzle.model.entity.Player;

import java.util.List;
import java.util.Objects;

/**
 * Created by Owner on 7/2/2018.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private static final String TAG = "PlayerAdapter";

    List<? extends Player> mPlayerList;

    public PlayerAdapter() { Log.d(TAG, "DEBUG: Construct"); }

    public void setPlayerList(final List<? extends Player> playerList) {
        Log.d(TAG, "DEBUG: setProductList");
        if (mPlayerList == null) {
            mPlayerList = playerList;
            notifyItemRangeInserted(0, playerList.size());
        } else {
            // TODO: this is interesting to have calc diff library but for my case is not needed - later before publish, remove it.
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPlayerList.size();
                }

                @Override
                public int getNewListSize() {
                    return playerList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mPlayerList.get(oldItemPosition).getId() ==
                            playerList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Player newPlayer = playerList.get(newItemPosition);
                    Player oldPlayer = mPlayerList.get(oldItemPosition);
                    return newPlayer.getId() == oldPlayer.getId()
                            && Objects.equals(newPlayer.getId(), oldPlayer.getId())
                            && Objects.equals(newPlayer.getName(), oldPlayer.getName());
                }
            });
            mPlayerList = playerList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "DEBUG: onCreateViewHolder");

        PlayerItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.player_item,
                        parent, false);
        return new PlayerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Log.d(TAG, "DEBUG: onBindViewHolder, Pos: " + position);

        holder.bind(mPlayerList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlayerList == null ? 0 : mPlayerList.size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {

        final PlayerItemBinding binding;

        public PlayerViewHolder(PlayerItemBinding binding) {
            super(binding.getRoot());
            Log.d(TAG, "DEBUG: PlayerViewHolder - Construct");
            this.binding = binding;
        }

        public void bind(@NonNull Object obj) {
            binding.setVariable(BR.obj, obj);
            binding.executePendingBindings();
        }
    }

}
