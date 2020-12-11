package com.example.planterria;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlantListAdapter extends ArrayAdapter<Houseplant> {

    public static final String TAG = "PlantListAdapter";
    private Context mContext;

    public PlantListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Houseplant> objects) {
        super(context, resource, objects);
        this.mContext = context;
    }


}
