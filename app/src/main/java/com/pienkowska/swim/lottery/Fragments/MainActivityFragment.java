package com.pienkowska.swim.lottery.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pienkowska.swim.lottery.Fragments.LotteryFragment;
import com.pienkowska.swim.lottery.MainActivity;
import com.pienkowska.swim.lottery.R;

public class MainActivityFragment extends Fragment {
    private View rootView;
    private Button toMini, toLotto, toMulti;
    private FragmentTransaction ft;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ft = getFragmentManager().beginTransaction();

        initViews();
        initListeners();

        return rootView;
    }

    private void initViews() {
        toMini = (Button) rootView.findViewById(R.id.miniBT);
        toLotto = (Button) rootView.findViewById(R.id.lottoBT);
        toMulti = (Button) rootView.findViewById(R.id.multiBT);
    }

    private void initListeners() {
        toMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LotteryFragment fragment = new LotteryFragment();
                Bundle bundle = new Bundle();
                bundle.putString(LotteryFragment.LOTTERY_NAME_ARG, MainActivity.MINI);
                fragment.setArguments(bundle);
                ft.replace(R.id.fragmentContainer, fragment, "MiniFragment");
                ft.commit();
                ft.addToBackStack(null);
            }
        });

        toLotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LotteryFragment fragment = new LotteryFragment();
                Bundle bundle = new Bundle();
                bundle.putString(LotteryFragment.LOTTERY_NAME_ARG, MainActivity.LOTTO);
                fragment.setArguments(bundle);
                ft.replace(R.id.fragmentContainer, fragment, "LottoFragment");
                ft.commit();
                ft.addToBackStack(null);
            }
        });

        toMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LotteryFragment fragment = new LotteryFragment();
                Bundle bundle = new Bundle();
                bundle.putString(LotteryFragment.LOTTERY_NAME_ARG, MainActivity.MULTI);
                fragment.setArguments(bundle);
                ft.replace(R.id.fragmentContainer, fragment, "MultiFragment");
                ft.commit();
                ft.addToBackStack(null);
            }
        });
    }
}
