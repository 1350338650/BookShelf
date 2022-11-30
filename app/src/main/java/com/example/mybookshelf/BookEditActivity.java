package com.example.mybookshelf;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class BookEditActivity extends AppCompatActivity {

    public static final int RESULT_CODE_OK = 666;
    public static final String String_book_edit = "编辑书籍";
    private int resourceid;
    private String title;
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText publisherEditText;
    private EditText pubyearEditText;
    private EditText pubmonthEditText;
    private EditText isbnEditText;
    private ImageView coverImageView;
    private Uri picUri;
    private ActivityResultLauncher<Intent> mResultLaucher;
    private Uri picUri1;
    private String title1;
    private String author;
    private String publisher;
    private int pubyear;
    private int pubmonth;
    private String isbn;
    private Button button_ok;
    private int position;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(String_book_edit) ;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        initspinner();
        init();
        Bundle bundle=this.getIntent().getExtras();

        title = bundle.getString("title");

        if(title!=null){
            position = bundle.getInt("position",0);
//**            picUri1 = Uri.parse(bundle.getString("imageUri"));
//**            Log.d("www", "bookactivity update: "+picUri1.toString());
            author = bundle.getString("author");
            publisher = bundle.getString("publisher");
            pubyear = bundle.getInt("pubyear");
            pubmonth = bundle.getInt("pubmonth");
            isbn = bundle.getString("isbn");
//**            coverImageView.setImageURI(picUri1);
            coverImageView.setImageResource(R.drawable.add2);
            titleEditText.setText(title);
            authorEditText.setText(author);
            publisherEditText.setText(publisher);
            pubyearEditText.setText(String.valueOf(pubyear));
            pubmonthEditText.setText(String.valueOf(pubmonth));
            isbnEditText.setText(isbn);
        }
//        Toolbar toolbar =findViewById(R.id.bookedit_toolbar);


        mResultLaucher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    Intent intent = result.getData();
                    //选中图片的路径对象
                    picUri=intent.getData();

//                    String[] images={MediaStore.Images.Media.DATA};
//                    Cursor cursor=getContentResolver().query(picUri,images,null,null,null);
//                    int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    cursor.moveToFirst();
//                    String img_uri=cursor.getString(index);
//                    bitmap = null;
//                    try {
//                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(picUri));
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    coverImageView.setImageBitmap(bitmap);

                    if(picUri!=null){
                        coverImageView.setImageURI(picUri);
                        Log.d("ning", "picUri: "+picUri.toString());
                    }
                }
            }
        });
//        toolbar.setTitle("编辑书籍");
//        toolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(BookEditActivity.this, "dianjile", Toast.LENGTH_SHORT).show();
//            }
//        });
        coverImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");//打开系统相册

                mResultLaucher.launch(intent);
            }
        });
//        button_ok = findViewById(R.id.button_save_ok);
//        button_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
////                intent.addFlags(Intent.FLAG.ACTION_OPEN_DOCUMENT);
//
//                Bundle bundle=new Bundle();
//                bundle.putString("title",titleEditText.getText().toString());
//                bundle.putString("author",authorEditText.getText().toString());
//                bundle.putString("publisher",publisherEditText.getText().toString());
//                bundle.putInt("position",position);
//                bundle.putInt("pubyear",Integer.parseInt(pubyearEditText.getText().toString()));
//                bundle.putInt("pubmonth",Integer.parseInt(pubmonthEditText.getText().toString()));
////                bundle.putString("pubyear",pubyearEditText.getText().toString());
////                bundle.putString("pubmonth",pubmonthEditText.getText().toString());
//                bundle.putString("isbn",isbnEditText.getText().toString());
//
//                bundle.putString("imageUri",picUri.toString());
////                intent.putExtra("image",bitmap);
//
//                Log.d("www", "bookactivity add: "+picUri.toString());
////                intent.putExtra("imageUri",picUri.toString());
//
//
//                bundle.putParcelable("image",bitmap);
//                intent.putExtras(bundle);
////                intent.putPa
//                intent.setType("image/*");
//                setResult(RESULT_CODE_OK,intent);
////                bundle.putInt("resourceid",);
//                Toast.makeText(BookEditActivity.this, "save successed", Toast.LENGTH_SHORT).show();
//                BookEditActivity.this.finish();
//
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.book_add_save:
                Intent intent=new Intent();
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                intent.addFlags(Intent.FLAG.ACTION_OPEN_DOCUMENT);

                Bundle bundle=new Bundle();
                bundle.putString("title",titleEditText.getText().toString());
                bundle.putString("author",authorEditText.getText().toString());
                bundle.putString("publisher",publisherEditText.getText().toString());
                bundle.putInt("position",position);
                bundle.putInt("pubyear",Integer.parseInt(pubyearEditText.getText().toString()));
                bundle.putInt("pubmonth",Integer.parseInt(pubmonthEditText.getText().toString()));
//                bundle.putString("pubyear",pubyearEditText.getText().toString());
//                bundle.putString("pubmonth",pubmonthEditText.getText().toString());
                bundle.putString("isbn",isbnEditText.getText().toString());

                bundle.putString("imageUri",picUri.toString());
//                intent.putExtra("image",bitmap);

                Log.d("www", "bookactivity add: "+picUri.toString());
//                intent.putExtra("imageUri",picUri.toString());


                bundle.putParcelable("image",bitmap);
                intent.putExtras(bundle);
//                intent.putPa
                intent.setType("image/*");
                setResult(RESULT_CODE_OK,intent);
//                bundle.putInt("resourceid",);
                Toast.makeText(BookEditActivity.this, "save successed", Toast.LENGTH_SHORT).show();
                BookEditActivity.this.finish();
                return true;
            case android.R.id.home:
                BookEditActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.book_edit_menu,menu);
        return true ;
    }


    public void init(){
        coverImageView = findViewById(R.id.book_cover_image_view);
        titleEditText = (EditText) findViewById(R.id.book_title_edit_text);
        authorEditText = (EditText) findViewById(R.id.book_author_edit_text);
        publisherEditText = (EditText) findViewById(R.id.book_publisher_edit_text);
        pubyearEditText = (EditText) findViewById(R.id.book_pubyear_edit_text);
        pubmonthEditText = (EditText) findViewById(R.id.book_pubmonth_edit_text);
        isbnEditText = (EditText) findViewById(R.id.book_isbn_edit_text);

    }
    public void initspinner(){
        final String[] spinnerItems1 = {"未读","阅读中","已读"};
        final String[] spinnerItems2 = {"默认书架","添加新书架"};
        Spinner spinner1=(Spinner)findViewById(R.id.reading_status_spinner);
        Spinner spinner2=(Spinner)findViewById(R.id.book_shelf_spinner) ;

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] array2 = getResources().getStringArray(R.array.bookshelf);
//                switch (position){
//                    case 0:
//                        spinner1.setPrompt("默认书架");
//                        break;
//                    case 1:
//                        spinner1.setPrompt("添加新书架");
//                        break;
//
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        spinner1.setPrompt("");
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] array = getResources().getStringArray(R.array.languages);
//                switch (position){
//                    case 0:
//                        spinner1.setPrompt("未读");
//                        break;
//                    case 1:
//                        spinner1.setPrompt("阅读中");
//                        break;
//                    case 2:
//                        spinner1.setPrompt("已读");
//                        break;
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}