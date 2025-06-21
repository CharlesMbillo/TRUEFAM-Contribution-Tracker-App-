public class Schedule {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    
    private Date startTime;
    private Date endTime;
    private boolean smsEnabled;
    private boolean emailEnabled;
}