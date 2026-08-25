package com.almus.minicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String current = "";
    private double first = 0;
    private String operator = "";
    private boolean newNumber = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
    }

    public void numberClick(View view) {
        Button b = (Button) view;
        if (newNumber) {
            current = "";
            newNumber = false;
        }
        if (current.equals("0") && !b.getText().toString().equals(".")) current = "";
        if (b.getText().toString().equals(".") && current.contains(".")) return;
        current += b.getText().toString();
        display.setText(current);
    }

    public void operatorClick(View view) {
        if (current.isEmpty()) return;
        first = Double.parseDouble(current);
        operator = ((Button) view).getText().toString();
        newNumber = true;
    }

    public void equalsClick(View view) {
        if (current.isEmpty() || operator.isEmpty()) return;
        double second = Double.parseDouble(current);
        double result = 0;
        switch (operator) {
            case "+": result = first + second; break;
            case "−": result = first - second; break;
            case "×": result = first * second; break;
            case "÷":
                if (second == 0) {
                    display.setText("Error");
                    current = "";
                    operator = "";
                    return;
                }
                result = first / second;
                break;
        }
        current = format(result);
        display.setText(current);
        operator = "";
        newNumber = true;
    }

    public void clearClick(View view) {
        current = "";
        first = 0;
        operator = "";
        newNumber = false;
        display.setText("0");
    }

    private String format(double value) {
        if (value == Math.rint(value)) return String.valueOf((long) value);
        return String.valueOf(value);
    }
}
