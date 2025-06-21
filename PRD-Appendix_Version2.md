# Appendix

---

## 1. Regex Patterns

Below are regex patterns to extract needed fields from SMS and email notifications for each payment source:

### General Memo/Member ID Extraction
- **Member ID (5 or 6 digit number):**  
  ```regex
  \b\d{5,6}\b
  ```

### Source-specific Patterns

#### Zelle (SMS & Email)
- **Sender Name:**  
  ```
  from ([A-Za-z\s]+)
  ```
- **Amount:**  
  ```
  received \$([0-9,.]+)
  ```
- **Date:**  
  ```
  on (\d{2}/\d{2}/\d{4})
  ```
- **Memo/Member ID:**  
  ```
  Memo[:\s]*([0-9]{5,6})
  ```

#### Venmo (SMS)
- **Sender Name:**  
  ```
  Venmo: ([A-Za-z\s]+) paid you
  ```
- **Amount:**  
  ```
  paid you \$([0-9,.]+)
  ```
- **Date:**  
  ```
  on (\d{2}/\d{2}/\d{4})
  ```
- **Memo/Member ID:**  
  ```
  ["“”']([0-9]{5,6})["“”'] 
  ```

#### Cash App (SMS & Email)
- **Sender Name:**  
  ```
  from ([A-Za-z\s]+)
  ```
- **Amount:**  
  ```
  received \$([0-9,.]+)
  ```
- **Date:**  
  ```
  on (\d{2}/\d{2}/\d{4})
  ```
- **Note/Member ID:**  
  ```
  Note[:\s]*([0-9]{5,6})
  ```

#### M-PESA (SMS)
- **Sender Name:**  
  ```
  from ([A-Za-z\s]+)
  ```
- **Amount:**  
  ```
  received Ksh\s?([0-9,]+)
  ```
- **Date:**  
  ```
  on (\d{2}/\d{2}/\d{4})
  ```
- **Acc. No/Member ID:**  
  ```
  Acc\. No: ([0-9]{5,6})
  ```

---

## 2. Google Sheet Template

The output Google Sheet should have the following columns in this order:

| Sender Name | Amount ($) | Member ID (Memo) | Date       | Source |
|-------------|------------|------------------|------------|--------|
| John Doe    | 20.00      | 784293           | 07/25/2025 | SMS    |
| Mary Kamau  | 20.00      | 918245           | 07/25/2025 | SMS    |
| Carlos Diaz | 20.00      | 675312           | 07/25/2025 | SMS    |
| Linda Okoro | 20.00      | 432789           | 07/25/2025 | SMS    |
| Amina Yusuf | 20.00      | 129873           | 07/25/2025 | Email  |

**Column headers (first row):**
- Sender Name
- Amount ($)
- Member ID (Memo)
- Date
- Source

---

## 3. Wireframes/Mockups

### A. Home/Setup Screen

```
+-----------------------------------------------------+
| TRUEFAM Contribution Tracker                        |
+-----------------------------------------------------+
| [ Input ] Phone Number:      [___________________]  |
| [ Input ] Email Address:     [___________________]  |
| [ Input ] Google Sheet Link: [___________________]  |
|                                                     |
| [ Select ] Start Date/Time:  [______/______/_____]  |
| [ Select ] End Date/Time:    [______/______/_____]  |
|                                                     |
| [ Start Monitoring ]   [ View Logs ]                |
+-----------------------------------------------------+
```

### B. Logs/Alerts Screen

```
+-----------------------------------------------------+
| Real-Time Logs                                      |
+-----------------------------------------------------+
| [✓] 07/25/2025 - SMS from John Doe: $20, Memo 784293|
| [✓] 07/25/2025 - Email from Mary Kamau: $20, Memo...|
| [!] 07/25/2025 - Parsing error: Unable to extract...|
|                                                     |
| [ Retry ]    [ Manual Edit ]    [ Export Logs ]     |
+-----------------------------------------------------+
```

### C. Manual Edit/Override Screen

```
+-----------------------------------------------------+
| Review/Correct Entry                                |
+-----------------------------------------------------+
| Sender Name:     [ John Doe        ]                |
| Amount ($):      [ 20.00           ]                |
| Member ID:       [ 784293           ]               |
| Date:            [ 07/25/2025      ]                |
| Source:          [ SMS             ]                |
|                                                     |
| [ Save Changes ]   [ Discard ]                      |
+-----------------------------------------------------+
```

---

*For high-fidelity mockups, consider using Figma, Balsamiq, or similar design tools. The above are low-fidelity wireframes to clarify UI and flow.*