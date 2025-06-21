public class DashboardActivity extends AppCompatActivity {
    private TransactionViewModel viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // RecyclerView setup
        RecyclerView rv = findViewById(R.id.rvLogs);
        rv.setLayoutManager(new LinearLayoutManager(this));
        TransactionAdapter adapter = new TransactionAdapter();
        rv.setAdapter(adapter);
        
        // Observe LiveData
        viewModel.getTransactions().observe(this, transactions -> {
            adapter.submitList(transactions);
        });
        
        // Schedule monitoring
        WorkManager.getInstance(this).enqueue(
            new PeriodicWorkRequest.Builder(
                EmailWorker.class, 
                15, 
                TimeUnit.MINUTES
            ).build()
        );
    }
}