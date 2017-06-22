# PrApp

**TODOs**

* Create database tables for all possible entry types
* Load database into calendar in 3 cases:
  1. On creation of activity
  2. On month change
  3. On addition/deletion of database entries
* Show entries in calendar
* Create day view
  * On click on a day in the calendar, a list of all the entries for this day shows
  (Design: ordered for time, no gaps corresponding to the time gaps between entries, each entry contains time field)
  * Add and remove entries there
* Create medication entries automatically for a planned encounter
  (default answer to "Medication taken": no)
  * Reminder (push notification, default time in settings, time changeable, deactivatable)
* Check for "obligatory/optional" on entry
* Settings view
  * Color-blind mode
  * Default reminder time for medication reminders
  * Dump database as .csv
