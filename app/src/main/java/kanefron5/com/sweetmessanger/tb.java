package kanefron5.com.sweetmessanger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Роман on 15.04.2015.
 */
public class tb extends FragmentActivity {
    private WebView mWebView;


    public void onCreate(Bundle savedInstanceState) {
        mWebView.setWebViewClient(new HelloWebViewClient());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talkback);

        mWebView = (WebView) findViewById(R.id.webview);
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        mWebView.loadUrl("http://romik.cf");
    }
    private class HelloWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }

    }
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
