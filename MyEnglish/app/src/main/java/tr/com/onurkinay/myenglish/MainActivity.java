package tr.com.onurkinay.myenglish;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.DialogInterface.BUTTON_NEUTRAL;

public class MainActivity extends AppCompatActivity  implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    public TextView textEnglishS;
    public TextView textTurkishS;
    public TextView textSampleS;
    public TextView textMeansS;
    public TextView textTypeS;

    private GestureDetectorCompat DetectMe;

    wordClass wordC;
    wordOrder word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEnglishS =  findViewById(R.id.txtEnglish);
        textTurkishS = findViewById(R.id.txtTurkish);
        textSampleS =findViewById(R.id.txtSample);
        textMeansS = findViewById(R.id.txtMeans);
        textTypeS = findViewById(R.id.txtType);

        textTurkishS.setVisibility(View.GONE);

        DetectMe = new GestureDetectorCompat(this, this);
        DetectMe.setOnDoubleTapListener(this);

        Feedback("", true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //Changes 'back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {

        }
        return true;
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {// RANDOM WORD
        Feedback("",true);
        return true;
    }
    @Override
    public void onLongPress(MotionEvent e) { //DELETE WORD
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set a title for alert dialog
        builder.setTitle("Delete the word that is "+word.English+"?");

        // Ask the final question
        builder.setMessage("Are you sure to delete the word");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 wordC.deleteWord(word.ID, getBaseContext());
                 Feedback("",true);
            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked

            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {// SHOW TURKISH

        textTurkishS.setVisibility(View.VISIBLE);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //output_text.setText("onDoubleTap");
        return true;
    }


    @Override
    public boolean onDown(MotionEvent e) {
       // output_text.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //output_text.setText("onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
     //   output_text.setText("onSingleTapUp");
        return true;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
       // output_text.setText("onScroll");
        return true;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
     //   output_text.setText("onFling");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.DetectMe.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void Feedback(String message, boolean ref){
        if(ref == true) {
            wordC = new wordClass(getBaseContext());
            word = wordC.randomGetWord();

            textEnglishS.setText(word.English);
            textTurkishS.setText(word.Turkish);
            textSampleS.setText(word.Sample);
            textMeansS.setText(word.Mean);
            textTypeS.setText(word.Type);

            textTurkishS.setVisibility(View.GONE);
        }
        if(message != "") {
            Context context = getApplicationContext();
            CharSequence text = message;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // Set a title for alert dialog
            builder.setTitle("Reset?");

            // Ask the final question
            builder.setMessage("Are you sure to reset the application");

            // Set the alert dialog yes button click listener
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    File file = getFileStreamPath(wordC.dbName);
                    if(file.delete()){

                        Feedback("The application was reset successfully",true);

                    }
                }
            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when No button clicked

                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();
            return true;

        }else if(id == R.id.action_info){
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_backup){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // Set a title for alert dialog
            builder.setTitle("Where backup?");

            // Ask the final question
            builder.setMessage("Where will app save your words?");

            // Set the alert dialog yes button click listener
            builder.setPositiveButton("SD Card", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Date date = new Date();
                    SimpleDateFormat ft =
                            new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

                    String backup = wordC.BackupJSON(getBaseContext());
                    String state = Environment.getExternalStorageState();
                    //external storage availability check
                    if (!Environment.MEDIA_MOUNTED.equals(state)) {
                        return;
                    }
                    File dir = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS), "MyEnglishBackup");
                    if (!dir.mkdirs()) {
                    }
                    File file = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS), "MyEnglishBackup/MyEnglish-"+ft.format(date)+".json");

                    FileOutputStream outputStream = null;
                    try {
                        file.createNewFile();
                        //second argument of FileOutputStream constructor indicates whether to append or create new file if one exists
                        outputStream = new FileOutputStream(file, true);

                        outputStream.write(backup.getBytes());
                        outputStream.flush();
                        outputStream.close();
                        Feedback("Your words was backup successfully", false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Feedback("There is a error", false);
                    }

                }
            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("Google Drive", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Feedback("The application can't backup to Google Drive.", false);

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else if(id == R.id.action_restore){
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, 42);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {


        if (requestCode == 42 && resultCode == MainActivity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                try {
                    uri = resultData.getData();

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                    reader.close();
                    wordC.RestoreJSON(stringBuilder.toString(), getBaseContext());

                    Feedback("Your backup was loaded successfully", true);
                }catch (Exception e){
                    Feedback("There is an error", false);
                }
            }
        }
    }
}
