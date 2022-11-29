package com.example.mybookshelf.data;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.List;

public class Book implements Serializable, Parcelable {
    protected Book(Parcel in) {
        title = in.readString();
        resourceid = in.readInt();
        author = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        putyear = in.readInt();
        putmonth = in.readInt();
        publisher = in.readString();
        isbn = in.readString();
        hasCover = in.readByte() != 0;
        readingStatus = in.readInt();
        uri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceid() {
        return resourceid;
    }

    public void setResourceid(int resourceid) {
        this.resourceid = resourceid;
    }

    public Book(String title, int resourceid, String author, int putyear, int putmonth, String publisher, String isbn) {
        this.title = title;
        this.resourceid = resourceid;
        this.author = author;
        this.putyear = putyear;
        this.putmonth = putmonth;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public Book(String title, int resourceid) {
        this.title = title;
        this.resourceid = resourceid;
    }

    public Book(String title,  Bitmap bitmap, String author,int putyear, int putmonth, String publisher, String isbn) {
        this.title = title;
        this.author = author;
        this.bitmap = bitmap;
        this.putyear = putyear;
        this.putmonth = putmonth;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public Book(String title, Uri uri, String author, int putyear, int putmonth, String publisher, String isbn) {
        this.title = title;
//        this.resourceid = resourceid;
        this.uri=uri;
        this.author = author;
        this.putyear = putyear;
        this.putmonth = putmonth;
        this.publisher = publisher;
        this.isbn = isbn;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    private String  title;
    private int resourceid;
    private String author;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private Bitmap bitmap;

    public int getPutyear() {
        return putyear;
    }

    public void setPutyear(int putyear) {
        this.putyear = putyear;
    }

    public int getPutmonth() {
        return putmonth;
    }

    public void setPutmonth(int putmonth) {
        this.putmonth = putmonth;
    }

    private int putyear;
    private int putmonth;
    private String publisher;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(int readingStatus) {
        this.readingStatus = readingStatus;
    }

    private String isbn;
    private boolean hasCover;
    private int readingStatus;

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    private Uri uri;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(resourceid);
        dest.writeString(author);
        dest.writeParcelable(bitmap, flags);
        dest.writeInt(putyear);
        dest.writeInt(putmonth);
        dest.writeString(publisher);
        dest.writeString(isbn);
        dest.writeByte((byte) (hasCover ? 1 : 0));
        dest.writeInt(readingStatus);
        dest.writeParcelable(uri, flags);
    }
}
