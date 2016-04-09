package com.lamboratory.android.bottleTop.race;

import com.lamboratory.android.bottleTop.utils.Sharing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class BottleTopRaceMenu extends Activity implements View.OnClickListener {

	int playerNumber = 1;
	int lapsToWin = 1;
	int circuit = 0;
	
	TextView playerNumberView = null;
	TextView lapNumberView = null;
	TextView circuitView = null;
	ImageView circuitImage = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ScrollView scrollView = new ScrollView(this.getApplicationContext());
		setContentView(scrollView);
		
		LinearLayout ll = new LinearLayout(this.getApplicationContext());
		ll.setOrientation(LinearLayout.VERTICAL);
		scrollView.addView(ll);
		
		LinearLayout.LayoutParams layoutParamsValue = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		
		TextView titleView = new TextView(this.getApplicationContext());
		titleView.setText(R.string.app_name);
		titleView.setGravity(Gravity.CENTER_HORIZONTAL);
		titleView.setTextSize(25);
		ll.addView(titleView);
		
		TextView separator = new TextView(this.getApplicationContext());
		ll.addView(separator);
		
		// Remove player / player number / Add player
		LinearLayout llPlayers = new LinearLayout(this.getApplicationContext());
		Button removePlayerButton = new Button(this.getApplicationContext());
		removePlayerButton.setText(R.string.remove_player);
		removePlayerButton.setId(R.string.remove_player);
		removePlayerButton.setOnClickListener(this);
		llPlayers.addView(removePlayerButton);
		
		playerNumberView = new TextView(this.getApplicationContext());
		playerNumberView.setText(getResources().getText(R.string.players).toString()+" "+this.playerNumber);
		playerNumberView.setGravity(Gravity.CENTER_HORIZONTAL);
		llPlayers.addView(playerNumberView, layoutParamsValue);
		
		Button addPlayerButton = new Button(this.getApplicationContext());
		//addPlayerButton.setBackgroundResource(R.drawable.right);
		addPlayerButton.setText(R.string.add_player);
		addPlayerButton.setId(R.string.add_player);
		addPlayerButton.setOnClickListener(this);
		llPlayers.addView(addPlayerButton);
		
		ll.addView(llPlayers);
		
		// Laps
		/*TextView lapsView = new TextView(this.getApplicationContext());
		lapsView.setText(R.string.laps);
		ll.addView(lapsView);*/

		// Remove lap / lap number / Add lap
		LinearLayout llLaps = new LinearLayout(this.getApplicationContext());
		
		Button removeLapButton = new Button(this.getApplicationContext());
		removeLapButton.setText(R.string.remove_lap);
		removeLapButton.setId(R.string.remove_lap);
		removeLapButton.setOnClickListener(this);
		llLaps.addView(removeLapButton);
		
		lapNumberView = new TextView(this.getApplicationContext());
		lapNumberView.setText(getResources().getText(R.string.laps).toString()+" "+this.lapsToWin);
		lapNumberView.setGravity(Gravity.CENTER_HORIZONTAL);
		llLaps.addView(lapNumberView, layoutParamsValue);

		Button addLapButton = new Button(this.getApplicationContext());
		addLapButton.setText(R.string.add_lap);
		addLapButton.setId(R.string.add_lap);
		addLapButton.setOnClickListener(this);
		llLaps.addView(addLapButton);
		
		ll.addView(llLaps);
		
		LinearLayout llCircuit = new LinearLayout(this.getApplicationContext());
		
		Button previousCircuitButton = new Button(this.getApplicationContext());
		previousCircuitButton.setText(R.string.previous_circuit);
		previousCircuitButton.setId(R.string.previous_circuit);
		previousCircuitButton.setOnClickListener(this);
		llCircuit.addView(previousCircuitButton);

		LinearLayout circuitDetailsView = new LinearLayout(this.getApplicationContext());
		circuitDetailsView.setOrientation(LinearLayout.VERTICAL);
		circuitDetailsView.setGravity(Gravity.TOP);
		
		circuitView = new TextView(this.getApplicationContext());
		circuitView.setText(getResources().getText(R.string.circuit).toString()+" "+this.circuit);
		circuitView.setGravity(Gravity.CENTER_HORIZONTAL);
		circuitDetailsView.addView(circuitView);
		
		circuitImage = new ImageView(this.getApplicationContext());
		circuitImage.setImageDrawable(getResources().getDrawable(R.drawable.circuit0_preview));
		circuitImage.setMaxHeight(100);
		circuitDetailsView.addView(circuitImage);

		llCircuit.addView(circuitDetailsView, layoutParamsValue);

		Button nextCircuitButton = new Button(this.getApplicationContext());
		nextCircuitButton.setText(R.string.next_circuit);
		nextCircuitButton.setId(R.string.next_circuit);
		nextCircuitButton.setOnClickListener(this);
		llCircuit.addView(nextCircuitButton);
		
		ll.addView(llCircuit);

		TextView separatorStart = new TextView(this.getApplicationContext());
		circuitDetailsView.addView(separatorStart);

		Button startButton = new Button(this.getApplicationContext());
		startButton.setText(R.string.start);
		startButton.setId(R.string.start);
		startButton.setOnClickListener(this);
		ll.addView(startButton);
		
		TextView separatorQuit = new TextView(this.getApplicationContext());
		ll.addView(separatorQuit);
		
		Button quitButton = new Button(this.getApplicationContext());
		quitButton.setText(R.string.quit);
		quitButton.setId(R.string.quit);
		quitButton.setOnClickListener(this);
		ll.addView(quitButton);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.string.add_player:
			playerNumber++;
			updatePlayerNumber();
			break;
			
		case R.string.remove_player:
			playerNumber = Math.max(playerNumber-1, 1);
			updatePlayerNumber();
			break;
			
		case R.string.add_lap:
			lapsToWin++;
			updateLapNumber();
			break;
			
		case R.string.remove_lap:
			lapsToWin = Math.max(lapsToWin-1, 1);
			updateLapNumber();
			break;
			
		case R.string.previous_circuit:
			circuit = Math.max(circuit-1, 0);
			updateCircuitNumber();
			break;
			
		case R.string.next_circuit:
			circuit = Math.min(circuit+1, 1);
			updateCircuitNumber();
			break;
			
		case R.string.start:
			Intent intent = new Intent(this, BottleTopRace.class);
			intent.putExtra("playerNumber", playerNumber);
			intent.putExtra("trackId", circuit);
			startActivityForResult(intent, 1);
			break;
			
		case R.string.share:
			Sharing.share(this);
			break;
			
		case R.string.quit:
			quit();
			break;
			
		default:
			break;
		}
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
	
	public void updatePlayerNumber() {
		playerNumberView.setText(getResources().getText(R.string.players).toString()+" "+this.playerNumber);
		playerNumberView.invalidate();
	}

	public void updateLapNumber() {
		lapNumberView.setText(getResources().getText(R.string.laps).toString()+" "+this.lapsToWin);
		lapNumberView.invalidate();
	}

	public void updateCircuitNumber() {
		circuitView.setText(getResources().getText(R.string.circuit).toString()+" "+this.circuit);
		circuitView.invalidate();
		circuitImage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("circuit"+this.circuit+"_preview", "drawable", this.getClass().getPackage().getName())));
		circuitImage.invalidate();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String msg=data.getStringExtra("msg");
		if(msg!=null) {
			Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		}
	}
}
