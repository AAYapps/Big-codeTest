package e.thejo.timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {

    EditText Title;
    EditText IMDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        Title = findViewById(R.id.Title);
        IMDB = findViewById(R.id.imdb);
    }

    public void addToMovieList(View view) {
        Intent data = new Intent();
        data.putExtra("movieTitle", Title.getText().toString());
        data.putExtra("movieIMDB", IMDB.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}
