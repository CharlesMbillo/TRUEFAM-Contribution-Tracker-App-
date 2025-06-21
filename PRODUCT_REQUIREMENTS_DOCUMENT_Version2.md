# Product Requirements Document (PRD)

## Product Name
TRUEFAM Bereavement Campaign Contribution Tracker

## Document Owner
Product Manager: Charles Mbillo

## Date
2025-06-21

---

## 1. Purpose

Automate the tracking of financial contributions for bereavement campaigns by parsing payment notifications from Zelle, Venmo, Cash App, and M-PESA received via SMS and email, extracting key details, and updating a Google Sheet in real-time. The app is designed to minimize manual data entry, ensure data accuracy, and provide timely, actionable insights for campaign organizers.

---

## 2. Background

TRUEFAM campaigns rely on rapid, transparent reporting of member contributions to foster trust and accountability. Manual tracking is time-consuming and error-prone. With members using multiple payment platforms, automating the process will streamline reconciliation and provide a single source of truth.

---

## 3. Goals & Objectives

- Automatically capture and record payment notifications from supported platforms (SMS and Email).
- Extract sender, amount, member ID (from memo), and date, and log them in a centralized Google Sheet.
- Allow users to configure monitoring periods and designate a target Google Sheet.
- Ensure data integrity, privacy, and security.

---

## 4. Target Audience & Users

- TRUEFAM administrators and campaign treasurers
- Volunteers managing fundraising or bereavement campaigns
- Contributors wishing to verify their payment is logged

---

## 5. Features & Requirements

| Feature           | Description |
|-------------------|-------------|
| **SMS Access**    | Read and parse incoming SMS notifications from Zelle, Venmo, Cash App, and M-PESA using ContentObserver (Android) after explicit user permission. |
| **Email Access**  | Connect to Gmail or IMAP inbox to parse payment emails using Gmail API or JavaMail with OAuth2. |
| **Memo Parsing**  | Extract Member ID from memo/note fields using regex (e.g., `\b\d{5,6}\b`). |
| **Data Fields**   | Parse: Sender Name, Amount, Member ID, Date, and Source (SMS/Email). |
| **Google Sheets Integration** | Allow user to paste Google Sheet URL and write new rows in append mode using the Sheets API and OAuth2. |
| **Schedule Controls** | User can set start and end date/time for monitoring incoming messages. |
| **Input Setup**   | User inputs: phone number, email address, Google Sheet link. |
| **Logs & Alerts** | Real-time logs for updates, errors, misparsed messages; alert user on failures. |
| **Manual Override** | UI to manually review and edit misparsed or duplicate entries. |
| **Security**      | Secure connections (OAuth2), encrypted credential storage, privacy by design. |
| **Duplicate Prevention** | Use transaction reference or timestamp to prevent double-logging. |

---

## 6. User Stories

### As an Administrator
- I want the app to automatically record all payments with relevant details in my Google Sheet, so I don’t have to enter them manually.
- I want to set the time window for monitoring, so I only capture relevant contributions.
- I want to review and correct any incorrectly parsed entries before they are finalized.
- I want to receive alerts if there are issues parsing messages or connecting to services.

### As a Contributor
- I want to know that my payment has been recorded accurately and appears on the log.

---

## 7. Sample Inputs & Outputs

### SMS Samples
- Zelle: "Zelle: You received $20.00 from John Doe on 07/25/2025. Memo: 784293."
- Venmo: "Venmo: Mary Kamau paid you $20.00 – '918245' on 07/25/2025."
- Cash App: "Cash App: You received $20.00 from Carlos Diaz on 07/25/2025. Note: 675312."
- M-PESA: "M-PESA: Confirmed. You have received Ksh 2,500 from Jane Mwangi on 25/07/2025 at 10:45 AM. Acc. No: 918245."

### Email Samples
- Zelle: "John Doe has sent you $20.00 with Zelle® on July 25, 2025. Memo: 784293"
- Venmo: "Mary Kamau just sent you $20.00 on Venmo. Note: 918245"
- Cash App: "You’ve received $20.00 from Carlos Diaz on July 25, 2025. Note: 675312"

### Google Sheet Output
| Sender Name | Amount ($) | Member ID | Date       | Source |
|-------------|------------|-----------|------------|--------|
| John Doe    | 20.00      | 784293    | 07/25/2025 | SMS    |
| Mary Kamau  | 20.00      | 918245    | 07/25/2025 | SMS    |
| Carlos Diaz | 20.00      | 675312    | 07/25/2025 | Email  |

---

## 8. Technical/Developer Notes

- **SMS Parsing:** Use Android ContentObserver for real-time listening.
- **Email Parsing:** Use Gmail API or IMAP (JavaMail).
- **Memo Extraction:** Regex `\b\d{5,6}\b` to extract numeric member IDs.
- **Google Sheets:** Use Sheets API with OAuth2; append rows.
- **Duplicate Detection:** Use transaction reference or timestamp.
- **Manual Review:** UI for editing misparsed or duplicate entries.
- **Security:** Store OAuth tokens securely; never store raw credentials.
- **Logs:** Real-time log display in-app.

---

## 9. Non-Functional Requirements

- Responsive UI (web/mobile)
- High reliability (>99.9% up-time)
- Data privacy (encrypted storage, OAuth2)
- Scalability: Should handle hundreds of messages/day
- Accessibility: WCAG 2.1 compliance

---

## 10. Success Metrics

- 99% parsing accuracy (manual intervention <1%)
- <3 minutes from message arrival to sheet update
- >95% reduction in manual entry errors
- 100% OAuth2 compliance, no credential leaks

---

## 11. Risks & Mitigations

| Risk | Mitigation |
|------|------------|
| SMS/Email provider format changes | Regularly update parsing logic and maintain test suite |
| Duplicate entries | Use transaction ID/timestamp as unique key |
| OAuth token expiry | Auto-refresh and prompt user for re-auth when needed |
| Data privacy concerns | Use encrypted storage and transparent privacy policy |

---

## 12. Open Questions

- Should the app support iOS (given SMS access limitations)?
- Is multi-user access to the same Google Sheet required?
- Is there a need for push notifications or only in-app alerts?
- How are edits/audits logged for compliance?

---

## 13. Appendix

- **Sample messages** (see above)
- **Regex patterns**
- **Google Sheet template**
- **Wireframes/mockups** (not included—TBD)
