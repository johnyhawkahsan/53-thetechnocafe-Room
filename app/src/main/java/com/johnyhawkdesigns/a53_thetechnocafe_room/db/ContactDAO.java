package com.johnyhawkdesigns.a53_thetechnocafe_room.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.johnyhawkdesigns.a53_thetechnocafe_room.models.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    public void insert(Contact... contacts);

    @Update
    public void update(Contact... contacts);

    @Delete
    public void delete(Contact contact);

    /*
        Use the @Query annotation and pass in the query you want to run. Since we want all the contacts in the table, we will set the return type to
        a list of contacts,  List<Contact> . Remember when you ran a query in SQLite it would return a Cursor object,
        then you would have to iterate over the cursor and fetch all the values while adding them to a list.
        Room does all of this behind the scenes, it will run the query, convert the cursor result to list and return the list to you. Pretty amazing!
    */
    @Query("SELECT * FROM contact")
    public List<Contact> getContacts();

    //You will pass the phone number in the method arguments and use it inside the query by prefixing the parameter name with  : (colon). The return type here is Contact  because we are looking for a single contact.
    @Query("SELECT * FROM contact WHERE phoneNumber = :number")
    public Contact getContactWithId(String number);

}
