package com.hdaf.aplikacja;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Button datePickerButton = findViewById(R.id.date_picker_button);
        Button bookServiceButton = findViewById(R.id.book_service_button); // Przycisk do rezerwacji
        final EditText serviceDetailsEditText = findViewById(R.id.service_details); // Pole na szczegóły usługi

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Formatowanie daty i zapisanie w zmiennej
                                selectedDate = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth); // Miesiące są indeksowane od 0

                                // Aktualizacja tekstu przycisku
                                datePickerButton.setText(selectedDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Dodany fragment do obsługi przekazanych danych
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("serviceName")) {
            String serviceName = intent.getStringExtra("serviceName");
            String serviceDescription = intent.getStringExtra("serviceDescription");
            double servicePrice = intent.getDoubleExtra("servicePrice", 0);

            // Ustawienie tekstu w EditText na podstawie przekazanych danych
            serviceDetailsEditText.setText(serviceName + " - " + serviceDescription + " - $" + servicePrice);
        }

        // Logika dla potwierdzenia rezerwacji
        bookServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmBooking(serviceDetailsEditText.getText().toString());
            }
        });
    }

    private void confirmBooking(String serviceDetails) {
        // Sprawdź, czy wszystkie pola zostały wypełnione
        if (serviceDetails.isEmpty() || selectedDate.isEmpty()) {
            Toast.makeText(this, "Proszę wypełnić wszystkie pola.", Toast.LENGTH_LONG).show();
            return;
        }

        // Tu można dodać logikę przetwarzania rezerwacji, np. zapis do bazy danych lub wysyłka do API
        Toast.makeText(this, "Rezerwacja złożona! Szczegóły: " + serviceDetails + ", Data: " + selectedDate, Toast.LENGTH_LONG).show();

        // Uruchomienie MainActivity
        Intent intent = new Intent(BookingActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); // Zapewnia, że nie umieszczamy wielu instancji MainActivity na stosie
        startActivity(intent);
    }
}

