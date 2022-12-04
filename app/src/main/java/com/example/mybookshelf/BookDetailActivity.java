package com.example.mybookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetailActivity extends AppCompatActivity {
    private int resourceid;
    private String title;
    private Uri picUri;
    private String author;
    private String publisher;
    private String pubtime;
    private String isbn;
    private int position;
    private Bitmap bitmap;
    private String booklabel;
    private int readingstatus;
    private String  note;
    private TextView titleText;
    private ImageView coverImageView;
    private TextView publisherText;
    private TextView authorText;
    private TextView pubdtimeText;
    private TextView isbneText;
    private TextView readingstatusText;
    private TextView noteText;
    private TextView labelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
//        ActionBar actionBar=getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        // 去掉logo图标
//        actionBar.setDisplayShowHomeEnabled(false);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("书籍详情") ;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        init();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        title = bundle.getString("title");
        String[]status=new String[]{"未读","阅读中","已读"};
        if(title!=null){
            author = bundle.getString("author");
            publisher = bundle.getString("publisher");
            pubtime=bundle.getString("pubtime");
            isbn = bundle.getString("isbn");
            booklabel=bundle.getString("booklabel");
            note=bundle.getString("note");
            readingstatus=bundle.getInt("readingstatus",0);
//**            picUri=Uri.parse(bundle.getString("image"));
//**            coverImageView.setImageURI(picUri);
//            coverImageView.setImageResource(R.drawable.add2);
            titleText.setText(title);
            authorText.setText(author);
            publisherText.setText(publisher);
            pubdtimeText.setText(pubtime);
            isbneText.setText(isbn);
            labelText.setText(booklabel);
            noteText.setText(note);
            readingstatusText.setText(status[readingstatus]);
//**            coverImageView.setImageURI(picUri);


        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                BookDetailActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void init(){
        titleText = findViewById(R.id.book_detail_name);
        coverImageView = findViewById(R.id.book_detail_image);
        publisherText = findViewById(R.id.book_info_publisher_text_view);
        authorText = findViewById(R.id.book_info_author_text_view);
        pubdtimeText = findViewById(R.id.book_info_pubtime_text_view);
        isbneText = findViewById(R.id.book_info_isbn_text_view);
        readingstatusText = findViewById(R.id.book_detail_reading_status_text_view);
        noteText = findViewById(R.id.book_detail_labels_text_view);
        labelText = findViewById(R.id.book_detail_website_text_view);

    }
}