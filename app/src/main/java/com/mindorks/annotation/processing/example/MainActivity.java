package com.mindorks.annotation.processing.example;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.tttt.beantest.IBase33;
import com.justsafe.android.core.bean.Services;
import com.justsafe.android.core.bean.annotations.BeanImp;
import com.mindorks.binder.Binding;
import com.mindorks.lib.annotations.BindView;
import com.mindorks.lib.annotations.OnClick;
import com.test.bean.IBase2;

import java.util.Iterator;
import java.util.ServiceLoader;

import mindorks.com.annotationprocessingexample.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding.bind(this);

//        Log.d("SUN", new BeanAnnotationManager().toString());

        Services.load(IBase.class).test();
        Services.load(IBase2.class).test2();

        Services.load(IBase33.class).test3();

        serviceLoad();
        serviceLoad2();
    }

    private void serviceLoad(){
        //加载Fragment 类型的ServiceLoader
        ServiceLoader<IBase> iterator = ServiceLoader.load(IBase.class);

        for (Iterator<IBase> iterator1 = iterator.iterator(); iterator1.hasNext(); ) {
            final IBase fragment = iterator1.next();

            //获取ITabPage 注解的类
            BeanImp property = fragment.getClass().getAnnotation(BeanImp.class);
            if (property == null) {
                continue;
            }

            Log.d("SUN", "class = "+fragment.getClass().getName() + "   => BeanImp = "+ property.toString());
        }

    }

    private void serviceLoad2(){
        //加载Fragment 类型的ServiceLoader
        ServiceLoader<IBase2> iterator = ServiceLoader.load(IBase2.class);

        for (Iterator<IBase2> iterator1 = iterator.iterator(); iterator1.hasNext(); ) {
            final IBase2 fragment = iterator1.next();

            //获取ITabPage 注解的类
            BeanImp property = fragment.getClass().getAnnotation(BeanImp.class);
            if (property == null) {
                continue;
            }

            Log.d("SUN", "class = "+fragment.getClass().getName() + "   => BeanImp = "+ property.toString());
        }

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
