package com.example.flappybird.game.logic;

import android.util.Log;
import com.example.flappybird.game.objects.Pipe;
import com.example.flappybird.game.objects.Player;
import java.util.ArrayList;

/**
 * @author David Siegbert
 * Verwaltet verschiedene Objekte, welche
 */
public class ObstacleManager {
    private ArrayList<Pipe> pipes;
    private ArrayList<ScoreTrigger> scoreTriggers;

    /**
     * @param pipes Arrayliste zum angeben von schon bekannten Pipes
     * @param scoreTriggers Arrayliste zum angeben von schon bekannten scoreTriggern
     */
    public ObstacleManager(ArrayList<Pipe> pipes, ArrayList<ScoreTrigger> scoreTriggers) {
        this.pipes = pipes;
        this.scoreTriggers = scoreTriggers;
    }

    public ObstacleManager() {
        this.pipes = new ArrayList<Pipe>();
        this.scoreTriggers = new ArrayList<ScoreTrigger>();
    }

    /**
     * Löscht obstacles welche links vom Bildschirm sind, also nicht mehr sichtbar sind
     */
    public void obstacleDeleter(){
        pipes.removeIf(p -> p.getPosition().getX() < 0);
    }

    /**
     * Spawn obstacles rechts vom Bildschirm, also außerhalb der sichtbarkeit
     * @param pos position an der die pipe gespawned werden soll
     * @param yScale höhe des handys, benutzt für verschiedene Größen
     */
    public void obstacleSpawner(Position pos, float yScale){
        float pipeWidth = 60f;
        float pipeHeight = 700f * yScale;
        float scoreHeight = 300f;

        pipes.add(new Pipe(pos, pipeWidth, pipeHeight));
        scoreTriggers.add(new ScoreTrigger(pos.addPosition(0, pipeHeight), pipeWidth, scoreHeight));
        pipes.add(new Pipe(pos.addPosition(0, pipeHeight + scoreHeight), pipeWidth, pipeHeight));
    }

    /**
     * Checks collision with player
     * @param player spieler, auf den eine collision gecheckt werden soll
     * @param displayHeight höhe des Handys, benutzt damit der spieler am unteren rand stirbt
     */
    public void checkCollision(Player player, float displayHeight){
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
        else if(player.getPosition().getY() > displayHeight ) player.kill();
    }

    /**
     * return all the pipe
     * @return list of all pipes
     */
    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    /**
     * return all the scoreTriggers
     * @return list of all scoreTriggers
     */
    public ArrayList<ScoreTrigger> getScoreTriggers() {
        return scoreTriggers;
    }
}
