package e.thejo.timer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

/**
 * Created by thejo on 3/20/2018.
 */

public class ColorActivity extends AppCompatActivity {

    int color = 0;
    RadioGroup radg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycolor);

        radg = findViewById(R.id.colors);

        radg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.whitebtn:
                        color = 0xffffffff;
                        break;
                    case R.id.greenbtn:
                        color = 0xff00ff00;
                        break;
                    case R.id.bluebtn:
                        color = 0xff0000ff;
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("Color", color);
        setResult(RESULT_OK, data);
        finish();
    }

}
