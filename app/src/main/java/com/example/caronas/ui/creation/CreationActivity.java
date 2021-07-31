package com.example.caronas.ui.creation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.caronas.R;
import com.example.caronas.Service;
import com.example.caronas.models.Offer;
import com.example.caronas.models.RideRequest;


public class CreationActivity extends AppCompatActivity {

    private Service service;
    private RadioButton radioButtonOffer, radioButtonRequest;
    private TextView textViewDateCreation, textViewTime1, textViewTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        service = Service.getInstance();

        LinearLayout linearVacancies = findViewById(R.id.linearVacancies);

        radioButtonOffer = findViewById(R.id.radioButtonOffer);
        radioButtonOffer.setOnClickListener(v -> linearVacancies.setVisibility(View.VISIBLE));

        radioButtonRequest = findViewById(R.id.radioButtonRequest);
        radioButtonRequest.setOnClickListener(v -> linearVacancies.setVisibility(View.GONE));

        textViewDateCreation = findViewById(R.id.textViewDateCreation);
        textViewDateCreation.setOnClickListener(v -> {
            YoYo.with(Techniques.Flash)
                    .duration(700)
                    .repeat(1)
                    .playOn(textViewDateCreation);

            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");

        });

        textViewTime1 = findViewById(R.id.textViewTime1);
        textViewTime1.setOnClickListener(v -> {
            YoYo.with(Techniques.Flash)
                    .duration(700)
                    .repeat(1)
                    .playOn(textViewTime1);

            DialogFragment newFragment = new TimePickerFragment(1);
            newFragment.show(getSupportFragmentManager(), "timePicker");
        });

        textViewTime2 = findViewById(R.id.textViewTime2);
        textViewTime2.setOnClickListener(v -> {
            YoYo.with(Techniques.Flash)
                    .duration(700)
                    .repeat(1)
                    .playOn(textViewTime2);

            DialogFragment newFragment = new TimePickerFragment(2);
            newFragment.show(getSupportFragmentManager(), "timePicker");
        });
    }

    public void handleRideRegister(View view) {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        Long userId = pref.getLong("user_id", -1);

        EditText editTextOriginCreation = findViewById(R.id.editTextOriginCreation);
        EditText editTextDestinationCreation = findViewById(R.id.editTextDestinationCreation);

        EditText editTextPhoneCreation = findViewById(R.id.editTextPhoneCreation);

        if (!radioButtonRequest.isChecked() && !radioButtonOffer.isChecked()) {
            Toast.makeText(view.getContext(), "Selecione o tipo de carona", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(editTextPhoneCreation.getText())) {
            editTextPhoneCreation.setError("Digite o seu telefone!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextPhoneCreation);
            return;
        }
        String phone = editTextPhoneCreation.getText().toString();

        RadioGroup radioGroupCreation = findViewById(R.id.radioGroupCreation);

        if (radioGroupCreation.getCheckedRadioButtonId() == radioButtonOffer.getId()) {
            EditText editTextVacanciesCreation = findViewById(R.id.editTextVacanciesCreation);
            Offer offer = new Offer();
            offer.setUser_id(userId);
            offer.setPhone(phone);
            offer.setFrom_city(null);
            offer.setFrom_neighborhood(null);
            offer.setFrom_street(null);
            offer.setTo_city(null);
            offer.setTo_neighborhood(null);
            offer.setTo_street(null);
            offer.setStart_date(null);
            offer.setEnd_date(null);
            offer.setAvailable_vacancies(Long.parseLong(editTextVacanciesCreation.getText().toString()));
        } else {
            RideRequest rideRequest = new RideRequest();
            rideRequest.setUser_id(userId);
            rideRequest.setPhone(phone);
            rideRequest.setFrom_city(null);
            rideRequest.setFrom_neighborhood(null);
            rideRequest.setFrom_street(null);
            rideRequest.setTo_city(null);
            rideRequest.setTo_neighborhood(null);
            rideRequest.setTo_street(null);
            rideRequest.setStart_date(null);
            rideRequest.setEnd_date(null);
        }
    }
}