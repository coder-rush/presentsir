package cf.presentsir.www.presentsir;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class teacher_login extends Activity {
    public static String classname;
    public static String start;
    public static String end;
    public static String today;
    public static final String LOGIN_URL = "http://presentsir.cf/takeatt.php";
    private List<EditText> editTexts = new ArrayList<EditText>();

    public void takeAttClick(View v)
    {
        String branch=((EditText)findViewById(R.id.edt1)).getText().toString();
        String sem=((EditText)findViewById(R.id.edt2)).getText().toString();
        String sub=((EditText)findViewById(R.id.edt3)).getText().toString();
        String sec=((EditText)findViewById(R.id.edt4)).getText().toString();
        start=((EditText)findViewById(R.id.edt5)).getText().toString();
        end=((EditText)findViewById(R.id.edt6)).getText().toString();
        classname= branch+"_"+sem+"_"+sec+"_"+sub;
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dMMM");
        today= sdf.format(date);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Record updated successfully")){
                            Toast.makeText(teacher_login.this, "Record updated successfully", Toast.LENGTH_LONG).show();
                            //zb.setBackgroundColor(Color.BLUE);              //success login code

                        }else{
                            Toast.makeText(teacher_login.this,"Error "+ response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(teacher_login.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("start",start);
                map.put("tablename",classname);
                map.put("end",end);
                map.put("date",today);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        Intent intent = new Intent(this, display_att.class);
        intent.putExtra("myClass",classname);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teacher_login, menu);
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
