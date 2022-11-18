package com.example.currencyxchange;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    EditText convertFromEditText, convertToEditText;
    Spinner convertFromSpinner, convertToSpinner;

    String[] countryList = {"ADA", "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "ATS", "AUD", "AWG", "AZM", "AZN", "BAM", "BBD", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD",
            "BTC", "BTN", "BWP", "BYN", "BYR", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNH", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CYP", "CZK", "DEM", "DJF", "DKK",
            "DOGE", "DOP", "DOT", "DZD", "EEK", "EGP", "ERN", "ESP", "ETB", "ETH", "EUR", "FIM", "FJD", "FKP", "FRF", "GBP", "GEL", "GGP", "GHC", "GHS", "GIP", "GMD", "GNF",
            "GRD", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "IEP", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "ITL", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR",
            "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LINK", "LKR", "LRD", "LSL", "LTC", "LTL", "LUF", "LUNA", "LVL", "LYD", "MAD", "MDL", "MGA", "MGF", "MKD",
            "MMK", "MNT", "MOP", "MRO", "MRU", "MTL", "MUR", "MVR", "MWK", "MXN", "MXV", "MYR", "MZM", "MZN", "NAD", "NGN", "NIO", "NLG", "NOK", "NPR", "NZD", "OMR", "PAB",
            "PEN", "PGK", "PHP", "PKR", "PLN", "PTE", "PYG", "QAR", "ROL", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDD", "SDG", "SEK", "SGD", "SHP", "SIT", "SKK",
            "SLE", "SLL", "SOS", "SPL", "SRD", "SRG", "STD", "STN", "SVC", "SYP", "SZL", "THB", "TJS", "TMM", "TMT", "TND", "TOP", "TRL", "TRY", "TTD", "TVD", "TWD", "TZS",
            "UAH", "UGX", "UNI", "USD", "UYU", "UZS", "VAL", "VEB", "VED", "VEF", "VES", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XBT", "XCD", "XDR", "XLM", "XOF", "XPD",
            "XPF", "XPT", "XRP", "YER", "ZAR", "ZMK", "ZMW", "ZWD", "ZWL"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
        convertToSpinner = findViewById(R.id.convertToSpinner);
        convertFromSpinner = findViewById(R.id.convertFromSpinner);
        convertFromEditText = findViewById(R.id.convertFromEditText);
        convertToEditText = findViewById(R.id.currencyToEditText);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, countryList);
        convertFromSpinner.setAdapter(adapter);
        convertToSpinner.setAdapter(adapter);

        convertFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        convertToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertToSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void fetchCurrencyConversion(View view) {
        String convertFrom = convertFromSpinner.getSelectedItem().toString();
        String convertTo = convertToSpinner.getSelectedItem().toString();
        String convertFromValue = convertFromEditText.getText().toString();
        String mUrl = "https://api.currencyscoop.com/v1/convert?from=" + convertFrom + "&to=" + convertTo + "&amount=" + convertFromValue + "&api_key=e00fd5f5fe7aa4cca73584d3d2b2bb5f";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrl, null,
                response -> {
                    parseJsonAndUpdateUI(response);
                }, error -> {

        });

        mQueue.add(jsonObjectRequest);
    }


    private void parseJsonAndUpdateUI(JSONObject currencyObject) {
        TextView resultTextView = findViewById(R.id.resultTextView);
        try {
            double result = currencyObject.getJSONObject("response").getDouble("value");
            String convertTo = convertToSpinner.getSelectedItem().toString();
            String convertFrom = convertFromSpinner.getSelectedItem().toString();
            String convertFromValue = convertFromEditText.getText().toString();


            double roundedResult = round(result * 100.0) / 100.0;
            resultTextView.setText(convertFromValue + " " + convertFrom + " is " + roundedResult + " " + convertTo);
            convertToEditText.setText(roundedResult + "");
            //Log.i(String.valueOf(result), "parseJsonAndUpdateUI: ");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}