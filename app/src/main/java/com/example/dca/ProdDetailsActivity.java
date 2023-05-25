package com.example.dca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProdDetailsActivity extends AppCompatActivity {
    private int mScore1;

    private TextView mScoreText1;
    TextView detailDesc, detailTitle, detailLang;
    ImageView detailImage;

    Button add;
    String key = "";

    String imageUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_details);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailLang = findViewById(R.id.detailLang);
        add = findViewById(R.id.addtocart);

        mScoreText1=(TextView)findViewById(R.id.score_1);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailLang.setText(bundle.getString("Language"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdDetailsActivity.this, CartActivity.class)
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Language", detailLang.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });



        if(savedInstanceState != null){

            mScoreText1.setText(String.valueOf(mScore1));

        }
    }

    @SuppressLint("NonConstantResourceId")
    public void decreaseScore(View view) {

        int  viewID = view.getId();
        switch (viewID) {
            case R.id.decreaseTeam1:
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
        }
    }
    @SuppressLint("NonConstantResourceId")
    public void increaseScore(View view) {

        int  viewID = view.getId();
        switch (viewID) {
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
        }
    }


}
