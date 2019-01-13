package tr.com.onurkinay.myenglish;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    String s = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wordClass WordClass = new wordClass(getBaseContext());

                EditText Word = findViewById(R.id.textWord);
                EditText Translate = findViewById(R.id.textTranslate);
                EditText Sample = findViewById(R.id.textSample);
                EditText Means = findViewById(R.id.textMeans);
                Spinner Type = findViewById(R.id.spWordType);
if(Word.getText().toString().matches("") || Translate.getText().toString().matches("") || Sample.getText().toString().matches("")|| Means.getText().toString().matches("")){
    showMessage("There is one or more blank inputs. Please type all inputs");
}else{
                wordOrder newWord = new wordOrder(-2,
                        Word.getText().toString(),
                        Translate.getText().toString(),
                        Sample.getText().toString(),
                        Means.getText().toString(),
                        Type.getSelectedItem().toString()
                );

                WordClass.addWord(newWord, getBaseContext());

                showMessage("The word was added successfully");

            }}
        });
    }

    public void showMessage(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
