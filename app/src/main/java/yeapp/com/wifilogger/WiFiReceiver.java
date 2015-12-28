package yeapp.com.wifilogger;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

public class WiFiReceiver extends BroadcastReceiver {

    private final static String TAG = "WiFiLogger";

    public WiFiReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        WifiManager mng = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> list = mng.getScanResults();
        Log.d(TAG, "Ricevuto");
        ContentValues cv = new ContentValues();
        long time = System.currentTimeMillis();
        WiFiDBHelper dbh = new WiFiDBHelper(context);
        SQLiteDatabase sloh = dbh.getWritableDatabase();
        sloh.beginTransaction();
        for(ScanResult scan : list){
            cv.put(DBColumns.SSID, scan.SSID);
            cv.put(DBColumns.BSSID, scan.BSSID);
            cv.put(DBColumns.SIGNAL_POWER, WifiManager.calculateSignalLevel(scan.level, 101));
            cv.put(DBColumns.TIMESTAMP, time);
        }
        sloh.insert(DBColumns.TABLE_NAME, null, cv);
        sloh.setTransactionSuccessful();
        sloh.endTransaction();
        Log.d(TAG, "aggiunto");
    }
}
