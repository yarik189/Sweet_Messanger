package kanefron5.com.sweetmessenger;

import com.perm.kate.api.Auth;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginActivity extends Activity {
        private static final String TAG = "Kate.LoginActivity";

        WebView webview;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);

            webview = (WebView) findViewById(R.id.vkontakteview);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.clearCache(true);

//Чтобы получать уведомления об окончании загрузки страницы
            webview.setWebViewClient(new VkontakteWebViewClient());

//otherwise CookieManager will fall with java.lang.IllegalStateException: CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()
// CookieSyncManager.createInstance(this);

// CookieManager cookieManager = CookieManager.getInstance();
// cookieManager.removeAllCookie();

            String url = Auth.getUrl(Constants.API_ID, Auth.getSettings());
            webview.loadUrl(url);
        }

        class VkontakteWebViewClient extends WebViewClient {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                parseUrl(url);
            }
        }

        private void parseUrl(String url) {
            try {
                if (url == null)
                    return;
                Log.i(TAG, "url=" + url);
                if (url.startsWith(Auth.redirect_url)) {
                    if (!url.contains("error=")) {
                        String[] auth = Auth.parseRedirectUrl(url);
                        Intent intent = new Intent();
                        intent.putExtra("token", auth[0]);
                        intent.putExtra("user_id", Long.parseLong(auth[1]));
                        setResult(Activity.RESULT_OK, intent);

                        Account account = new Account();
                        account.access_token = auth[0];
                        account.user_id = Long.parseLong(auth[1]);
                        account.save(this);

                    }
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
/*
*              0000000
*            10  1 1  01
*             0   1   0                                           1
*             0  111  0                                           10
*              0000000                        11                  010
*                 0                          1111                 0101
*          000000000000000       1  1       111111                010
*         0  0         0  0       11         1111                 10
*        0   0         0   0    00000         11                  1
*       0    0         0    0000
*       0    0         0
*            0         0
*             000000000
*               0   0
*               0   0
*               0   0
*               0   0
*            0000   0000
* */