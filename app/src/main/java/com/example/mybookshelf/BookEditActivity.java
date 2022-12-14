package com.example.mybookshelf;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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

//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    private int pubyear=2020;
    private int pubmonth=9;
    private String isbn;
    private Button button_ok;
    private int position;
    private Bitmap bitmap;
    private EditText labelEditText;
    public ArrayList<String> labels=new ArrayList<>();
    private String booklabel;
    private int readingstatus;
    private String  note;
    private EditText noteEditText;
    private Spinner spinner1;
    private Spinner spinner2;
    public int isuri=0;

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
        labels=getIntent().getStringArrayListExtra("label");
        if(title!=null){
            position = bundle.getInt("position",0);
//**            picUri1 = Uri.parse(bundle.getString("imageUri"));
//**            Log.d("www", "bookactivity update: "+picUri1.toString());

            author = bundle.getString("author");
            publisher = bundle.getString("publisher");
            pubyear = bundle.getInt("pubyear",2020);
            pubmonth = bundle.getInt("pubmonth",9);
            isbn = bundle.getString("isbn");
            booklabel=bundle.getString("booklabel");
            note=bundle.getString("note");

            readingstatus=bundle.getInt("readingstatus",0);
//**            coverImageView.setImageURI(picUri1);
            coverImageView.setImageResource(R.drawable.add2);
            titleEditText.setText(title);
            authorEditText.setText(author);
            publisherEditText.setText(publisher);
            pubyearEditText.setText(String.valueOf(pubyear));
            pubmonthEditText.setText(String.valueOf(pubmonth));
            isbnEditText.setText(isbn);
            labelEditText.setText(booklabel);
            noteEditText.setText(note);
            spinner1.setSelection(readingstatus);

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
                        isuri=1;
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
                intent.putStringArrayListExtra("label",labels);
                intent.addCategory(Intent.ACTION_OPEN_DOCUMENT);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Bundle bundle=new Bundle();
                bundle.putString("title",titleEditText.getText().toString());
                bundle.putString("author",authorEditText.getText().toString());
                bundle.putString("publisher",publisherEditText.getText().toString());
                bundle.putString("note",noteEditText.getText().toString());
                bundle.putInt("readingstatus",readingstatus);
                bundle.putInt("position",position);
                int pubyear=pubyearEditText.getText().toString().isEmpty()?2020:Integer.parseInt(pubyearEditText.getText().toString());
                int pubmonth=pubmonthEditText.getText().toString().isEmpty()?9:Integer.parseInt(pubmonthEditText.getText().toString());

//                bundle.putInt("pubyear",Integer.parseInt(pubyearEditText.getText().toString()));
                bundle.putInt("pubyear",pubyear);
                bundle.putInt("pubmonth",pubmonth);
//                bundle.putInt("pubmonth",Integer.parseInt(pubmonthEditText.getText().toString()));
//                bundle.putString("pubyear",pubyearEditText.getText().toString());
//                bundle.putString("pubmonth",pubmonthEditText.getText().toString());
                bundle.putString("isbn",isbnEditText.getText().toString());
                bundle.putString("booklabel",booklabel);

                if(isuri==1) {
                    bundle.putString("imageUri", picUri.toString());
                    Log.d("www", "bookactivity add: "+picUri.toString());
                }
                bundle.putInt("isuri",isuri);
//                intent.putExtra("image",bitmap);


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
            case R.id.book_scanning:
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
//                    return true;
//                }
//                //申请文件(相册)读写权限
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//                    return true;
//                }
                //二维码扫码
                //然后通过Intent机制启动zxing框架的CaptureActivity，请求返回结果
                Intent intent2 = new Intent(BookEditActivity.this, CaptureActivity.class);
                startActivityForResult(intent2, 1111);

//                Intent intent2 = new Intent(this, CaptureActivity.class);
//                startActivityForResult(intent2, 100);

//                Intent intent2 = new Intent(this, BookEditActivity.class);
//                startActivityForResult(intent2,100);
//                IntentIntegrator intentIntegrator = new IntentIntegrator(BookEditActivity.this);
//                // 开始扫描
//                intentIntegrator.initiateScan();
//                ScanCodeConfig.create(BookEditActivity.this)
//                        //设置扫码页样式 ScanStyle.NONE：无  ScanStyle.QQ ：仿QQ样式   ScanStyle.WECHAT ：仿微信样式    ScanStyle.CUSTOMIZE ： 自定义样式
//                        .setStyle( ScanStyle.WECHAT)
//                        //扫码成功是否播放音效  true ： 播放   false ： 不播放
//                        .setPlayAudio(false)
//                        .buidler()
//                        //跳转扫码页   扫码页可自定义样式
//                        .start(ScanCodeActivity.class);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111) {
            AlertDialog.Builder builder = new AlertDialog.Builder(BookEditActivity.this);
            builder.setTitle("无法获取详情");
            builder.setMessage("无法获取图书详情，请选择手动加入");
            builder.setCancelable(true);
            //设置正面按钮
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    Intent intent=new Intent(,BookEditActivity.class);
//                    startActivity(intent);
//                    CaptureActivity.finish();
                    dialog.dismiss();
                }
            });
            //设置反面按钮
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();

            //显示对话框
            dialog.show();



