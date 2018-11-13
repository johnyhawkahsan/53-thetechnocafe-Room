package com.johnyhawkdesigns.a53_thetechnocafe_room;

import android.arch.persistence.room.Room;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.johnyhawkdesigns.a53_thetechnocafe_room.db.AppDatabase;
import com.johnyhawkdesigns.a53_thetechnocafe_room.db.ContactDAO;
import com.johnyhawkdesigns.a53_thetechnocafe_room.models.Contact;

import java.util.Date;

public class CreateContactActivity extends AppCompatActivity {

    private static final String TAG = CreateContactActivity.class.getSimpleName();
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneNumberEditText;
    private Button mSaveButton;
    private ContactDAO mContactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        getSupportActionBar().setTitle("Create new Contact");

        // Get instance of DAO from Database class
        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread - This is not recommended
                .build()
                .getContactDAO();

        mFirstNameEditText = findViewById(R.id.firstNameEditText);
        mLastNameEditText = findViewById(R.id.lastNameEditText);
        mPhoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        mSaveButton = findViewById(R.id.saveButton);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();
                String phoneNumber = mPhoneNumberEditText.getText().toString();

                if (firstName.length() == 0 || lastName.length() == 0 || phoneNumber.length() == 0) {
                    Toast.makeText(CreateContactActivity.this, "Please make sure all details are correct", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create new object from entered text
                Contact contact = new Contact();
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setPhoneNumber(phoneNumber);
                contact.setCreatedDate(new Date());

                //Insert to database
                try {
                    mContactDAO.insert(contact);
                    Log.d(TAG, "onClick: inserting new contact = " + contact.getFirstName() + " " + contact.getLastName() + ", phone no = " + contact.getPhoneNumber());
                    Toast.makeText(CreateContactActivity.this, "Adding new contact = " + contact.getFirstName() + " " + contact.getLastName() + ", phone no = " + contact.getPhoneNumber() , Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK);
                    finish();
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(CreateContactActivity.this, "A cotnact with same phone number already exists.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
