package com.example.mybookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditBookActivity extends AppCompatActivity {

    public static final int RESULT_CODE_SUCCESS = 666;
    private int position;
    private int resourceid;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        Bundle bundle=this.getIntent().getExtras();
        position=bundle.getInt("position",0);
        resourceid=bundle.getInt("image",R.drawable.book_no_name);
        title=bundle.getString("title");

        ImageView imageView=findViewById(R.id.book_image);
        Button button=findViewById(R.id.button_ok);
        EditText editText=findViewById(R.id.book_edit);
        TextView textView=findViewById(R.id.book_title);
        Button button1=findViewById(R.id.button_cancel);
        if(null!=title) {
            textView.setText(title);
            imageView.setImageResource(resourceid);
            editText.setText(title);
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditBookActivity.this.finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("title",editText.getText().toString());
                bundle.putInt("position",position);
                bundle.putInt("image",resourceid);

                intent.putExtras(bundle);
                setResult(RESULT_CODE_SUCCESS,intent);
                EditBookActivity.this.finish();
            }
        });
    }
}