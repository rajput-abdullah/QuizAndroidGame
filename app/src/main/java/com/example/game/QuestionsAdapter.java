package com.example.game;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionVh> {
    Context context;
    List<Question> questions;
    public static int score=0;
    public QuestionsAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_view, parent, false);
        return new QuestionVh(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionVh holder, int position) {
        holder.question.setText(questions.get(position).getQuestion());
        List<String> incorrectQuestion=questions.get(position).getIncorrectAnswers();
        holder.choice1.setText(incorrectQuestion.get(0));
        holder.choice2.setText(incorrectQuestion.get(1));
        holder.choice3.setText(incorrectQuestion.get(2));
//        holder.choice4.setText(incorrectQuestion.get(3));

        holder.choice4.setText(questions.get(position).getCorrectAnswer());
        holder.choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.choice1.isChecked())
                    if( holder.choice1.getText().toString().equals(questions.get(position).getCorrectAnswer())) {
                    score++;
                        holder.choice4.setEnabled(true);
                        holder.choice3.setEnabled(true);
                        holder.choice2.setEnabled(true);
                        holder.choice1.setEnabled(true);
                    Toast.makeText(context, ""+score, Toast.LENGTH_SHORT).show();
                }
                else {
                    score--;
                    }
                holder.choice3.setEnabled(false);
                holder.choice2.setEnabled(false);
                holder.choice1.setEnabled(false);
                holder.choice4.setEnabled(false);

            }
        });
        holder.choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.choice2.isChecked())
                    if( holder.choice2.getText().toString().equals(questions.get(position).getCorrectAnswer())) {
                    score++;
                        holder.choice4.setEnabled(true);
                        holder.choice3.setEnabled(true);
                        holder.choice2.setEnabled(true);
                        holder.choice1.setEnabled(true);
                    Toast.makeText(context, ""+score, Toast.LENGTH_SHORT).show();
                }
                    else {
                        score--;
                    }
                holder.choice3.setEnabled(false);
                holder.choice2.setEnabled(false);
                holder.choice1.setEnabled(false);
                holder.choice4.setEnabled(false);

            }
        });
        holder.choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.choice3.isChecked())
                    if(holder.choice3.getText().toString().equals(questions.get(position).getCorrectAnswer())) {
                    score++;
                        holder.choice4.setEnabled(true);
                        holder.choice3.setEnabled(true);
                        holder.choice2.setEnabled(true);
                        holder.choice1.setEnabled(true);
                    Toast.makeText(context, ""+score, Toast.LENGTH_SHORT).show();
                }
                    else {
                        score--;
                    }
                holder.choice3.setEnabled(false);
                holder.choice2.setEnabled(false);
                holder.choice1.setEnabled(false);
                holder.choice4.setEnabled(false);

            }
        });
        holder.choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.choice4.isChecked())
                    if(holder.choice4.getText().toString().equals(questions.get(position).getCorrectAnswer())) {
                        score++;
                        holder.choice4.setEnabled(true);
                        holder.choice3.setEnabled(true);
                        holder.choice2.setEnabled(true);
                        holder.choice1.setEnabled(true);
                        Toast.makeText(context, ""+score, Toast.LENGTH_SHORT).show();
                    }  else {
                        score--;
                    }
                holder.choice3.setEnabled(false);
                holder.choice2.setEnabled(false);
                holder.choice1.setEnabled(false);
                holder.choice4.setEnabled(false);
            }
        });

        }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionVh extends RecyclerView.ViewHolder{
        TextView question;
        RadioGroup radioGroup;
        RadioButton choice1,choice2,choice3,choice4;
        public QuestionVh(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question);
            choice1=itemView.findViewById(R.id.option1);
            choice2=itemView.findViewById(R.id.option2);
            choice3=itemView.findViewById(R.id.option3);
            radioGroup=itemView.findViewById(R.id.optionsRadio);
            choice4=itemView.findViewById(R.id.option4);
        }
    }

}
