public class EmailWorker extends Worker {
    @NonNull
    @Override
    public Result doWork() {
        if (!isMonitoringTime()) return Result.success();
        
        new EmailFetcher().fetchEmails(getApplicationContext());
        return Result.success();
    }
}