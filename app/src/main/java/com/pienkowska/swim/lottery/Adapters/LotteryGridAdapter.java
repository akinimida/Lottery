package com.pienkowska.swim.lottery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pienkowska.swim.lottery.Lottery;
import com.pienkowska.swim.lottery.R;

public class LotteryGridAdapter extends BaseAdapter {

    private final Context context;
    private final Lottery lottery;
    private int[] lots;

    public LotteryGridAdapter(Context context, Lottery lottery) {
        this.context = context;
        this.lottery = lottery;
        lots = lottery.getLots();
    }

    @Override
    public int getCount() {
        return lots.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(lottery.layoutId, null);
        }

        TextView numTV = (TextView) convertView.findViewById(R.id.numTV);
        numTV.setText(String.valueOf(lots[position]));

        return convertView;
    }
}
