package com.jksdairyhub.jksdairyhub.Utils.Network;

import android.content.Context;
import android.util.Log;

import com.jksdairyhub.jksdairyhub.App.Models.User;
import com.jksdairyhub.jksdairyhub.Utils.AppController;
import com.jksdairyhub.jksdairyhub.Utils.DummyData;
import com.jksdairyhub.jksdairyhub.Utils.Storage.Constants;
import com.jksdairyhub.jksdairyhub.Utils.Storage.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

public class BackgroundTask {

    private ResponseListener mListener;
    private Context mContext;
    private User mUser;
    private DummyData dummyData;

    public BackgroundTask(ResponseListener listener) {
        this.mListener = listener;
        this.mContext = AppController.getAppContext();
        mUser = Preferences.getUserLoggedIn();
        dummyData = new DummyData();
    }


    public void verifyLogin(User sUser) {
//        if (Connectivity.isConnected()) {
//            JSONObject inputs = new JSONObject();
//            JSONObject params = new JSONObject();
//            try {
//                inputs.put("user_name", sUser.getUsername());
//                inputs.put("password", sUser.getPassword());
//                params.put("inputs", inputs);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String serverPath = Constants.getBaseUrlOf(Constants.GET_VERIFY_LOGIN);
//            logger(serverPath, params);
//            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, serverPath, params, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
//                        int RESP_CODE = response.getInt("Code");
//                        switch (RESP_CODE) {
//                            case 500:
//                                mListener.onResponseError(Constants.REQ_VERIFY_LOGIN, Constants.NO_DATA_FOUND, "Invalid login!");
//                                break;
//                            case 200:
//                                mListener.onResponseSuccess(Constants.REQ_VERIFY_LOGIN, response);
//                                break;
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        mListener.onResponseError(Constants.REQ_VERIFY_LOGIN, Constants.JSON_PARSE_ERROR, Constants.sJSON_PARSE_ERROR);
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    mListener.onResponseError(Constants.REQ_VERIFY_LOGIN, Constants.SERVER_ERROR, error.toString());
//                }
//            });
//            req.setRetryPolicy(new DefaultRetryPolicy(Constants.MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            AppController.getInstance().addToRequestQueue(req);
//        } else {
//            mListener.onResponseError(Constants.REQ_VERIFY_LOGIN, Constants.NETWORK_ERROR, Constants.sNETWORK_ERROR);
//        }

        if (sUser.getUsername().equals(dummyData.getUser().getUsername()) && sUser.getPassword().equals(dummyData.getUser().getPassword())) {
            try {
                mListener.onResponseSuccess(Constants.REQ_VERIFY_LOGIN, new JSONObject().put("result", dummyData.getUser()));
                Log.e("userBg", String.valueOf(new JSONObject().put("result", dummyData.getUser())));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            mListener.onResponseError(Constants.REQ_VERIFY_LOGIN, Constants.JSON_PARSE_ERROR, "Invalid login !");
        }
    }
}
