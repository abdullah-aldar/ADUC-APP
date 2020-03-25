package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.mastercard.mp.checkout.AddPaymentMethodRequest;
import com.mastercard.mp.checkout.Amount;
import com.mastercard.mp.checkout.CheckoutResponseConstants;
import com.mastercard.mp.checkout.CryptoOptions;
import com.mastercard.mp.checkout.MasterpassButton;
import com.mastercard.mp.checkout.MasterpassCheckoutCallback;
import com.mastercard.mp.checkout.MasterpassCheckoutRequest;
import com.mastercard.mp.checkout.MasterpassError;
import com.mastercard.mp.checkout.MasterpassInitCallback;
import com.mastercard.mp.checkout.MasterpassMerchant;
import com.mastercard.mp.checkout.MasterpassMerchantConfiguration;
import com.mastercard.mp.checkout.MasterpassPaymentMethod;
import com.mastercard.mp.checkout.MasterpassServices;
import com.mastercard.mp.checkout.NetworkType;
import com.mastercard.mp.checkout.PaymentMethodCallback;
import com.mastercard.mp.checkout.Tokenization;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity implements MasterpassInitCallback, MasterpassCheckoutCallback  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);


        MasterpassMerchantConfiguration sampleConfig = new MasterpassMerchantConfiguration.Builder()
                .setContext(this.getApplicationContext()) //context
                .setEnvironment(MasterpassMerchantConfiguration.SANDBOX) //environment
                .setLocale(new Locale("en","UAE")) //locale
                .setMerchantName("Merchant Name")
                .setExpressCheckoutEnabled(true)//if merchant is express enabled
                .setCheckoutId("3e184cd420994e069782b26a1f0a8222")
                .build();


        MasterpassMerchant.initialize(sampleConfig, this);
    }
    @Override public void onInitSuccess() {
        try {
            MasterpassButton masterpassButton = MasterpassMerchant.getMasterpassButton(MasterpassButton.PAIRING_CHECKOUT_FLOW_ENABLED,this);
            this.addContentView(masterpassButton, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override public MasterpassCheckoutRequest getCheckoutRequest() {
        // note that Amount value is a long, so $9.99 should be given as 999 and $10 as 1000 for USD
        Amount total = new Amount(1000, Currency.getInstance(Locale.US).getCurrencyCode());  // $10.00 US
        ArrayList<NetworkType> allowedNetworkTypes = new ArrayList<>();
        allowedNetworkTypes.add(new NetworkType(NetworkType.MASTER));
        Tokenization tokenization = getSampleTokenizationObject();

        return new MasterpassCheckoutRequest.Builder()
                .setMerchantUserId("00557038091b28e0a3dc1a548aaf39b9") // I did't find any merchant key in dashboard
                .setCheckoutId("3e184cd420994e069782b26a1f0a8222")
                .setCartId("bb9410f9-e5a7-4f14-9c99-ea557d6fe2e8")
                .setAmount(total)
                .setMerchantName("Merchant Name")
                .setTokenization(tokenization) // DSRP checkout is supported by Merchant
                .setAllowedNetworkTypes(allowedNetworkTypes)
                .setSuppress3Ds(false)
                .setShippingProfileId("5886541")
                .setCallBackUrl("https://rakbankpay.gateway.mastercard.com/checkout/")
                .isShippingRequired(false)
                .setCvc2Support(false)
                .setValidityPeriodMinutes(0)
                .build();
    }

    @Override public void onCheckoutComplete(Bundle bundle) {
        String transactionId = bundle.getString(CheckoutResponseConstants.TRANSACTION_ID);

        Toast.makeText(this, "Here is the transaction ID:" + transactionId, Toast.LENGTH_SHORT).show();
    }

    @Override public void onCheckoutError(MasterpassError masterpassError) {
        Toast.makeText(this, masterpassError.message(), Toast.LENGTH_SHORT).show();
    }

    @Override public void onInitError(MasterpassError masterpassError) {
        Toast.makeText(this, masterpassError.message(), Toast.LENGTH_SHORT).show();
    }

    private Tokenization getSampleTokenizationObject() {
        try {
            ArrayList<String> format = new ArrayList<>();
            CryptoOptions.Mastercard mastercard = new CryptoOptions.Mastercard();
            CryptoOptions cryptoOptions = new CryptoOptions();
            format.add("UCAF");
            mastercard.setFormat(format);
            cryptoOptions.setMastercard(mastercard);
            String unpreditableNumber = Base64.encodeToString(
                    Integer.toString(10000).getBytes("UTF-8"), Base64.NO_WRAP);

            return new Tokenization(unpreditableNumber, cryptoOptions);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
