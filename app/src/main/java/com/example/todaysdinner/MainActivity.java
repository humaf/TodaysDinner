package com.example.todaysdinner;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<RecyclerItem> ItemList;
    List<RecyclerItem> m_listItems = new ArrayList<>();
    Button btn;
    RecyclerView recyclerView;
    String[] mStringArray;
    FloatingActionButton fab;
    ListView lv;
    EditText edit;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private  RecyclerItem item;
    Bundle bundle = new Bundle();

    public void addFunction(View view) {

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        String input = edit.getText().toString();
        Log.i("user Input",input);
        bundle.putString("userinput", input);

        adapter = new RecyclerAdapter(getApplicationContext(), m_listItems);
         recyclerView.setAdapter(adapter);
         item = new RecyclerItem();

        if(input!=null&&input.length()>0) {
            item.setIngredient(input);
            Log.i("checking item ",item.getIngredient().toString());
            Log.i("checking",m_listItems.toString());
            m_listItems.add(item);
            Log.i("checking adding" ,m_listItems.toString());
            edit.setText("");
            btn.setEnabled(true);
            adapter.notifyDataSetChanged();
        }

        /*
                  String newName = nameField.getText().toString();
                if(!newName.equals("")){
                    if(myRecyclerViewAdapter.getItemCount()>1){
                        myRecyclerViewAdapter.add(1, newName);
                    }else{
                        myRecyclerViewAdapter.add(0, newName);
                    }
         */

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

        edit = (EditText) findViewById(R.id.e1);

        btn = (Button)findViewById(R.id.recipebtn);

        fab = (FloatingActionButton)findViewById(R.id.floating_action_button);

        btn.setEnabled(false);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    //    adapter = new RecyclerAdapter(getApplicationContext(), m_listItems);
      //  recyclerView.setAdapter(adapter);
      // item = new RecyclerItem();

    }
}
