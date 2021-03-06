package com.delaroystudios.alarmreminder;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.delaroystudios.alarmreminder.data.AlarmReminderContract;

/**
 * Created by delaroy on 10/27/17.
 */

public class AlarmCursorAdapter extends CursorAdapter {

    private TextView mTitleText,mMedicineText,mExpirytext, mDateAndTimeText, mRepeatInfoText;
    private ImageView mActiveImage ,mActivemed,mActiveexpi,mThumbnailImage,mThumbnailmed,mThumbnailexpi;
    private ColorGenerator mColorGenerator = ColorGenerator.DEFAULT;
    private TextDrawable mDrawableBuilder,mDrawablemed,mDrawableexpi;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public AlarmCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.alarm_items, parent, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        mTitleText = (TextView) view.findViewById(R.id.recycle_title);
        mMedicineText = (TextView) view.findViewById(R.id.medicineintake);
        mExpirytext = (TextView) view.findViewById(R.id.Expiration);
        mDateAndTimeText = (TextView) view.findViewById(R.id.recycle_date_time);
        mRepeatInfoText = (TextView) view.findViewById(R.id.recycle_repeat_info);
        mActiveImage = (ImageView) view.findViewById(R.id.active_image);
        mActivemed = (ImageView) view.findViewById(R.id.active_med);
        mActiveexpi = (ImageView) view.findViewById(R.id.active_expi);
        mThumbnailImage = (ImageView) view.findViewById(R.id.thumbnail_image);
        mThumbnailmed = (ImageView) view.findViewById(R.id.thumbnail_med);
        mThumbnailexpi = (ImageView) view.findViewById(R.id.thumbnail_expi);

        int titleColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_TITLE);
        int MedicineColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_MEDICINE);
        int ExpiryColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_EXPIRY);
        int dateColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_DATE);
        int timeColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_TIME);
        int repeatColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT);
        int repeatNoColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO);
        int repeatTypeColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE);
        int activeColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE);

        String title = cursor.getString(titleColumnIndex);
        String med = cursor.getString(MedicineColumnIndex);
        String expi = cursor.getString(ExpiryColumnIndex);
        String date = cursor.getString(dateColumnIndex);
        String time = cursor.getString(timeColumnIndex);
        String repeat = cursor.getString(repeatColumnIndex);
        String repeatNo = cursor.getString(repeatNoColumnIndex);
        String repeatType = cursor.getString(repeatTypeColumnIndex);
        String active = cursor.getString(activeColumnIndex);

        String dateTime = date + " " + time;


        setReminderTitle(title);
        setRemindermed(med);
        setReminderexpiry(expi);
        setReminderDateTime(dateTime);
        setReminderRepeatInfo(repeat, repeatNo, repeatType);
        setActiveImage(active);




    }

    // Set reminder title view
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void setReminderTitle(String title) {
        mTitleText.setText(title);
        String letter = "A";

        if(title != null && !title.isEmpty()) {
            letter = title.substring(0, 1);
        }

        int color = mColorGenerator.getRandomColor();

        // Create a circular icon consisting of  a random background colour and first letter of title
        mDrawableBuilder = TextDrawable.builder()
                .buildRound(letter, color);
        mThumbnailImage.setImageDrawable(mDrawableBuilder);
    }




    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void setRemindermed(String med) {

        mMedicineText.setText(med);
        String letter = "B";

        if(med != null && !med.isEmpty()) {
            letter = med.substring(0, 1);
        }

        int color = mColorGenerator.getRandomColor();

        // Create a circular icon consisting of  a random background colour and first letter of title
        mDrawablemed = TextDrawable.builder()
                .buildRound(letter, color);
        mThumbnailmed.setImageDrawable(mDrawablemed);

    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void setReminderexpiry(String expi) {

        mExpirytext.setText(expi);
        String letter = "C";

        if(expi != null && !expi.isEmpty()) {
            letter = expi.substring(0, 1);
        }

        int color = mColorGenerator.getRandomColor();

        // Create a circular icon consisting of  a random background colour and first letter of title
        mDrawableexpi = TextDrawable.builder()
                .buildRound(letter, color);
        mThumbnailexpi.setImageDrawable(mDrawableexpi);


    }






    // Set date and time views
    public void setReminderDateTime(String datetime) {
        mDateAndTimeText.setText(datetime);
    }

    // Set repeat views
    public void setReminderRepeatInfo(String repeat, String repeatNo, String repeatType) {
        if(repeat.equals("true")){
            mRepeatInfoText.setText("Every " + repeatNo + " " + repeatType + "(s)");
        }else if (repeat.equals("false")) {
            mRepeatInfoText.setText("Repeat Off");
        }
    }

    // Set active image as on or off
    public void setActiveImage(String active){
        if(active.equals("true")){
            mActiveImage.setImageResource(R.drawable.ic_notifications_on_white_24dp);
        }else if (active.equals("false")) {
            mActiveImage.setImageResource(R.drawable.ic_notifications_off_grey600_24dp);
        }
        if(active.equals("true")){
            mActivemed.setImageResource(R.drawable.ic_notifications_on_white_24dp);
        }else if (active.equals("false")) {
            mActivemed.setImageResource(R.drawable.ic_notifications_off_grey600_24dp);
        }  if(active.equals("true")){
            mActiveexpi.setImageResource(R.drawable.ic_notifications_on_white_24dp);
        }else if (active.equals("false")) {
            mActiveexpi.setImageResource(R.drawable.ic_notifications_off_grey600_24dp);
        }



    }




}
