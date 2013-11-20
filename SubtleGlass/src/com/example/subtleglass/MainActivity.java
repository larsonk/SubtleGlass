package com.example.subtleglass;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
	private ArrayList<String> final_results= new ArrayList<String>();
	ListView lv;
	static final int check = 1111;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView)findViewById(R.id.lvVoiceReturn);
		Button b = (Button)findViewById(R.id.bVoice);
		b.setOnClickListener(this);
	}

	public void onClick(View v){
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Listening");
		startActivityForResult(i, check);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
	
		if(requestCode == check && resultCode == RESULT_OK){
			final_results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, final_results));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
