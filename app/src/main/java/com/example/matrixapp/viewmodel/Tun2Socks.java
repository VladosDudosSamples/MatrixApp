package com.example.matrixapp.viewmodel;

import android.content.Context;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Tun2Socks {

    private static final String TAG = "tun2socks";
    private static volatile boolean isInitialized = false;

    public static void initialize(Context context) {
        if (isInitialized) {
            Log.w(TAG, "initialization before done");
            return;
        }

        System.loadLibrary("native-lib");
        isInitialized = true;
    }

    public static boolean startTun2Socks(
            LogLevel logLevel,
            ParcelFileDescriptor vpnInterfaceFileDescriptor,
            int vpnInterfaceMtu,
            String socksServerAddress,
            int socksServerPort,
            String netIPv4Address,
            @Nullable String netIPv6Address,
            String netmask,
            boolean forwardUdp) {
        // TODO: 9/26/21 "--dnsgw", "127.0.0.1:5353"

        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("badvpn-tun2socks"); // app name (:D)
        arguments.addAll(
                Arrays.asList("--logger", "stdout")); // set logger to stdout so can see logs in logcat
        arguments.addAll(
                Arrays.asList("--loglevel", String.valueOf(logLevel.ordinal()))); // set log level
        arguments.addAll(
                Arrays.asList("--tunfd", String.valueOf(vpnInterfaceFileDescriptor.getFd()))); // set fd
        arguments.addAll(Arrays.asList("--tunmtu", String.valueOf(vpnInterfaceMtu)));
        arguments.addAll(Arrays.asList("--netif-ipaddr", netIPv4Address));

        if (!TextUtils.isEmpty(netIPv6Address)) {
            arguments.addAll(Arrays.asList("--netif-ip6addr", netIPv6Address));
        }

        arguments.addAll(Arrays.asList("--netif-netmask", netmask));
        arguments.addAll(
                Arrays.asList(
                        "--socks-server-addr",
                        String.format(Locale.US, "%s:%d", socksServerAddress, socksServerPort)));

        if (forwardUdp) {
            arguments.add("--socks5-udp");
        }
        arguments.addAll(new ArrayList<>());

        int exitCode = start_tun2socks(arguments.toArray(new String[]{}));
        return exitCode == 0;
    }

    private static native int start_tun2socks(String[] args);


    public static native void stopTun2Socks();

    public static native void printTun2SocksHelp();

    public static native void printTun2SocksVersion();

    public enum LogLevel {
        NONE, // 0
        ERROR, // 1
        WARNING, // 2
        NOTICE, // 3
        INFO, // 4
        DEBUG // 5
    }
}