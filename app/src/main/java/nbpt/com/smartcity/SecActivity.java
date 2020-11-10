package nbpt.com.smartcity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class SecActivity extends Activity
{
    EditText edtName;
    EditText edtPsw;
    private Button btnDisplay,btnClear,btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        btnDisplay = findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(listener_display);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(listener_clear);

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(listener_clear);

        edtName = findViewById(R.id.editText);
        edtName.setBackgroundColor(Color.parseColor("#40E0D0"));
        edtName.setHint("请输入用户名");

        edtPsw = findViewById(R.id.editText2);
        edtPsw.setText("123456");
        edtPsw.setBackgroundColor(Color.parseColor("#40E0D0"));
        edtPsw.setHint("请输入密码");

        String pwd = edtPsw.getText().toString();
        Log.d("The password is:", pwd);
    }
    View.OnClickListener listener_display =new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            String username =edtName.getText().toString();
            String password =edtPsw.getText().toString();
            if((username.equals("admin"))&&(password.equals("123456")))
            {
                Toast.makeText(SecActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
            }
//            else if(username.equals(""))
//            {
//                Toast.makeText(SecActivity.this,"用户名不完整",Toast.LENGTH_LONG).show();
//            }
//            else if(password.equals(""))
//            {
//                Toast.makeText(SecActivity.this,"密码不完整",Toast.LENGTH_LONG).show();
//            }
            else
            {
                Toast.makeText(SecActivity.this,"用户名或密码不对！",Toast.LENGTH_LONG).show();
            }
        }
    };
    View.OnClickListener listener_clear =new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId()) {
                case  R.id.btnClear:
//                    edtName.setText("");
//                    edtPsw.setText("");
                    break;
                case  R.id.btnExit:
                    finish();
                    break;
            }
        }
    };
}


