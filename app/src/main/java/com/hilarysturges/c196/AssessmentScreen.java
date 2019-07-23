package com.hilarysturges.c196;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class AssessmentScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList textArray = new ArrayList();
        for (int i = 0; i < MainActivity.assessments.size(); i++) {
            Assessment assessment = MainActivity.assessments.get(i);
            textArray.add(assessment.getName());
        }
        ScrollView scrollView = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < textArray.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(textArray.get(i).toString());
            textView.setTextSize(30);
            textView.setId(i);
            textView.setPadding(30,30,0,30);
            final String name = MainActivity.assessments.get(i).getName();
            final String type = MainActivity.assessments.get(i).getType();
            final String id = String.valueOf(MainActivity.assessments.get(i).get_id());
            final String index = String.valueOf(i);
            final String[] courseDetails = new String[2];
            for (int j=0 ; j<MainActivity.courses.size() ; j++) {
                if (MainActivity.courses.get(j).getCourseAssessments()!=null) {
                    for (int k=0 ; k<MainActivity.courses.get(j).getCourseAssessments().size() ; k++) {
                        Assessment assessment = MainActivity.courses.get(j).getCourseAssessment(k);
                        if (String.valueOf(assessment.get_id()).equals(id)) {
                            courseDetails[0] = String.valueOf(MainActivity.courses.get(j).getEndDate());
                            courseDetails[1] = MainActivity.courses.get(j).getNotes();
                        }
                    }
                }
            }

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), DetailAssessmentScreen.class);
                    intent.putExtra("name", name);
                    intent.putExtra("type", type);
                    intent.putExtra("ID", id);
                    intent.putExtra("index", index);
                    intent.putExtra("courseDueDate", courseDetails[0] );
                    intent.putExtra("notes", courseDetails[1]);
                    startActivity(intent);
                }
            });
            linearLayout.addView(textView);

        }



        Button addAssessmentButton = new Button(this);
        addAssessmentButton.setText("Add Assessment");
        Button homeScreenButton = new Button(this);
        homeScreenButton.setText("Return to home");

        addAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddAssessmentScreen.class);
                startActivity(i);
            }
        });

        homeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        linearLayout.addView(addAssessmentButton);
        linearLayout.addView(homeScreenButton);
        scrollView.addView(linearLayout);
        setContentView(scrollView);
    }
}
