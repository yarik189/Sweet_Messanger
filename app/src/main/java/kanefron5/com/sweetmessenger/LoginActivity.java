package kanefron5.com.sweetmessenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;



/**
 * Created by Роман on 15.04.2015.
 */
public class LoginActivity extends Activity {

    private static final String VK_APP_ID = "4856309";
    private final VKSdkListener sdkListener = new VKSdkListener() {

        @Override
        public void onAcceptUserToken(VKAccessToken token) {
            Log.d("VkDemoApp", "onAcceptUserToken " + token);
            startLoading();
        }

        @Override
        public void onReceiveNewToken(VKAccessToken newToken) {
            Log.d("VkDemoApp", "onReceiveNewToken " + newToken);
            startLoading();
        }

        @Override
        public void onRenewAccessToken(VKAccessToken token) {
            Log.d("VkDemoApp", "onRenewAccessToken " + token);
            startLoading();
        }

        @Override
        public void onCaptchaError(VKError captchaError) {
            Log.d("VkDemoApp", "onCaptchaError " + captchaError);
        }

        @Override
        public void onTokenExpired(VKAccessToken expiredToken) {
            Log.d("VkDemoApp", "onTokenExpired " + expiredToken);
        }

        @Override
        public void onAccessDenied(VKError authorizationError) {
            Log.d("VkDemoApp", "onAccessDenied " + authorizationError);
        }

    };

    private VKRequest currentRequest;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        VKSdk.initialize(sdkListener, VK_APP_ID);

        VKUIHelper.onCreate(this);

        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VKSdk.authorize(VKScope.FRIENDS, VKScope.MESSAGES);
            }
        });

        if (VKSdk.wakeUpSession()) {
            startLoading();
        } else {
            loginButton.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        VKUIHelper.onResume(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        VKUIHelper.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this);
        if (currentRequest != null) {
            currentRequest.cancel();
        }

    }

    private void startLoading() {
        loginButton.setVisibility(View.GONE);
        if (currentRequest != null) {
            currentRequest.cancel();
        }

Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
}