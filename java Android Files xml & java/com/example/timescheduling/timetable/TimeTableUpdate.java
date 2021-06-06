package com.example.timescheduling.timetable;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timescheduling.Constant;
import com.example.timescheduling.R;
import com.example.timescheduling.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TimeTableUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String timeFromParam="",timeToParam="",dateParam="",dayParam="",depParam,placeParam;
    String setDate,filter,setTimeTo;
    TextView t_v_date,t_v_time_from,t_v_time_to,temp;
    EditText e_t_batch,e_t_subject,message;
    Spinner dep_spinner,place_spinner;
    Button btn_update_notify,btn_notify;

    private static final String CHANNEL_ID="Notification_System";
    private static final String CHANNEL_NAME="Time Table Nottification";
    private static final String CHANNEL_DESC="Nottifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_update);


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        t_v_date=findViewById(R.id.textViewDate);
        t_v_time_from=findViewById(R.id.textViewTimeFrom);
        t_v_time_to=findViewById(R.id.textViewTimeTo);
        dep_spinner=findViewById(R.id.department_table_update);
        place_spinner=findViewById(R.id.place_table_update);
        btn_update_notify=findViewById(R.id.btn_table_update);
        e_t_batch=findViewById(R.id.editTextBatch);
        e_t_subject=findViewById(R.id.editTextSubject);
        message=findViewById(R.id.editTextTextMessage);
        temp=findViewById(R.id.textViewTemp);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dep_spinner.setAdapter(adapter);
        dep_spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.place,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        place_spinner.setAdapter(adapter2);
        place_spinner.setOnItemSelectedListener(this);

        final Notification notification=new Notification();

        t_v_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDate();
            }
        });
        t_v_time_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeFrom();
            }
        });
        t_v_time_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeTo();
            }
        });

        btn_update_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentDateTime= (String) DateFormat.format("yyyy-MM-dd HH.mm",Calendar.getInstance().getTime());
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(Calendar.getInstance().getTime());
                calendar.add(Calendar.MINUTE,45);
                String newCurrentDateTime= (String) DateFormat.format("yyyy-MM-dd HH.00",calendar.getTime());

                /*Calendar calendar1=Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                String getCurrentDate=simpleDateFormat.format(calendar1.getTime());
                String getMyDate=setDate;*/

                /***************Important******************
                 * CompareTo method must return
                 * negative number if current object is less than other object,
                 * positive number if current object is greater then other object and
                 * zero if both objects are equal to each other
                 */


                temp.setText(newCurrentDateTime);
                if ((newCurrentDateTime.compareTo(setDate+" "+timeFromParam)<0) && newCurrentDateTime.compareTo(setDate+" "+timeToParam)<0 ) {

                    final String batch=e_t_batch.getText().toString().trim();
                    final String subject=e_t_subject.getText().toString().trim();
                    temp.setText(dateParam+dayParam+"/"+timeFromParam+"/"+timeToParam+"/"+depParam+"/"+batch+"/"+subject+" "+placeParam);
                    //temp.setText((currentDateTime+1));

                    timeTableUpdate();
                }else {
                    Toast.makeText(getApplicationContext(),"Only you can add lecture earlier 45 minutes Start Lecture",Toast.LENGTH_LONG).show();

                }

            }
        });

        filter=SharedPrefManager.getInstance(this).getTable();
    }

    public void displayNotification(String title, String text){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_school_24)
                .setContentTitle("Lecture Updated")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());

    }

    private void handleDate(){
        Calendar calendar=Calendar.getInstance();

        final int YEAR=calendar.get(Calendar.YEAR);
        final int MONTH=calendar.get(Calendar.MONTH);
        //final int MONTH=10;
        //final int MONTH2=calendar.get(Calendar.MONTH);
        final int DATE=calendar.get(Calendar.DATE);
        final int DAY=calendar.get(Calendar.DAY_OF_WEEK);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int MONTH2=month+1;
                String dateString;
                if(MONTH2<10){
                    String monthString= 0+String.valueOf(MONTH2);
                    dateString=year+"-"+monthString+"-"+dayOfMonth;
                }else {
                    dateString=year+"-"+MONTH2+"-"+dayOfMonth;
                }
                //date.setText(dateString);

                //set the format of date
                Calendar calendar1= Calendar.getInstance();
                calendar1.set(Calendar.YEAR,year);
                calendar1.set(Calendar.MONTH,month);
                calendar1.set(Calendar.DATE,dayOfMonth);

                setDate= (String) DateFormat.format("yyyy-MM-dd",calendar1);
                CharSequence dateSequence = DateFormat.format("yyyy-MM-dd",calendar1); dateParam=(String) dateSequence;
                CharSequence daySequence = DateFormat.format("EEEE",calendar1); dayParam=(String)daySequence;
                CharSequence viewSequence = DateFormat.format("EEE,yyyy-MM-dd",calendar1);
                t_v_date.setText(viewSequence);
                t_v_date.setTextColor(Color.BLACK);
            }
        },YEAR,MONTH,DATE);
        datePickerDialog.show();
    }


    private void handleTimeFrom(){
        final Calendar calendar=Calendar.getInstance();
        final int HOUR=calendar.get(Calendar.HOUR);
        int MINUTE=calendar.get(Calendar.MINUTE);

        // To change the clock interface according to settings in phone
        boolean is24HourFormat= DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //Start of set the format of time
                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar1.set(Calendar.MINUTE,minute);

                CharSequence charSequence=DateFormat.format("hh:mm a",calendar1);
                CharSequence charSequenceParam=DateFormat.format("HH.00",calendar1);
                //End of set the format of time

                //settedTimeFrom=charSequenceParam;
                timeFromParam= (String) charSequenceParam;
                t_v_time_from.setText(charSequence);
                t_v_time_from.setTextColor(Color.BLACK);

                /*timeFromParam=timeFormater(hourOfDay,minute).substring(0,3)+"00";
                t_v_time_from.setText(timeFormater(hourOfDay,minute));
                t_v_time_from.setTextColor(Color.BLACK);*/
            }
        },HOUR,MINUTE,is24HourFormat);
        timePickerDialog.show();
    }
    private void handleTimeTo(){
        final Calendar calendar=Calendar.getInstance();
        final int HOUR=calendar.get(Calendar.HOUR_OF_DAY);
        final int MINUTE=calendar.get(Calendar.MINUTE);

        // To change the clock interface according to settings in phone
        boolean is24HourFormat= DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //Start of set the format of time
                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar1.set(Calendar.MINUTE,minute);

                CharSequence charSequence=DateFormat.format("hh:mm a",calendar1);
                CharSequence charSequenceParam=DateFormat.format("HH.00",calendar1);

                //End of set the format of time
                timeToParam= (String) charSequenceParam;
                t_v_time_to.setText(charSequence);
                t_v_time_to.setTextColor(Color.BLACK);

                String meridiem="";
                //timeToParam=timeFormater(hourOfDay,minute).substring(0,3)+"00";
                //t_v_time_to.setText(timeFormater(hourOfDay,minute));

            }
        },HOUR,MINUTE,is24HourFormat);
        timePickerDialog.show();
    }


    private String timeFormater(int hourOfDay,int minute){

        String t_v_time="";
        String M="";
        String H="";
        String meridiem="";

        if (minute<10){
            M="0"+minute;
        }else
            M= String.valueOf(minute);

        if (hourOfDay<12&hourOfDay!=0){
            meridiem="AM";
            if (hourOfDay<10){
                H="0"+hourOfDay;
                //t_v_time=H+"."+M+"  "+meridiem;
            }else {
                H= String.valueOf(hourOfDay);
                //t_v_time=H+"."+M+"  "+meridiem;
            }
            t_v_time=H+"."+M+"  "+meridiem;
        }
        else if (hourOfDay==0){
            meridiem="AM";
            H=String.valueOf(hourOfDay+12);
            t_v_time=H+"."+M+"  "+meridiem;
            /*hourOfDay+=12;
            t_v_time=hourOfDay+"."+M+"  "+meridiem;*/
        }
        else if(hourOfDay==12){
            meridiem="PM";
            H=String.valueOf(hourOfDay);
            t_v_time=H+"."+M+"  "+meridiem;
        }
        else if (hourOfDay>12){
            meridiem="PM";
            hourOfDay-=12;
            if (hourOfDay<10){
                H="0"+hourOfDay;
            }else {
                H = String.valueOf(hourOfDay);
            }
            t_v_time = H + "." + M + "  " + meridiem;
        }

        return t_v_time;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            depParam=dep_spinner.getSelectedItem().toString();
            placeParam=place_spinner.getSelectedItem().toString();

            String spin =parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), spin, Toast.LENGTH_SHORT).show();
        }
        if (position==0){
            ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.textGray));
            //((TextView) view).setText("");
            //spinner.setPrompt("jfhfh");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void messageSender(final String text, final String dateParam, final String depParam, final String batch){
        //

                    //progressDialog.setMessage("Registering User....");
                    //progressDialog.show();
            /** Request a string response from the provided URL */
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constant.URL_SEND_MESSAGE,

                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            //progressDialog.dismiss();
                            /*this is the JSONobject {"error":true,"message":"Invalid Requst"} to show this we create JSONobject
                             * in here "error" is the key & "true" is value. same message & Invalid request*/

                            /**If String response is not JSONObject its go to the catch*/
                            try {
                                //temp.setText(response);
                                //JSONObject jsonObject=new JSONObject("{\"error\":false,\"message\":\"Update Successful\"}");
                                JSONObject jsonObject=new JSONObject(response);

                                if(!jsonObject.getBoolean("error")){
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                                }else {
                                    Toast errorTost= Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG);

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Error Parsing Data",Toast.LENGTH_SHORT).show();
                                //Log.v("asd",e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //when use this "progressDialog.hide();" app was crashed
                            // progressDialog.hide();
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String,String> params=new HashMap<>();



                        params.put("message", text);
                        params.put("date", dateParam);
                        params.put("dep_batch", depParam +batch);
                        params.put("lecturer",filter);



                        return params;
                    }
                };

            /** Instantiate & Add the request to the request queue **/
                RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


                /** Instantiate the RequestQueue **/
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
             /** Add the request to the request queue **/
            //requestQueue.add(stringRequest);



        }



    private void timeTableUpdate(){
        final String shortname=t_v_date.getText().toString().trim();
        final String index_no=t_v_time_from.getText().toString().trim();
        final String email=t_v_time_to.getText().toString().trim();
        final String batch=e_t_batch.getText().toString().trim();
        final String subject=e_t_subject.getText().toString().trim();
        final String msg=message.getText().toString().trim();
        final String[] shortMsg = {""};
        String type="register";

        if(TextUtils.isEmpty(shortname) || TextUtils.isEmpty(index_no) || TextUtils.isEmpty(email) || TextUtils.isEmpty(batch) || TextUtils.isEmpty(subject)) {
            Toast.makeText(TimeTableUpdate.this, "some field are not set", Toast.LENGTH_SHORT).show();

        } else {

            //progressDialog.setMessage("Registering User....");
            //progressDialog.show();
            /** Request a string response from the provided URL */
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constant.URL_TABLE_UPDATE,
                    // "http://192.168.43.20/LectureSS/Android/LectureRegister.php",

                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            //progressDialog.dismiss();
                            /*this is the JSONobject {"error":true,"message":"Invalid Requst"} to show this we create JSONobject
                             * in here "error" is the key & "true" is value. same message & Invalid request*/

                            /**If String response is not JSONObject its go to the catch*/
                            try {
                                //temp.setText(response);
                                //JSONObject jsonObject=new JSONObject("{\"error\":false,\"message\":\"Update Successful\"}");
                                JSONObject jsonObject=new JSONObject(response);

                                if(!jsonObject.getBoolean("error")){

                                    String text="You have lecture on "+dayParam+" of "+dateParam+" From"+timeFromParam+" To "+timeToParam;
                                    displayNotification(placeParam,text);
                                    text=text+"\n"+msg;
                                    //shortMsg[0]=shortMsg[0] +"\n"+ msg;
                                    messageSender(text,dateParam,depParam,batch);


                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();


                                    /*startActivity(new Intent(getApplicationContext(), LectureLogin.class));
                                    finish();*/
                                }else {
                                    Toast errorTost= Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG);
                                    //Toast errorTost= Toast.makeText(getApplicationContext(), Html.fromHtml("<font color='#e3f2d2'"),Toast.LENGTH_LONG);
                                    View view=errorTost.getView();
                                    view.setBackgroundColor(Color.RED);
                                    errorTost.show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Error Parsing Data",Toast.LENGTH_SHORT).show();
                                //Log.v("asd",e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //when use this "progressDialog.hide();" app was crashed
                    // progressDialog.hide();
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params=new HashMap<>();

                    params.put("day",dayParam);
                    params.put("date",dateParam);
                    params.put("timeFrom",timeFromParam);
                    params.put("timeTo",timeToParam);
                    params.put("dep",depParam);
                    params.put("batch",batch);
                    params.put("sub",subject);
                    params.put("place",placeParam);
                    params.put("lecturer",filter);

                    return params;
                }
            };

            /** Instantiate & Add the request to the request queue **/
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


            /** Instantiate the RequestQueue **/
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            /** Add the request to the request queue **/
            //requestQueue.add(stringRequest);



        }


    }

}