package nbpt.com.smartcity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private String TAG = "MainActivty";

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG,"enter onPostResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"enter onPause()");
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        super.onProvideAssistData(data);
        Log.d(TAG,"enter onProvideAssistData()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"enter onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"enter onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"enter onRestart()");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        Log.d(TAG,"enter onStateNotSaved()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"enter onCreate()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"enter onStart()");
    }
}
