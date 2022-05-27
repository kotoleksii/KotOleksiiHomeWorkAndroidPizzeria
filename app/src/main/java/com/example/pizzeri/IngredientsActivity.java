package com.example.pizzeri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.pizzeri.models.Pizza;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    private Pizza pizza;

    private ArrayList<CheckBox> cbGroupIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        getPizzaFromIntent();
        getCbGroupIngredients();
        setCheckedSelectedIngredients();

        Button btnSubmit = findViewById(R.id.btnNext);
        btnSubmit.setOnClickListener(this::showNextActivity);
    }

    private void getPizzaFromIntent() {
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            pizza = (Pizza) arguments.getSerializable(Pizza.class.getSimpleName());
        }
    }

    private void getCbGroupIngredients() {
        cbGroupIngredients = new ArrayList<>();
        cbGroupIngredients.add(findViewById(R.id.cbxPineapple));
        cbGroupIngredients.add(findViewById(R.id.cbxBacon));
        cbGroupIngredients.add(findViewById(R.id.cbxTurkey));
        cbGroupIngredients.add(findViewById(R.id.cbxChickenFillet));
        cbGroupIngredients.add(findViewById(R.id.cbxOlives));
        cbGroupIngredients.add(findViewById(R.id.cbEggplant));
        cbGroupIngredients.add(findViewById(R.id.cbxHuntingSausages));
    }

    private void showNextActivity(View view) {
        addSelectedIngredients();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null && arguments.containsKey("requestCodeKey")) {
            if (arguments.getInt("requestCodeKey") == 2222) {
                Intent intent = new Intent();
                intent.putExtra(Pizza.class.getSimpleName(), pizza);
                setResult(RESULT_OK, intent);
            }
        } else {
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra(Pizza.class.getSimpleName(), pizza);
            startActivity(intent);
        }

        finish();
    }

    private void addSelectedIngredients() {
        if (pizza == null)
            return;

        String extraIngredients = "";
        for (CheckBox cbIngredient : cbGroupIngredients) {
            if (!cbIngredient.isChecked())
                continue;

            extraIngredients = extraIngredients.concat(cbIngredient.getText().toString().concat(", "));
        }

        if (extraIngredients.length() != 0)
            extraIngredients = extraIngredients.substring(0, extraIngredients.length() - 2);

        pizza.setIngredients(extraIngredients);
    }

    private void setCheckedSelectedIngredients() {
        if (pizza.getIngredients() == null)
            return;

        String[] selectedIngredients = pizza.getIngredients().split(", ");

        for (CheckBox cbIngredient : cbGroupIngredients) {
            String cbTitle = cbIngredient.getText().toString();

            for (String ingredient : selectedIngredients) {
                if (!ingredient.equals(cbTitle))
                    continue;

                cbIngredient.setChecked(true);
            }
        }
    }
}
