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


public class teacherRegisterActivity extends Activity {

    public static final String LOGIN_URL = "http://presentsir.cf/volleyRegisterTeacher.php";

    Button b;

    public void onClickAction(View v)
    {

        final String firstname=((EditText)findViewById(R.id.txtfn)).getText().toString();
        final  String lastname=((EditText)findViewById(R.id.txtln)).getText().toString();
        final String tid=((EditText)findViewById(R.id.txttid)).getText().toString();
        final String dept=((EditText)findViewById(R.id.txtdept)).getText().toString();
        final String pass=((EditText)findViewById(R.id.txtteacherpass)).getText().toString();
        final String confirmpass=((EditText)findViewById(R.id.txtcp)).getText().toString();
        final String mobno=((EditText)findViewById(R.id.txtmno)).getText().toString();


        if(pass.equals(confirmpass))
        {}
        else
        {}



        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Successfully Registered")){
                            Toast.makeText(teacherRegisterActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();


                        }else{
                            Toast.makeText(teacherRegisterActivity.this,"Error Login"+ response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(teacherRegisterActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("kfirstname",firstname);
                map.put("klastname",lastname);
                map.put("ktid",tid);
                map.put("kdept",dept);
                map.put("kpass",pass);
                map.put("kmobno",mobno);



                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        b=(Button)findViewById(R.id.btnregteach);
        b.setBackgroundColor(Color.rgb(0,187,255));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teacher_register, menu);
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
