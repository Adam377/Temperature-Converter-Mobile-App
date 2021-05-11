package mmu.ac.uk;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity
{
    EditText input;
    RadioButton fromC, fromF, fromK, toC, toF, toK;
    Button convertBtn;
    CheckBox toInt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.convertTextBox);

        fromC = (RadioButton) findViewById(R.id.fromCentigrade);
        fromF = (RadioButton) findViewById(R.id.fromFarenheit);
        fromK = (RadioButton) findViewById(R.id.fromKelvin);

        toC = (RadioButton) findViewById(R.id.toCentigrade);
        toF = (RadioButton) findViewById(R.id.toFahrenheit);
        toK = (RadioButton) findViewById(R.id.toKelvin);

        convertBtn = (Button) findViewById(R.id.convertButton);
        toInt = (CheckBox) findViewById(R.id.roundToInt);

        fromC.setChecked(true);
        toC.setChecked(true);
    }

    public void convertOnClick(View v)
    {
        //Take string from input text and convert to double
        double oldTemp = Double.parseDouble(String.valueOf(input.getText()));
        double newTemp;

        //Kelvin to Centigrade
        if(fromK.isChecked() && toC.isChecked())
        {
            //K-273.15
            newTemp = oldTemp - 273.15;
            convertToInt(newTemp);
        }

        //Kelvin to Fahrekheit
        else if(fromK.isChecked() && toF.isChecked())
        {
            //(K-273.15) * 9/5 + 32
            newTemp = (oldTemp - 273.15) * (9/5) + 32;
            convertToInt(newTemp);
        }

        //Centigrade to Kelvin
        else if(fromC.isChecked() && toK.isChecked())
        {
            //C + 273.15
            newTemp = oldTemp + 273.15;
            convertToInt(newTemp);
        }

        //Centigrade to Fahrenheit
        else if(fromC.isChecked() && toF.isChecked())
        {
            //(C * 9/5) + 32
            newTemp = (oldTemp * 9/5) + 32;
            convertToInt(newTemp);
        }

        //Fahrenheit to Kelvin
        else if(fromF.isChecked() && toK.isChecked())
        {
            //(F-32) * 5/9 + 273.15
            newTemp = (oldTemp - 32) * 5/9 + 273.15;
            convertToInt(newTemp);
        }

        else if((fromK.isChecked()&&toK.isChecked()) || (fromC.isChecked()&&toC.isChecked()) || (fromF.isChecked()&&toF.isChecked()))
        {
            input.setText("You're using same units.");
        }

        //Fahrenheit to Centigrade
        else
        {
            //(F-32) * 5/9
            newTemp = (oldTemp - 32) * 5/9;
            convertToInt(newTemp);
        }
    }

    private void convertToInt(double temp)
    {
        if(toInt.isChecked())
        {
            input.setText(String.valueOf(Math.round(temp)));
        }
        else
        {
            input.setText(String.valueOf(temp));
        }
    }
}
