package id.ac.umn.utsmobile21498;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ViewAnimasi extends View {

    private Bitmap bmNinja; //gambar
    private int ninjaX = 0, ninjaY = 0; //posisi gambar
    private int x = 10, y = 10, speed = 10; //perubahan koordiant x, y, dan speed/arah.
    private boolean play = true;    //penanda game masih berjalan

    //Constructor
    public ViewAnimasi(Context context, AttributeSet attrs) {
        super(context, attrs);
        bmNinja = BitmapFactory.decodeResource(getResources(), R.drawable.ninja2_2);
    }

    //onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //kalo masih play
        if(play){
            //kalo udah mentok di balik arah
            if(ninjaX >= (getWidth()-200)) x =- speed;
            if(ninjaX <= 0) x = speed;
            if(ninjaY >= (getHeight()-200)) y =- speed;
            if(ninjaY <= 0) y = speed;

            //posisi di ubah
            ninjaX = ninjaX + x;
            ninjaY = ninjaY + y;
        }
        canvas.drawBitmap(bmNinja, ninjaX, ninjaY, null); //gambar ulang
        invalidate(); // screen di refresh tiap ada perubahan
    }

    //kalo canvas di touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Button btnExit = (Button) ((StartActivity)getContext()).findViewById(R.id.btnExit); //akses button exit

        //ambil koordinat touch
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN: //pada saat di tap
                if(play){   //kalo masih main
                    if( x > ninjaX && x < ninjaX + 150 && y > ninjaY && y < ninjaY + 150 )  //kalo gambar di tap
                    {
                        play = false;   //game berakhir

                        MediaPlayer ring= MediaPlayer.create(getContext(),R.raw.hit);   //nyalain musik hit
                        ring.start();
                        btnExit.setVisibility(View.VISIBLE);
                    }
                    else{   //kalo gambar ga di tap
                        MediaPlayer ring= MediaPlayer.create(getContext(),R.raw.miss);  //nyalain musik miss
                        ring.start();
                    }
                }
                return true;
        }
        return false;
    }
}