package teerawat.skyict.co.th.ticketservice.utility;

import teerawat.skyict.co.th.ticketservice.R;

public class MyConstance {

    private String urlAssignString = "https://android.skyict.co.th/getAssign.php";
    private String urlSeverityString = "https://android.skyict.co.th/getSeverity.php";

    private String urlNewItemString = "https://android.skyict.co.th/getNewTicket.php";
    private String urlActiveString = "https://android.skyict.co.th/getPendingTicket.php";
    private String urlCriticalString = "https://android.skyict.co.th/getCriticalTicket.php";
    private String urlLastString = "https://android.skyict.co.th/getClosedLastTicket.php";

    private String[] titleTabLayout = new String[]{"NewItem","Active","Critical","Last"};

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

    public String getUrlAssignString() {
        return urlAssignString;
    }

    public String getUrlSeverityString() {
        return urlSeverityString;
    }

    public String getUrlNewItemString() {
        return urlNewItemString;
    }

    public String getUrlActiveString() {
        return urlActiveString;
    }

    public String getUrlCriticalString() {
        return urlCriticalString;
    }

    public String getUrlLastString() {
        return urlLastString;
    }

    public String[] getTitleTabLayout() {
        return titleTabLayout;
    }

    public String[] getTitleListStrings() {
        return titleListStrings;
    }

    public int[] getIconInts() {
        return iconInts;
    }

}
