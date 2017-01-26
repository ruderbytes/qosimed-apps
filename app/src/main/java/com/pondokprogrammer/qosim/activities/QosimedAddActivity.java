package com.pondokprogrammer.qosim.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pondokprogrammer.qosim.R;

public class QosimedAddActivity extends AppCompatActivity {
    private Button btnCamera;
    private ImageView capturedImage,addnew;
    AutoCompleteTextView text;
    String[] languages = {
            "Resto BBQ Korea",
            "Tempat Makan Dua Lima",
            "Japanese Food",
            "Chinese Food",
            "Mie Ramen Oishi",
            "Resto Mie Japan",
            "American Burger"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qosimed_add);

//        btnCamera = (Button) findViewById(R.id.btnCamera);
        text=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);

        addnew = (ImageView) findViewById(R.id.addNew);

        capturedImage= (ImageView) findViewById(R.id.capturedImage);

        btnCamera = (Button) findViewById(R.id.btnCamera);


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,languages);
        text.setAdapter(adapter);

        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            capturedImage.setImageBitmap(bp);
            Toast.makeText(getApplicationContext(), "" + bp, Toast.LENGTH_SHORT).show();
        }
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bp = (Bitmap) data.getExtras().get("data");
                Toast.makeText(getApplicationContext(), bp+" was inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
}
