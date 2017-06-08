package com.pienkowska.swim.lottery.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pienkowska.swim.lottery.Lottery;
import com.pienkowska.swim.lottery.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NumberGridAdapter extends BaseAdapter {
    private final Context context;
    private Lottery lottery;
    private ArrayList<Integer> clicked;

    public NumberGridAdapter(Context context, Lottery lottery) {
        this.context = context;
        this.lottery = lottery;
        clicked = new ArrayList<>(lottery.lotsNum);
    }

    public ArrayList<Integer> getClicked() {
        return clicked;
    }

    @Override
    public int getCount() {
        return lottery.range;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(lottery.layoutId, null);
            final TextView numText = (TextView) convertView.findViewById(R.id.numTV);
            final ImageView bgImage = (ImageView) convertView.findViewById(R.id.bgIV);

            final ViewHolder viewHolder = new ViewHolder(numText, bgImage);
            convertView.setClickable(false);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.numText.setText(String.valueOf(position + 1));

        if(clicked.contains(position + 1))
            viewHolder.bgImage.setColorFilter(Color.argb(100,0,0,0));
        else
            viewHolder.bgImage.setColorFilter(Color.argb(0,0,0,0));

        viewHolder.bgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clicked.contains(position + 1) && clicked.size() < lottery.lotsNum) {
                    clicked.add(position + 1);
                    viewHolder.bgImage.setColorFilter(Color.argb(100,0,0,0));
                }
                else {
                    clicked.remove(Integer.valueOf(position + 1));
                    viewHolder.bgImage.setColorFilter(Color.argb(0,0,0,0));
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {

        public final TextView numText;
        public final ImageView bgImage;

        public ViewHolder(TextView numText, ImageView bgImage) {
            this.numText = numText;
            this.bgImage = bgImage;
        }
    }
}
