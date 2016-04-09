package com.lamboratory.android.bottleTop.race;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;
import android.util.Pair;

public class GameState implements Serializable {

	private static final long serialVersionUID = 7519985970905686320L;

	protected int trackId = -1;
	protected List<Pair<PointF, PointF>> track = null;
	int lapsToWin = -1;

	protected List<Player> players;
	
	protected List<PointF> playerPositions;
	protected List<PointF> playerDirections;
	protected List<Integer> playerStrokes;
	protected List<Pair<Integer, Integer>> playerProgress;
	
	protected int currentPlayer = 0;
	protected PointF currentPlayerThrowPosition = new PointF(960, 455);
	
	public GameState(int playerNumber, int trackId, int lapsToWin) {
		super();
		
		this.trackId = trackId;
		track = GameTrackFactory.getTrackData(trackId);
		this.lapsToWin = lapsToWin;
		
		players = new ArrayList<Player>();
		playerPositions = new ArrayList<PointF>();
		playerDirections = new ArrayList<PointF>();
		playerStrokes = new ArrayList<Integer>();
		playerProgress = new ArrayList<Pair<Integer, Integer>>();
		
		for(int i=0; i<playerNumber; i++) {
			addPlayer(new Player());
		}
		
		currentPlayerThrowPosition = new PointF(playerPositions.get(currentPlayer).x, playerPositions.get(currentPlayer).y);
	}

	public void addPlayer(Player player) {
		players.add(player);
		playerPositions.add(GameTrackFactory.getStartPosition(trackId));
		playerDirections.add(new PointF(0, -1));
		playerStrokes.add(0);
		playerProgress.add(new Pair<Integer, Integer>(0, 0));
	}

	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Pair<PointF, PointF>> getTrack() {
		return track;
	}

	public void setTrack(List<Pair<PointF, PointF>> track) {
		this.track = track;
	}

	public List<PointF> getPlayerPositions() {
		return playerPositions;
	}

	public void setPlayerPositions(List<PointF> playerPositions) {
		this.playerPositions = playerPositions;
	}


	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}


	public PointF getCurrentPlayerDirection() {
		return playerDirections.get(currentPlayer);
	}

	public void setCurrentPlayerDirection(PointF currentPlayerDirection) {
		playerDirections.set(currentPlayer, currentPlayerDirection);
	}


	public PointF getCurrentPlayerThrowPosition() {
		return currentPlayerThrowPosition;
	}


	public void setCurrentPlayerThrowPosition(PointF currentPlayerThrowPosition) {
		this.currentPlayerThrowPosition = new PointF();
		this.currentPlayerThrowPosition.set(currentPlayerThrowPosition);
	}

	public int getLapsToWin() {
		return lapsToWin;
	}
	
}
