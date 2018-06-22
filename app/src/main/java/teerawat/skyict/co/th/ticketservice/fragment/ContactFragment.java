package teerawat.skyict.co.th.ticketservice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import teerawat.skyict.co.th.ticketservice.R;
import teerawat.skyict.co.th.ticketservice.utility.MyConstance;
import teerawat.skyict.co.th.ticketservice.utility.ReadAllData;
import teerawat.skyict.co.th.ticketservice.utility.ShowListContactAdapter;

public class ContactFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //        Create ListView
        createListView();

    }

    private void createListView() {

        ListView listView = getView().findViewById(R.id.listViewContact);

        try {

            MyConstance myConstance = new MyConstance();
            String urlJSON = myConstance.getUrlContactString();

            ReadAllData readAllData = new ReadAllData(getContext());
            readAllData.execute(urlJSON);
            String jsonString = readAllData.get();
            //Log.d("30MayV1", "JSON ==>" + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            final String[] idStrings = new String[jsonArray.length()];
            final String[] nameThaiStrings = new String[jsonArray.length()];
            final String[] nickNameThaiStrings = new String[jsonArray.length()];
            final String[] nameEngStrings = new String[jsonArray.length()];
            final String[] nickNameStrings = new String[jsonArray.length()];
            final String[] emailStrings = new String[jsonArray.length()];
            final String[] carStrings = new String[jsonArray.length()];
            final String[] phoneStrings = new String[jsonArray.length()];
            final String[] imageURLStrings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                idStrings[i] = jsonObject.getString("id");
                nameThaiStrings[i] = jsonObject.getString("NameThai");
                nickNameThaiStrings[i] = jsonObject.getString("NickNameThai");
                nameEngStrings[i] = jsonObject.getString("NameEng");
                nickNameStrings[i] = jsonObject.getString("NickNameEng");
                emailStrings[i] = jsonObject.getString("Email");
                carStrings[i] = jsonObject.getString("Car");
                phoneStrings[i] = jsonObject.getString("Phone");
                imageURLStrings[i] = jsonObject.getString("ImageURL");

            }   //for

            // Show ListContact
            ShowListContactAdapter showListContactAdapter = new ShowListContactAdapter(getActivity(),
                    idStrings,
                    nameThaiStrings,
                    nickNameThaiStrings,
                    nameEngStrings,
                    nickNameStrings,
                    emailStrings,
                    carStrings,
                    phoneStrings,
                    imageURLStrings);
            listView.setAdapter(showListContactAdapter);

            // OnClick ItemList
            /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String[] strings = new String[8];

                    strings[0] = idStrings[position];
                    strings[1] = nameThaiStrings[position];
                    strings[2] = nickNameThaiStrings[position];
                    strings[3] = nameEngStrings[position];
                    strings[4] = nickNameStrings[position];
                    strings[5] = emailStrings[position];
                    strings[6] = carStrings[position];
                    strings[7] = phoneStrings[position];

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentContactFragment,
                                    DetailFragment.detailInstance(strings))
                            .commit();
                }
            });*/

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        return view;

    }
}
