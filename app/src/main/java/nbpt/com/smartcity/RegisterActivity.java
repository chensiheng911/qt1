package nbpt.com.smartcity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Administrator on 2020/9/30.
 */

public class RegisterActivity extends Activity {
    EditText editName;
    EditText editPwd1;
    EditText editPwd2;
    EditText editSJ;
    private Button btnExit, btnZC,btnceshi;

    private SharedPreferences sp;

    private String sUsername,sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registet);

        editName = findViewById(R.id.editText4);
        editName.setHint("请输入用户名");

        editPwd1 = findViewById(R.id.editText5);
        editPwd1.setHint("请输入密码");

        editPwd2 = findViewById(R.id.editText6);
        editPwd2.setHint("请再次输入密码");

        editSJ = findViewById(R.id.editText7);
        editSJ.setHint("请输入手机号码");

        btnceshi = findViewById(R.id.btn1);
        btnceshi.setOnClickListener(listener_zc);

        btnExit = findViewById(R.id.btn2);
        btnExit.setOnClickListener(listener_zc);

        btnZC = findViewById(R.id.btn3);
        btnZC.setOnClickListener(listener_register);

        sp = RegisterActivity.this.getSharedPreferences("user_info1", Context.MODE_PRIVATE);
    }
    View.OnClickListener listener_register=new View.OnClickListener()
    {
        @Override
        public void onClick(View view){
            String username =editName.getText().toString();
            String password1 =editPwd1.getText().toString();
            String password2 =editPwd2.getText().toString();
            String SJ =editSJ.getText().toString();
            if(username.isEmpty()||password1.isEmpty()&&password2.isEmpty()){
                Toast.makeText(RegisterActivity.this,"用户名和密码不能为空！",Toast.LENGTH_LONG).show();
            }
            else if(password1.isEmpty() != password2.isEmpty()) {
                Toast.makeText(RegisterActivity.this,"两次密码输入不正确！",Toast.LENGTH_LONG).show();
            }
            else{
                AccountDBHelper db=new AccountDBHelper(RegisterActivity.this,AccountDBHelper.VERSION_1);
                long ret =db.insertUser(username,password1,1,null);
                Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener listener_zc = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String zhanghao = editName.getText().toString();
            String mima = editPwd1.getText().toString();
            switch (view.getId()) {
                case R.id.btn1:
                sUsername=sp.getString("zhanghao","");
                sPassword=sp.getString("mima","");
                    if((!zhanghao.isEmpty())&&(!mima.isEmpty())){
                        if(zhanghao.equals(sUsername)&&(mima.equals(sPassword))){
                            Toast.makeText(RegisterActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                case R.id.btn2:
                    Intent intent=new Intent (RegisterActivity.this,FourthActivity.class);//使用Intent启动
                    startActivity(intent);
                    finish();
                    break;
//                case R.id.btn3:
//                    if((!zhanghao.isEmpty())&&(!mima.isEmpty())){
//                        //通过SharedPreferences获取Editor对象
//                        SharedPreferences.Editor editor =sp.edit();
//                        //把用户名、密码和开关添加到Editor中
//                        editor.putString("zhanghao",zhanghao);
//                        editor.putString("mima",mima);
//                        editor.putBoolean("isCheck",isCheck);
//                        //进行保存
//                        editor.commit();
//                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
//                        break;
//                    }
            }
        }
    };
}