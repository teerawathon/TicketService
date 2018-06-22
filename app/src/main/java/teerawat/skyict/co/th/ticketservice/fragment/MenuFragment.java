package teerawat.skyict.co.th.ticketservice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import teerawat.skyict.co.th.ticketservice.ContactActivity;
import teerawat.skyict.co.th.ticketservice.MenuActivity;
import teerawat.skyict.co.th.ticketservice.R;
import teerawat.skyict.co.th.ticketservice.ServiceActivity;

public class MenuFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Intent controller
        IntentController();


    } //Main Method

    private void IntentController() {

        ImageView imageViewContact = getView().findViewById(R.id.imvContact);
        imageViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        ImageView imageViewService = getView().findViewById(R.id.imvService);
        imageViewService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ServiceActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return view;
    }
}
