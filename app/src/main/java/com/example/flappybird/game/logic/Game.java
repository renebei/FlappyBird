/**
 * @author David Siegbert, René Beiermann
 * Datenbanken Repository für die Abfragen unserer Anwendung.
 */

package com.example.flappybird.game.logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.flappybird.R;
import com.example.flappybird.game.GameActivity;
import com.example.flappybird.game.objects.Pipe;
import com.example.flappybird.game.objects.Player;
import com.example.flappybird.profile.data.DatabaseRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Thread {
    public static final float GRAVIATION = 1500.f;
    public static final float SPEED = -300.f;
    private GameActivity gameActivity;
    private Player player = new Player(new Position(100f,10f), 40f);

    private DatabaseRepository dataAdapter;

    public Game(GameActivity gameActivity){
        this.gameActivity = gameActivity;
        this.dataAdapter = new DatabaseRepository(this.gameActivity.getApplication());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void run(){
        try {
            GameLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void GameLoop() throws InterruptedException {
        int frameTimeStartNano = LocalDateTime.now().getNano();

        ArrayList<Pipe> pipes = new ArrayList<>();
        ArrayList<ScoreTrigger> scoreTriggers = new ArrayList<>();
        float spawnRate = 2.f;
        float currentSpawnRate = 0;
        while(true) {
            int frameTimeNano = LocalDateTime.now().getNano() -  frameTimeStartNano; // How this wrong
            if(frameTimeNano < 0) frameTimeNano += 1000000000;

            float frameTime =  frameTimeNano / 1000000000.f;
            //Log.e("frameTime: ", ""+  frameTime);

            frameTimeStartNano = LocalDateTime.now().getNano();

            player.update(frameTime);
            if(!player.isDead()){
                for(Pipe p : pipes){
                    p.update(frameTime);
                }
                for(ScoreTrigger s : scoreTriggers){
                    s.update(frameTime);
                }
            }

            // counting down spawn rate
            if(currentSpawnRate <= 0){
                Random r = new Random(LocalDateTime.now().getNano());
                int randomY = r.nextInt(800) - 400;
                obstacleSpawner(pipes, new Position(2000, -400 + randomY), scoreTriggers);
                currentSpawnRate = spawnRate;
            } else {
                currentSpawnRate -= frameTime;
            }

            //obstacleDeleter(pipes);
            checkCollision(pipes, player, scoreTriggers);
            drawAll(player, pipes);

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

    private void checkCollision(ArrayList<Pipe> pipes, Player player, ArrayList<ScoreTrigger> scoreTriggers){
        for(Pipe p : pipes){
            if( player.getCollider().collides(p.getCollider())) {
                Log.e("Dead.", "Player just died wtf");
                player.kill();
            }
        }

        for(ScoreTrigger s : scoreTriggers){
            if( player.getCollider().collides(s.getCollider()) && !s.getHasTriggered()) {
                s.setHasTriggered(true);
                player.addScore();
                Log.e("Score", "Player score now " + player.getScore());
            }
        }

        // upper and downer check
        if(player.getPosition().getY() < 0) player.kill();
    }

    private void obstacleDeleter(ArrayList<Pipe> pipes){
        pipes.removeIf(p -> p.getPosition().getX() < 0);
    }

    private void obstacleSpawner( ArrayList<Pipe> pipes, Position pos, ArrayList<ScoreTrigger> scoreTriggers){
        float pipeWidth = 60f;
        float pipeHeight = 1400f;
        float scoreHeight = 300f;

        // TODO maybe add a check if arraylist are avaible
        pipes.add(new Pipe(pos, pipeWidth, pipeHeight));
        scoreTriggers.add(new ScoreTrigger(pos.addPosition(0, pipeHeight), pipeWidth, scoreHeight));
        pipes.add(new Pipe(pos.addPosition(0, pipeHeight + scoreHeight), pipeWidth, pipeHeight));
    }

    private void drawBackGround(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawRect(0,0, gameActivity.getPhoneSize().x, gameActivity.getPhoneSize().y, paint);

    }

    private void drawPlayer(Player player, Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(player.getPosition().getX(), player.getPosition().getY(), player.getRadius(), paint);

        //Bitmap playerBitmap = BitmapFactory.decodeResource(gameActivity.getResources(), R.drawable.oracle);
        //canvas.drawBitmap(playerBitmap, player.getPosition().getX()-62, player.getPosition().getY()-38, paint);
    }

    private void drawPipes(ArrayList<Pipe> pipes, Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        for(Pipe p : pipes){
            canvas.drawRect(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getX() + p.getWidth(), p.getPosition().getY() + p.getHeight(), paint);
        }
    }

    private void drawAll(Player player, ArrayList<Pipe> pipes){
        Canvas canvas = null;
        try {
            canvas = gameActivity.getHolder().lockCanvas();
            synchronized (gameActivity.getHolder()) {
                if(canvas != null) {
                    drawBackGround(canvas);
                    drawPipes(pipes, canvas);
                    drawPlayer(player, canvas);
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

    public void jump(){
        player.jump();
    }

    public Player getPlayer() {
        return this.player;
    }
}
