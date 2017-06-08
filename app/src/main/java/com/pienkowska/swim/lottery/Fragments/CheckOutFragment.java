package com.pienkowska.swim.lottery.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.pienkowska.swim.lottery.Adapters.NumberGridAdapter;
import com.pienkowska.swim.lottery.Lottery;
import com.pienkowska.swim.lottery.Manager;
import com.pienkowska.swim.lottery.R;

import java.util.ArrayList;

public class CheckOutFragment extends Fragment {
    public static final String LOTTERY_NAME_ARG = "lottery_name";

    private Lottery lottery;

    private GridView grid;
    private Button button;

    public CheckOutFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_check_out, container, false);

        final String name = getArguments().getString(LOTTERY_NAME_ARG);
        lottery = Manager.getInstance().getLottery(name);

        grid = (GridView) rootView.findViewById(R.id.numbersGV);
        button = (Button) rootView.findViewById(R.id.confirmBT);

        final NumberGridAdapter gridAdapter = new NumberGridAdapter(rootView.getContext(), lottery);
        grid.setAdapter(gridAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> numbers = gridAdapter.getClicked();
                if(numbers.size() < lottery.lotsNum)
                    Toast.makeText(getActivity(), "Musisz wybrać " + String.valueOf(lottery.lotsNum) + " liczb", Toast.LENGTH_SHORT).show();
                else {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ResultFragment fragment = new ResultFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(ResultFragment.LOTTERY_NAME_ARG, name);
                    bundle.putIntegerArrayList(ResultFragment.NUM_ARRAYLIST_ARG, numbers);
                    fragment.setArguments(bundle);
                    ft.replace(R.id.fragmentContainer, fragment, "ResultFragment");
                    ft.commit();
                    ft.addToBackStack(null);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_check).setVisible(false);
        menu.findItem(R.id.action_rewards).setVisible(true);
    }


}
