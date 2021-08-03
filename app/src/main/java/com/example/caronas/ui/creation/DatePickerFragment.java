package com.example.caronas.ui.creation;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.caronas.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public void onDateSet(DatePicker view, int year, int month, int day) {
        LocalDate date = LocalDate.of( year , month+1 , day );

        if (!date.isBefore(LocalDate.now())) {
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String text = date.format(formatters);

            TextView textView = requireActivity().findViewById(R.id.textViewDateCreation);
            textView.setText(text);
        }
    }
}
