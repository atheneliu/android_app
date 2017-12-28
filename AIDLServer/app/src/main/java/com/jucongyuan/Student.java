package com.jucongyuan;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private int result;
    private int count;


    public Student() {
    }

    public int getresult() {
        return result;
    }

    public void setresult(int result) {
        this.result = result;
    }

    public int getcount() {
        return count;
    }

    public void setcount(int count) {
        this.count = count;
    }

    protected Student(Parcel in) {
        result = in.readInt();
        count = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(result);
        dest.writeInt(count);
    }
}
