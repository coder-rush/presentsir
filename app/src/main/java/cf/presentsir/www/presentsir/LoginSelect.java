package cf.presentsir.www.presentsir;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class LoginSelect extends Activity {
     Button b,b1;
    //b=(Button) findViewById(R.id.button);




    public void studClick(View v)
    {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void teacherClick(View v)
    {
        Intent intent = new Intent(this, teacherRegisterActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_select);
        b=(Button)findViewById(R.id.button);
        b1=(Button)findViewById(R.id.button2);

        b.setBackgroundColor(Color.rgb(0, 187, 255));
        b1.setBackgroundColor(Color.rgb(0, 187, 255));
        b.setTextColor(Color.WHITE);
        b1.setTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_select, menu);
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
