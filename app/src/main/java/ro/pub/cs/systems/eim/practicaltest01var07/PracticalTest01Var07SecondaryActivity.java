package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    private Button sum;
    private Button prod;

    private int nr1, nr2, nr3, nr4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);
        sum = (Button) findViewById(R.id.buttSum);
        prod = (Button) findViewById(R.id.buttProd);
        Bundle extras = getIntent().getExtras();
        ((TextView) findViewById(R.id.text1)).setText(extras.getString("nr1"));
        ((TextView) findViewById(R.id.text2)).setText(extras.getString("nr2"));
        ((TextView) findViewById(R.id.text3)).setText(extras.getString("nr3"));
        ((TextView) findViewById(R.id.text4)).setText(extras.getString("nr4"));
        nr1 = Integer.valueOf(extras.getString("nr1"));
        nr2 = Integer.valueOf(extras.getString("nr2"));
        nr3 = Integer.valueOf(extras.getString("nr3"));
        nr4 = Integer.valueOf(extras.getString("nr4"));

        sum.setOnClickListener(it -> {
            int S = nr1 + nr2 + nr3 + nr4;
            Intent intent = new Intent();
            intent.putExtra("EX_RESULT",String.valueOf(S));
            setResult(RESULT_OK, intent);
            finish();
        });

        prod.setOnClickListener(it -> {
            int P = nr1 * nr2 * nr3 * nr4;
            Intent intent = new Intent();
            intent.putExtra("EX_RESULT",String.valueOf(P));
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}