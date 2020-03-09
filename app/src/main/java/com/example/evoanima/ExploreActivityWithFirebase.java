package com.example.evoanima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivityWithFirebase extends AppCompatActivity {
    private final String COLLECTION_KEY = "environments";

    // player from the player activity
    private Player player;

    // data base
    private FirebaseFirestore db;

    // adapter
    private ExploreAdapter mExploreAdapter;
    private ArrayList<Environment> mEnvironmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_with_firebase);
        player = (Player) getIntent().getExtras().get("player");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get database
        initFirestore();

        // grabs all the environments from the databases
        db.collection(COLLECTION_KEY).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                // a list of the environments from the db
                final List<Environment> mEnvironmentList = new ArrayList<>();
                if(task.isSuccessful()) {
                    // if we establish contact, iterate over the results and create environments to add to the list
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        Environment env = document.toObject(Environment.class);
                        mEnvironmentList.add(env);
                    }
                    // grab the list view to fill up with the adapter
                    ListView listEnvironments = (ListView) findViewById(R.id.list_environments_firebase);
                    // set the adapter
                    mExploreAdapter = new ExploreAdapter(ExploreActivityWithFirebase.this, mEnvironmentList);
                    listEnvironments.setAdapter(mExploreAdapter);

                    // set the item click listener
                    AdapterView.OnItemClickListener itemClickListener =
                            new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> listEnvironments,
                                                        View itemView,
                                                        int position,
                                                        long id) {
                                    // go from here to the environment activity class where we display a list of animas
                                    Intent intent = new Intent(ExploreActivityWithFirebase.this, EnvironmentActivityWithFirebase.class);
                                    // put the name of the environment in so we can filter the animas
                                    intent.putExtra(EnvironmentActivityWithFirebase.EXTRA_ENVIRONMENTID, mEnvironmentList.get((int) id).getName());
                                    intent.putExtra("player", player);
                                    startActivity(intent);
                                }
                            };
                    // set the item click listener for the list
                    listEnvironments.setOnItemClickListener(itemClickListener);
                }
            }
        });
    }

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }
}
