package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    private EditText text1;
    private EditText text2;
    private EditText text3;
    private EditText text4;
    private Button set;

    private SetButtonClickListener setListen = new SetButtonClickListener();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private IntentFilter intentFilter = new IntentFilter();

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_EXTRA, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
            String messageRecv = new String(intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
            String[] tokens = messageRecv.split(" ");
            text1.setText(tokens[0]);
            text2.setText(tokens[1]);
            text3.setText(tokens[2]);
            text4.setText(tokens[3]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);
        set = (Button) findViewById(R.id.buttSet);
        text1 = (EditText) findViewById(R.id.editTextNumber1);
        text2 = (EditText) findViewById(R.id.editTextNumber2);
        text3 = (EditText) findViewById(R.id.editTextNumber3);
        text4 = (EditText) findViewById(R.id.editTextNumber4);
        set.setOnClickListener(setListen);
        intentFilter.addAction(Constants.actionType);
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
        getApplicationContext().startService(intent);
    }

    private class SetButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            String nr1 = String.valueOf(text1.getText());
            String nr2 = String.valueOf(text2.getText());
            String nr3 = String.valueOf(text3.getText());
            String nr4 = String.valueOf(text4.getText());

            if (!nr1.matches("") && !nr2.matches("") && !nr3.matches("") && !nr4.matches("")) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
                intent.putExtra("nr1", nr1);
                intent.putExtra("nr2", nr2);
                intent.putExtra("nr3", nr3);
                intent.putExtra("nr4", nr4);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE) {
            Bundle result = data.getExtras();
            String resultFinal = result.getString("EX_RESULT");
            Toast.makeText(getApplication(), resultFinal, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("saved1", String.valueOf(text1.getText()));
        outState.putString("saved2", String.valueOf(text2.getText()));
        outState.putString("saved3", String.valueOf(text3.getText()));
        outState.putString("saved4", String.valueOf(text4.getText()));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text1.setText(savedInstanceState.getString("saved1"));
        text2.setText(savedInstanceState.getString("saved2"));
        text3.setText(savedInstanceState.getString("saved3"));
        text4.setText(savedInstanceState.getString("saved4"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        Log.d("broadcast", "DESTROYED");
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
        getApplicationContext().stopService(intent);
    }
}