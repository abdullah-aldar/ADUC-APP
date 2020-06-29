package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.activities.common.WebActivity;
import com.aldar.studentportal.worker.WebClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PaymentActivity extends AppCompatActivity {
    private String strLink;
    private WebView webView;
    private ProgressBar progressBar;
    private TextView tvNoAccess;
    private Disposable disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        tvNoAccess = findViewById(R.id.no_acess);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            strLink = bundle.getString("paymentLink");
        }

        progressBar = findViewById(R.id.progressbar);
        webView = findViewById(R.id.web_view);

        Maybe<String> StringObservable = getStringObservable();
        MaybeObserver<String> StringObserver = getStringObserver();
        StringObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(StringObserver);


    }

    private MaybeObserver<String> getStringObserver() {
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(String string) {
                loadWebView(string);
            }

            @Override
            public void onError(Throwable e) {
                progressBar.setVisibility(View.GONE);
                progressBar = null;
                Toast.makeText(PaymentActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private Maybe<String> getStringObservable() {
        return Maybe.create(emitter -> {
            if (!emitter.isDisposed()) {
                emitter.onSuccess(strLink);

            }
        });
    }

    private void loadWebView(String link) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new HelloWebViewClient());
        webView.loadUrl(link);

        if (!com.aldar.studentportal.utilities.NetworkUtils.isNetworkConnected(this)) {
            webView.setVisibility(View.GONE);
            tvNoAccess.setVisibility(View.VISIBLE);
        }

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

            }
        });
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
            if (!com.aldar.studentportal.utilities.NetworkUtils.isNetworkConnected(PaymentActivity.this)) {
                webView.setVisibility(View.GONE);
                tvNoAccess.setVisibility(View.VISIBLE);
            }

            webView.loadUrl(newUrl);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    finish();
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
