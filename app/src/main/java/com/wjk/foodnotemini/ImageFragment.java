package com.wjk.foodnotemini;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by wjk on 2018. 5. 23..
 */

public class ImageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        Bundle extra = getArguments();
        int a = extra.getInt("position");

        ImageView tv = (ImageView)rootView.findViewById(R.id.imageview);
        Glide.with(getActivity()).load(extra.getString("image")).into(tv);

        return rootView;
    }
}
