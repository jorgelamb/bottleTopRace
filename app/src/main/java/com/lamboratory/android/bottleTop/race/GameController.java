package com.lamboratory.android.bottleTop.race;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.lamboratory.android.bottleTop.utils.Geometry;

public class GameController implements SensorEventListener, OnTouchListener {

	protected Activity activity;
	protected GameState gameState;
	protected GameView gameView;
	
	Stack<Pair<Integer, PointF>> movements = new Stack<Pair<Integer, PointF>>();

	public GameController(Activity activity, GameState gameState, GameView gameView) {
		this.activity = activity;
		this.gameState = gameState;
		this.gameView = gameView;

		SensorManager sensorManager = (SensorManager)activity.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);

		gameView.setOnTouchListener(this);

		setViewMessage();
	}


	protected float[] gravity = null;

	public void onSensorChanged(SensorEvent evt) {
		switch(evt.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			gravity = evt.values;
			onAccelerometer(evt.values);
			break;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	protected LinkedList<float[]> accelerationData = new LinkedList<float[]>();
	protected int state = -1;
	protected float[] lastStable = null;
	protected float[] strokeAcceleration = null;
	protected float[] strokeAccelerationAbs = null;
	protected int stabilizedCount = 0;
	protected void onAccelerometer(float[] values) {
		float[] newAcceleration = values.clone();
		accelerationData.add(newAcceleration);
		if(accelerationData.size()>100) {
			accelerationData.remove();
		} else if(accelerationData.size()<6) {
			return;
		}

		switch(state) {
		case 0:
			for(int i=accelerationData.size()-1-1, j=0; j<5; i--, j++) {
				if(!isStable(newAcceleration, accelerationData.get(i), 0.2f))
					return;
			}
			lastStable = accelerationData.get(accelerationData.size()-3);
			setState(1);

			break;

		case 1:
			for(int i=accelerationData.size()-1-1, j=0; j<5; i--, j++) {
				if(isStable(newAcceleration, accelerationData.get(i), 0.3f))
					return;
			}

			strokeAcceleration = new float[3];
			strokeAccelerationAbs = new float[3];
			for(int i=0; i<3; i++) {
				strokeAcceleration[i] = newAcceleration[i]-lastStable[i];
				strokeAccelerationAbs[i] = Math.abs(newAcceleration[i]-lastStable[i]);
			}
			setState(2);
			stabilizedCount=0;
			break;

		case 2:
			if(++stabilizedCount>25) {
				setState(0);
			}
			for(int i=0; i<3; i++) {
				strokeAcceleration[i] += newAcceleration[i]-lastStable[i];
				strokeAccelerationAbs[i] += Math.abs(newAcceleration[i]-lastStable[i]);
			}
			for(int i=accelerationData.size()-1-1, j=0; j<5; i--, j++) {
				if(!isStable(lastStable, accelerationData.get(i), 0.2f))
					return;
			}
			move(strokeAccelerationAbs[1], 0); // TODO
			setState(-1);
			break;
		}
	}

	protected boolean isStable(float[] a, float[] b, float threshold) {
		for(int i=0; i<a.length; i++) {
			if(Math.abs(a[i]-b[i])>threshold)
				return false;
		}
		return true;
	}

	int tempProgressLaps;
	int tempProgressSteps;
	
	BottleTopMoveTask btmT = null;
	protected void move(float f, float err) {
		
		gameState.playerStrokes.set(gameState.currentPlayer, gameState.playerStrokes.get(gameState.currentPlayer)+1);

		tempProgressLaps = gameState.playerProgress.get(gameState.currentPlayer).first;
		tempProgressSteps = gameState.playerProgress.get(gameState.currentPlayer).second;
		
		btmT = new BottleTopMoveTask(this, f, err);
		btmT.execute();
	}

	
	public void moveStep(float f, float err) {

		PointF position = gameState.playerPositions.get(gameState.currentPlayer);

		List<Pair<PointF, PointF>> track = gameState.getTrack();
		boolean crash = false;
		for(int i=1; i<track.size() && !crash; i++) {
			Pair<PointF, PointF> start = track.get(i-1);
			Pair<PointF, PointF> end = track.get(i);

			if(Geometry.isIntersect(
					start.first.x, start.first.y,
					end.first.x, end.first.y,
					position.x, position.y,
					position.x+f*gameState.getCurrentPlayerDirection().x,
					position.y+f*gameState.getCurrentPlayerDirection().y)) {
				crash = true;
			}

			if(Geometry.isIntersect(
					start.second.x, start.second.y,
					end.second.x, end.second.y,
					position.x, position.y,
					position.x+f*gameState.getCurrentPlayerDirection().x,
					position.y+f*gameState.getCurrentPlayerDirection().y)) {
				crash = true;
			}
		}

		if(crash) {
			btmT.crash();
			
			position.x = gameState.getCurrentPlayerThrowPosition().x;
			position.y = gameState.getCurrentPlayerThrowPosition().y;
		} else {
			
			while(true) {
				if(Geometry.isIntersect(
						track.get(tempProgressSteps).first.x, track.get(tempProgressSteps).first.y,
						track.get(tempProgressSteps).second.x, track.get(tempProgressSteps).second.y,
						position.x, position.y,
						position.x+f*gameState.getCurrentPlayerDirection().x,
						position.y+f*gameState.getCurrentPlayerDirection().y)) {
					tempProgressSteps++;
					if(tempProgressSteps==track.size()) {
						tempProgressLaps++;
						tempProgressSteps = 0;
						
						if(tempProgressLaps>=gameState.getLapsToWin()) {
							Intent intent = activity.getIntent();
							String winnerMsg = activity.getResources().getText(R.string.player_wins).toString();
							intent.putExtra("msg", String.format(winnerMsg, gameState.getCurrentPlayer()+1));
							if (activity.getParent() == null) {
								activity.setResult(Activity.RESULT_OK, intent);
							} else {
								activity.getParent().setResult(Activity.RESULT_OK, intent);
							}
							activity.finish();
							break;
						}
					}
				} else {
					break;
				}
			}
				
			position.x += f*gameState.getCurrentPlayerDirection().x;
			position.y += f*gameState.getCurrentPlayerDirection().y;
		}
	}

	public void moveFinalize() {
		gameState.playerProgress.set(gameState.currentPlayer, new Pair<Integer, Integer>(tempProgressLaps, tempProgressSteps));
	}
	
	public void changePlayer() {
		movements.push(new Pair<Integer, PointF>(gameState.currentPlayer, gameState.getCurrentPlayerThrowPosition()));
		gameState.currentPlayer = (gameState.currentPlayer+1)%gameState.players.size();
		gameState.setCurrentPlayerThrowPosition(gameState.getPlayerPositions().get(gameState.currentPlayer));
	}

	public void undoMove() {
		if(!movements.isEmpty()) {
			Pair<Integer, PointF> lastMove = movements.pop();
			gameState.currentPlayer = lastMove.first;
			gameState.playerPositions.get(gameState.currentPlayer).set(lastMove.second);
			gameState.setCurrentPlayerThrowPosition(gameState.getPlayerPositions().get(gameState.currentPlayer));
			gameView.invalidate();
		}
	}
	
	public void crash() {
		setState(-2);
	}
	
	public void updateView() {
		gameView.invalidate();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		accelerationData.clear();
		
		if(event.getAction() != MotionEvent.ACTION_DOWN)
			return true;

		if(state==-1) {
			setState(0);
		} else if(state==-2) {
			setState(-1);
			return true;
		}


		try {
			Matrix m = new Matrix(gameView.getMatrix());
			Matrix mInverted = new Matrix();
			m.invert(mInverted);
			float[] pts = { event.getX(), event.getY() };
			mInverted.mapPoints(pts);

			PointF currentPlayerPosition = gameState.getPlayerPositions().get(gameState.getCurrentPlayer());
			PointF newDirection = new PointF(pts[0]-currentPlayerPosition.x, pts[1]-currentPlayerPosition.y);
			if(newDirection.x!=0 || newDirection.y!=0) {
				double module = Math.sqrt(newDirection.x*newDirection.x+newDirection.y*newDirection.y);
				newDirection.x = (float)(newDirection.x / module);
				newDirection.y = (float)(newDirection.y / module);
				gameState.setCurrentPlayerDirection(newDirection);
				gameView.invalidate();
			}
		} catch (Exception e) {
		}
		return true;
	}

	protected void setState(int state) {
		if(this.state != state) {
			this.state = state;
			setViewMessage();
		}
	}

	protected void setViewMessage() {
		switch(state) {
		case -1:
			gameView.setMessage("Click to point");
			break;
		case 0:
			gameView.setMessage("Click to point or make your move");
			break;
		case -2:
			gameView.setMessage("You crashed!");
			break;
		case 1:
		case 2:
		default:
			break;
		}
		gameView.invalidate();
	}
}
