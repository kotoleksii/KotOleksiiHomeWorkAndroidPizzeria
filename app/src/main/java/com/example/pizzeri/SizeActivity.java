package com.example.pizzeri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pizzeri.models.Pizza;

public class SizeActivity extends AppCompatActivity {

    private final String[] pizzaSizes = {
            Pizza.SMALL,
            Pizza.MEDIUM,
            Pizza.BIG,
            Pizza.VERY_BIG
    };
    private final String[] pizzaTitles = {
            Pizza.MARGHERITA,
            Pizza.PEPPERONI,
            Pizza.SICILIAN,
            Pizza.BAVARIAN,
    };

    Pizza pizza;

    private Spinner spSize;
    private Spinner spTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);

        getPizzaFromIntentOrCreate();

        createSpinnersLists();

        Button btnSubmit = findViewById(R.id.btnNext);
        btnSubmit.setOnClickListener(this::showNextActivity);
    }

    private void getPizzaFromIntentOrCreate() {
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            pizza = (Pizza) arguments.getSerializable(Pizza.class.getSimpleName());
        } else {
            pizza = new Pizza();
        }
    }

    private void createSpinnersLists() {
        spSize = findViewById(R.id.spSize);
        spTitle = findViewById(R.id.spTitle);

        ArrayAdapter<String> adapterSize = new ArrayAdapter<>(this, R.layout.custom_spinner_item, pizzaSizes);
        adapterSize.setDropDownViewResource(R.layout.custom_spinner_item);
        ArrayAdapter<String> adapterTitle = new ArrayAdapter<>(this, R.layout.custom_spinner_item, pizzaTitles);
        adapterTitle.setDropDownViewResource(R.layout.custom_spinner_item);

        spSize.setAdapter(adapterSize);
        spTitle.setAdapter(adapterTitle);

        spSize.setSelection(adapterSize.getPosition(pizza.getSize()));
        spTitle.setSelection(adapterTitle.getPosition(pizza.getTitle()));
    }

    private void showNextActivity(View view) {
        pizza.setSize(spSize.getSelectedItem().toString());
        pizza.setTitle(spTitle.getSelectedItem().toString());

        Bundle arguments = getIntent().getExtras();
        if (arguments != null && arguments.containsKey("requestCodeKey")) {
            if (arguments.getInt("requestCodeKey") == 1111) {
                Intent intent = new Intent();
                intent.putExtra(Pizza.class.getSimpleName(), pizza);
                setResult(RESULT_OK, intent);
            }
        } else {
            Intent intent = new Intent(this, IngredientsActivity.class);
            intent.putExtra(Pizza.class.getSimpleName(), pizza);
            startActivity(intent);
        }
        finish();
    }
}