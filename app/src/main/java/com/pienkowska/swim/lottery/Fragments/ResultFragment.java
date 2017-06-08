package com.pienkowska.swim.lottery.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.pienkowska.swim.lottery.Adapters.ResultGridAdapter;
import com.pienkowska.swim.lottery.Lottery;
import com.pienkowska.swim.lottery.Manager;
import com.pienkowska.swim.lottery.R;

import java.util.ArrayList;

public class ResultFragment extends Fragment {
    public static final String LOTTERY_NAME_ARG = "lottery_name";
    public static final String NUM_ARRAYLIST_ARG = "num_arraylist";

    private View rootView;
    private Lottery lottery;
    private ArrayList<Integer> numbers;

    private GridView grid;
    private TextView text;

    public ResultFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_result, container, false);

        final String name = getArguments().getString(LOTTERY_NAME_ARG);
        lottery = Manager.getInstance().getLottery(name);
        numbers = getArguments().getIntegerArrayList(NUM_ARRAYLIST_ARG);

        initViews();

        text.setText(String.valueOf(count()) + " z " + String.valueOf(lottery.lotsNum));

        return rootView;
    }

    private void initViews() {
        text = (TextView) rootView.findViewById(R.id.resultTV);
        grid = (GridView) rootView.findViewById(R.id.gridGV);
        setGrid();
    }

    private void setGrid() {
        if(lottery.lotsNum == 6) {
            grid.setNumColumns(3);
            grid.setPadding(64,0,64,0);
            grid.setHorizontalSpacing(32);
        }
        grid.setAdapter(new ResultGridAdapter(getActivity(), lottery, numbers));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_check).setVisible(false);
        menu.findItem(R.id.action_rewards).setVisible(true);
    }

    private int count() {
        int[] lots = lottery.getLots();
        int result = 0;
        for(int i = 0; i < lottery.lotsNum; i++) {
            if(numbers.contains(lots[i]))
                result++;
        }
        return result;
    }
}
