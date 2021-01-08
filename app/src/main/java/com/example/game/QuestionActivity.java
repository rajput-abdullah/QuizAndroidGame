package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnSubmit;
    public static String selectedCategoryText = "selectedTypeText";
    public static String selectedDifficultyText = "selectedDifficultyText";
    public static String selectedTypeText = "selectedTypeText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionactivity);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(QuestionActivity.this,Score.class);

                startActivity(intent);
            }
        });
        recyclerView=findViewById(R.id.questionRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                    QuestionsAdapter userListAdapter = new QuestionsAdapter(getApplicationContext(),questionList);
                    recyclerView.setAdapter(userListAdapter);
//                    Toast.makeText(this, ""+questionList.size(), Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show() );
            App.getInstance().getRequestQueue().add(r);

        }

      /*  if(selectedTypeText.equals("True/False")){
             StringRequest r = new StringRequest(Request.Method.GET, Url, response -> {
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
                        QuestionsAdapter trueFalse = new QuestionsAdapter(getApplicationContext(),questionList);
                        recyclerView.setAdapter(trueFalse);

//                    Toast.makeText(this, ""+questionList.size(), Toast.LENGTH_SHORT).show();
                        for (Question e :
                                questionList) {
                            Log.d("data", "onCreate: "+e.getQuestion());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {

                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            });
            App.getInstance().getRequestQueue().add(r);
        }*/



}
