package com.backpacker.yflLibrary.java;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 网络判断的工具类
 */
public class JavaNetUtils {
    private static final String TAG = JavaNetUtils.class.getSimpleName();
    /**
     * 是否有网络
     * @param var0
     * @return
     */
    public static boolean hasNetwork(Context var0) {
        if(var0 != null) {
            ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo var2 = var1.getActiveNetworkInfo();
            return var2 != null?var2.isAvailable():false;
        } else {
            return false;
        }
    }

    /**
     * 是否有数据连接
     * @param var0
     * @return
     */
    @TargetApi(13)
    public static boolean hasDataConnection(Context var0) {
        try {
            ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo var2 = var1.getNetworkInfo(1);
            if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                Log.d(TAG, "has wifi connection");
                return true;
            } else {
                var2 = var1.getNetworkInfo(0);
                if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                    Log.d(TAG, "has mobile connection");
                    return true;
                } else {
                    if(VERSION.SDK_INT >= 13) {
                        var2 = var1.getNetworkInfo(9);
                        if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                            Log.d(TAG, "has ethernet connection");
                            return true;
                        }
                    }
                    Log.d(TAG, "no data connection");
                    return false;
                }
            }
        } catch (Exception var3) {
            return false;
        }
    }
    /**
     * 是否是wifi链接
     * @param var0
     * @return
     */
    @Deprecated
    public static boolean isWifiConnection(Context var0) {
        return isWifiConnected(var0);
    }


    /**
     * 是否是手机网络链接
     * @param var0
     * @return
     */
    @Deprecated
    public static boolean isMobileConnection(Context var0) {
        return isMobileConnected(var0);
    }
    /**
     * 是否是手机网络链接
     * @param var0
     * @return
     */
    public static boolean isMobileConnected(Context var0) {
        ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo var2 = var1.getNetworkInfo(0);
        if(var2 != null && var2.isAvailable() && var2.isConnected()) {
            Log.d(TAG, "mobile is connected");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 以太网是否链接
     * @param var0
     * @return
     */
    @Deprecated
    public static boolean isEthernetConnection(Context var0) {
        return isEthernetConnected(var0);
    }
    /**
     * 以太网是否链接
     * @param var0
     * @return
     */
    public static boolean isEthernetConnected(Context var0) {
        if(VERSION.SDK_INT >= 13) {
            ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo var2 = var1.getNetworkInfo(9);
            if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                Log.d(TAG, "ethernet is connected");
                return true;
            }
        }
        return false;
    }

    /**
     * 获取wifi的SSID
     * @param var0
     * @return
     */
    public static String getWiFiSSID(Context var0) {
        ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo var2 = var1.getNetworkInfo(1);
        WifiManager var3 = (WifiManager)var0.getSystemService(Context.WIFI_SERVICE);
        WifiInfo var4 = var3.getConnectionInfo();
        return var4.getSSID();
    }

    /**
     * 获取上传缓冲区大小
     * @param var0
     * @return
     */
    public static int getUploadBufSize(Context var0) {
        ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo var2 = var1.getActiveNetworkInfo();
        return var2 != null && var2.getType() == 1?102400:(VERSION.SDK_INT >= 13 && var2 != null && var2.getType() == 9?102400:(var2 == null && isConnectionFast(var2.getType(), var2.getSubtype())?10240:1024));
    }

    /**
     * 获取下载缓冲区大小
     * @param var0
     * @return
     */
    public static int getDownloadBufSize(Context var0) {
        ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo var2 = var1.getActiveNetworkInfo();
        return var2 != null && var2.getType() == 1?102400:(VERSION.SDK_INT >= 13 && var2 != null && var2.getType() == 9?102400:(var2 == null && isConnectionFast(var2.getType(), var2.getSubtype())?30720:2024));
    }

    /**
     * 判断网络是否快速
     * @param var0
     * @param var1
     * @return
     */
    private static boolean isConnectionFast(int var0, int var1) {
        if(var0 == 1) {
            return true;
        } else if(VERSION.SDK_INT >= 13 && var0 == 9) {
            return true;
        } else {
            if(var0 == 0) {
                switch(var1) {
                case 1:
                    return false;
                case 2:
                    return false;
                case 3:
                    return true;
                case 4:
                    return false;
                case 5:
                    return true;
                case 6:
                    return true;
                case 7:
                    return false;
                case 8:
                    return true;
                case 9:
                    return true;
                case 10:
                    return true;
                default:
                    if(VERSION.SDK_INT >= 11 && (var1 == 14 || var1 == 13)) {
                        return true;
                    }
                    if(VERSION.SDK_INT >= 9 && var1 == 12) {
                        return true;
                    }
                    if(VERSION.SDK_INT >= 8 && var1 == 11) {
                        return false;
                    }
                }
            }
            return false;
        }
    }

    public static final int NETWORK_WIFI    = 1;    // wifi network
    public static final int NETWORK_4G      = 4;    // "4G" networks
    public static final int NETWORK_3G      = 3;    // "3G" networks
    public static final int NETWORK_2G      = 2;    // "2G" networks
    public static final int NETWORK_UNKNOWN = 5;    // unknown network
    public static final int NETWORK_NO      = -1;   // no network

    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    private static final int NETWORK_TYPE_IWLAN = 18;

    private static final String CMCC_ISP  = "46000"; //中国移动
    private static final String CMCC2_ISP = "46002";//中国移动
    private static final String CU_ISP    = "46001";   //中国联通
    private static final String CT_ISP    = "46003";   //中国电信

    /**
     * 打开网络设置界面
     * <p>3.0以下打开设置界面</p>
     *
     * @param context 上下文
     */
    public static void openWirelessSettings(Context context) {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    /**
     * 获取活动网络信息
     *
     * @param context 上下文
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 判断网络是否可用
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 可用<br>{@code false}: 不可用
     */
    public static boolean isAvailable(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable();
    }

    /**
     * 判断网络是否连接
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    /**
     * 判断网络是否是4G
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean is4G(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }

    /**
     * 判断wifi是否连接状态
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 连接<br>{@code false}: 未连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 获取移动网络运营商名称
     * <p>中国移动、如中国联通、中国电信</p>
     *
     * @param context 上下文
     * @return 移动网络运营商名称
     */
    public static String getNetworkOperatorName(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String np = tm != null ? tm.getNetworkOperatorName() : null;
        String teleCompany = "unknown";
        if (np != null) {
            if (np.equals(CMCC_ISP) || np.equals(CMCC2_ISP)) {
                teleCompany = "中国移动";
            } else if (np.startsWith(CU_ISP)) {
                teleCompany = "中国联通";
            } else if (np.startsWith(CT_ISP)) {
                teleCompany = "中国电信";
            }
        }
        return teleCompany;
    }

    /**
     * 获取移动终端类型
     *
     * @param context 上下文
     * @return 手机制式
     * <ul>
     * <li>{@link TelephonyManager#PHONE_TYPE_NONE } : 0 手机制式未知</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_GSM  } : 1 手机制式为GSM，移动和联通</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_CDMA } : 2 手机制式为CDMA，电信</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_SIP  } : 3</li>
     * </ul>
     */
    public static int getPhoneType(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getPhoneType() : -1;
    }


    /**
     * 获取当前的网络类型(WIFI,2G,3G,4G)
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return 网络类型
     * <ul>
     * <li>{@link #NETWORK_WIFI   } = 1;</li>
     * <li>{@link #NETWORK_4G     } = 4;</li>
     * <li>{@link #NETWORK_3G     } = 3;</li>
     * <li>{@link #NETWORK_2G     } = 2;</li>
     * <li>{@link #NETWORK_UNKNOWN} = 5;</li>
     * <li>{@link #NETWORK_NO     } = -1;</li>
     * </ul>
     */
    public static int getNetWorkType(Context context) {
        int netType = NETWORK_NO;
        NetworkInfo info = getActiveNetworkInfo(context);
        if (info != null && info.isAvailable()) {

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {

                    case NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        netType = NETWORK_2G;
                        break;

                    case NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netType = NETWORK_3G;
                        break;

                    case NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        netType = NETWORK_4G;
                        break;
                    default:

                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            netType = NETWORK_3G;
                        } else {
                            netType = NETWORK_UNKNOWN;
                        }
                        break;
                }
            } else {
                netType = NETWORK_UNKNOWN;
            }
        }
        return netType;
    }

    /**
     * 获取当前的网络类型(WIFI,2G,3G,4G)
     * <p>依赖上面的方法</p>
     *
     * @param context 上下文
     * @return 网络类型名称
     * <ul>
     * <li>NETWORK_WIFI   </li>
     * <li>NETWORK_4G     </li>
     * <li>NETWORK_3G     </li>
     * <li>NETWORK_2G     </li>
     * <li>NETWORK_UNKNOWN</li>
     * <li>NETWORK_NO     </li>
     * </ul>
     */
    public static String getNetWorkTypeName(Context context) {
        switch (getNetWorkType(context)) {
            case NETWORK_WIFI:
                return "NETWORK_WIFI";
            case NETWORK_4G:
                return "NETWORK_4G";
            case NETWORK_3G:
                return "NETWORK_3G";
            case NETWORK_2G:
                return "NETWORK_2G";
            case NETWORK_NO:
                return "NETWORK_NO";
            default:
                return "NETWORK_UNKNOWN";
        }
    }

    /**
     * 根据域名获取ip地址
     *
     * @param domain 域名
     * @return ip地址
     */
    public static String getIpAddress(final String domain) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            Future<String> fs = exec.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    InetAddress inetAddress;
                    try {
                        inetAddress = InetAddress.getByName(domain);
                        return inetAddress.getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return fs.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
