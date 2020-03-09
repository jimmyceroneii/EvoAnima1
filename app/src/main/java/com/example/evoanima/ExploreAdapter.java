package com.example.evoanima;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

// source for the code: https://stackoverflow.com/questions/49659549/how-to-show-a-firestore-collection-in-an-android-listview-using-an-adapter

public class ExploreAdapter extends ArrayAdapter<Environment> {
    public ExploreAdapter(Context context, List<Environment> object) {
        super(context, 0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = (((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_environment, parent, false));
        }

        TextView environmentTitle = (TextView) convertView.findViewById(R.id.environment_name_firebase);

        Environment environment = getItem(position);

        environmentTitle.setText(environment.getName());

        return convertView;
    }
}
