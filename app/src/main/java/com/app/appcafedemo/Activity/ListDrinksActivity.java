package com.app.appcafedemo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.app.appcafedemo.Adapter.DrinkListAdapter;
import com.app.appcafedemo.Domain.Drinks;
import com.app.appcafedemo.R;
import com.app.appcafedemo.databinding.ActivityListDrinksBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListDrinksActivity extends BaseActivity {
    ActivityListDrinksBinding binding;
    private RecyclerView.Adapter adapterListDrink;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;

    public ListDrinksActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListDrinksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Drinks");
        binding.progressBarListDrink.setVisibility(View.VISIBLE);
        ArrayList<Drinks> list = new ArrayList<>();
        Query query;
        if(isSearch){
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else{
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Drinks.class));
                    }
                    if(list.size()>0){
                        binding.drinkListView.setLayoutManager(new GridLayoutManager(ListDrinksActivity.this,2));
                        adapterListDrink = new DrinkListAdapter(list);
                        binding.drinkListView.setAdapter(adapterListDrink);
                    }
                    binding.progressBarListDrink.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId",0);
        categoryName = getIntent().getStringExtra("Category");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch",false);

        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}