package com.example.flappybird.game.logic;

import android.util.Log;

import com.example.flappybird.game.objects.Pipe;
import com.example.flappybird.game.objects.Player;

import java.util.ArrayList;

public class ObstacleManager {
    private ArrayList<Pipe> pipes;
    private ArrayList<ScoreTrigger> scoreTriggers;

    public ObstacleManager(ArrayList<Pipe> pipes, ArrayList<ScoreTrigger> scoreTriggers) {
        this.pipes = pipes;
        this.scoreTriggers = scoreTriggers;
    }

    public ObstacleManager() {
        this.pipes = new ArrayList<Pipe>();
        this.scoreTriggers = new ArrayList<ScoreTrigger>();
    }

    public void obstacleDeleter(){
        pipes.removeIf(p -> p.getPosition().getX() < 0);
    }

    public void obstacleSpawner(Position pos, float yScale){
        float pipeWidth = 60f;
        float pipeHeight = 700f * yScale;
        float scoreHeight = 300f;

        // TODO maybe add a check if arraylist are avaible
        pipes.add(new Pipe(pos, pipeWidth, pipeHeight));
        scoreTriggers.add(new ScoreTrigger(pos.addPosition(0, pipeHeight), pipeWidth, scoreHeight));
        pipes.add(new Pipe(pos.addPosition(0, pipeHeight + scoreHeight), pipeWidth, pipeHeight));
    }

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

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public ArrayList<ScoreTrigger> getScoreTriggers() {
        return scoreTriggers;
    }
}
