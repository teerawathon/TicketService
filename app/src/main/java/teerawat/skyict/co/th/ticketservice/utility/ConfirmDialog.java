package teerawat.skyict.co.th.ticketservice.utility;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class ConfirmDialog extends DialogFragment {
    /* ควรสร้างตัวเลือกแบบ enum สำหรับใช้เปรียบเทียบว่าปุ่มใดถูกคลิก
        จะช่วยลดข้อผิดพลาดได้มากกว่าการใช้ค่าเป็นสตริง
     */
    public enum Button {    //จะอ้างถึง enum นี้ในแบบ ConfirmDialog.Button
        Negative,
        Positive
    }

    public ConfirmDialog() {
    }

    public static ConfirmDialog newInstance(String msg, String negText, String posText) {
        ConfirmDialog dialog = new ConfirmDialog();
        Bundle args = new Bundle();
        args.putString("message", msg);
        args.putString("negText", negText);
        args.putString("posText", posText);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString("message");
        String posText = getArguments().getString("posText");
        String negText = getArguments().getString("negText");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setNegativeButton(negText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ถ้าปุ่ม Negative ถูกคลิก ก็ส่ง
                        mListener.onFinishDialog(Button.Negative);
                    }
                })
                .setPositiveButton(posText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onFinishDialog(Button.Positive);
                    }
                });

        return builder.create();
    }

    public interface OnFinishDialogListener {
        void onFinishDialog(ConfirmDialog.Button button);
        //กำหนด Type ของพารามิเตอร์เป็น enum ที่สร้างขึ้น
    }

    private OnFinishDialogListener mListener;

    public void setOnFinishDialogListener(OnFinishDialogListener listener) {
        mListener = listener;
    }
}
