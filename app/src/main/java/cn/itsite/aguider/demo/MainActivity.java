package cn.itsite.aguider.demo;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.itsite.aguider.AGuiderListener;
import cn.itsite.aguider.Guide;
import cn.itsite.aguider.Guider;
import cn.itsite.aguider.highlight.OvalHighlight;
import cn.itsite.aguider.highlight.RectHighlight;
import cn.itsite.aguider.position.Position;

/**
 * @author leguang
 * @version v0.0.0
 * @E-mail langmanleguang@qq.com
 * @time 2016/11/24 0024 9:08
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        final TextView target0 = ((TextView) findViewById(R.id.tv0));
        final TextView target1 = ((TextView) findViewById(R.id.tv1));
        final TextView target2 = ((TextView) findViewById(R.id.tv2));


        target0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValueAnimator animator = ValueAnimator.ofInt(0, target0.getHeight()+1000);
                animator.setInterpolator(new DecelerateInterpolator(2F));
                animator.setDuration(100000);

                final Guide guide0 = new Guide.Builder()
                        .setPoint(target0)
                        .setHighlight(new OvalHighlight(target0.getWidth(), target0.getHeight()))
                        .setView(getDesView("000000000"))
                        .setBackground(0x90FF0000)
                        .setAnimator(animator)
                        .build();

                final Guide guide1 = new Guide.Builder()
                        .setPoint(target1)
                        .setPosition(Position.bottom())
                        .setHighlight(new OvalHighlight(target1.getWidth(), target1.getHeight()))
                        .setView(getDesView("1111111"))
                        .setBackground(0x9000FF00)
                        .build();

                final Guide guide2 = new Guide.Builder()
                        .setPoint(target2)
                        .setPosition(Position.topleft())
                        .setHighlight(new RectHighlight(target2.getWidth(), target2.getHeight()))
                        .setView(getDesView("2222222222"))
                        .setBackground(0x900000FF)
                        .build();

                new Guider.Builder()
                        .setAnchor(MainActivity.this)
                        .addGuides(guide0, guide1, guide2)
                        .setMode(Guider.MODE_TOGETHER)
                        .setOnGuidertStartListener(new AGuiderListener.OnGuidertStartListener() {
                            @Override
                            public void onStart() {
                                Log.e(TAG, "onStart^^^^^^^^^^^");
                            }
                        })
                        .setOnGuidertStopListener(new AGuiderListener.OnGuidertStopListener() {
                            @Override
                            public void onStop() {
                                Log.e(TAG, "onStart^^^^^^^^^^^");
                            }
                        })
                        .show();
            }
        });

//        final GuiderView guiderView = new GuiderView(MainActivity.this);
//
//        target1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KLog.e("1111111111");
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                layoutParams.leftMargin = 30;
//                guiderView.setBackgroundColor(Color.parseColor("#00ff00"));
//                guiderView.setLayoutParams(layoutParams);
//
//            }
//        });
//
//        target2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KLog.e("1111111111");
//                ll.addView(guiderView);
//
//                guiderView.addView(getDesView());
//
//            }
//        });

    }

    public View getDesView(String s) {
        final TextView description = new TextView(this);
        description.setText(s + "...........\n..............\n.................\n..");
        description.setTextColor(Color.parseColor("#000000"));
        description.setGravity(Gravity.CENTER);
        description.setTextColor(Color.parseColor("#ff0000"));
        description.setPadding(50, 50, 0, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM;
        description.setLayoutParams(layoutParams);
        description.setBackgroundColor(Color.parseColor("#77ff00ff"));
        return description;
    }
}