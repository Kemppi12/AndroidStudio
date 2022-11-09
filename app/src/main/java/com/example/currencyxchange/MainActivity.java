package com.example.currencyxchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    //Button convertButton;
    EditText convertFromEditText, convertToEditText;
    Spinner convertFromSpinner, convertToSpinner;
    View mResultTextView;


    //String textConvertTo = convertToSpinner.getSelectedItem().toString();
    //String textConvertFrom = convertFromSpinner.getSelectedItem().toString();

    /*public EditText getConvertFromEditText() {
        return convertFromEditText;
    }*/

    //StringBuilder sbUrl = ("https://api.exchangerate.host/convert?from=").append(convertFromSpinner).append("&to=").append(convertToSpinner).append("&amount=").append(convertFromEditText);

    //private String mUrl = "https://api.exchangerate.host/convert?from=" + textConvertFrom + "&to=" + textConvertTo + "&amount=" + convertFromEditText;
    private String mUrl = "https://api.exchangerate.host/convert?from=EUR&to=SEK&amount=256";
    private RequestQueue mQueue;

    String [] countryList = {"ADA","AED","AFN","ALL", "AMD", "ANG", "AOA", "ARS", "ATS", "AUD", "AWG", "AZM", "AZN", "BAM", "BBD", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD",
            "BTC", "BTN", "BWP", "BYN", "BYR", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNH", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CYP", "CZK", "DEM", "DJF", "DKK",
            "DOGE", "DOP", "DOT", "DZD", "EEK", "EGP", "ERN", "ESP", "ETB", "ETH", "EUR", "FIM", "FJD", "FKP", "FRF", "GBP", "GEL", "GGP", "GHC", "GHS", "GIP", "GMD","GNF",
            "GRD","GTQ","GYD","HKD","HNL", "HRK", "HTG", "HUF", "IDR", "IEP", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "ITL", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR",
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

        convertFromSpinner = findViewById(R.id.convertFromSpinner);
        convertToSpinner = findViewById(R.id.convertToSpinner);
        convertFromEditText = findViewById(R.id.convertFromEditText);
        convertToEditText = findViewById(R.id.currencyToEditText);
        mResultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, countryList);
        convertFromSpinner.setAdapter(adapter);
        convertToSpinner.setAdapter(adapter);

        //String textConvertTo = convertToSpinner.getSelectedItem().toString();
        //String textConvertFrom = convertFromSpinner.getSelectedItem().toString();

        //String mUrl;
        //mUrl = "https://api.exchangerate.host/convert?from=" + textConvertFrom + "&to=" + textConvertTo + "&amount=" + convertFromEditText;

        /*Button mConvertButton = (Button) findViewById(R.id.convertButton);
        mConvertButton.setOnClickListener(this);*/

        }

        //Button convertButton = findViewById(R.id.convertButton)




        /*convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchCurrencyConversion();
            }
        });*/


    //@Override
    //*public void onSaveInstanceState(Bundle savedInstanceState)


    /*@Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }*/

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void fetchCurrencyConversion(View view) {
        Log.i("Toimiiko" , "fetchCurrencyConversion: ");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrl, null,
                response -> {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    parseJsonAndUpdateUI (response);
                }, error -> {

                });

        mQueue.add(jsonObjectRequest);

    }

    private void parseJsonAndUpdateUI (JSONObject currencyObject) {
        TextView resultTextView = findViewById(R.id.resultTextView);
        try {
            double result = currencyObject.getJSONObject("info").getDouble("rate");
            resultTextView.setText((int) result);
            Log.i(String.valueOf(result), "parseJsonAndUpdateUI: ");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /*public void showResult (View view) {

        Intent intent = new Intent( this, MainActivity.class);
        startActivity( intent );
    }*/
}