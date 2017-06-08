package com.pienkowska.swim.lottery.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pienkowska.swim.lottery.Lottery;
import com.pienkowska.swim.lottery.R;

import java.util.Locale;

public class RewardsListAdapter extends RecyclerView.Adapter<RewardsListAdapter.ViewHolder> {
    private Context context;
    private Lottery lottery;

    public RewardsListAdapter(Context context, Lottery lottery) {
        this.context = context;
        this.lottery = lottery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_reward_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView hits = holder.hits;
        TextView reward = holder.reward;
        String hitsText = "Trafionych " + String.valueOf(position + 1) + " z " + String.valueOf(lottery.lotsNum);
        String rewardText = String.valueOf(lottery.rewards[position]) + " zł";
        hits.setText(hitsText);
        reward.setText(rewardText);
    }

    @Override
    public int getItemCount() {
        return lottery.lotsNum;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView hits;
        public TextView reward;

        public ViewHolder(View itemView) {
            super(itemView);
            hits = (TextView) itemView.findViewById(R.id.hitsTV);
            reward = (TextView) itemView.findViewById(R.id.rewardTV);
        }
    }
}
