@Entity(tableName = "transactions")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id;
    
    @ColumnInfo(name = "sender")
    private String sender;
    
    @ColumnInfo(name = "amount")
    private double amount;
    
    @ColumnInfo(name = "member_id")
    private String memberId;
    
    // Other fields: date, source, rawData, status
}