package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

//import com.example.fileioexample.store.Store;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ListStores extends AppCompatActivity {

    private RecyclerView recyclerView;
    StoresListAdapter adapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_stores);

        mbase = FirebaseDatabase.getInstance().getReference("/accounts/owners/");
        Log.d("THIS_IS_TAG", mbase.getKey());
        recyclerView = findViewById(R.id.recycler_store);

        // displays the recyclerview linearly(vertical)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // makes query into database to obtain the required data
        FirebaseRecyclerOptions<StoreObj> options = new FirebaseRecyclerOptions.Builder<StoreObj>().setQuery(mbase, StoreObj.class).build();

        //connecting the StoreObj class to the Adapter class
        adapter = new StoresListAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}