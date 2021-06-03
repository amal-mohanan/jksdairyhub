package com.jksdairyhub.jksdairyhub.App.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.jksdairyhub.jksdairyhub.App.Models.User;
import com.jksdairyhub.jksdairyhub.R;
import com.jksdairyhub.jksdairyhub.Utils.DummyData;
import com.jksdairyhub.jksdairyhub.Utils.Network.BackgroundTask;
import com.jksdairyhub.jksdairyhub.Utils.Network.ResponseListener;
import com.jksdairyhub.jksdairyhub.Utils.Storage.Constants;
import com.jksdairyhub.jksdairyhub.Utils.Storage.Preferences;
import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

import org.json.JSONObject;

import java.util.Objects;

public class AuthActivity extends AppCompatActivity implements ResponseListener, FingerPrintAuthCallback {
    private AppCompatEditText edtUsername, edtPassword;
    private AppCompatTextView btnLogin, btnUpdateIp;
    private ProgressBar progressLogin;
    private LinearLayout layFinger;
    private boolean hasFingerPrintHardware = true;
    private boolean isAboveMarshmallow = true;
    private FingerPrintAuthHelper mFingerPrintAuthHelper;
    private User mUser;
    private BackgroundTask bgTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initViews();
    }

    private void initViews() {
        edtPassword = findViewById(R.id.edt_password);
        edtUsername = findViewById(R.id.edt_username);
        btnLogin = findViewById(R.id.btn_login);
        progressLogin = findViewById(R.id.progress_login);
        layFinger = findViewById(R.id.img_finger);
        btnUpdateIp = findViewById(R.id.btn_configure);

        initObjects();

    }

    private void initObjects() {
        bgTask = new BackgroundTask(this);
        mUser = new User();
        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);
        if (Preferences.getIP().length() <= 0) {
            configure();
        }
        enableFingerPrintIfRegistered();
        initListeners();
    }

    private void initListeners() {

        btnUpdateIp.setOnClickListener(v -> configure());

        btnLogin.setOnClickListener(v -> {
            if (Preferences.getIP().length() <= 0) {
                configure();
            } else {
                if (validate()) {
                    progressLogin(true);
                    bgTask.verifyLogin(mUser);
                }
            }
        });


    }

    private void progressLogin(boolean onProgress) {
        if (onProgress) {
            btnLogin.setVisibility(View.GONE);
            progressLogin.setVisibility(View.VISIBLE);
        } else {
            progressLogin.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }
    }

    private boolean validate() {
        int e = 0;
        if (Objects.requireNonNull(edtUsername.getText()).toString().isEmpty()) {
            edtUsername.setError("Please enter username ");
            e++;
        } else {
            mUser.setUsername(edtUsername.getText().toString());
        }
        if (Objects.requireNonNull(edtPassword.getText()).toString().isEmpty()) {
            edtPassword.setError("Please enter password ");
            e++;
        } else {
            mUser.setPassword(edtPassword.getText().toString());
        }
        return e == 0;
    }


    private void enableFingerPrintIfRegistered() {
        if (isAboveMarshmallow && hasFingerPrintHardware) {
            if (Preferences.getUserLoggedIn().getId() > 0) {
                if (!Preferences.isNotifiedFingerEnabled(this)) {
                    notifyFingerEnabled();
                }
                layFinger.setVisibility(View.VISIBLE);
            }
        }
    }


    private void notifyFingerEnabled() {
        Dialog dialogFinger = new Dialog(this);
        dialogFinger.setContentView(R.layout.dialog_finger_print);
        dialogFinger.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogFinger.show();
        dialogFinger.setOnCancelListener(dialogInterface -> Preferences.setNotifiedFingerEnabled());
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            dialogFinger.dismiss();
            Preferences.setNotifiedFingerEnabled();
        }, 4000);
    }

    private void configure() {
        AppCompatEditText edtReason;
        AppCompatTextView btnCancel, btnUpdate;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_ip);
        dialog.setCanceledOnTouchOutside(false);
        edtReason = dialog.findViewById(R.id.edt_reason);
        btnCancel = dialog.findViewById(R.id.btn_cancel_reason);
        btnUpdate = dialog.findViewById(R.id.btn_update_stock);
        edtReason.setText(Preferences.getIP());
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnUpdate.setOnClickListener(v -> {
            if (Objects.requireNonNull(edtReason.getText()).toString().trim().length() > 0) {
                Preferences.setIP(edtReason.getText().toString().trim());
                dialog.dismiss();
            } else {
                edtReason.setError("Please enter the IP");
            }
        });

        dialog.show();
    }


    @Override
    public void onResponseSuccess(int RequestCode, JSONObject result) {
        switch (RequestCode) {
            case Constants
                    .REQ_VERIFY_LOGIN:
                mUser = new DummyData().getUser();
                Preferences.setUserLoggedIn(mUser);
                progressLogin(false);
                startActivity(new Intent(this, MainActivity.class));
                this.finish();
                break;
        }
    }

    @Override
    public void onResponseError(int RequestCode, int ErrorCode, String Error) {
        Toast.makeText(this, Error, Toast.LENGTH_SHORT).show();
        progressLogin(false);

    }

    @Override
    public void onNoFingerPrintHardwareFound() {
        hasFingerPrintHardware = false;
        layFinger.setVisibility(View.GONE);
    }

    @Override
    public void onNoFingerPrintRegistered() {
        Toast.makeText(this, "You haven't registered any fingerprint", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBelowMarshmallow() {
        isAboveMarshmallow = false;
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        mUser = Preferences.getUserLoggedIn();
        if (mUser != null && mUser.getUsername() != null && mUser.getPassword() != null) {
            progressLogin(true);
            bgTask.verifyLogin(mUser);
        } else
            Toast.makeText(this, "Please login first time", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        String message;// = null;
        switch (errorCode) {
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                message = "Cannot recognize your finger print. Please try again.";
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
//                message = "Finger print authentication failed. Please try logging in with username and password.";
//                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
//                message = errorMessage;
//                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFingerPrintAuthHelper.stopAuth();
    }
}