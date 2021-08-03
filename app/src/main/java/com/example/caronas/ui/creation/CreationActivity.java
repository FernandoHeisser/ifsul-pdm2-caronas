package com.example.caronas.ui.creation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.caronas.R;
import com.example.caronas.Service;
import com.example.caronas.models.Offer;
import com.example.caronas.models.RideRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


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

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        if (TextUtils.isEmpty(textViewDateCreation.getText())) {
            textViewDateCreation.setError("Escolha a data!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(textViewDateCreation);
            return;
        }
        String dateString = textViewDateCreation.getText().toString();

        if (TextUtils.isEmpty(textViewTime1.getText())) {
            textViewTime1.setError("Escolha o horario inicial!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(textViewTime1);
            return;
        }
        String time1 = textViewTime1.getText().toString();

        if (TextUtils.isEmpty(textViewTime2.getText())) {
            textViewTime2.setError("Escolha o horario final!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(textViewTime2);
            return;
        }
        String time2 = textViewTime2.getText().toString();

        if (TextUtils.isEmpty(editTextOriginCreation.getText())) {
            editTextOriginCreation.setError("Digite o local de origem!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextOriginCreation);
            return;
        }
        String origin = editTextOriginCreation.getText().toString();

        if (TextUtils.isEmpty(editTextDestinationCreation.getText())) {
            editTextDestinationCreation.setError("Digite o local de destino!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextDestinationCreation);
            return;
        }
        String destination = editTextDestinationCreation.getText().toString();

        if (TextUtils.isEmpty(editTextPhoneCreation.getText())) {
            editTextPhoneCreation.setError("Digite o seu telefone!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextPhoneCreation);
            return;
        }

        if (editTextPhoneCreation.getText().length() != 11) {
            editTextPhoneCreation.setError("Digite corretamente o seu telefone!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextPhoneCreation);
            return;
        }
        String phone = editTextPhoneCreation.getText().toString();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(time1, timeFormatter);
        LocalTime endTime = LocalTime.parse(time2, timeFormatter);

        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);

        OffsetDateTime startDateOffsetDateTime = OffsetDateTime.of(startDateTime, ZoneOffset.UTC);
        OffsetDateTime endDateOffsetDateTime = OffsetDateTime.of(endDateTime, ZoneOffset.UTC);

        startDateOffsetDateTime = startDateOffsetDateTime.minusHours(3L);
        endDateOffsetDateTime = endDateOffsetDateTime.minusHours(3L);

        String[] originParts = origin.split(", ");
        String cityFrom = originParts[0].trim();
        String neighborhoodFrom = originParts[1].trim();
        String streetFrom = originParts[2].trim();

        String[] destinationParts = destination.split(", ");
        String cityTo = destinationParts[0].trim();
        String neighborhoodTo = destinationParts[1].trim();
        String streetTo = destinationParts[2].trim();

        RadioGroup radioGroupCreation = findViewById(R.id.radioGroupCreation);

        if (radioGroupCreation.getCheckedRadioButtonId() == radioButtonOffer.getId()) {

            EditText editTextVacanciesCreation = findViewById(R.id.editTextVacanciesCreation);
            if (TextUtils.isEmpty(editTextVacanciesCreation.getText())) {
                editTextVacanciesCreation.setError("Digite o número de vagas!");
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .repeat(1)
                        .playOn(editTextVacanciesCreation);
                return;
            }
            Long vacancies = Long.parseLong(editTextVacanciesCreation.getText().toString());

            Offer offer = new Offer();
            offer.setUser_id(userId);
            offer.setPhone(phone);
            offer.setFrom_city(cityFrom);
            offer.setFrom_neighborhood(neighborhoodFrom);
            offer.setFrom_street(streetFrom);
            offer.setTo_city(cityTo);
            offer.setTo_neighborhood(neighborhoodTo);
            offer.setTo_street(streetTo);
            offer.setStart_date(startDateOffsetDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
            offer.setEnd_date(endDateOffsetDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
            offer.setAvailable_vacancies(vacancies);

            service.createOffer(offer);
            offer.setId(service.getLastIdCreated());
            service.myRides.add(offer);

            Toast.makeText(view.getContext(), "Cadastro realizado!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            RideRequest rideRequest = new RideRequest();
            rideRequest.setUser_id(userId);
            rideRequest.setPhone(phone);
            rideRequest.setFrom_city(cityFrom);
            rideRequest.setFrom_neighborhood(neighborhoodFrom);
            rideRequest.setFrom_street(streetFrom);
            rideRequest.setTo_city(cityTo);
            rideRequest.setTo_neighborhood(neighborhoodTo);
            rideRequest.setTo_street(streetTo);
            rideRequest.setStart_date(startDateOffsetDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
            rideRequest.setEnd_date(endDateOffsetDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

            service.createRideRequest(rideRequest);
            rideRequest.setId(service.getLastIdCreated());
            service.myRides.add(rideRequest);

            Toast.makeText(view.getContext(), "Cadastro realizado!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}