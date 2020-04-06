package id.ac.umn.utsmobile21498;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2 button
        Button btnStart,btnProfil;
        btnStart = findViewById(R.id.btnStart);
        btnProfil = findViewById(R.id.btnProfil);

        //onclick buat start/profil
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.start);
                ring.start();
                Intent intent = new Intent(MainActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProfilActivity.class);
                startActivity(intent);
            }
        });

    }
}
