package com.pienkowska.swim.lottery.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.pienkowska.swim.lottery.Adapters.ResultGridAdapter;
import com.pienkowska.swim.lottery.Adapters.RewardsListAdapter;
import com.pienkowska.swim.lottery.Lottery;
import com.pienkowska.swim.lottery.Manager;
import com.pienkowska.swim.lottery.R;

import java.util.ArrayList;

public class RewardsFragment extends Fragment {
    public static final String LOTTERY_NAME_ARG = "lottery_name";

    private Lottery lottery;
    private RecyclerView list;

    public RewardsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rewards, container, false);

        final String name = getArguments().getString(LOTTERY_NAME_ARG);
        lottery = Manager.getInstance().getLottery(name);

        list = (RecyclerView) rootView.findViewById(R.id.listRV);
        list.setAdapter(new RewardsListAdapter(getActivity(), lottery));
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_check).setVisible(false);
        menu.findItem(R.id.action_rewards).setVisible(true);
    }
}
