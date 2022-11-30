package com.example.mybookshelf;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import com.example.mybookshelf.data.Book;
import com.example.mybookshelf.data.DataSaver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class BookListMainActivity extends AppCompatActivity {

    public static final int MENU_ADD = 1;
    public static final int MENU_UPDATE = 2;
    public static final int MENU_DELETE = 3;
    private BookAdapter adapter;
    private ArrayList<Book> books;
    private Uri picUri;
    private String title;
    private String author;
    private String publisher;
    private int pubyear;
    private int pubmonth;
    private String isbn;
    private String main_title="所有(1)";
    private View bookself_num;
//    private ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->  {
//        if(null!=result){
//            Intent intent=result.getData();
//            if(null!=result&&result.getResultCode()==EditBookActivity.RESULT_CODE_SUCCESS){
//                Bundle bundle=intent.getExtras();
//                String title=bundle.getString("title");
//                int position=bundle.getInt("position");
//                int resourceid=bundle.getInt("image");
//
//                books.get(position).setTitle(title);
//                books.get(position).setResourceid(resourceid);
//                new DataSaver().save(BookListMainActivity.this,books);
//                adapter.notifyItemChanged(position);
//            }
//        }
//    });
private ActivityResultLauncher<Intent> updateResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->  {
    if(null!=result){
        Intent intent=result.getData();
        if(null!=result&&result.getResultCode()==BookEditActivity.RESULT_CODE_OK){
            Bundle bundle=intent.getExtras();
            int position=bundle.getInt("position");
            picUri = Uri.parse(bundle.getString("imageUri"));
            Log.d("www", "booklistactivity update: "+picUri.toString());
            title = bundle.getString("title");
            author = bundle.getString("author");
            publisher = bundle.getString("publisher");
            pubyear = bundle.getInt("pubyear");
            pubmonth = bundle.getInt("pubmonth");
            isbn = bundle.getString("isbn");

            books.get(position).setTitle(title);
            books.get(position).setUri(picUri);
            books.get(position).setAuthor(author);
            books.get(position).setPublisher(publisher);
            books.get(position).setPutyear(pubyear);
            books.get(position).setPutmonth(pubmonth);
            books.get(position).setIsbn(isbn);

            new DataSaver().save(BookListMainActivity.this,books);
            adapter.notifyItemChanged(position);
        }
    }
});

    private Bitmap bitmap;
    //    private ActivityResultLauncher<Intent> addResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->  {
//        if(null!=result){
//            Intent intent=result.getData();
//            if(null!=result&&result.getResultCode()==EditBookActivity.RESULT_CODE_SUCCESS){
//                Bundle bundle=intent.getExtras();
//                String title=bundle.getString("title");
//                int position=bundle.getInt("position");
//                int resourceid=bundle.getInt("image");
//                books.add(position,new Book(title,resourceid));
//                new DataSaver().save(BookListMainActivity.this,books);
//                adapter.notifyItemInserted(position);
////                books.get(position).setTitle(title);
////                books.get(position).setResourceid(resourceid);
////                adapter.notifyItemChanged(position);
//            }
//        }
//    });
private ActivityResultLauncher<Intent> addResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->  {
    if(null!=result){
        Intent intent=result.getData();
        if(null!=result&&result.getResultCode()==BookEditActivity.RESULT_CODE_OK){
            Bundle bundle=intent.getExtras();

//            Uri picUri=intent.getData();
            picUri = Uri.parse(bundle.getString("imageUri"));
            bitmap = (Bitmap)bundle.getParcelable("image");
            title = bundle.getString("title");
            author = bundle.getString("author");
            publisher = bundle.getString("publisher");
            pubyear = bundle.getInt("pubyear");
            pubmonth = bundle.getInt("pubmonth");
            isbn = bundle.getString("isbn");

//            int position=bundle.getInt("position");
            int position=books.size();
//            int position=1;
//            int resourceid=bundle.getInt("image");
            int resourceid=R.drawable.book_1;
            Log.d("www", "booklistactivity add: "+picUri.toString());
            books.add(new Book(title, picUri, author, pubyear, pubmonth, publisher, isbn));
            new DataSaver().save(BookListMainActivity.this,books);
            adapter.notifyItemInserted(position);
//                books.get(position).setTitle(title);
//                books.get(position).setResourceid(resourceid);
//                adapter.notifyItemChanged(position);
        }
    }
});
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    public ArrayList<Book> getBooks() {
        return books;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_main);
        ActionBar supportActionBar = getSupportActionBar();
//        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
//        toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
//        toolbar.setTitle("Title");//设置主标题
//        toolbar.setSubtitle("Subtitle");//设置子标题
//
//        toolbar.inflateMenu(R.menu.book_list_menu);//设置右上角的填充菜单

//        supportActionBar.setDisplayShowHomeEnabled(false);
//        supportActionBar.setDisplayShowTitleEnabled(false);
//        supportActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_book:
//                        drawerLayout.openDrawer(GravityCompat.START,true);
//                        Toast.makeText(BookListMainActivity.this, "书籍", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        return true;
                    case R.id.nav_label:
