package e.thejo.timer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private TextView timer;
    private long startTime;
    private Handler handler;
    private Update update;
    private long timeStore;
    private GridLayout grid;
    private SeekBar seek;
    private ListView history;
    private RelativeLayout color;

    void resetGrid ()
    {
        grid.removeAllViews();
        int dim = seek.getProgress()*3+3;
        grid.setColumnCount(dim);
        grid.setRowCount(dim);
        int size = dim*dim;
        Random imagexy = new Random();
        int xy = imagexy.nextInt(size);
        for (int i = 0; i < size; i++)
        {
            ImageView largeImage = (ImageView) getLayoutInflater().inflate(R.layout.imageview, grid, false);
            if (i == xy) {
                Drawable image = getResources().getDrawable(R.drawable.aay);
                largeImage.setImageDrawable(image);
                TextView historyItem = new TextView(getApplicationContext());
                historyItem.setText("Random number: " + xy + " in x: " + largeImage.getX() + " y: " + largeImage.getY());

            }
            else
            {
                //Drawable image2 = getResources().getDrawable(R.drawable.sonicxp);
                //largeImage.setImageDrawable(image2);
            }
            grid.addView(largeImage);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timer);
        handler = new Handler();
        update = new Update();
        startTime = System.currentTimeMillis();
        handler.postDelayed(update, 1000);
        seek = findViewById(R.id.seekBarThing);
        color = findViewById(R.id.mainview);
        color.setBackgroundColor(0xff1fd545);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                resetGrid();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        grid = findViewById(R.id.grid);
        resetGrid();
    }

    public void gotoColor(View v)
    {
        Intent i = new Intent(this, ColorActivity.class);
        startActivityForResult(i, 1);
    }

    public void gotoMovieList(View v)
    {
        Intent i = new Intent(this, MovielistActivity.class);
        startActivity(i);
    }

    public void gotoCards(View v)
    {
        Intent i = new Intent(this, CardsActivity.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        int icolor = data.getIntExtra("Color", 0xffff0000);
        color.setBackgroundColor(icolor);
    }

    public void buttonStartHandler(View v)
    {
        handler.postDelayed(update, 1000);
        timer.setText("" + startTime);
    }

    public void buttonResetHandler(View v)
    {
        startTime = System.currentTimeMillis();
        timer.setText("00");
    }

    public void buttonHandler(View v)
    {
        handler.removeCallbacks(update);
    }

    private class Update implements Runnable
    {
        @Override
        public void run()
        {
            resetGrid();
            long current = System.currentTimeMillis();
            long elapsed = (current - startTime)/1000;
            timer.setText("" + elapsed);
            handler.postDelayed(update, 1000);
        }
    }
}
