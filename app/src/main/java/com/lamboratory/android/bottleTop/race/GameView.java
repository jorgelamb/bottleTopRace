package com.lamboratory.android.bottleTop.race;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.View;

public class GameView extends View {
	
	public static int ALPHA = 100;
	
	protected static int POSITION_X = -1;
	protected static int POSITION_Y = -1;
	protected static int POSITION_Y_MSG = -1;
	
	ArrayList<Bitmap> playerBitmaps = new ArrayList<Bitmap>();
	
	protected int trackId = -1;
	Bitmap circuit = null;

	GameState gameState = null;
	Matrix matrix = null;
	
	GameView(Context applicationContext, GameState gameState) {
		super(applicationContext);
		
		this.gameState = gameState;
		
		for(int i=0; ; i++) {
			try {
				int bottleTopId = getResources().getIdentifier("bottle_top_"+i, "drawable", this.getClass().getPackage().getName());
				if(bottleTopId==0) {
					break;
				}
				Bitmap bottleTopBitmap = BitmapFactory.decodeResource(getResources(), bottleTopId);
				playerBitmaps.add(bottleTopBitmap);
			} catch (Exception e) {
				break;
			}
		}
	}
	
	public void onDraw(Canvas c) {
		super.onDraw(c);

		if(POSITION_X<0) {
			POSITION_X = c.getWidth()/2;
			POSITION_Y = c.getHeight()-POSITION_X/2;
			POSITION_Y_MSG = c.getHeight()-POSITION_X/4+20;
		}
		
		c.drawColor(Color.rgb(32, 152, 32));
		
		Paint p = new Paint();
		p.setTextAlign(Align.CENTER);
		try {
			c.setMatrix(null);
		} catch (NullPointerException e) {
			Log.e("BottleTopRace", "NPE", e);
			return;
		}
		
		int currentPlayer = gameState.getCurrentPlayer();
		PointF currentPlayerPosition = gameState.getCurrentPlayerThrowPosition();
		PointF currentPlayerDirection = gameState.getCurrentPlayerDirection();
		
		double degrees = Math.acos(-currentPlayerDirection.y / Math.sqrt(currentPlayerDirection.x*currentPlayerDirection.x+currentPlayerDirection.y*currentPlayerDirection.y));
		if(currentPlayerDirection.x>0)
			degrees = -degrees;
		
		Matrix m = new Matrix();
		m.preRotate((float)(180*degrees/Math.PI), POSITION_X, POSITION_Y);
		m.preTranslate(POSITION_X-currentPlayerPosition.x, POSITION_Y-currentPlayerPosition.y);
		c.setMatrix(m);
		matrix = m;

		c.drawBitmap(circuit, 0, 0, p);
		
		p.setColor(Color.BLACK);
		p.setAlpha(ALPHA);
		
		for(int i=0; i<gameState.getPlayerPositions().size(); i++) {
			if(i!=currentPlayer) {
				Bitmap bottleTop = getPlayerBitmap(i);
				PointF position = gameState.getPlayerPositions().get(i);
				c.drawBitmap(bottleTop, position.x-bottleTop.getWidth()/2, position.y-bottleTop.getHeight()/2, p);
				
				c.drawText("player "+(i+1), position.x, position.y+10, p);
			}
		}
		p.setAlpha(255);
		PointF position = gameState.getPlayerPositions().get(currentPlayer);
		Bitmap bottleTop = getPlayerBitmap(currentPlayer);
		c.drawBitmap(bottleTop, position.x-bottleTop.getWidth()/2, position.y-bottleTop.getHeight()/2, p);
		c.drawText("player "+(currentPlayer+1), position.x, position.y+10, p);
		
		if(message!=null) {
			c.setMatrix(null);
			p.setColor(Color.BLACK);
			c.drawText(message, POSITION_X, POSITION_Y_MSG, p);
		}

	}

	public void setTrackId(int trackId) {
		if(this.trackId!=trackId) {
			int circuitId = getResources().getIdentifier("circuit"+trackId, "drawable", this.getClass().getPackage().getName());
			circuit = BitmapFactory.decodeResource(getResources(), circuitId);
		}
		this.trackId = trackId;
	}
	
	protected Bitmap getPlayerBitmap(int player) {
		return playerBitmaps.get(player % playerBitmaps.size());
	}
	
	public Matrix getMatrix() {
		return matrix;
	}
	
	protected String message = null;
	public void setMessage(String message) {
		this.message = message;
		this.invalidate();
	}
}
