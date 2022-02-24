package com.example.flappybird.game.logic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.example.flappybird.game.GameActivity;
import com.example.flappybird.game.objects.Pipe;
import com.example.flappybird.game.objects.Player;

import java.util.ArrayList;

/**
 * @author David Siegbert
 * Statische Klasse zum zeichnen auf dem Canvas
 */
public class Drawer {
    /**
     * drawing the background
     * @param canvas Canvas auf dem gemalt werden soll
     * @param gameActivity Gameactivity wird für größe des Handys benutzt
     */
    public static void drawBackGround(Canvas canvas, GameActivity gameActivity){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawRect(0,0, gameActivity.getPhoneSize().x, gameActivity.getPhoneSize().y, paint);
    }

    /**
     * Zeichne den Spieler
     * @param player Spieler, der gemalt werden soll
     * @param canvas Canvas auf dem gemalt werden soll
     */
    public static void drawPlayer(Player player, Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(player.getPosition().getX(), player.getPosition().getY(), player.getRadius(), paint);
    }

    /**
     * Zeichne alle Pipes
     * @param pipes Pipes die gemalt werden sollen
     * @param canvas Canvas auf dem gemalt werden soll
     */
    public static void drawPipes(ArrayList<Pipe> pipes, Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        for(Pipe p : pipes){
            canvas.drawRect(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getX() + p.getWidth(), p.getPosition().getY() + p.getHeight(), paint);
        }
    }
}
