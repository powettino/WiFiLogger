package yeapp.com.wifilogger;

import android.provider.BaseColumns;

/**
 * Created by powered on 06/03/15.
 */
public class DBColumns implements BaseColumns {
    public static final String TABLE_NAME = "WiFiTable";
    public static final String TIMESTAMP = "time";
    public static final String SSID = "ssid";
    public static final String BSSID = "bssid";
    public static final String SIGNAL_POWER = "power";


}
