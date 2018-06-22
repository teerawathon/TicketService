package teerawat.skyict.co.th.ticketservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import teerawat.skyict.co.th.ticketservice.fragment.MenuFragment;
import teerawat.skyict.co.th.ticketservice.utility.ConfirmDialog;

public class MenuActivity extends AppCompatActivity {

    private String displayNameString, userString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //        GetValue FromIntent
        getValueFromIntent();

        //        Create Toolbar
        createToolbar();

        //        Add fragment
        if (savedInstanceState == null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentDashboardFragment, new MenuFragment())
                    .commit();

        }

    } // Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //        For SignOut
        if (item.getItemId() == R.id.itemSignOut) {

            showConfirmDialog();

        }

        return super.onOptionsItemSelected(item);
    }

    private void showConfirmDialog() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        ConfirmDialog confirmDialog = ConfirmDialog.newInstance("Are you want Logout?"
                , "NO", "YES");
        confirmDialog.show(fragmentManager, null);
        confirmDialog.setOnFinishDialogListener(new ConfirmDialog.OnFinishDialogListener() {
            @Override
            public void onFinishDialog(ConfirmDialog.Button button) {
                if(button == ConfirmDialog.Button.Positive) {

                    signOut();

                }
            }
        });


    }

    private void signOut() {
        SharedPreferences sharedPreferences = getSharedPreferences("SkyUser",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", "");
        editor.putString("chkRemember", "");
        editor.commit();

//        Back To Login
        startActivity(new Intent(MenuActivity.this, MainActivity.class));

//        Close App
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    private void createToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbarDashboard);
        setSupportActionBar(toolbar);

//        Setup Title
        getSupportActionBar().setTitle("Dash Board");
        getSupportActionBar().setSubtitle(displayNameString+" Login");

    }

    private void getValueFromIntent() {
        userString = getIntent().getStringExtra("username");
        displayNameString = getIntent().getStringExtra("display");
    }

} //Main Class
