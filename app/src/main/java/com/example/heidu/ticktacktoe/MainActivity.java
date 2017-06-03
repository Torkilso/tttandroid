package com.example.heidu.ticktacktoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
    }


    private static ImageView[][] brett = new ImageView[3][3];
    private static EditText header;
    private static Button resetBtn;

    private static int[][] brettL = new int[3][3];
    private static int spiller = 0, counter = 0;




    public void setUp(){
        header = (EditText)findViewById(R.id.header);
        header.setKeyListener(null);
        header.setText("X");

        resetBtn = (Button)findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        reset();
                        resetBtn.setVisibility(View.INVISIBLE);
                        //header.setVisibility(View.INVISIBLE);
                    }
                }
        );

        brett[0][0] = (ImageView)findViewById(R.id.imageView24);
        brett[0][1] = (ImageView)findViewById(R.id.imageView27);
        brett[0][2] = (ImageView)findViewById(R.id.imageView20);
        brett[1][0] = (ImageView)findViewById(R.id.imageView26);
        brett[1][1] = (ImageView)findViewById(R.id.imageView31);
        brett[1][2] = (ImageView)findViewById(R.id.imageView25);
        brett[2][0] = (ImageView)findViewById(R.id.imageView30);
        brett[2][1] = (ImageView)findViewById(R.id.imageView1);
        brett[2][2] = (ImageView)findViewById(R.id.imageView29);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                brettL[i][j] = 0;

                //Variables for the onClick
                final int finalI = i;
                final int finalJ = j;
                brett[i][j].setOnClickListener(
                        new ImageView.OnClickListener() {
                            public void onClick(View v) {
                                if (brettL[finalI][finalJ] == 0){
                                    if (spiller == 0){
                                        //Sets clicked square to X
                                        brett[finalI][finalJ].setImageResource(R.drawable.x);
                                        brettL[finalI][finalJ] = 1;
                                        header.setText("O");
                                        if (check()){
                                            header.setText("Player X won");
                                            resetBtn.setVisibility(View.VISIBLE);
                                            //header.setVisibility(View.VISIBLE);
                                            preReset();
                                        } else spiller = 1;
                                    } else {
                                        //sets clicked square to O
                                        brett[finalI][finalJ].setImageResource(R.drawable.o);
                                        brettL[finalI][finalJ] = 2;
                                        header.setText("X");
                                        if(check()){
                                            header.setText("Player O won");
                                            resetBtn.setVisibility(View.VISIBLE);
                                            //header.setVisibility(View.VISIBLE);
                                            preReset();
                                        } else spiller = 0;
                                    }
                                    counter++;

                                    if(counter == 9){
                                        resetBtn.setVisibility(View.VISIBLE);

                                    }
                                }
                            }
                        }
                );
            }
        }





    }

    public boolean check(){
        //Check for winner
        for (int i = 0; i < 3; i++) {
            for (int k = 1; k < 3; k++) {
                if(brettL[i][0] == k && brettL[i][1] == k && brettL[i][2] == k){
                    return true;
                }
            }

            for (int j = 1; j < 3; j++) {
                if(brettL[0][i] == j && brettL[1][i] == j && brettL[2][i] == j){
                    return true;
                }
            }
        }

        for (int j = 1; j < 3; j++) {
            if(brettL[0][0] == j && brettL[1][1] == j && brettL[2][2] == j){
                return true;
            }
        }

        for (int j = 1; j < 3; j++) {
            if(brettL[0][2] == j && brettL[1][1] == j && brettL[2][0] == j){
                return true;
            }
        }



        return false;
    }

    public void preReset(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                brettL[i][j] = 1;
            }
        }
    }

    public void reset(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                brett[i][j].setImageResource(R.drawable.blank);
                brettL[i][j] = 0;
            }
        }
        counter = 0;
        header.setText("X");
        spiller = 0;
    }


}
