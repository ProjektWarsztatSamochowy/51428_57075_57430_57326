package com.hdaf.aplikacja;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hdaf.aplikacja.Service;
import com.hdaf.aplikacja.R;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private List<Service> servicesList;

    public ServicesAdapter(List<Service> servicesList) {
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = servicesList.get(position);
        holder.nameTextView.setText(service.getName());
        holder.descriptionTextView.setText(service.getDescription());
        holder.priceTextView.setText(String.format("$%.2f", service.getPrice()));

        // Dodajemy OnClickListener do całego widoku ViewHoldera
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzymy Intent do uruchomienia BookingActivity
                Intent intent = new Intent(v.getContext(), BookingActivity.class);
                // Przekazujemy szczegóły usługi jako dodatkowe informacje do Intentu
                intent.putExtra("serviceName", service.getName());
                intent.putExtra("serviceDescription", service.getDescription());
                intent.putExtra("servicePrice", service.getPrice());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView priceTextView;

        ServiceViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.service_name);
            descriptionTextView = itemView.findViewById(R.id.service_description);
            priceTextView = itemView.findViewById(R.id.service_price);
        }
    }
}