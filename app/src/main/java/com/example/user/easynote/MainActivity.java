package com.example.user.easynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DatabaseHandler mydb = new DatabaseHandler(this);
    private List<Data> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int totalRecord = mydb.getRecordCount();
        for(long i=totalRecord;i>0;i--){
            Data item = new Data(mydb.getTitle(i),mydb.getDescription(i),mydb.getIcon(i));
            datas.add(item);
        }

        MyAdapter adapter = new MyAdapter(this,datas);
        ListView lv = (ListView)findViewById(R.id.list1);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        Intent itn = new Intent(this,UpdateNoteActivity.class);
        itn.putExtra("ID",mydb.getRecordCount() - i);
        startActivity(itn);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,0,0,"Setting");
        menu.add(0,1,1,"About App");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 0:
                Toast.makeText(this,"selected 0",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this,"selected 1",Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Note(View v){
        Intent itnNote = new Intent(this,NoteActivity.class);
        startActivity(itnNote);
    }
}
