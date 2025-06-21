public class SmsReceiver extends BroadcastReceiver {
    private static final Pattern MPESA_PATTERN = Pattern.compile(
        "Confirmed. You have received (Ksh [\\d,]+) from (.+?) on (\\d{2}/\\d{2}/\\d{4}).*Acc. No: (\\d{6})"
    );

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isMonitoringActive(context)) return; // Check user schedule
        
        for (SmsMessage sms : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
            String body = sms.getMessageBody();
            String sender = sms.getOriginatingAddress();
            long timestamp = sms.getTimestampMillis();
            
            parseSms(body, sender, timestamp, context);
        }
    }
    
    private void parseSms(String body, String sender, long timestamp, Context context) {
        Transaction transaction = null;
        
        // Zelle/Venmo/CashApp parsing (simplified)
        if (body.contains("Zelle")) {
            transaction = parseZelle(body);
        } 
        // MPESA parsing
        else if (body.contains("M-PESA")) {
            Matcher m = MPESA_PATTERN.matcher(body);
            if (m.find()) {
                transaction = new Transaction(
                    m.group(2),           // Sender
                    convertToUSD(m.group(1)), // Amount
                    m.group(4),           // Member ID
                    m.group(3),           // Date
                    "SMS",
                    body
                );
            }
        }
        
        if (transaction != null) {
            new SheetsUploader(context).upload(transaction);
        }
    }
}