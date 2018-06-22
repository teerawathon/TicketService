package teerawat.skyict.co.th.ticketservice.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import teerawat.skyict.co.th.ticketservice.R;

public class ShowListContactAdapter extends BaseAdapter {

    private Context context;
    private String[] idStrings, nameThaiStrings, nickNameThaiStrings, nameEngStrings,
            nickNameEngStrings, emailStrings, carStrings, phoneStrings, imageURLStrings;

    public ShowListContactAdapter(Context context,
                                  String[] idStrings,
                                  String[] nameThaiStrings,
                                  String[] nickNameThaiStrings,
                                  String[] nameEngStrings,
                                  String[] nickNameStrings,
                                  String[] emailStrings,
                                  String[] carStrings,
                                  String[] phoneStrings,
                                  String[] imageURLStrings) {

        this.context = context;
        this.idStrings = idStrings;
        this.nameThaiStrings = nameThaiStrings;
        this.nickNameThaiStrings = nickNameThaiStrings;
        this.nameEngStrings = nameEngStrings;
        this.nickNameEngStrings = nickNameStrings;
        this.emailStrings = emailStrings;
        this.carStrings = carStrings;
        this.phoneStrings = phoneStrings;
        this.imageURLStrings = imageURLStrings;
    }

    @Override
    public int getCount() {
        return idStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview_show_contact, parent, false);

        TextView idTextView = view.findViewById(R.id.txtID);
        TextView nameThaiTextView = view.findViewById(R.id.txtNameThai);
        TextView nickNameThaiTextView = view.findViewById(R.id.txtNickNameThai);
        TextView nameEngTextView = view.findViewById(R.id.txtNameEng);
        TextView nickNameEngTextView = view.findViewById(R.id.txtNickNameEng);
        TextView emailTextView = view.findViewById(R.id.txtEmail);
        TextView carTextView = view.findViewById(R.id.txtCar);
        TextView phoneTextView = view.findViewById(R.id.txtPhone);


        ImageView imageView = view.findViewById(R.id.imvContactShow);
        DownloadImageTask downloadImageTask = new DownloadImageTask(imageView);
        downloadImageTask.execute(imageURLStrings[position]);

        idTextView.setText(idStrings[position]);
        nameThaiTextView.setText(nameThaiStrings[position]);
        nickNameThaiTextView.setText(nickNameThaiStrings[position]);
        nameEngTextView.setText(nameEngStrings[position]);
        nickNameEngTextView.setText(nickNameEngStrings[position]);
        emailTextView.setText(emailStrings[position]);
        carTextView.setText(carStrings[position]);
        phoneTextView.setText(phoneStrings[position]);

        return view;
    }
}
