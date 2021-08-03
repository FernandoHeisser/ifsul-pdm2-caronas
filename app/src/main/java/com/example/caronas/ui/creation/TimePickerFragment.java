package com.example.caronas.ui.creation;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.caronas.R;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private final int id;

    TimePickerFragment(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView;
        LocalTime time = LocalTime.of(hourOfDay, minute);

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("HH:mm");
        String text = time.format(formatters);

        if (id == 1) {
            textView = requireActivity().findViewById(R.id.textViewTime1);
        } else {
            textView = requireActivity().findViewById(R.id.textViewTime2);
        }
        textView.setText(text);
    }
}
