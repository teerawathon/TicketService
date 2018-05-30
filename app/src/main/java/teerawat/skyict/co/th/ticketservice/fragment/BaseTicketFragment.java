package teerawat.skyict.co.th.ticketservice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teerawat.skyict.co.th.ticketservice.R;
import teerawat.skyict.co.th.ticketservice.utility.MyConstance;
import teerawat.skyict.co.th.ticketservice.utility.TicketViewPagerAdapter;

public class BaseTicketFragment extends Fragment {

    private String idString, nameString;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public static BaseTicketFragment baseTicketInstance(String idString, String nameString) {

        BaseTicketFragment baseTicketFragment = new BaseTicketFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", idString);
        bundle.putString("Name", nameString);
        baseTicketFragment.setArguments(bundle);

        return baseTicketFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        GetValue Argument
        getValueArgument();

//        Create TabLayout
        createTabLayout();

//        Create ViewPager
        createViewPager();

    }//Main medthod

    private void createViewPager() {
        viewPager = getView().findViewById(R.id.viewPagerTicket);
        TicketViewPagerAdapter ticketViewPagerAdapter = new TicketViewPagerAdapter(
                getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(ticketViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void createTabLayout() {
        MyConstance myConstance = new MyConstance();
        String[] strings = myConstance.getTitleTabLayout();
        tabLayout = getView().findViewById(R.id.tabLayoutTicket);
        for (int i = 0; i < strings.length; i += 1) {
            tabLayout.addTab(tabLayout.newTab().setText(strings[i]));
        } //for
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


    }

    private void getValueArgument() {
        idString = getArguments().getString("id");
        nameString = getArguments().getString("Name");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_ticket, container, false);
        return view;
    }
}
