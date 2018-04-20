package com.example.user.easynote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UpdateNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHandler mydb = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("ID");
        EditText title = (EditText)findViewById(R.id.titletxt);
        EditText description = (EditText)findViewById(R.id.descriptiontext);
        ImageView img = (ImageView)findViewById(R.id.picturenote);
        String txtT = mydb.getTitle(id);
        String txtD = mydb.getDescription(id);
        int txticon = mydb.getIcon(id);

        title.setText(txtT);
        description.setText(txtD);
        img.setImageResource(txticon);

        Button btnsave = (Button)findViewById(R.id.update);
        btnsave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText title = (EditText)findViewById(R.id.titletxt);
        EditText description = (EditText)findViewById(R.id.descriptiontext);
        String txtT = title.getText().toString();
        String txtD = description.getText().toString();
        title.setText(txtT);
        description.setText(txtD);

        //edit to update
        mydb.addRecord(txtT,txtD,R.mipmap.ic_launcher);
        Intent itnmain = new Intent(this,MainActivity.class);
        startActivity(itnmain);
    }
}

