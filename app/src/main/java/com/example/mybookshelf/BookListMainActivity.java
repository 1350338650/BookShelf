package com.example.mybookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybookshelf.data.Book;

import java.util.ArrayList;

public class BookListMainActivity extends AppCompatActivity {

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

        BookAdapter adapter=new BookAdapter(books);
        recyclerView.setAdapter(adapter);

    }

    public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
        private ArrayList<Book> localDataSet;

        public BookAdapter(ArrayList<Book> books) {
            localDataSet=books;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

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