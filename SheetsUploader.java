public class SheetsUploader {
    private static final String[] COLUMNS = {"Sender", "Amount", "Member ID", "Date", "Source"};
    
    public void upload(Transaction t) {
        new Thread(() -> {
            try {
                List<Object> row = Arrays.asList(
                    t.getSender(),
                    t.getAmount(),
                    t.getMemberId(),
                    t.getDate(),
                    t.getSource()
                );
                
                ValueRange body = new ValueRange()
                    .setValues(Collections.singletonList(row));
                
                Sheets service = new Sheets.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    GoogleAccountCredential.usingOAuth2(context, Collections.singleton(SheetsScopes.SPREADSHEETS))
                    .build();
                
                service.spreadsheets().values()
                    .append(getSheetId(), "A:E", body)
                    .setValueInputOption("USER_ENTERED")
                    .execute();
                
                logSuccess(t);
            } catch (Exception e) {
                saveForRetry(t); // Save to Room DB
            }
        }).start();
    }
}