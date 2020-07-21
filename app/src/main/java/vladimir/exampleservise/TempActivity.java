package vladimir.exampleservise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import vladimir.exampleservise.services.CountService;

public class TempActivity extends AppCompatActivity {

    TextView textView;
    private String stringExtra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        textView = findViewById(R.id.tv_text);

        stringExtra = getIntent().getStringExtra(CountService.TIME);

        textView.setText("Time is from notification" + stringExtra);
    }
}