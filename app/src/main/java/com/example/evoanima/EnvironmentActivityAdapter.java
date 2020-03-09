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

public class EnvironmentActivityAdapter extends ArrayAdapter<Anima> {
    public EnvironmentActivityAdapter(Context context, List<Anima> object) {
        super(context, 0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = (((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_anima, parent, false));
        }

        TextView animaTitle = (TextView) convertView.findViewById(R.id.anima_name_firebase);

        Anima anima = getItem(position);

        animaTitle.setText(anima.getName());

        return convertView;
    }
}
