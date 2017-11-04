package cf.presentsir.www.presentsir;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity  {
  //  SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
   // SharedPreferences.Editor editor = pref.edit();
    public static final String LOGIN_URL = "http://presentsir.cf/volleyRegister.php";
   // public static final String LOGIN_URL = "http://192.168.1.6:80/MyApp/volleyRegister.php";
    Button b;

    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_TABLENAME="tablename";
    String table_name="";
    public void loginAction(View v)
    {

        final String username;
        final String pwd;
        final RadioButton ch1;
        final RadioButton ch2;
        RadioGroup rg1;
        ch1=(RadioButton)findViewById(R.id.chkstudent);
        ch2=(RadioButton)findViewById(R.id.chkteacher);



        username=((EditText)findViewById(R.id.txtusername)).getText().toString();
        pwd=((EditText)findViewById(R.id.txtteacherpass)).getText().toString();

        if(ch1.isChecked() )
        {
            table_name="stud_reg";
        }
        else if(ch2.isChecked())
        {
            table_name="teacher_reg";
        }

        if(validate())
        {

        }



        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(LoginActivity.this, "Success",Toast.LENGTH_LONG).show();  //success login code
                            if(ch2.isChecked())
                            {
                                Intent intent = new Intent(LoginActivity.this,teacher_login.class);
                                startActivity(intent);
                            }
                            if(ch1.isChecked())
                            {
                                Intent intent = new Intent(LoginActivity.this,StudentGiveAtt.class);
                                intent.putExtra("rollno",username);
                                startActivity(intent);
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"Error Login"+ response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME,username);
                map.put(KEY_PASSWORD,pwd);
                map.put(KEY_TABLENAME,table_name);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    public void onRegisterClickAction(View v)
    {
        Intent intent = new Intent(this, LoginSelect.class);
        startActivity(intent);
    }
    public boolean validate()
    {
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b=(Button)findViewById(R.id.btnlogin);
        b.setBackgroundColor(Color.rgb(0, 187, 255));
        b.setTextColor(Color.WHITE);

        b=(Button)findViewById(R.id.btnRegister);
        b.setBackgroundColor(Color.rgb(0, 187, 255));
        b.setTextColor(Color.WHITE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
