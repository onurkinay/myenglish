package tr.com.onurkinay.myenglish;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.fsmvu)
                .addItem(new Element().setTitle("Version Alpha 0.1"))
                .addGroup("Connect with us")
                .addEmail("ben@onurkinay.com.tr")
                .addWebsite("http://onurkinay.com.tr")
                .addItem(getBitbucket())
                .addItem(getLinkedIn())
                .setDescription("This application is my first application. The application can save your words you want to learn and you can practice words you had saved every day")
                .create();
        super.onCreate(savedInstanceState);
        setContentView(aboutPage);
    }

    Element getBitbucket(){
        Element bitbucket = new Element();
        bitbucket.setTitle("Visit my Bitbucket");
        bitbucket.setIconDrawable(R.drawable.bitbucket);
        bitbucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bitbucket.org/onurkinay/"));
                startActivity(browserIntent);
            }
        });

        return bitbucket;
    }

    Element getLinkedIn(){
        Element linkedIn = new Element();
        linkedIn.setTitle("Visit my LinkedIn");
        linkedIn.setIconDrawable(R.drawable.linkedin);
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/onur-k%C4%B1nay-1387b8176/"));
                startActivity(browserIntent);
            }
        });

        return linkedIn;
    }
}
