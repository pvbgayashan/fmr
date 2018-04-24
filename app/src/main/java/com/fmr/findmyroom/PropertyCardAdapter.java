package com.fmr.findmyroom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;

public class PropertyCardAdapter extends BaseAdapter {

    private List<Property> propList;
    private LayoutInflater layoutInflater;
    private Context context;

    public PropertyCardAdapter(List<Property> propList, Context context) {
        this.propList = propList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return propList.size();
    }

    @Override
    public Object getItem(int i) {
        return propList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        // set property card layout
        view = layoutInflater.inflate(R.layout.list_prop_card, null);

        // update data for property card
        TextView propNameTxtView = view.findViewById(R.id.propName);
        TextView miniAddressTxtView = view.findViewById(R.id.miniAddress);
        TextView priceTxtView = view.findViewById(R.id.price);
        ImageView propImgView = view.findViewById(R.id.propImageView);

        // set click listener for property card
        CardView propCard = view.findViewById(R.id.propCard);
        propCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailViewIntent = new Intent(context, DetailViewActivity.class);

                // add data to intent
                detailViewIntent.putExtra("imgDownloadUrl", propList.get(i).getDownloadUrl());
                detailViewIntent.putExtra("propName", propList.get(i).getPropName());

                context.startActivity(detailViewIntent);
            }
        });

        propNameTxtView.setText(propList.get(i).getPropName());
        miniAddressTxtView.setText(propList.get(i).getCity() + ", " + propList.get(i).getCountry());
        priceTxtView.setText("$" + propList.get(i).getPrice() + "/day");
        Picasso.with(context)
                .load(propList.get(i).getDownloadUrl())
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerInside()
                .into(propImgView);

        return view;
    }
}
