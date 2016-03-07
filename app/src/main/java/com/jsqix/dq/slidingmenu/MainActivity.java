package com.jsqix.dq.slidingmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {
    private SlidingMenu menu;
    private ImageView topLeft, topRight;
    private PasswordInputView passwordInputView;
    private TextView pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        menuinitview();
    }

    private void initView() {
        topLeft = (ImageView) findViewById(R.id.top_left);
        topLeft.setOnClickListener(myClick);
        topRight = (ImageView) findViewById(R.id.top_right);
        topRight.setOnClickListener(myClick);
        passwordInputView= (PasswordInputView) findViewById(R.id.password_edit);
        pass= (TextView) findViewById(R.id.password_txt);
        passwordInputView.setPasswordLength(6);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pass.setText(passwordInputView.getText());

            }
        };
        passwordInputView.addTextChangedListener(watcher);
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.top_left:
                    menu.showMenu();
                    break;
                case R.id.top_right:
                    menu.showSecondaryMenu();
                    break;
            }


        }
    };

    private void menuinitview() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT_RIGHT);

        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置菜单页面滑动方式
        menu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置阴影图片的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //设置阴影图片
        menu.setShadowDrawable(R.drawable.menu_shadow);
        menu.setSecondaryShadowDrawable(R.drawable.menu_shadow);
        //划出时主页面显示的剩余宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 为侧滑菜单设置布局
        menu.setMenu(R.layout.menu_left);
        menu.setSecondaryMenu(R.layout.menu_right);
        // 设置SlidingMenu与下方视图的移动的速度比，当为1时同时移动，取值0-1
        menu.setBehindScrollScale(0.3f);


    }
}
