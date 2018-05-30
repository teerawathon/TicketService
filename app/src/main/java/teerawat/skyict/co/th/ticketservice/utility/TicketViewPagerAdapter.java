package teerawat.skyict.co.th.ticketservice.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import teerawat.skyict.co.th.ticketservice.fragment.ActiveFragment;
import teerawat.skyict.co.th.ticketservice.fragment.CriticalFragment;
import teerawat.skyict.co.th.ticketservice.fragment.LastFragment;
import teerawat.skyict.co.th.ticketservice.fragment.NewItemFragment;

public class TicketViewPagerAdapter extends FragmentStatePagerAdapter{

    private FragmentManager fragmentManager;
    private int tabLayoutAnInt;

    public TicketViewPagerAdapter(FragmentManager fragmentManager,
                                  int tabLayoutAnInt) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.tabLayoutAnInt = tabLayoutAnInt;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NewItemFragment newItemFragment = new NewItemFragment();
                return newItemFragment;
            case 1:
                ActiveFragment activeFragment = new ActiveFragment();
                return activeFragment;
            case 2:
                CriticalFragment criticalFragment = new CriticalFragment();
                return criticalFragment;
            case 3:
                LastFragment lastFragment = new LastFragment();
                return lastFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabLayoutAnInt;
    }
}
