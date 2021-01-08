package com.example.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrueFalse extends RecyclerView.Adapter<TrueFalse.TrueFalseViewHolder> {
    Context context;
    List<Question> questions;

    public TrueFalse(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public TrueFalseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.truefalseitemview, parent, false);
        return new TrueFalse.TrueFalseViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TrueFalseViewHolder holder, int position) {
        holder.questionTrueFalse.setText(questions.get(position).getQuestion());
        List<String> incorrectQuestion=questions.get(position).getIncorrectAnswers();

        holder.choice1TrueFalse.setText(questions.get(position).getCorrectAnswer());
        holder.choice2TrueFalse.setText(incorrectQuestion.get(0));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class TrueFalseViewHolder extends RecyclerView.ViewHolder {
        TextView questionTrueFalse;
        RadioButton choice1TrueFalse,choice2TrueFalse;

        public TrueFalseViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTrueFalse=itemView.findViewById(R.id.questionTrueFalse);
            choice1TrueFalse=itemView.findViewById(R.id.option1TrueFalse);
            choice2TrueFalse=itemView.findViewById(R.id.option2TrueFalse);
        }
    }
}
