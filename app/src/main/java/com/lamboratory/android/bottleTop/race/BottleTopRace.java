package com.lamboratory.android.bottleTop.race;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class BottleTopRace extends Activity {
	
	private PowerManager.WakeLock wl;
	
	GameView gameView = null;
	GameController gameController = null;

	GameState gameState = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FrameLayout fl = new FrameLayout(this.getApplicationContext());
		setContentView(fl);
		
		int playerNumber = getIntent().getExtras().getInt("playerNumber", 1);
		int trackId = getIntent().getExtras().getInt("trackId", 0);
		int lapsToWin = getIntent().getExtras().getInt("lapsToWin", 1);
		initGame(playerNumber, trackId, lapsToWin);
		
		gameView = new GameView(this.getApplicationContext(), gameState);
		gameView.setTrackId(trackId);
		fl.addView(gameView);
		
		gameController = new GameController(this, gameState, gameView);
		
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");
	}
	
	public void initGame(int playerNumber, int trackId, int lapsToWin) {
		gameState = new GameState(playerNumber, trackId, lapsToWin);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		switch (item.getItemId()) {
	    case R.id.undo:
	        gameController.undoMove();
	        return true;
	    case R.id.back:
	        quit();
	        return true;
	    case R.id.quit:
	        quit();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

    @Override
    protected void onPause() {
            super.onPause();
            wl.release();
    }
    
    @Override
    protected void onResume() {
            super.onResume();
            wl.acquire();
    }
    
	@Override 
	public void onBackPressed() {
		quit();
	}
	
	protected void quit() {
		Intent intent = this.getIntent();
		if (getParent() == null) {
		    setResult(Activity.RESULT_OK, intent);
		} else {
		    getParent().setResult(Activity.RESULT_OK, intent);
		}
		finish();
	}
}