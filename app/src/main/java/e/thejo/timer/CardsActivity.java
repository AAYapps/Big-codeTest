package e.thejo.timer;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CardsActivity extends AppCompatActivity {
    ImageView card;
    ArrayList<String> cardNames;
    Random genCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        card = findViewById(R.id.card);
        cardNames = new ArrayList<String>();
        genCard = new Random();

        AssetManager man = getAssets();

        try
        {
            for (String file : man.list(""))
            {
                cardNames.add(file);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Assest unavalible: " + e.getMessage(), Toast.LENGTH_LONG);
        }

    }

    public void onCardClick(View view) throws IOException
    {
        int r = genCard.nextInt(cardNames.size());
        card.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cardNames.get(r))));
    }
}
