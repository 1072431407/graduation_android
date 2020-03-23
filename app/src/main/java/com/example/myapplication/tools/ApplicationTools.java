package com.example.myapplication.tools;

import android.graphics.drawable.Drawable;

public class ApplicationTools {
    private static Drawable headImage = null;

    public static void setHeadImage(Drawable headImage) {
        ApplicationTools.headImage = headImage;
    }

    public static Drawable getHeadImage() {
        return headImage;
    }

}
/**
 editor
 .putInt("userid",bundle.getInt("userid"))
 .putString("headimage",bundle.getString("headimage"))
 .putString("nickname",bundle.getString("nickname"))
 .putString("signature",bundle.getString("signature"))
 .putString("date",bundle.getString("date"))
 .putInt("minute",bundle.getInt("minute"))
 .putString("name",bundle.getString("name"))
 .putString("phone",bundle.getString("phone"))
 .putString("sex",bundle.getString("sex"))
 .putString("age",bundle.getString("age"))
 .putString("job",bundle.getString("job"))
 .putString("qqCode",bundle.getString("qqCode"))
 .putString("weChat",bundle.getString("weChat"))
 .putString("e_mail",bundle.getString("e_mail"))
 .putString("username",userString)
 .putString("password",passwordString)
 .putBoolean("autologin",true)
 .putBoolean("savepassword",true)
 .putBoolean("loginState",true)
 */