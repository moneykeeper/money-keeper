package com.android.tiagorodrigues.moneykeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class objectivos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_objectivos);

     /*   TableLayout tableLayout = new TableLayout(this);
        for (int i = 0; i < 5; i++){
            TableRow tableRow = new TableRow(this);

            for(int j = 0; j< 3 ;j++){
                Button button = new Button(this);
                button.setText(""+j);
                tableRow.addView(button);
            }
            tableLayout.addView(tableRow);
        }
        setContentView(tableLayout);*/
        String type = "objectivos";
        ConnectObjectivos connect = new ConnectObjectivos(this);
        connect.execute(type,"tiago","tiagogp");

    }
}
