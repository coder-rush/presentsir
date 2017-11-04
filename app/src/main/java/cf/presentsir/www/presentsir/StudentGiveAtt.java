package cf.presentsir.www.presentsir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class StudentGiveAtt extends Activity {
    public static final String LOGIN_URL = "http://presentsir.cf/submitatt.php";
    public static String today;
    public static String myClass;
    public static String rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     Button b;

        //RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.activity_student_give_att);
        //remoteViews.setTextViewText(R.id.b1, "hola");
        /*
        */


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_give_att);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dow="mon";
        switch (day) {
            case Calendar.SUNDAY:
                dow="mon"; //Remove this entry
                break;
            case Calendar.MONDAY:
                dow="mon"; // Current day is Monday
                break;

            case Calendar.WEDNESDAY:
                dow="wed";// etc.
                break;
            case Calendar.THURSDAY:
                dow="thu";// etc.
                break;
            case Calendar.FRIDAY:
                dow="fri";// etc.
                break;
            case Calendar.SATURDAY:
                dow="sat";// etc.
                break;
            case Calendar.TUESDAY:
                dow="tue";// etc.
        }

        Context context;
        context=this;
        DatabaseHelper dataHelper=new DatabaseHelper(context);
        dataHelper.getReadableDatabase();
        String[] arr=dataHelper.getPeriodsForDay(dow);
        for(int m=0; m<8; m++)
        {
            System.out.println("Elements of the day" + dow +"are"+ arr[m]);
        }
        b=(Button)findViewById(R.id.b1);
        b.setText(arr[0]);
        b=(Button)findViewById(R.id.b2);
        b.setText(arr[1]);
        b=(Button)findViewById(R.id.b3);
        b.setText(arr[2]);
        b=(Button)findViewById(R.id.b4);
        b.setText(arr[3]);
        b=(Button)findViewById(R.id.b5);
        b.setText(arr[4]);
        b=(Button)findViewById(R.id.b6);
        b.setText(arr[5]);
        b=(Button)findViewById(R.id.b7);
        b.setText(arr[6]);
        b=(Button)findViewById(R.id.b8);
        b.setText(arr[7]);


    }
  public void sendAtt(View v)
  {
    Button b=(Button)v;
      String sub = b.getText().toString();
      Date date=new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("dMMM");
      today= sdf.format(date);
      student_login obj = new student_login();
      myClass= "CSE_6_C";

      rollno=getIntent().getExtras().getString("rollno");
      myClass=myClass+"_"+sub;

      System.out.println("Finally the date is "+ today+" and class is "+myClass+" and toll no is "+rollno );





      StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
              new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      if(response.trim().equals("Record updated successfully")){
                          Toast.makeText(StudentGiveAtt.this, "Record updated successfully", Toast.LENGTH_LONG).show();
                          //zb.setBackgroundColor(Color.BLUE);              //success login code

                      }else{
                          Toast.makeText(StudentGiveAtt.this,"Error "+ response, Toast.LENGTH_LONG).show();
                      }
                  }
              },
              new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                      Toast.makeText(StudentGiveAtt.this,error.toString(),Toast.LENGTH_LONG ).show();
                  }
              }){
          @Override
          protected Map<String, String> getParams() throws AuthFailureError {
              Map<String,String> map = new HashMap<String,String>();
              map.put("rollno",rollno);
              map.put("tablename",myClass);
              map.put("date",today);
              return map;
          }
      };

      RequestQueue requestQueue = Volley.newRequestQueue(this);
      requestQueue.add(stringRequest);
  }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_give_att, menu);
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
