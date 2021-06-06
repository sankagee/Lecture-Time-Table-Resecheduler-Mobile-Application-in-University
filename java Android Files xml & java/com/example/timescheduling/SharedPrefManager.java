package com.example.timescheduling;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME="mysharedpfre12";
    private static final String KEY_USERNAME="username";
    private static final String KEY_TIME_TABLE="time_table";
    private static final String KEY_INDEX="index";
    private static final String KEY_DEP="department";
    private static final String KEY_USER="user";

    private SharedPrefManager(Context context) {
        ctx = context;
    }


        public static synchronized SharedPrefManager getInstance (Context context){
            if (instance == null) {
                instance = new SharedPrefManager(context);
            }
            return instance;
        }


    public boolean login(String username,String table,String user){
        SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPrefManager.edit();

        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_TIME_TABLE,table);
        editor.putString(KEY_USER,user);

        editor.apply();
        return true;
    }


        //remenber to add "dep" here
        public boolean lecLogin( String username,String dep){
            SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPrefManager.edit();

            editor.putString(KEY_USERNAME,username);
            editor.putString(KEY_DEP,dep);

            editor.apply();
            return true;
        }
    public boolean nonLogin( String username,String dep){
        SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPrefManager.edit();

        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_DEP,dep);

        editor.apply();
        return true;
    }

        public boolean stuLogin(String username,String table,String user){
            SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPrefManager.edit();

            editor.putString(KEY_USERNAME,username);
            editor.putString(KEY_TIME_TABLE,table);
            editor.putString(KEY_USER,user);

            editor.apply();
            return true;
        }

        public boolean isStudentLoggedIn(){
            SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            //if the user name is not available in the "sharedPreferences.getString(KEY_USERNAME,null)" it return null
            if (sharedPreferences.getString(KEY_USERNAME,null) != null){
                return true;
            }
            return false;
        }

        public boolean isLectureLoggedIn(){
            SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            //if the user name is not available in the "sharedPreferences.getString(KEY_USERNAME,null)" it return null
            if (sharedPreferences.getString(KEY_USERNAME,null) != null){
                return true;
            }
            return false;
        }



            public boolean logout(){
                SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                return true;
            }
            //to get the current user details to the profile activity
            public String getUsername(){
                SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                return sharedPrefManager.getString(KEY_USERNAME,null);
            }
            public int getIndex(){
            SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPrefManager.getInt(KEY_INDEX,-1);
            }
            public String getTable(){
                SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                return sharedPrefManager.getString(KEY_TIME_TABLE,null);
            }

            public String getUser(){
                SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                return sharedPrefManager.getString(KEY_USER,"user");
            }
            public String getDep(){
                SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                return sharedPrefManager.getString(KEY_DEP,null);
            }
            /*public int getUserIndex(){
                SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                return sharedPrefManager.getInt(KEY_INDEX,null);
            }*/

}
