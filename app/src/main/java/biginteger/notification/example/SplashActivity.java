package biginteger.notification.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sandeep on 6/13/15.
 */
public class SplashActivity extends Activity {


    private ImageView logo;
    private TextView txt;
    private Animation animFadeIn;
    private Animation animFadeOut;
    private Context con;

    /**
     * Duration of wait *
     */
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_activity);

        con = this;

        //get ids
        logo = (ImageView) findViewById(R.id.imgLogo);
        txt=(TextView)findViewById(R.id.txt);

        //set move animation
        //changing move animation to fade in
        animFadeIn = AnimationUtils.loadAnimation(con, R.anim.fade_in);
        animFadeIn.setStartOffset(500);   // delay
        logo.startAnimation(animFadeIn);      //start animation
        txt.startAnimation(animFadeIn);      //start animation

        //set fade out animation
        animFadeOut = AnimationUtils.loadAnimation(con, R.anim.fade_out);
        animFadeOut.setStartOffset(3000);


        animFadeIn.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                logo.startAnimation(animFadeOut);
                txt.startAnimation(animFadeOut);
            }
        });

        animFadeOut.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                finish();
                Intent intent = new Intent(con, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
