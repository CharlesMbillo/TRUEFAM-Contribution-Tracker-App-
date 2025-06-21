public class EmailFetcher {
    private static final Pattern ZELLE_EMAIL_PATTERN = Pattern.compile(
        "has sent you \\$(\\d+\\.\\d{2}).*?from (.+?) with Zelle.*?Memo: (\\d{6})", 
        Pattern.DOTALL
    );

    public void fetchEmails(Context context) {
        // 1. Gmail API Implementation
        if (usingGmailAPI(context)) {
            fetchViaGmailAPI(context);
        } 
        // 2. IMAP Fallback
        else {
            fetchViaIMAP(context);
        }
    }
    
    private void fetchViaGmailAPI(Context context) {
        List<Message> messages = gmailService.users().messages()
            .list("me")
            .setQ("from:alerts@zellepay.com OR from:venmo.com after:" + getLastCheckDate())
            .execute()
            .getMessages();
            
        for (Message msg : messages) {
            String body = extractBody(msg);
            parseEmail(body, "Email", context);
        }
    }
    
    private void parseEmail(String body, String source, Context context) {
        Matcher m = ZELLE_EMAIL_PATTERN.matcher(body);
        if (m.find()) {
            Transaction t = new Transaction(
                m.group(2), 
                Double.parseDouble(m.group(1)),
                m.group(3),
                new SimpleDateFormat("MM/dd/yyyy").format(new Date()),
                source,
                body
            );
            new SheetsUploader(context).upload(t);
        }
    }
}