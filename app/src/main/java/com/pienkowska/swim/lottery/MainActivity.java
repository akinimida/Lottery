package com.pienkowska.swim.lottery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.pienkowska.swim.lottery.Fragments.CheckOutFragment;
import com.pienkowska.swim.lottery.Fragments.MainActivityFragment;
import com.pienkowska.swim.lottery.Fragments.RewardsFragment;

public class MainActivity extends AppCompatActivity {
    public static final String MINI = "Mini Lotto";
    public static final String LOTTO = "Lotto";
    public static final String MULTI = "Multi Multi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Loteria A'la Lotto");
        setSupportActionBar(toolbar);

        intiLotteries();

        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null)
                return;

            MainActivityFragment fragment = new MainActivityFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, fragment).commit();
        }
    }

    private void intiLotteries() {
        Lottery mini = new Lottery(42, 5, R.layout.layout_lottery_ball_yellow, 100000);
        Lottery lotto = new Lottery(49, 6, R.layout.layout_lottery_ball_yellow, 500000);
        Lottery multi = new Lottery(80, 20, R.layout.layout_lottery_ball_purple, 2500000);

        Manager m = Manager.getInstance();
        m.addLottery(mini, MINI);
        m.addLottery(lotto, LOTTO);
        m.addLottery(multi, MULTI);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_check).setVisible(false);
        menu.findItem(R.id.action_rewards).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_check:
                goToCheckOut();
                return true;
            case R.id.action_rewards:
                showRewards();
                return true;
            case R.id.action_visit_lotto:
                showLottoSite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showLottoSite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lotto.pl"));
        startActivity(browserIntent);
    }

    private void goToCheckOut() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        CheckOutFragment fragment = new CheckOutFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CheckOutFragment.LOTTERY_NAME_ARG, Manager.getInstance().getLotteryName());
        fragment.setArguments(bundle);
        ft.replace(R.id.fragmentContainer, fragment, "CheckOutFragment");
        ft.commit();
        ft.addToBackStack(null);
    }

    private void showRewards() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        RewardsFragment fragment = new RewardsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CheckOutFragment.LOTTERY_NAME_ARG, Manager.getInstance().getLotteryName());
        fragment.setArguments(bundle);
        ft.replace(R.id.fragmentContainer, fragment, "RewardsFragment");
        ft.commit();
        ft.addToBackStack(null);
    }

}
