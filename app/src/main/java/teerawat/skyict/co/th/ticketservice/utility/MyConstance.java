package teerawat.skyict.co.th.ticketservice.utility;

import teerawat.skyict.co.th.ticketservice.R;

public class MyConstance {

    private String urlGetAllUser = "https://android.skyict.co.th/getAllUserWat.php";

    private String[] titleListStrings = new String[]{"Ticket", "New Ticket", "Report", "About"};

    public String getUrlGetAllUser() {
        return urlGetAllUser;
    }

    private int[] iconInts = new int[]{
            R.drawable.ic_action_ticket,
            R.drawable.ic_action_new_ticket,
            R.drawable.ic_action_report,
            R.drawable.ic_action_about
    };

    public String[] getTitleListStrings() {
        return titleListStrings;
    }

    public int[] getIconInts() {
        return iconInts;
    }

}
