package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    String[] categoryValue = {"Any Category", "General Knowledge", "Entertainment: Books", "Entertainment: Film", "Entertainment: Music", "Entertainment: Musicals & Theatres", "Entertainment: Television", "Entertainment: Video Games", "Entertainment: Board Games", "Science & Nature", "Science: Computers", "Science: Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Art", "Celebrities", "Animals", "Vehicles", "Entertainment: Comics", "Science: Gadgets", "Entertainment: Japanese Anime & Manga", "Entertainment: Cartoon & Animations"};
    String[] difficultyValue = {"Any Difficulty", "Easy", "Medium", "Hard"};
    String[] typeValue = {"Any Type", "Multiple Choice", "True/False"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner category_spinner = findViewById(R.id.selectCategory);
        Spinner difficulty_spinner = findViewById(R.id.selectDifficulty);
        Spinner type_spinner = findViewById(R.id.selectType);
        Button apply = findViewById(R.id.apply);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryValue);
        ArrayAdapter difficultyAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, difficultyValue);
        ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typeValue);

        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        category_spinner.setAdapter(categoryAdapter);
        difficulty_spinner.setAdapter(difficultyAdapter);
        type_spinner.setAdapter(typeAdapter);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCategoryText = category_spinner.getSelectedItem().toString();
                String selectedDifficultyText = difficulty_spinner.getSelectedItem().toString();
                String selectedTypeText = type_spinner.getSelectedItem().toString();
                if (category_spinner.getSelectedItem().equals("Any Category")){
                    Toast.makeText(MainActivity.this, "Select Category Value First", Toast.LENGTH_LONG).show();
                } else if (difficulty_spinner.getSelectedItem().equals("Any Difficulty")) {
                    Toast.makeText(MainActivity.this, "Select Difficulty Value First", Toast.LENGTH_LONG).show();

                } else if (type_spinner.getSelectedItem().equals("Any Type")) {
                    Toast.makeText(MainActivity.this, "Select Type Value First", Toast.LENGTH_LONG).show();
                }else {
                    String type = "";
                    if (type_spinner.getSelectedItemPosition()==1) {
                        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                        intent.putExtra("selectedCategoryText", category_spinner.getSelectedItemPosition());
                        intent.putExtra("selectedDifficultyText", difficulty_spinner.getSelectedItem().toString());
                        type = "multiple";
                        intent.putExtra("selectedTypeText", type);
                        startActivity(intent);
                    }else {
                        type = "boolean";
                        Intent intent1=new Intent(MainActivity.this,TruFalseActivity.class);
                        intent1.putExtra("selectedCategoryText", category_spinner.getSelectedItemPosition());
                        intent1.putExtra("selectedDifficultyText", difficulty_spinner.getSelectedItem().toString());
                        intent1.putExtra("selectedTypeText",type);
                        startActivity(intent1);
                    }
                }
            }
        });
    }


}