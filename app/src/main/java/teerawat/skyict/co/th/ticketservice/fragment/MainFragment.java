package teerawat.skyict.co.th.ticketservice.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import teerawat.skyict.co.th.ticketservice.MainActivity;
import teerawat.skyict.co.th.ticketservice.MenuActivity;
import teerawat.skyict.co.th.ticketservice.R;
import teerawat.skyict.co.th.ticketservice.ServiceActivity;
import teerawat.skyict.co.th.ticketservice.utility.MyAlertDialog;
import teerawat.skyict.co.th.ticketservice.utility.MyConstance;
import teerawat.skyict.co.th.ticketservice.utility.ReadAllData;
import teerawat.skyict.co.th.ticketservice.utility.ReadDataLogin;

public class MainFragment extends Fragment {

    private String displayNameString, userString;
    private String rememberString = "false";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Check SharePrefer
        checkSharePrefer();

//        Create Toolbar
        createToolbar();

//        Submit controller
        submitController();


    } //Main Method

    private void checkSharePrefer() {
        try {
            SharedPreferences sharedPreferences = getActivity()
                    .getSharedPreferences("SkyUser", Context.MODE_PRIVATE);
            //String idString = sharedPreferences.getString("id", "");
            //String nameString = sharedPreferences.getString("name", "");
            String chkRememberString = sharedPreferences.getString("chkRemember", "");

            if (chkRememberString.equals("true")) {

                intentToMenu(userString, displayNameString);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void intentToMenu(String usernameString, String displayNameString) {

        Intent intent = new Intent(getActivity(), MenuActivity.class);
        intent.putExtra("username", usernameString);
        intent.putExtra("display", displayNameString);
        startActivity(intent);
        getActivity().finish();

    }

    private void submitController() {
        Button button = getView().findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

//                Check Space
                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.normalDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));

                } else {
//                    No Space
                    try {

                        MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());

                        SharedPreferences sharedPreferences = getActivity()
                                .getSharedPreferences("SkyUser", Context.MODE_PRIVATE);
                        String ipServerString = sharedPreferences.getString("server", "");

                        MyConstance myConstance = new MyConstance();
                        String urlJSON = ipServerString + myConstance.getUrlGetDataUser();
                        //String urlJSON = ipServerString + myConstance.getUrlGetAllUser();

                        ReadDataLogin readDataLogin = new ReadDataLogin(getActivity());
                        readDataLogin.execute(userString,passwordString,urlJSON);

                        String resultJSON = readDataLogin.get();
                        Log.d("19JunV1", "JSON ==>" + resultJSON);

                        JSONObject jsonObject = new JSONObject(resultJSON);

                            String statusString = jsonObject.getString("status");
                            Log.d("19JunV2", " StatusString ==>" + statusString);

                            if (statusString.equals("pass")) {

                                displayNameString = jsonObject.getString("display");
                                Toast.makeText(getActivity(),"Welcome "+displayNameString,
                                        Toast.LENGTH_SHORT).show();

                                // Check Remember
                                CheckBox checkBox = getView().findViewById(R.id.chbRemember);
                                if (checkBox.isChecked()) {

                                    rememberString = "true";
                                    savePreference(rememberString,userString,displayNameString);

                                }

                                // Intent to Menu Activity
                                intentToMenu(userString, displayNameString);

                            } else {

                                myAlertDialog.normalDialog("Username/Password Incorrect.",
                                        "Please Try Fill Username or Password Again.");

                            }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }// if

            } // onClick
        });

    }

    private void saveIdAndName(String idString, String nameString) {

        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences("SkyUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", idString);
        editor.putString("name", nameString);

        editor.commit();

    }

    private void savePreference(String rememberString, String usernameString, String displayString) {

        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences("SkyUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("chkRemember", rememberString);

        editor.commit();

    }

    private void intentToService(String nameString, String idString) {
        Intent intent = new Intent(getActivity(), ServiceActivity.class);
        intent.putExtra("id", idString);
        intent.putExtra("name", nameString);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemSetting) {

//            Replace Fragment
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentMainFragment, new SettingFragment())
                    .addToBackStack(null)
                    .commit();

            return true;
        }


        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);

    }

    private void createToolbar() {

        Toolbar toolbar = getView().findViewById(R.id.toolbarMain);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Welcome");

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

}
