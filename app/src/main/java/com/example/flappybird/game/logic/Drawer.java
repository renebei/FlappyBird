package com.example.flappybird.game.logic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.example.flappybird.game.GameActivity;
import com.example.flappybird.game.objects.Pipe;
import com.example.flappybird.game.objects.Player;

import java.util.ArrayList;

public class Drawer {
    public static void drawBackGround(Canvas canvas, GameActivity gameActivity){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawRect(0,0, gameActivity.getPhoneSize().x, gameActivity.getPhoneSize().y, paint);
    }

    public static void drawPlayer(Player player, Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(player.getPosition().getX(), player.getPosition().getY(), player.getRadius(), paint);
    }

    public static void drawPipes(ArrayList<Pipe> pipes, Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        for(Pipe p : pipes){
            canvas.drawRect(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getX() + p.getWidth(), p.getPosition().getY() + p.getHeight(), paint);
        }
    }
}
