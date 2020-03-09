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

public class EnvironmentActivityWithFirebase extends AppCompatActivity {
    public static final String EXTRA_ENVIRONMENTID = "environment";
    private final String COLLECTION_KEY = "animas";

    // player from the explore activity
    private Player player;

    // data base
    private FirebaseFirestore db;

    // adapter
    private EnvironmentActivityAdapter mEnvironmentActivityAdapter;
    private ArrayList<Anima> mAnimaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_with_firebase);
        player = (Player) getIntent().getExtras().get("player");
        String environment = (String) getIntent().getExtras().get(EnvironmentActivityWithFirebase.EXTRA_ENVIRONMENTID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the database
        initFirestore();

        // grabs all the animas from the specific environment in the databases
        db.collection(COLLECTION_KEY).whereEqualTo(EXTRA_ENVIRONMENTID, environment).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                // a list of the environments from the db
                final List<Anima> mAnimaList = new ArrayList<>();
                if(task.isSuccessful()) {
                    // if we establish contact, iterate over the results and create environments to add to the list
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        Anima an = document.toObject(Anima.class);
                        mAnimaList.add(an);
                    }
                    // grab the list view to fill up with the adapter
                    ListView animaList = (ListView) findViewById(R.id.list_animas_firebase);
                    // set the adapter
                    mEnvironmentActivityAdapter = new EnvironmentActivityAdapter(EnvironmentActivityWithFirebase.this, mAnimaList);
                    animaList.setAdapter(mEnvironmentActivityAdapter);

                    // set the item click listener
                    AdapterView.OnItemClickListener itemClickListener =
                            new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> listAnimas,
                                                        View itemView,
                                                        int position,
                                                        long id) {
                                    // go from here to the anima activity, where we show the anima that's been "caught"
                                    Intent intent = new Intent(EnvironmentActivityWithFirebase.this, AnimaActivity.class);
                                    // set up the player to be passed to the anima activity
                                    player.setAnima(mAnimaList.get((int) id));
                                    intent.putExtra("player", player);
                                    startActivity(intent);
                                }
                            };
                    // set the item click listener for the list
                    animaList.setOnItemClickListener(itemClickListener);
                }
            }
        });
    }

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }
}