//            if (data != null) {
//
//                String content = data.getStringExtra(Constant.CODED_CONTENT);
//                Log.i("扫描结果为:", content);
//
//                scanFragment.onActivityResult(requestCode, resultCode, data);
//
//            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.book_edit_menu,menu);
        return true ;
    }


    private int checkedItem = 0;
    public void showdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择标签：");
//        labelEditText.setText("booklabel");
        List<String> selectedlables = new ArrayList<>();
        String[] labelsString = (String[]) labels.toArray(new String[0]);
        builder.setSingleChoiceItems(labelsString, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("wwww", "onClick: "+which);
//                checkedItem = which;
                booklabel = labels.get(which);
//                Log.d("wwww", "onClick: "+booklabel);
                labelEditText.setText(booklabel);
//                labelEditText.setText("123");

            }
        });
        //设置正面按钮
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                Toast.makeText(BookEditActivity.this, "确认"+booklabel, Toast.LENGTH_SHORT).show();
//                labelEditText=findViewById(R.id.book_labels_edit_text);
//                labelEditText.setText(booklabel);
            }
        });

        builder.setNeutralButton("添加标签", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(BookEditActivity.this, "添加标签2", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(BookEditActivity.this);
                final AlertDialog dialog2 = builder.create();
                View dialogView = View.inflate(BookEditActivity.this, R.layout.labels_adddialog, null);
                //设置对话框布局
                dialog2.setView(dialogView);
                dialog2.show();
                EditText labelName = (EditText) dialogView.findViewById(R.id.label_name);
                Button btnLogin = (Button) dialogView.findViewById(R.id.btn_login);
                Button btnCancel = (Button) dialogView.findViewById(R.id.btn_cancel);
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newlabelname = labelName.getText().toString();
                        labels.add(newlabelname);
                        dialog2.dismiss();
                        showdialog();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    public void init(){
        coverImageView = findViewById(R.id.book_cover_image_view);
        titleEditText = (EditText) findViewById(R.id.book_title_edit_text);
        authorEditText = (EditText) findViewById(R.id.book_author_edit_text);
        publisherEditText = (EditText) findViewById(R.id.book_publisher_edit_text);
        pubyearEditText = (EditText) findViewById(R.id.book_pubyear_edit_text);
        pubmonthEditText = (EditText) findViewById(R.id.book_pubmonth_edit_text);
        isbnEditText = (EditText) findViewById(R.id.book_isbn_edit_text);
        noteEditText = (EditText)findViewById(R.id.book_notes_edit_text);
        labelEditText = findViewById(R.id.book_labels_edit_text);
        labelEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });
    }
    public void initspinner(){
        final String[] spinnerItems1 = {"未读","阅读中","已读"};
        final String[] spinnerItems2 = {"默认书架","添加新书架"};
        spinner1 = (Spinner)findViewById(R.id.reading_status_spinner);
        spinner2 = (Spinner)findViewById(R.id.book_shelf_spinner);

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
//                String[] array = getResources().getStringArray(R.array.languages);
                Log.d("wwww", "onItemSelected: "+position);
                readingstatus = position;
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