package com.example.pizzeri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pizzeri.models.Pizza;

public class OrderActivity extends AppCompatActivity {
    private Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getPizzaFromIntent();
        pizza.calculatePrice();

        setPizzaProperty(R.id.txtSize, pizza.getSize());
        setPizzaProperty(R.id.txtTitle, pizza.getTitle());
        setPizzaProperty(R.id.txtIngredients, pizza.getIngredients());
        setPizzaProperty(R.id.txtPrice, pizza.getPrice() + "₴");

        Button btnChangePizza = findViewById(R.id.btnEditOrder);
        btnChangePizza.setOnClickListener(this::ShowSelectPizzaActivity);

        Button btnChangeIngredients = findViewById(R.id.btnEditIngredients);
        btnChangeIngredients.setOnClickListener(this::showExtraIngredientsActivity);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra(Pizza.class.getSimpleName(), pizza);
        intent.putExtra("requestCodeKey", requestCode);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data != null) {
                pizza = (Pizza) data.getSerializableExtra(Pizza.class.getSimpleName());
            }
        }

        switch (requestCode) {
            case 1111:
                setPizzaProperty(R.id.txtSize, pizza.getSize());
                setPizzaProperty(R.id.txtTitle, pizza.getTitle());
                break;
            case 2222:
                setPizzaProperty(R.id.txtIngredients, pizza.getIngredients());
                break;
        }

        pizza.calculatePrice();
        setPizzaProperty(R.id.txtPrice, pizza.getPrice() + "₴");
    }

    private void getPizzaFromIntent() {
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            pizza = (Pizza) arguments.getSerializable(Pizza.class.getSimpleName());
        }
    }

    private void setPizzaProperty(int tvId, String property) {
        TextView tvProperty = findViewById(tvId);
        tvProperty.setText(property);
    }

    private void ShowSelectPizzaActivity(View view) {
        Intent intent = new Intent(this, SizeActivity.class);
        startActivityForResult(intent, 1111);
    }

    private void showExtraIngredientsActivity(View view) {
        Intent intent = new Intent(this, IngredientsActivity.class);
        startActivityForResult(intent, 2222);
    }
}
