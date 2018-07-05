package com.mycustody.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mycustody.Activity.ScanQRCodeTripActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SessionManagementUtil {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_QR_TRIP = "QRTrip";

    // Constructor
    public SessionManagementUtil(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String qRTrip){

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_QR_TRIP,qRTrip);
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
//    public void checkLogin() {
//        // Check login status
//        if(this.isLoggedIn() == false){
//            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(_context, LoginActivity.class);
//            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            // Staring Login Activity
//            _context.startActivity(i);
//        }else{
//
//        }
//
//    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_QR_TRIP, pref.getString(KEY_QR_TRIP, null));
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, ScanQRCodeTripActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn() {

        return pref.getBoolean(IS_LOGIN, false);
    }


}
