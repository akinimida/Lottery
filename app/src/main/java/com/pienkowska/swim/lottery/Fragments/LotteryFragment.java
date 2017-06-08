package com.pienkowska.swim.lottery.Fragments;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.pienkowska.swim.lottery.Adapters.LotteryGridAdapter;
import com.pienkowska.swim.lottery.Lottery;
import com.pienkowska.swim.lottery.MainActivity;
import com.pienkowska.swim.lottery.Manager;
import com.pienkowska.swim.lottery.R;
import com.pienkowska.swim.lottery.ShakeDetector;

public class LotteryFragment extends Fragment {
    public static final String LOTTERY_NAME_ARG = "lottery_name";
    private Lottery lottery;

    private LotteryGridAdapter gridAdapter;

    private View rootView;
    private GridView grid;
    private TextView title;

    private SensorManager sensorManager;
    private Sensor sensor;
    private ShakeDetector shakeDetector;

    public LotteryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lottery, container, false);
        setHasOptionsMenu(true);

        final String name = getArguments().getString(LOTTERY_NAME_ARG);
        lottery = Manager.getInstance().getLottery(name);
        Manager.getInstance().setCurrent(name);

        initViews();
        title.setText("Wyniki " + name);

        setGridAdapterAndAnim();

        setSensor();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDetector, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        sensorManager.unregisterListener(shakeDetector);
        super.onPause();
    }

    private void initViews() {
        title = (TextView) rootView.findViewById(R.id.titleTV);
        grid = (GridView) rootView.findViewById(R.id.gridGV);
        if(lottery.lotsNum == 6) {
            grid.setNumColumns(3);
            grid.setPadding(64,0,64,0);
            grid.setHorizontalSpacing(32);
        }
    }

    private void setSensor() {
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector();
        shakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                lottery.shuffle();
                setGridAdapterAndAnim();
            }
        });
    }

    private void setGridAdapterAndAnim() {
        gridAdapter = new LotteryGridAdapter(getContext(), lottery);
        grid.invalidateViews();
        grid.setAdapter(gridAdapter);
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.ball_animation);
        grid.setLayoutAnimation(new LayoutAnimationController(anim, 0.1f));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_check).setVisible(true);
        menu.findItem(R.id.action_rewards).setVisible(true);
    }
}
