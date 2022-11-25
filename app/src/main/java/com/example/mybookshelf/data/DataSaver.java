package com.example.mybookshelf.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataSaver  {
    public void save(Context context, ArrayList<Book> data){
        FileOutputStream outStream= null;
        try {
            outStream = context.openFileOutput("bookdata.dat", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(outStream);
            out.writeObject(data);
            out.close();
            outStream.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @NonNull
    public ArrayList<Book> load(Context context) {
        ArrayList<Book> data = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput("bookdata.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = ( ArrayList<Book>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
        }
    }
