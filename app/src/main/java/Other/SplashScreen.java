package Other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ptsganjil202111rpl1melvin30.MainActivity;
import com.example.ptsganjil202111rpl1melvin30.R;

public class SplashScreen extends AppCompatActivity {

    ImageView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        img_logo = findViewById(R.id.img_logo);

        //splash screen
        Thread splash = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 3 seconds
                    sleep(2*1000);
                    Intent nextactivity=new Intent(getBaseContext(), MainActivity.class);
                    startActivity(nextactivity);
                    finish();
                } catch (Exception e) {
                }
            }
        };

        splash.start();

    }
}