package cf.presentsir.www.presentsir;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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


public class display_att extends ActionBarActivity {
 public static String myClass;
    public static final String LOGIN_URL = "http://presentsir.cf/show_att.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Adding comments 
        // changes on local dev 2 only

        setContentView(R.layout.activity_display_att);
    }

    public void onClickRefresh(View v)
    {
        myClass=(getIntent().getExtras()).getString("myClass");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Record updated successfully")){
                            Toast.makeText(display_att.this, "Record updated successfully"+response, Toast.LENGTH_LONG).show();
                            //zb.setBackgroundColor(Color.BLUE);              //success login code

                        }else{
                            Toast.makeText(display_att.this,response, Toast.LENGTH_LONG).show();
                            TextView edt = (TextView)findViewById(R.id.txtDisplay);
                            edt.setText(" ");
                            edt.setText(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(display_att.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();

                map.put("tablename",myClass);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_att, menu);
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
