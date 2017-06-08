package com.pienkowska.swim.lottery.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pienkowska.swim.lottery.Lottery;
import com.pienkowska.swim.lottery.R;

import java.util.ArrayList;

public class ResultGridAdapter extends BaseAdapter {

    private final Context context;
    private final Lottery lottery;
    private ArrayList<Integer> numbers;
    private int[] lots;

    public ResultGridAdapter(Context context, Lottery lottery, ArrayList<Integer> numbers) {
        this.context = context;
        this.lottery = lottery;
        this.numbers = numbers;
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
        if(!numbers.contains(lots[position]))
            numTV.setTextColor(Color.argb(100,0,0,0));

        return convertView;
    }
}