//                        drawerLayout.openDrawer(GravityCompat.START,true);
                        Toast.makeText(BookListMainActivity.this, "标签", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_setting:
//                        drawerLayout.openDrawer(GravityCompat.START,true);
                        Toast.makeText(BookListMainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_search:
//                        drawerLayout.openDrawer(GravityCompat.START,true);
                        Toast.makeText(BookListMainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.nav_about:
//                        drawerLayout.openDrawer(GravityCompat.START,true);
                        Toast.makeText(BookListMainActivity.this, "关于", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
//        drawerLayout.openDrawer(GravityCompat.START);
       supportActionBar.setDisplayShowCustomEnabled(true);
       supportActionBar.setTitle("Mybookshelf");


        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycle_view_books);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        books=new ArrayList<>();
//        books.add(new Book("信息安全数学基础(第2版)",R.drawable.book_1));
//        books.add(new Book("软件项目管理案例教程",R.drawable.book_2));
        DataSaver dataSaver=new DataSaver();
//        dataSaver.save(this,books);
        books=dataSaver.load(this);
        Log.d("www", "books size: "+books.size());

        if(books.size()==0){
            books.add(new Book("信息安全数学基础(第2版)",R.drawable.book_1,"张三",2019,6,"人民邮电出版社","9886541236580"));
        }
        adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);

        FloatingActionButton floatingActionButton=findViewById(R.id.add_book_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),BookEditActivity.class);
                Bundle bundle=new Bundle();
//                bundle.putInt("position",item.getOrder());
                intent.putExtras(bundle);
                addResultLauncher.launch(intent);
            }
        });
    }
//drawerLayout.openDrawer(GravityCompat.START);
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.drawer_menu:
                drawerLayout.openDrawer(GravityCompat.START,true);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

   @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.book_list_menu,menu);
        return true ;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case MENU_UPDATE:
                Intent intentupdate=new Intent(this,BookEditActivity.class);
                Bundle bundleupdate=new Bundle();
                bundleupdate.putInt("position",item.getOrder());
                bundleupdate.putString("title",books.get(item.getOrder()).getTitle());
                bundleupdate.putString("author",books.get(item.getOrder()).getAuthor());
                bundleupdate.putString("publisher",books.get(item.getOrder()).getPublisher());
                bundleupdate.putInt("pubyear",books.get(item.getOrder()).getPutyear());
                bundleupdate.putInt("pubmonth",books.get(item.getOrder()).getPutmonth());
//                bundle.putString("pubyear",pubyearEditText.getText().toString());
//                bundle.putString("pubmonth",pubmonthEditText.getText().toString());
                bundleupdate.putString("isbn",books.get(item.getOrder()).getIsbn());
//***                bundleupdate.putString("imageUri",books.get(item.getOrder()).getUri().toString());

//                bundleupdate.putString("title",books.get(item.getOrder()).getTitle().toString());
//                bundleupdate.putInt("image",books.get(item.getOrder()).getResourceid());
                intentupdate.putExtras(bundleupdate);
                updateResultLauncher.launch(intentupdate);
//                books.get(item.getOrder()).setTitle("updated");
//                adapter.notifyItemChanged(item.getOrder());
                break;
            case MENU_DELETE:
                AlertDialog alertDialog=new AlertDialog.Builder(this).setTitle("Confirmation").setMessage("Are you sure to delete this book?").
                        setNegativeButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                books.remove(item.getOrder());
                                adapter.notifyItemRemoved(item.getOrder());
                            }
                        }).setPositiveButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alertDialog.show();


                break;
        }
        return super.onContextItemSelected(item);
    }

    public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
        private ArrayList<Book> localDataSet;

        public BookAdapter(ArrayList<Book> books) {
            localDataSet=books;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {






            public ImageView getImageView() {
                return imageView;
            }

            public void setImageView(ImageView imageView) {
                this.imageView = imageView;
            }

            public TextView getTitleview() {
                return titleview;
            }

            public void setTitleview(TextView titleview) {
                this.titleview = titleview;
            }

            public TextView getAuthorview() {
                return authorview;
            }

            public void setAuthorview(TextView authorview) {
                this.authorview = authorview;
            }

            public TextView getPublisherview() {
                return publisherview;
            }

            public void setPublisherview(TextView publisherview) {
                this.publisherview = publisherview;
            }

            public TextView getPubtimeview() {
                return pubtimeview;
            }

            public void setPubtimeview(TextView pubtimeview) {
                this.pubtimeview = pubtimeview;
            }
            private ImageView imageView;
            private TextView titleview;
            private TextView authorview;
            private TextView publisherview;
            private TextView pubtimeview;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view_book_cover);
                titleview = itemView.findViewById(R.id.text_view_book_title);
                authorview=itemView.findViewById(R.id.text_view_author);
                publisherview=itemView.findViewById(R.id.text_view_publisher);
                pubtimeview=itemView.findViewById(R.id.text_view_pubtime);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.add(0,2,getAdapterPosition(),"Update");
                menu.add(0,3,getAdapterPosition(),"Delete");

            }
        }
        @NonNull
        @Override
        public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
//            holder.getImageView().setImageResource(localDataSet.get(position).getResourceid());
            holder.getImageView().setImageURI(localDataSet.get(position).getUri());
//            holder.getImageView().setImageBitmap(localDataSet.get(position).getBitmap());
            holder.getTitleview().setText(localDataSet.get(position).getTitle());
            holder.getAuthorview().setText(localDataSet.get(position).getAuthor());
            holder.getPublisherview().setText(localDataSet.get(position).getPublisher());
            String pubtime=localDataSet.get(position).getPutyear()+"-"+localDataSet.get(position).getPutmonth();
            holder.getPubtimeview().setText(pubtime);

        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }


    }


}