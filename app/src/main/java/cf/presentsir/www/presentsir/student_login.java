package cf.presentsir.www.presentsir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class student_login extends Activity {
    public String myclass;
    public String rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
    }
    public void onSubmitAction(View v)
    {
        EditText ed[]=new EditText[8];
        EditText ed1=(EditText)findViewById(R.id.etxtSem);
        EditText ed2=(EditText)findViewById(R.id.etxtSec);
        EditText ed3=(EditText)findViewById(R.id.etxtBranch);
        String sem =ed1.getText().toString();
        String brn =ed3.getText().toString();
        String sec =ed2.getText().toString();
        myclass=brn+"_"+sem+"_"+sec;



     String samp=((EditText)findViewById(R.id.mon1)).getText().toString();
        String [] mon = new String[9];
        mon[1]=((EditText)findViewById(R.id.mon1)).getText().toString();mon[2]=((EditText)findViewById(R.id.mon2)).getText().toString();mon[3]=((EditText)findViewById(R.id.mon3)).getText().toString();
        mon[4]=((EditText)findViewById(R.id.mon4)).getText().toString();mon[5]=((EditText)findViewById(R.id.mon5)).getText().toString();mon[6]=((EditText)findViewById(R.id.mon6)).getText().toString();
        mon[7]=((EditText)findViewById(R.id.mon7)).getText().toString();mon[8]=((EditText)findViewById(R.id.mon8)).getText().toString();

      String [] tue = new String[9];
        tue[1]=((EditText)findViewById(R.id.tue1)).getText().toString();tue[2]=((EditText)findViewById(R.id.tue2)).getText().toString();tue[3]=((EditText)findViewById(R.id.tue3)).getText().toString();
        tue[4]=((EditText)findViewById(R.id.tue4)).getText().toString();tue[5]=((EditText)findViewById(R.id.tue5)).getText().toString();tue[6]=((EditText)findViewById(R.id.tue6)).getText().toString();
        tue[7]=((EditText)findViewById(R.id.tue7)).getText().toString();tue[8]=((EditText)findViewById(R.id.tue8)).getText().toString();

        String [] wed = new String[9];
        wed[1]=((EditText)findViewById(R.id.wed1)).getText().toString();wed[2]=((EditText)findViewById(R.id.wed2)).getText().toString();wed[3]=((EditText)findViewById(R.id.wed3)).getText().toString();
        wed[4]=((EditText)findViewById(R.id.wed4)).getText().toString();wed[5]=((EditText)findViewById(R.id.wed5)).getText().toString();wed[6]=((EditText)findViewById(R.id.wed6)).getText().toString();
        wed[7]=((EditText)findViewById(R.id.wed7)).getText().toString();wed[8]=((EditText)findViewById(R.id.wed8)).getText().toString();

        String [] thu = new String[9];
        thu[1]=((EditText)findViewById(R.id.thu1)).getText().toString();thu[2]=((EditText)findViewById(R.id.thu2)).getText().toString();thu[3]=((EditText)findViewById(R.id.thu3)).getText().toString();
        thu[4]=((EditText)findViewById(R.id.thu4)).getText().toString();thu[5]=((EditText)findViewById(R.id.thu5)).getText().toString();thu[6]=((EditText)findViewById(R.id.thu6)).getText().toString();
        thu[7]=((EditText)findViewById(R.id.thu7)).getText().toString();thu[8]=((EditText)findViewById(R.id.thu8)).getText().toString();

        String [] sat = new String[9];
        sat[1]=((EditText)findViewById(R.id.sat1)).getText().toString();sat[2]=((EditText)findViewById(R.id.sat2)).getText().toString();sat[3]=((EditText)findViewById(R.id.sat3)).getText().toString();
        sat[4]=((EditText)findViewById(R.id.sat4)).getText().toString();sat[5]=((EditText)findViewById(R.id.sat5)).getText().toString();sat[6]=((EditText)findViewById(R.id.sat6)).getText().toString();
        sat[7]=((EditText)findViewById(R.id.sat7)).getText().toString();sat[8]=((EditText)findViewById(R.id.sat8)).getText().toString();

        String [] fri = new String[9];
        fri[1]=((EditText)findViewById(R.id.fri1)).getText().toString();fri[2]=((EditText)findViewById(R.id.fri2)).getText().toString();fri[3]=((EditText)findViewById(R.id.fri3)).getText().toString();
        fri[4]=((EditText)findViewById(R.id.fri4)).getText().toString();fri[5]=((EditText)findViewById(R.id.fri5)).getText().toString();fri[6]=((EditText)findViewById(R.id.fri6)).getText().toString();
        fri[7]=((EditText)findViewById(R.id.fri7)).getText().toString();fri[8]=((EditText)findViewById(R.id.fri8)).getText().toString();

         Context context;
        context=this;
        DatabaseHelper dataHelper=new DatabaseHelper(context);
        dataHelper.getWritableDatabase();
        for(int i=1;i<=8;i++)
        dataHelper.insertData(""+i,mon[i],tue[i],wed[i],thu[i],fri[i],sat[i]);


        //arr=dataHelper.getPeriodsForDay("mon");
        for(int m=1; m<9; m++)
        {
            System.out.println("mymymymy " + mon[m]);
        }
        dataHelper.getReadableDatabase();
    String[] arr=dataHelper.getPeriodsForDay("mon");// new String[8];

   for(int m=0; m<8; m++)
       {
           System.out.println("ohmymymymy" + arr[m]);
        }
        rollno =(getIntent().getExtras()).getString("rollno");
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);


       System.out.println("ohhello"+myclass);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_login, menu);
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
