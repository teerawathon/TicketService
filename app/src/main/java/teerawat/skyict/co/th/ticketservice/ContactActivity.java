package teerawat.skyict.co.th.ticketservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import teerawat.skyict.co.th.ticketservice.fragment.ContactFragment;

public class ContactActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //        Create Toolbar
        createToolbar();

        //        Add fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContactFragment, new ContactFragment())
                    .commit();
        }


    }//Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //        Go TO Menu
        if (item.getItemId() == R.id.itemContactToDash) {

            //        Back To Menu
            startActivity(new Intent(ContactActivity.this, MenuActivity.class));

            //        Close App
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;

    }

    private void createToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbarContact);
        setSupportActionBar(toolbar);

//        Setup Title
        getSupportActionBar().setTitle("Contact");

    }

}//Main Class
