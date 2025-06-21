public class ManualEditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Transaction transaction = getIntent().getParcelableExtra("transaction");
        
        // Pre-fill UI with transaction data
        binding.etSender.setText(transaction.getSender());
        binding.etAmount.setText(String.valueOf(transaction.getAmount()));
        binding.etMemberId.setText(transaction.getMemberId());
        
        binding.btnSave.setOnClickListener(v -> {
            // Update transaction object
            transaction.setSender(binding.etSender.getText().toString());
            // ... other fields
            
            // Upload to Sheets
            new SheetsUploader(this).upload(transaction);
        });
    }
}