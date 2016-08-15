package com.github.abdalimran.androidspeechrecognition;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RecognitionListener{

    private static final String LOG_TAG = "Logs";
    private TextView recognitionView;
    private SpeechRecognizer speechRecognizer;
    private Intent recognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recognitionView= (TextView) findViewById(R.id.recognitionView);

        initSpeechRecognizer();
        initRecognizerIntent();

    }

    private void initRecognizerIntent(){
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en-US");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        Log.i(LOG_TAG,"initRecognizerIntent");
    }


    private void initSpeechRecognizer(){
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);
        Log.i(LOG_TAG,"initSpeechRecognizer");
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        Log.i(LOG_TAG,"onReadyForSpeech");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG,"onBeginningOfSpeech");
    }

    @Override
    public void onRmsChanged(float v) {
        Log.i(LOG_TAG,"onRmsChanged");
    }

    @Override
    public void onBufferReceived(byte[] bytes) {
        Log.i(LOG_TAG,"onBufferReceived");
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG,"onEndOfSpeech");
    }

    @Override
    public void onError(int i) {
        Log.i(LOG_TAG,"onError");
    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        recognitionView.setText(result.get(0).toString());
        Log.i(LOG_TAG,"onResults");
    }

    @Override
    public void onPartialResults(Bundle bundle) {
        Log.i(LOG_TAG,"onPartialResults");
    }

    @Override
    public void onEvent(int i, Bundle bundle) {
        Log.i(LOG_TAG,"onEvent");
    }

    public void SpeakNow(View view) {
        speechRecognizer.startListening(recognizerIntent);
        Log.i(LOG_TAG,"SpeakNow");
    }
}
