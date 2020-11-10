package nbpt.com.smartcity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;


public class Main4Activity extends Activity {
    TextView tvSkip;
    private int qt=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tvSkip = findViewById(R.id.t1);
        tvSkip.setOnClickListener(l1);
        getString(R.string.skip_wait, 3);

            CountDownTimer timer = new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long l) {
                    tvSkip.setText(l / 1000+"秒 跳过");
                }

                @Override
                public void onFinish() {
                    if(qt==0){tvSkip.setText("正在跳转");
                        Intent intent = new Intent(Main4Activity.this, FourthActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            };
            timer.start();
    }
    View.OnClickListener l1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            qt=1;
            Intent intent = new Intent(Main4Activity.this, FourthActivity.class);
            startActivity(intent);
        }
    };
//    Intent intent = new Intent(Main4Activity.this, FourthActivity.class);
//    startActivity(intent);

}
