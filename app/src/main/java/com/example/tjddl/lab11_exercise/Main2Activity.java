package com.example.tjddl.lab11_exercise;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    static int count = 0;
    TextView tv;
    EditText et;
    myTask task1;
    ImageView iv;
    int a ;
    int sec_count;
    String menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iv = (ImageView) findViewById(R.id.imageView);

        iv.setImageResource(R.drawable.start);
        et = (EditText) findViewById(R.id.Edit_sec);
        tv = (TextView) findViewById(R.id.textViewSec);
    }

    class myTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {


            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            if(isCancelled()) return null;

            for (int i = 0; i < 100; i++) {
                try {
                    a=params[0];
                    Thread.sleep(1000);

                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sec_count = i;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int b = values[0]/a;
            if(b%3==0) {iv.setImageResource(R.drawable.ham); menu ="햄버거";}
            else if(b%3==1) {iv.setImageResource(R.drawable.ramen);menu ="라면";}
            else if(b%3==2) {iv.setImageResource(R.drawable.salad);menu ="샐러드";}
            tv.setText("시작부터" + values[0] + "초");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public void onClick(View v) {


        if (v.getId() == R.id.imageView) {
            if(count ==0) {
                task1 = new myTask();
                String s = et.getText().toString();

                if (!TextUtils.isEmpty(s)) {
                    task1.execute(Integer.parseInt(s));

                }
                else return;
                count++;
            }
            else if(count==1){
                task1.cancel(true);
                tv.setText(menu+" 선택("+sec_count+"초)");
                tv.setTextColor(Color.BLUE);
                count++;
            }
        }

        if(v.getId()==R.id.Button) {
            iv.setImageResource(R.drawable.start);
            et.setText("");
            tv.setText("");
            count=0;
        }
    }

}
