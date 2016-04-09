package com.lamboratory.android.bottleTop.race;

import android.os.AsyncTask;

public class BottleTopMoveTask extends AsyncTask<Object, int[], Integer> {

	protected GameController gameController;
	protected float force;
	protected float error;
	
	public boolean crashed = false;
	
	public BottleTopMoveTask(GameController gameController, float force, float error) {
		super();
		this.gameController = gameController;
		this.force = force;
		this.error = error;
	}

	@Override
	protected Integer doInBackground(Object... params) {
		
		for(int i=0; i<force/3 && !crashed; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			
			gameController.moveStep(20, error);
			
			publishProgress();
		}
		
		if(!crashed) {
			try {	
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			double rest = force/3-Math.floor(force/3);
			gameController.moveStep(Math.round(rest*20), error);
		}
		
		gameController.moveFinalize();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		publishProgress(new int[]{ 1 });

		return null;
	}

	@Override
	protected void onProgressUpdate(int[]... data) {
		if(data.length>0 && data[0][0]==1) {
			gameController.changePlayer();
		} else if(crashed) {
			gameController.crash();
		}
		gameController.updateView();
	}
	
	public void crash() {
		this.crashed = true;
	}
}
