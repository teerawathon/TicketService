package teerawat.skyict.co.th.ticketservice.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import teerawat.skyict.co.th.ticketservice.MainActivity;
import teerawat.skyict.co.th.ticketservice.R;
import teerawat.skyict.co.th.ticketservice.utility.MyAlertDialog;

public class SettingFragment extends Fragment {

    public String ipServerString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();

//        Show IpServer
        showIpServer();

//        Submit controller
        submitController();

    } // Main method

    private void submitController() {

        Button button = getView().findViewById(R.id.btnSaveConfigIP);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                try {

                    EditText ipServerEditText = getView().findViewById(R.id.edtIPServer);
                    String ipString = ipServerEditText.getText().toString().trim();

                    if (ipString.isEmpty()) {

                        MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                        myAlertDialog.normalDialog("IP Server Empty",
                                "Please Fill Server IP Address");

                    } else {

                        ipServerString = ipServerEditText.getText().toString();
                        saveIpServer(ipServerString);

                        Toast.makeText(getActivity(),"Save IP Server Success.",
                                Toast.LENGTH_SHORT).show();

                        ((MainActivity) getActivity())
                                .getSupportFragmentManager()
                                .popBackStack();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @NonNull
    private void showIpServer() {

        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences("SkyUser", Context.MODE_PRIVATE);
        ipServerString = sharedPreferences.getString("server", "");

        EditText ipServerEditText = getView().findViewById(R.id.edtIPServer);
        ipServerEditText.setText(ipServerString);

    }

    private void saveIpServer(String ipServerString) {
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences("SkyUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("server", ipServerString);
        editor.commit();
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarSetting);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Setting");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please Fill Server IP Address");

        //Set Navigation
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set Navigation Icon
        toolbar.setNavigationIcon(R.drawable.ic_action_back);

        //Set Navigation onClick
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity())
                        .getSupportFragmentManager()
                        .popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        return view;
    }
}
