package com.johnyhawkdesigns.a53_thetechnocafe_room.db.typeConverters;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import java.util.Date;




/*
    Saving non-primitive types using Type Converters
    In Room you can only save primitive types of the language. If you want to save a non-primitive type such as a List or Date ,
    you would have to provide a type converter for it. With TypeConverter you tell Room how to convert the non-primitive type to a primate type.
    We will add a createdDate field of type Date in our Contact mode.
    In our case we will provide type converter for Date, we will convert Date to long . When writing a type converter, you will have to
    provide conversion both ways, i.e. in our case one converter that converts  Date  to long  and another that converts long to Date .
    Writing type converter is easy, all you have to do is write methods with appropriate return type, argument and annotate them with  @TypeConverter .
 */
public class DateTypeConverter {

    private static final String TAG = DateTypeConverter.class.getSimpleName();

    // Convert Date to long time
    @TypeConverter
    public long convertDateToLong(Date date){
        long longDate = date.getTime();
        Log.d(TAG, "convertDateToLong: longDate = " + longDate);
        return longDate;
    }

    // Convert long time to Date
    @TypeConverter
    public Date convertLongToDate(long time){
        Date dateFromLong = new Date(time);
        Log.d(TAG, "convertLongToDate: dateFromLong = " + dateFromLong);
        return dateFromLong;
    }

}
