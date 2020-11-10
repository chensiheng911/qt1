package nbpt.com.smartcity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * Created by Administrator on 2020/9/10.
 */
public class ThirdActivity extends Activity {
    private EditText edtLabel ;
    private Button btnDisplay,btnClear ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main4);
        edtLabel =findViewById(R.id.edtLabel1);
        btnDisplay =findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(listener_display);
        btnClear =findViewById(R.id.btnClear);
        btnClear.setOnClickListener(listener_clear);
}

        View.OnClickListener listener_display =new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            edtLabel.setText("智慧城市！");
        }
    };
    View.OnClickListener listener_clear =new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            edtLabel.setText("");
        }
    };
    }
