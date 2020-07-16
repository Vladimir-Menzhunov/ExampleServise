package vladimir.exampleservise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import vladimir.exampleservise.services.CountService;

public class TempActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        textView = findViewById(R.id.tv_text);

        long time = getIntent().getLongExtra(CountService.TIME, 0L);

        textView.setText("Time is " + time);
    }
}