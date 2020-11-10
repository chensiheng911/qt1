package nbpt.com.smartcity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import static javax.xml.transform.OutputKeys.VERSION;

/**
 * Created by Administrator on 2020/9/17.
 */

public class FourthActivity extends Activity {
    EditText edtName;
    EditText edtPsw;
    private CheckBox C1,C2;
    private Button btnDisplay, btnClear, btnExit;
    private int count = 0;
    private SharedPreferences sp;
    private Boolean isCheck = false;
    private Boolean itCheck = false;
    private String sUsername,sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5);

        C1 =(CheckBox)findViewById(R.id.checkBox);
        C1.setOnCheckedChangeListener(chkBox_listener);

        C2 =(CheckBox)findViewById(R.id.checkBox1);
        C2.setOnCheckedChangeListener(hckBox_listener);

        btnDisplay = findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(listener_display);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(listener_clear);

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(listener_clear);

//        btnSelect = findViewById(R.id.btnSelect);
//        btnSelect.setOnClickListener(listener_select);

        edtName = findViewById(R.id.editText1);
//        edtName.setText("test");
        edtName.setHint("请输入用户名");

        edtPsw = findViewById(R.id.editText2);
//        edtPsw.setText("test");
        edtPsw.setHint("请输入密码");

//        String pwd = edtPsw.getText().toString();
//        Log.d("The password is:", pwd);

        sp = FourthActivity.this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        sUsername = sp.getString("username","");
        sPassword = sp.getString("password","");
        if(sp.getBoolean("isCheck",false)) {
            edtName.setText(sUsername);
            edtPsw.setText(sPassword);
            C1.setChecked(true);
//            finish();
        }
        if(sp.getBoolean("itCheck",false)) {
            Intent intent1 = new Intent(FourthActivity.this,ThirdActivity.class);
            startActivity(intent1);
            C2.setChecked(true);
//            finish();
        }
    }

    CompoundButton.OnCheckedChangeListener chkBox_listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            isCheck = b;
//            Toast.makeText(FourthActivity.this,""+isCheck,Toast.LENGTH_SHORT).show();
        }
    };

    CompoundButton.OnCheckedChangeListener hckBox_listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            itCheck = b;
//            Toast.makeText(FourthActivity.this,""+isCheck,Toast.LENGTH_SHORT).show();
        }
    };

        View.OnClickListener listener_display = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String username = edtName.getText().toString();
            String password = edtPsw.getText().toString();
            if ((!username.isEmpty()) && (!password.isEmpty())) {
                AccountDBHelper db = new AccountDBHelper(FourthActivity.this,1);
                Cursor Cursor = db.Select(null, null);
                if (Cursor.getCount() != 0) {
                    Cursor.moveToFirst();
                    do {
                        if (username.equals(Cursor.getString(1))) {
                            if (password.equals(Cursor.getString(2))) {
                                Toast.makeText(FourthActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("username",username);
                                    editor.putString("password",password);
                                    editor.putBoolean("isCheck",isCheck);
                                    editor.putBoolean("itCheck",itCheck);
                                    editor.commit();
                                    Intent intent = new Intent(FourthActivity.this,ThirdActivity.class);
                                    startActivity(intent);
                            } else if (!password.equals(Cursor.getString(2))) {
                                Toast.makeText(FourthActivity.this, "密码错误！", Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("username","");
                                editor.putString("password","");
                                editor.putBoolean("isCheck",false);
                                editor.putBoolean("itCheck",false);
                                editor.commit();
                            }
                            count = 1;
                        }
                    }
                    while (Cursor.moveToNext());
                    if (count == 0) {
                        Toast.makeText(FourthActivity.this, "账号未注册！", Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username","");
                        editor.putString("password","");
                        editor.putBoolean("isCheck",false);
                        editor.putBoolean("itCheck",false);
                        editor.commit();
                    }
                    Cursor.close();
                }
            } else {
                Toast.makeText(FourthActivity.this, "请输入账号或密码！", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username","");
                editor.putString("password","");
                editor.putBoolean("isCheck",false);
                editor.putBoolean("itCheck",false);
                editor.commit();
            }
        }
    };

    ;
    View.OnClickListener listener_clear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnClear:
                Intent intent = new Intent(FourthActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
                case R.id.btnExit:
                    finish();
                    break;
            }
        }
    };
//    View.OnClickListener listener_select = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            AccountDBHelper db1 = new AccountDBHelper(FourthActivity.this, 1);
//            Cursor cursor1=db1.selects(null,null);
//            if(cursor1.getCount()!=0)
//            {
//                cursor1.moveToFirst();
//                do{
//                    textView.setText(textView.getText()+cursor1.getString(1));
//                }
//                while(cursor1.moveToNext());
//                cursor1.close();
//            }
//        }
//    };
}


    //        public void onClick(View view)
//        {
//            String username =edtName.getText().toString();
//            String password =edtPsw.getText().toString();
//            if((username.equals("admin"))&&(password.equals("123456")))
//            {
//                Toast.makeText(FourthActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
//            }
////            else if(username.equals(""))
////            {
////                Toast.makeText(SecActivity.this,"用户名不完整",Toast.LENGTH_LONG).show();
////            }
////            else if(password.equals(""))
////            {
////                Toast.makeText(SecActivity.this,"密码不完整",Toast.LENGTH_LONG).show();
////            }
//            else
//            {
//                Toast.makeText(FourthActivity.this,"用户名或密码不对！",Toast.LENGTH_LONG).show();
//            }
//        }


