package com.example.dateandtimepicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();

    String time, date, time1, date1;
    EditText datepick1, timepick1, datepick2, timepick2;
    Button btn_one, btn_two, btn_compare;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datepick1 = findViewById(R.id.datepick1);
        timepick1 = findViewById(R.id.timepick1);

        datepick2 = findViewById(R.id.datepick2);
        timepick2 = findViewById(R.id.timepick2);

        //btn_one=findViewById(R.id.button1);
        // btn_two=findViewById(R.id.button2);
        btn_compare = findViewById(R.id.compare);
        result=findViewById(R.id.result);

        datepick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, day);
//                        Toast.makeText(MainActivity.this, String.valueOf(day), Toast.LENGTH_SHORT).show();
                        setdate1();

                    }
                };
                new DatePickerDialog(MainActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        timepick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        myCalendar.set(Calendar.HOUR_OF_DAY, hour);
                        myCalendar.set(Calendar.MINUTE, minute);
                        settime1();
                    }
                };
                new TimePickerDialog(MainActivity.this, time,
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE),
                        false).show();

            }
        });

        datepick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, day);
                        setdate2();
                    }
                };
                new DatePickerDialog(MainActivity.this, date1,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        timepick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        myCalendar.set(Calendar.HOUR_OF_DAY, hour);
                        myCalendar.set(Calendar.MINUTE, minute);
                        settime2();
                    }
                };
                new TimePickerDialog(MainActivity.this, time,
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE),
                        false).show();
            }
        });

        btn_compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // take a format of date and time.
                    SimpleDateFormat sdf = new SimpleDateFormat("dd LLLL yyyy");
                    SimpleDateFormat sdfTime = new SimpleDateFormat("KK : mm aaa");

                    //get a date and time in a string
                    String date_pick1 = datepick1.getText().toString();
                    String date_pick2 = datepick2.getText().toString();

                    String time_pick1 = timepick1.getText().toString();
                    String time_pick2 = timepick2.getText().toString();

                    //parsing string date and time to a Date class
                    Date date1 = sdf.parse(date_pick1);
                    Date date2 = sdf.parse(date_pick2);

                    Date time1 = sdfTime.parse(time_pick1);
                    Date time2 = sdfTime.parse(time_pick2);

                  //Comparing date and time with another set date and time :
                    if (date1.before(date2) && time1.before(time2)) {
                      result.setText(" Result : \n Date 1 is before Date 2 ");
                    } else if (date1.before(date2) && time2.before(time1)) {
                        result.setText(" Result : \n Date 1 is before Date 2 ");
                    } else if (date2.before(date1) && time2.before(time1)) {
                        result.setText(" Result : \n Date 2 is before Date 1 ");
                    }
                    else if(date2.before(date1) && time1.before(time2))
                    {
                        result.setText(" Result : \n Date 2 is before date 1 ");
                    }
                    else if(date1.equals(date2) && time1.before(time2))
                    {
                        result.setText(" Result : \n Dates are equal but Time 1 is before Time 2 ");
                    }
                    else if(date1.equals(date2) && time2.before(time1))
                    {
                        result.setText(" Result : \n Dates are equals but Time 2 is before Time 1 ");
                    }
                    else if(time1.equals(time2) && date1.before(date2))
                    {
                        result.setText(" Result : \n Time are equal but Date 1 is before date 2 ");
                    }
                    else if(time1.equals(time2) && date2.before(date1))
                    {
                        result.setText(" Result : \n Time are equal but Date 2 is before date 1 ");
                    }
                    else if(time1.equals(time2) && date2.equals(date1))
                    {
                        result.setText(" Result : \n Both are same ");
                    }

                    else {
                        result.setText("kk");
                    }

//                  if(date1.|| date2.equals(""))
//                  {
//
//                  }
//                  else if(date1.equals(date2))
//                  {
//                      Toast.makeText(MainActivity.this, "Same", Toast.LENGTH_SHORT).show();
//                  }
//                  else if(date1.before(date2))
//                  {
//                      Toast.makeText(MainActivity.this, "First date is before a second date", Toast.LENGTH_SHORT).show();
//                  }
//                  else
//                  {
//                      Toast.makeText(MainActivity.this, "Second date is before a First date", Toast.LENGTH_SHORT).show();
//                  }
//
//                  if(time1.before(time2))
//                  {
//                      Toast.makeText(MainActivity.this, "Time 1 is near", Toast.LENGTH_SHORT).show();
//                  }
//                  else
//                  {
//                      Toast.makeText(MainActivity.this, "Time 2 is near", Toast.LENGTH_SHORT).show();
//                  }
                } catch (ParseException e1) {
                    //Toast.makeText(MainActivity.this, "Please add date", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Messege")
                            .setPositiveButton("ok", null)
                            .setCancelable(false)
                            .setMessage( "All Fields are Required*")
                            .show();
                }

            }
        });
    }

    private void settime1() {
        String timeformat = "KK : mm aaa";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeformat, Locale.US);
        timepick1.setText(simpleDateFormat.format(myCalendar.getTime()));
    }

    private void settime2() {
        String timeformat = "KK : mm aaa";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeformat, Locale.US);
        timepick2.setText(simpleDateFormat.format(myCalendar.getTime()));
    }

    private void setdate1() {
        String dateformat = "dd LLLL yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateformat, Locale.US);
        datepick1.setText(simpleDateFormat.format(myCalendar.getTime()));
    }

    private void setdate2() {
        String dateformat = "dd LLLL yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateformat, Locale.US);
        datepick2.setText(simpleDateFormat.format(myCalendar.getTime()));

    }



}

