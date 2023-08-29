package com.zeydalcan.catchtheyoda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Runnable runnable;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView3);
        textView2=findViewById(R.id.textView4);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray=new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9
        };

        score=0;
        new CountDownTimer(16000, 1000) {
            @Override
            public void onTick(long l) {
                textView.setText("Time:"+l/1000);

            }

            @Override
            public void onFinish() {
                textView2.setText("TimeOff");
                handler.removeCallbacks(runnable);
                for (ImageView ımg:imageArray){
                    ımg.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
               alert.setMessage("Are you sure to restart game?");
                //YesBUTTON
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                //NoBUTTON
               alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Toast.makeText(MainActivity.this,"Game OVER",Toast.LENGTH_LONG);
                   }
               });

                alert.show();
            }
        }.start();
        hideImg();
    }
    public void touch(View view){

        score++;
        textView2.setText("Score:"+score);


    }
    public void hideImg(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView ımg:imageArray){
                    ımg.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable,300);
            }
        };
        handler.post(runnable);

    }

}