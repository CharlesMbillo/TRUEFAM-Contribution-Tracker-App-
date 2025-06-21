public class SecurePrefs {
    private static final String PREFS_NAME = "secure_prefs";
    
    public static void saveCredentials(Context context, String key, String value) {
        EncryptedSharedPreferences prefs = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(
            PREFS_NAME,
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
        prefs.edit().putString(key, value).apply();
    }
}