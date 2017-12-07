package cn.itsite.aguider.demo.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.socks.library.KLog;

import cn.itsite.aguider.AGuiderListener;
import cn.itsite.aguider.Guide;
import cn.itsite.aguider.Guider;
import cn.itsite.aguider.demo.R;
import cn.itsite.aguider.highlight.OvalHighlight;
import cn.itsite.aguider.highlight.RectHighlight;
import cn.itsite.aguider.position.Position;

/**
 * @author leguang
 * @version v0.0.0
 * @E-mail langmanleguang@qq.com
 * @time 2016/11/24 0024 9:08
 */
public class ActivityActivity extends AppCompatActivity {
    private static final String TAG = ActivityActivity.class.getSimpleName();
    private TextView textView;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        initView();
        initData();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initView() {
        textView = ((TextView) findViewById(R.id.textView));
        imageView = ((ImageView) findViewById(R.id.imageView));
        button = ((Button) findViewById(R.id.button));
    }

    private void initData() {
//        simple();
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

//        Guide guide0 = new Guide.Builder()
//                .setPoint(textView)
//                .setHighlight(new OvalHighlight(textView.getWidth(), textView.getHeight()))
//                .setView(getDesView("000000000"))
//                .setBackground(0x90FF0000)
//                .build();
//
//        Guide guide1 = new Guide.Builder()
//                .setPoint(imageView)
//                .setPosition(Position.bottom())
//                .setHighlight(new OvalHighlight(imageView.getWidth(), imageView.getHeight()))
//                .setView(getDesView("1111111"))
//                .setBackground(0x9000FF00)
//                .build();
        simple();

    }

    public void simple() {

        Guide guide0 = new Guide.Builder()
                .setPoint(textView)
                .setHighlight(new OvalHighlight(textView.getWidth(), textView.getHeight()))
                .setView(getDesView("000000000"))
                .setBackground(0x90FF0000)
                .build();
        Guide guide1 = new Guide.Builder()
                .setPoint(imageView)
                .setPosition(Position.bottom())
                .setHighlight(new OvalHighlight(imageView.getWidth(), imageView.getHeight()))
                .setView(getDesView("1111111"))
                .setBackground(0x9000FF00)
                .build();

        Guide guide = new Guide.Builder()
                .setPoint(button)
                .setPosition(Position.topleft())
                .setOnGuideListener(new AGuiderListener.OnGuideListener() {
                    @Override
                    public void onStart(Guide guide) {
                        KLog.e(TAG, "Guide----onStart…………");

                    }

                    @Override
                    public void onStop(Guide guide) {
                        KLog.e(TAG, "Guide----onStop…………");

                    }
                })
                .setHighlight(new RectHighlight(button.getWidth(), button.getHeight()))
                .setView(getDesView("2222222222"))
                .build();

        new Guider.Builder()
                .setAnchor(this)
                .addGuides(guide0, guide1, guide)
                .setMode(Guider.MODE_NEXT)//MODE_NEXT：一个接着一个显示。MODE_TOGETHER：一起显示。
                .addOnGuidertStartListener(new AGuiderListener.OnGuiderStartListener() {
                    @Override
                    public void onStart() {
                        KLog.e(TAG, "onStart…………");
                    }
                })
                .addOnGuidertStopListener(new AGuiderListener.OnGuiderStopListener() {
                    @Override
                    public void onStop() {
                        KLog.e(TAG, "onStop…………");
                    }
                })
                .show();
    }
}
