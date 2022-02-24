package com.example.flappybird.game.logic;
import android.graphics.Canvas;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import androidx.annotation.RequiresApi;
import com.example.flappybird.game.GameActivity;
import com.example.flappybird.game.objects.Pipe;
import com.example.flappybird.game.objects.Player;
import com.example.flappybird.profile.data.DatabaseRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author David Siegbert, René Beiermann
 * Datenbanken Repository für die Abfragen unserer Anwendung.
 */

public class Game extends Thread {
    public static final float GRAVIATION = 1500.f;
    public static final float SPEED = -300.f;
    private GameActivity gameActivity;
    private Player player = new Player(new Position(100f,10f), 40f);

    private DatabaseRepository dataAdapter;

    /**
     * @author David Siegbert
     * @param gameActivity the game activity that the canvas is on
     */
    public Game(GameActivity gameActivity){
        this.gameActivity = gameActivity;
        this.dataAdapter = new DatabaseRepository(this.gameActivity.getApplication());
    }

    /**
     * @author David Siegbert
     * runs the gameloop in a thread
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void run(){
        try {
            GameLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author David Siegbert
     * Runs the game in an infinite loop
     * @throws InterruptedException triggered when thread error
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void GameLoop() throws InterruptedException {
        int frameTimeStartNano = LocalDateTime.now().getNano();

        ObstacleManager obstacleManager = new ObstacleManager();
        float spawnRate = 2.f;
        float currentSpawnRate = 0;
        while(true) {
            // calculate time between frames
            int frameTimeNano = LocalDateTime.now().getNano() -  frameTimeStartNano;
            if(frameTimeNano < 0) frameTimeNano += 1000000000;
            float frameTime =  frameTimeNano / 1000000000.f;
            frameTimeStartNano = LocalDateTime.now().getNano();

            Display display = gameActivity.getWindowManager().getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics ();
            display.getMetrics(outMetrics);
            float yScale = outMetrics.heightPixels / (float)1000;

            player.update(frameTime);
            if(!player.isDead()){
                for(Pipe p : obstacleManager.getPipes()){
                    p.update(frameTime);
                }
                for(ScoreTrigger s : obstacleManager.getScoreTriggers()){
                    s.update(frameTime);
                }
            }

            // counting down spawn rate
            if(currentSpawnRate <= 0){
                Random r = new Random(LocalDateTime.now().getNano());
                int randomY = r.nextInt(800) - 400;
                obstacleManager.obstacleSpawner(new Position(2000, -400 + randomY), yScale);
                obstacleManager.obstacleDeleter();
                currentSpawnRate = spawnRate;
            } else {
                currentSpawnRate -= frameTime;
            }

            obstacleManager.checkCollision(player, yScale * 1000);
            drawAll(player, obstacleManager.getPipes());

            /**
             * @author René Beiermann
             * Sammelt Informationen über den Verlauf des Spielversuches und speichert Ihn in der Datenbank.
             */
            if (player.isDead()) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                dataAdapter.addAttempt(player.getScore(), dtf.format(now));
                dataAdapter.incrementGamesPlayed();
                dataAdapter.updateHighscore(player.getScore());
                return;
            }
        }
    }

    /**
     * @author David Siegbert
     * Threadsafe methode um die game Objekte zu malen
     * @param player spieler der gemalt werden soll
     * @param pipes list of pipes that need to be drawn
     */
    private void drawAll(Player player, ArrayList<Pipe> pipes){
        Canvas canvas = null;
        try {
            canvas = gameActivity.getHolder().lockCanvas();
            synchronized (gameActivity.getHolder()) {
                if(canvas != null) {
                    Drawer.drawBackGround(canvas, gameActivity);
                    Drawer.drawPipes(pipes, canvas);
                    Drawer.drawPlayer(player, canvas);
                }
            }
        } catch (Exception e){
            Log.e("no drawing", "error");
        }
        finally {
            if (canvas != null) {
                gameActivity.getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    /**
     * @author David Siegbert
     * Lässt den Spieler springen
     */
    public void jump(){
        player.jump();
    }

    /**
     * @author David Siegbert
     * Gibt den spieler zurück
     * @return Der Spieler
     */
    public Player getPlayer() {
        return this.player;
    }
}
