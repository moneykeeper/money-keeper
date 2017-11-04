package com.android.tiagorodrigues.moneykeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class lista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String[] myStrings = intent.getStringArrayExtra("strings");

        //  setContentView(R.layout.activity_objectivos);

        TableLayout tableLayout = new TableLayout(this);
        for (int i = 0; i < myStrings.length; i++){
            TableRow tableRow = new TableRow(this);
                Button button = new Button(this);
                button.setText(""+myStrings[i]);
                tableRow.addView(button);
            tableLayout.addView(tableRow);
        }
        setContentView(tableLayout);
    }
}
