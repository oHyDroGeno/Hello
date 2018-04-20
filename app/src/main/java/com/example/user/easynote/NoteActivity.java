package com.example.user.easynote;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


public class NoteActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHandler mydb = new DatabaseHandler(this);
    private static final String TAG = "images";
    private static final int SELECT_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Button btnsave = (Button)findViewById(R.id.save);
        btnsave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText title = (EditText)findViewById(R.id.titletxt);
        EditText description = (EditText)findViewById(R.id.descriptiontext);
        String txtT = title.getText().toString();
        String txtD = description.getText().toString();
        mydb.addRecord(txtT,txtD,R.mipmap.ic_launcher);
        Intent itnmain = new Intent(this,MainActivity.class);
        startActivity(itnmain);
    }

    public void addPhoto(View v){
        Toast.makeText(this,"You Choose Add Photo",Toast.LENGTH_SHORT).show();
        Intent itn = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(itn,SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            String [] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String pathfile = cursor.getString(columnIndex);
            cursor.close();

            ImageView img = (ImageView)findViewById(R.id.imageView1);
            int width = img.getWidth();
            int height = img.getHeight();

            BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
            int imageWidth = factoryOptions.outWidth;
            int imageHeight = factoryOptions.outHeight;
            int scaleFactor = Math.min(imageWidth/width, imageHeight/height);

            factoryOptions.inJustDecodeBounds = false;
            factoryOptions.inSampleSize = scaleFactor;
            factoryOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(pathfile, factoryOptions);

            img.setImageBitmap(bitmap);
            Toast.makeText(this,"Photo added",Toast.LENGTH_SHORT).show();
        }

    }

}
