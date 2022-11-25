package com.example.mybookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybookshelf.data.Book;

import java.util.ArrayList;

public class BookListMainActivity extends AppCompatActivity {

    private BookAdapter adapter;

    public ArrayList<Book> getBooks() {
        return books;
    }

    private ArrayList<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_main);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycle_view_books);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        books=new ArrayList<>();
        books.add(new Book("信息安全数学基础(第2版)",R.drawable.book_1));
        books.add(new Book("软件项目管理案例教程",R.drawable.book_2));

        adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                books.add(item.getOrder(),new Book("创新工程实践",R.drawable.book_no_name));
                adapter.notifyItemInserted(item.getOrder());
                break;
            case 2:
                books.get(item.getOrder()).setTitle("updated");
                adapter.notifyItemChanged(item.getOrder());
                break;
            case 3:
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

            public void setTextView(TextView textView) {
                this.textView = textView;
            }

            public void setImageView(ImageView imageView) {
                this.imageView = imageView;
            }

            public TextView getTextView() {
                return textView;
            }

            public ImageView getImageView() {
                return imageView;
            }

            private  TextView textView;
            private  ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view_book_cover);
                textView = itemView.findViewById(R.id.text_view_book_title);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.add(0,1,getAdapterPosition(),"Add"+getAdapterPosition());
                menu.add(0,2,getAdapterPosition(),"Update"+getAdapterPosition());
                menu.add(0,3,getAdapterPosition(),"Delete"+getAdapterPosition());

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
            holder.getImageView().setImageResource(localDataSet.get(position).getResourceid());
            holder.getTextView().setText(localDataSet.get(position).getTitle());

        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }


    }


}