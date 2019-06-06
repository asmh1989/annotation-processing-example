package com.mindorks.annotation.processing.example;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mindorks.binder.Binding;
import com.mindorks.lib.annotations.BindView;
import com.mindorks.lib.annotations.OnClick;

import mindorks.com.annotationprocessingexample.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding.bind(this);

        Log.d("SUN", new BeanAnnotationManager().toString());

    }

    @OnClick(R.id.bt_1)
    void bt1Click(View v) {
        tvContent.setText("Button 1 Clicked");
    }

    @OnClick(R.id.bt_2)
    void bt2Click(View v) {
        tvContent.setText("Button 2 Clicked");
    }
}
