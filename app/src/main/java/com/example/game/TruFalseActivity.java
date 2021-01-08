package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TruFalseActivity extends AppCompatActivity {
    RecyclerView mRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tru_false);
        mRecycler=findViewById(R.id.questionRecyclerTrueFalse);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        int categoryText = getIntent().getExtras().getInt("selectedCategoryText");
        String selectedDifficultyText = getIntent().getExtras().getString("selectedDifficultyText").toLowerCase();
        String selectedTypeText = getIntent().getExtras().getString("selectedTypeText");

        String Url = "https://opentdb.com/api.php?amount=10&category=" + (categoryText + 8) +
                "&difficulty=" +
                selectedDifficultyText +
                "&type=" + selectedTypeText;


            StringRequest r= new StringRequest(Request.Method.GET, Url, response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("results")) {
                        JSONArray results = jsonObject.getJSONArray("results");
                        List<Question> questionList = new ArrayList<>();
                        for (int i = 0; i < results.length(); i++) {
                            Question e = new Question();
                            e.setCategory(results.getJSONObject(i).getString("category"));
                            e.setType(results.getJSONObject(i).getString("type"));
                            e.setDifficulty(results.getJSONObject(i).getString("difficulty"));
                            e.setQuestion(results.getJSONObject(i).getString("question"));
                            e.setCorrectAnswer(results.getJSONObject(i).getString("correct_answer"));
                            List<String > stringList = new ArrayList<>();
                            JSONArray incorrect_answers = results.getJSONObject(i).getJSONArray("incorrect_answers");
                            for (int i1 = 0; i1 < incorrect_answers.length(); i1++) {
                                stringList.add(incorrect_answers.getString(i1));
                            }
                            e.setIncorrectAnswers(stringList);
                            questionList.add(e);
                        }
                        TrueFalse userListAdapter = new TrueFalse(getApplicationContext(),questionList);
                        mRecycler.setAdapter(userListAdapter);
//                    Toast.makeText(this, ""+questionList.size(), Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show() );
            App.getInstance().getRequestQueue().add(r);

    }
}