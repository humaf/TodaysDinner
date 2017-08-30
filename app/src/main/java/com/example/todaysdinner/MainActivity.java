package com.example.todaysdinner;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> m_adapter;
    private List<ListItem> ItemList;
    ArrayList<String> m_listItems = new ArrayList<String>();
    Button btn;
    FloatingActionButton fab;
    ImageButton Addbtn;
    ListView lv;
    EditText edit;
    Bundle bundle = new Bundle();


    public void addFunction(View view) {

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        final String input = edit.getText().toString();
        bundle.putString("userinput", input);

        String[] values = new String[] {"", input};
        if(null!=input&&input.length()>0) {
            m_listItems.add(new String(input));
            m_adapter.notifyDataSetChanged();
            lv.setAdapter(m_adapter);
            edit.setText("");
            btn.setEnabled(true);
        }
    }

    public void getRecipes(View view){
        Intent  i = new Intent(this,DisplayActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   Addbtn = (ImageButton) findViewById(R.id.imageButton);

        edit = (EditText) findViewById(R.id.e1);

       lv = (ListView) findViewById(R.id.listView);

        m_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, m_listItems);

        btn = (Button)findViewById(R.id.recipebtn);

        fab = (FloatingActionButton)findViewById(R.id.floating_action_button);

        btn.setEnabled(false);
/*
        if(m_listItems.isEmpty()){

            btn.setEnabled(false);
        }
        else
        {
            btn.setEnabled(true);
        }

    */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
