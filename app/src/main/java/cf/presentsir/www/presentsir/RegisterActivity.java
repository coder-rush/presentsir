package cf.presentsir.www.presentsir;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class RegisterActivity extends Activity {
    public static final String LOGIN_URL = "http://presentsir.cf/volleyRegisterFinal.php";

    Button b;
    public void onRegisterBtnClick(View v)
    {

       final String firstname=((EditText)findViewById(R.id.txtfirstname)).getText().toString();
       final  String lastname=((EditText)findViewById(R.id.txtlastname)).getText().toString();
       final String enrollno=((EditText)findViewById(R.id.txtenrollno)).getText().toString();
       final String sec=((EditText)findViewById(R.id.txtsec)).getText().toString();
       final String branch=((EditText)findViewById(R.id.txtbranch)).getText().toString();
       final String mobno=((EditText)findViewById(R.id.txtmobno)).getText().toString();
       final String rollno=((EditText)findViewById(R.id.txtrollno)).getText().toString();

        final String emailid=((EditText)findViewById(R.id.txtemailid)).getText().toString();
        final String pass=((EditText)findViewById(R.id.txtteacherpass)).getText().toString();
        final String confirmpass=((EditText)findViewById(R.id.txtconfirmpass)).getText().toString();

        if(pass.equals(confirmpass))
        {}
        else
        {}



        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();  //success login code
                        }else{
                            Toast.makeText(RegisterActivity.this,"Error Login"+ response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("kfirstname",firstname);
                map.put("klastname",lastname);
                map.put("kenrollno",enrollno);
                map.put("ksec",sec);
                map.put("kbranch",branch);
                map.put("kpass",pass);
                map.put("kmobno",mobno);
                map.put("krollno",rollno);
                map.put("kemailid",emailid);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        Intent intent = new Intent(this, student_login.class);
        intent.putExtra("rollno",enrollno);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studregister);
        b=(Button)findViewById(R.id.btnregister);
        b.setBackgroundColor(Color.rgb(0, 187, 255));
        b.setTextColor(Color.WHITE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
