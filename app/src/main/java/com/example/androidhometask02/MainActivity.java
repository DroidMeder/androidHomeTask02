package com.example.androidhometask02;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private RelativeLayout scrollRel, rtl;
    private EditText eemail, epass;
    private TextView tv, tvIn,  greeting, greeting1, greeting2;
    private Toast toast;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        scrollView=findViewById(R.id.scroll);
        scrollRel=findViewById(R.id.scrollRel);
        rtl=findViewById(R.id.rl1);
        tvIn=findViewById(R.id.tv_in);
        greeting1 = findViewById(R.id.tv_greeting2);
        greeting2 = findViewById(R.id.tv_greeting3);
        eemail = findViewById(R.id.et_email);
        epass=findViewById(R.id.et_password);
        tv=findViewById(R.id.tv_clickable);
        greeting=findViewById(R.id.tv_greeting);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        eemail.getRootView().addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            scrollView.smoothScrollTo(0, eemail.getBottom());
        });

        btn.setOnClickListener(v -> {
            if(accessPermitted()){
                if (rtl.getVisibility() == View.INVISIBLE) {
                    rtl.setVisibility(View.VISIBLE);
                    tvIn.setVisibility(View.VISIBLE);
                    greeting1.setVisibility(View.VISIBLE);
                    greeting2.setVisibility(View.VISIBLE);
                } else {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    rtl.setVisibility(View.INVISIBLE);
                    tvIn.setVisibility(View.INVISIBLE);
                    greeting2.setVisibility(View.INVISIBLE);
                    greeting1.setVisibility(View.INVISIBLE);
                    RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );

                    rl.addRule(RelativeLayout.FOCUSABLE);
                    rl.addRule(RelativeLayout.FOCUSABLES_TOUCH_MODE);
                    rl.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    rl.addRule(RelativeLayout.CENTER_VERTICAL);
                    scrollRel.removeView(greeting);
                    scrollRel.addView(greeting, rl);

                }
            }
        });
        tv.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Это не мои проблемы",
                Toast.LENGTH_SHORT).show());

        eemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    btn.setBackgroundResource(R.drawable.draw);
                }else {
                    btn.setBackgroundResource(R.drawable.shape_btn);
                }
            }
        });
        epass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    btn.setBackgroundResource(R.drawable.draw);
                }else {
                    btn.setBackgroundResource(R.drawable.shape_btn);
                }
            }
        });
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on){
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on){
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private boolean accessPermitted() {

        if(eemail.getText().toString().equals("admin@gmail.com") && epass.getText().toString().equals("admin")){
            Toast.makeText(MainActivity.this, "Вы успешно зарегистрировались",
                    Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(MainActivity.this, "Неправильный логин и пароль",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}