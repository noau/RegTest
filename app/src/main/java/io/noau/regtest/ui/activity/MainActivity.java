package io.noau.regtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.noau.regtest.R;
import io.noau.regtest.util.StringUtils;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		Toolbar toolbar=findViewById(R.id.toolbar_main);
        final EditText textEt = findViewById(R.id.et_main_testtxt);
        final EditText regexEt = findViewById(R.id.et_main_testregex);
        final TextView resultTv = findViewById(R.id.tv_main_result);
        final Button matchButton = findViewById(R.id.btn_main_match);
        final Button searchButton = findViewById(R.id.btn_main_search);
        final Button splitButton = findViewById(R.id.btn_main_split);

        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu_main_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_main_toolbar_test:
                        startActivity(new Intent(MainActivity.this, TestActivity.class));
                        break;
                }
                return true;
            }
        });
        resultTv.setMovementMethod(ScrollingMovementMethod.getInstance());

        matchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = textEt.getText().toString();
                    String regexStr = regexEt.getText().toString();
                    boolean isMatch = Pattern.matches(regexStr, text);
                    Toast.makeText(getApplication(), "Match: " + isMatch, Toast.LENGTH_SHORT).show();
                }
            });

        searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = textEt.getText().toString();
                    String regexStr = regexEt.getText().toString();
                    String[] resultArr = StringUtils.searchString(regexStr, text);
                    String result = StringUtils.stringArrayToString(resultArr);
                    resultTv.setText(result);
                }
            });

        splitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = textEt.getText().toString();
                    String regexStr = regexEt.getText().toString();
                    String[] resultArr = Pattern.compile(regexStr).split(text);
                    String result = StringUtils.stringArrayToString(resultArr);
                    resultTv.setText(result);
                }
            });
    }

}
