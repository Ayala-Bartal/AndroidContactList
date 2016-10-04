package com.example.or.contactlistmiddleproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Or on 11/08/2016.
 */
public class Person implements Serializable{

    private String fullName;
    private String telNum;
    private String adrress;
    private String email;
    private String birth_day;
    transient private Bitmap pic;

    private void writeObject (ObjectOutputStream oos) throws IOException {
        if (pic != null) {
            pic.compress(Bitmap.CompressFormat.JPEG, 70, oos);
        }
        oos.defaultWriteObject();
    }

    private void readObject (ObjectInputStream ois) throws IOException, ClassNotFoundException{
        pic = BitmapFactory.decodeStream(ois);
        ois.defaultReadObject();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getAdrress() {
        return adrress;
    }

    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }


}
