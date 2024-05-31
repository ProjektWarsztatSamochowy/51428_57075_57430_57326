package com.hdaf.aplikacja;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Service> servicesList = new ArrayList<>();
    private RecyclerView servicesRecyclerView;
    private ServicesAdapter servicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Istniejące usługi
        servicesList.add(new Service("Wymiana opon", "Wymiana opon letnich na zimowe", 100.0));
        servicesList.add(new Service("Przegląd okresowy", "Pełny przegląd techniczny pojazdu", 200.0));

        // Dodanie nowych usług
        servicesList.add(new Service("Naprawa hamulców", "Kompleksowa naprawa układu hamulcowego", 250.0));
        servicesList.add(new Service("Wymiana oleju", "Wymiana oleju i filtra oleju", 120.0));
        servicesList.add(new Service("Naprawa zawieszenia", "Diagnostyka i naprawa zawieszenia", 300.0));
        servicesList.add(new Service("Regeneracja turbosprężarki", "Regeneracja turbosprężarki do lepszej wydajności", 400.0));
        servicesList.add(new Service("Diagnostyka komputerowa", "Pełna diagnostyka komputerowa pojazdu", 150.0));

        servicesRecyclerView = findViewById(R.id.services_recycler_view);
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        servicesAdapter = new ServicesAdapter(servicesList);
        servicesRecyclerView.setAdapter(servicesAdapter);
    }
}
