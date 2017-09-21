package com.example.administrator.ggggggggggggggggg.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author lvhongyang.
 * @time 2017/9/21.
 * @desc:
 */

public class NetWork {
    public static boolean isNetworkConnected(Context context) {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable();
                }
            }
            return false;
        }
}
