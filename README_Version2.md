# TRUEFAM Bereavement Campaign Contribution Tracker

Automate the tracking of contributions for bereavement campaigns by capturing payment notifications from Zelle, Venmo, Cash App, and M-PESA. This app extracts key details such as sender name, amount, member ID, and date from both SMS and email notifications, then updates a specified Google Sheet in real-time.

---

## Features

- **SMS Parsing:** Reads and parses incoming payment notifications from Zelle, Venmo, Cash App, and M-PESA (Android only).
- **Email Parsing:** Connects to Gmail or IMAP mailbox to parse payment confirmation emails.
- **Memo/Member ID Extraction:** Extracts numeric member ID from memo/note fields using regex.
- **Google Sheets Integration:** Appends new rows to a Google Sheet with each parsed contribution.
- **Custom Time Frames:** Allows users to set a start and end datetime for message monitoring.
- **Real-time Logs & Alerts:** Provides real-time logs of updates, errors, and status in the UI.
- **Manual Override:** Enables review and manual correction of misparsed or duplicate entries.
- **Secure:** Uses OAuth2 and encrypted storage for email and sheet connections.
- **Duplicate Prevention:** Detects and prevents duplicate entries based on transaction reference or timestamp.

---

## Supported Notification Formats

### 📱 SMS Samples

- Zelle: `Zelle: You received $20.00 from John Doe on 07/25/2025. Memo: 784293.`
- Venmo: `Venmo: Mary Kamau paid you $20.00 – "918245" on 07/25/2025.`
- Cash App: `Cash App: You received $20.00 from Carlos Diaz on 07/25/2025. Note: 675312.`
- M-PESA: `M-PESA: Confirmed. You have received Ksh 2,500 from Jane Mwangi on 25/07/2025 at 10:45 AM. Acc. No: 918245.`

### 📧 Email Samples

- Zelle: Subject: `You’ve received money from John Doe`  
  Body: `John Doe has sent you $20.00 with Zelle® on July 25, 2025. Memo: 784293`
- Venmo: Subject: `Mary Kamau sent you $20.00`  
  Body: `Mary Kamau just sent you $20.00 on Venmo. Note: 918245`
- Cash App: Subject: `Payment Received - $20.00 from Carlos Diaz`  
  Body: `You’ve received $20.00 from Carlos Diaz on July 25, 2025. Note: 675312`

---

## Google Sheet Output Format

| Sender Name | Amount ($) | Member ID (Memo) | Date       | Source |
|-------------|------------|------------------|------------|--------|
| John Doe    | 20.00      | 784293           | 07/25/2025 | SMS    |
| Mary Kamau  | 20.00      | 918245           | 07/25/2025 | SMS    |
| Carlos Diaz | 20.00      | 675312           | 07/25/2025 | Email  |
| ...         | ...        | ...              | ...        | ...    |

---

## Getting Started

### Prerequisites

- Android device (for SMS monitoring)
- Gmail or IMAP email account
- Google Account with Sheets access
- Node.js (or Java/Kotlin for Android), Python, or your preferred stack

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/CharlesMbillo/TRUEFAM-Contribution-Tracker-App-.git
   cd TRUEFAM-Contribution-Tracker-App-
   ```

2. **Configure OAuth2 for Gmail and Google Sheets**
   - Register your app in Google Cloud Platform and enable Gmail API and Sheets API.
   - Download OAuth2 credentials and place them in the app’s config directory.

3. **Set up Input Sources**
   - Enter your phone number and email address in the app settings.
   - Paste the Google Sheet link where contributions should be logged.

4. **Set Monitoring Time Frame**
   - Select the start and end date/time for monitoring SMS and emails.

5. **Run the App**
   - Start the app and grant necessary permissions (SMS, email, Google Sheets).
   - Monitor real-time logs for incoming contributions and errors.
   - Use the manual override screen for corrections (if needed).

---

## Regex Patterns

See [PRD Appendix](PRD-Appendix.md) for complete regex details.

---

## Security

- OAuth2 is used for all external integrations.
- All sensitive credentials and tokens are encrypted at rest.
- No raw credentials are stored or logged.

---

## Developer Notes

- **SMS:** Listens via ContentObserver (Android).
- **Email:** Uses Gmail API or JavaMail (IMAP).
- **Google Sheets:** Uses Sheets API in append mode; avoids duplicates.
- **Manual Review:** UI allows for correction of misparsed entries.

---

## Contributing

PRs and issues are welcome! Please review the [PRD](PRODUCT_REQUIREMENTS_DOCUMENT.md) before contributing major features.

---

## License

[MIT](LICENSE)

---

## Acknowledgements

- Sample notification content adapted from Zelle, Venmo, Cash App, and M-PESA.
- Inspired by the needs of TRUEFAM bereavement campaign organizers.