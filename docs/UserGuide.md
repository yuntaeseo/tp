---
layout: page
title: User Guide
---
## NetWise User Guide

NetWise is a **desktop app for managing connections for Computer Science students, optimized for use via a
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, NetWise can get your connection management tasks done faster than traditional GUI apps. NetWise is best used with minimal screen space, so you can use it easily while doing other work, and it features a comfortable interface and color palette designed to reduce eye strain.

* Table of Contents
  {:toc}
--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed
    [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2526S1-CS2103T-T16-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your NetWise.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar NetWise.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all connections.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a connection named
   `John Doe` to NetWise.

   * `delete 3` : Deletes the connection with ID 3.

   * `clear` : Deletes all connections.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG_ID]` can be used as `n/John Doe t/1` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG_ID]…​` can be used as ` ` (i.e. 0 times), `t/1`, `t/1 t/3` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a connection : `add`

Adds a connection to NetWise. A connection is someone who you want to keep in contact, such as
friends, colleagues or people you met from a networking event.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG_ID]…​ [r/NOTE]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A connection can have any number of tags (including 0)
</div>

* `TAG_ID` refers to the **unique ID** of each tag, can be seen by using the [`listtag`](#listing-all-tags-listtag)
command. The tag ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/2 e/betsycrowe@example.com a/Newgate Prison p/1234567 t/1 r/She owed me lunch`,
supposed tag with ID 1 is `criminal`

### Listing all connections : `list`

Shows a list of all connections in NetWise with easy view of relationships for each connection.

Format: `list`

### Editing a connection : `edit`

Edits an existing connection in NetWise.

Format: `edit ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG_ID]…​ [r/NOTE]`

* Edits the connection with the specified `ID`.
* The ID refers to the **unique ID** each connection is given when created,
can be seen with [`list`](#listing-all-tags--listtag).
* The ID **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the connection will be removed, i.e adding of tags is not cumulative.
* You can remove all the connection’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the connection with ID 1
to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the connection with ID 2 to be `Betsy Crower` and
clears all existing tags.

### Locating connections by fields : `find`

Finds all connections (persons) whose specified fields contain any of the given keywords.
Matching is **case-insensitive** and supports **substring** (for most fields) and **word-based** (for tags) matching.

find `[n/NAME_KEYWORDS] [p/PHONE_KEYWORDS] [e/EMAIL_KEYWORDS] [a/ADDRESS_KEYWORDS] [t/TAG_IDS] [MORE KEYWORDS]...`

* At least one field prefix (`n/`, `p/`, `e/`, `a/`, `t/`) must be provided.
* Each field can take one or more keywords separated by spaces.
* Matching is partial for name, phone, email, and address (e.g., `n/Ali` matches “Alice”).
* Matching is exact (ID-based) for tags (e.g., `t/5` only matches tag “5”).
* The search across different fields uses **AND logic** — a person must match all fields provided. 
  (e.g. `n/Ali e/gmail` finds persons whose **name contains “Ali”** *and* **email contains “gmail”**.)
* The search within the same field uses **OR logic** — any one of the field’s keywords will match.
  (e.g. `a/Clementi a/Bishan` finds persons living in *either* Clementi *or* Bishan.)

Examples:
* `find n/Ali` → Finds all persons with names containing “Ali”.
* `find e/gmail a/Clementi` → Finds persons whose email contains “gmail” and address contains “Clementi”.
* `find n/Ali e/gmail a/Clementi a/Bishan t/2 t/5 t/7` → Finds persons who: 
  * name contains “Ali”:
  * email contains “gmail”,
  *	address contains “Clementi” or “Bishan”, and
  *	has tag IDs 2, 5, or 7.

### Deleting a connection : `delete`

Deletes the specified connection from NetWise.

Format: `delete ID`

* Deletes the connection with the specified `ID`.
* The ID refers to the **unique ID** each connection is given when created, can be seen with
[`list`](#listing-all-tags--listtag).
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the connection with ID 2 in NetWise.
* `find Betsy` followed by `delete 1` deletes the connection with ID 1 in the results of the `find` command.

### Clearing all connection entries : `clear`

Clears all connection entries from NetWise.

Format: `clear`

### Adding a tag : `addtag`

Adds a tag to NetWise. A tag is a keyword or label used to categorise and organise your connections.

Format: `addtag n/NAME [d/DESCRIPTION] [c/RGB_COLOR]`

* The `RGB_COLOR` describes the colour you want to set for the tag.
* `RGB_COLOR` field *must* be a HEX colour string of length 6, case-insensitive, ***without***
the hash ('#') such as 123456, 0F2AAB, abf1cd, …​
* The default `DESCRIPTION` field is "No Description"
* The default `RGB_COLOR` is gray (#808080)
* The created tag will be assigned a **fixed** unique ID

Examples:
* `addtag n/JC d/JC friends c/23f1cd`
* `addtag n/coworkers`

### Listing all tags : `listtag`

Shows a list of all tags in NetWise.

Format: `listtag`

* Unlike the connection list, the tag list does not show tags in any particular order.
It shows the tag name along with the associated **unique ID** given when the tag is created.

### Editing a tag : `edittag`

Edits a tag in NetWise.

Format: `edittag ID [n/NAME] [d/DESCRIPTION] [c/RGB_COLOR]`

* Edits the tag at the specified `ID`.
* The ID refers to the **unique ID** each tag is given when created, can be seen with
[`listtag`](#listing-all-tags--listtag).
* The ID **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can change the description or colour back to the default option by typing `d/` or `c/` without
  specifying any additional information after it.

Examples:
* `edittag 1 d/my extended family c/099fca` changes the description of tag 1 to "my extended family",
and set its color to the color with hex code #099fca.
* `edittag 2 n/Prof d/ c/` changes the name of tag 1 to "Prof", and set both description and color to default
value mentioned [above](#adding-a-tag--addtag).

### Deleting a tag : `deletetag`

Deletes a tag from NetWise.

Format: `deletetag ID`

* Deletes the tag at the specified `ID`.
* The ID refers to the **unique ID** each tag is given when created, can be seen with
[`listtag`](#listing-all-tags--listtag).
* The ID **must be a positive integer** 1, 2, 3, …​

Example:
* `delete 2` to delete the tag with ID 2

### Adding a relationship : `addrel`

Adds a relationship to NetWise. A relationship links any two connections together.

Format: `addrel p1/CONNECTION_1 p2/CONNECTION_2 d/DESCRIPTION`

* `CONNECTION_1` and `CONNECTION_2` refers to the unique IDs of the two connections that this relationship links.
* `DESCRIPTION` is a field to describe the relationship, e.g.: colleagues from ABC company

Examples:

* `addrel p1/1 p2/2 d/childhood friends`. Adds a relationship between the connections with ID 1 and 2, noting
that they are childhood friends.

### Listing all relationships : `listrel`

Shows a list of relationships and people who are connected to p1. If p2 is stated as well, listrel will
give a string of connections of how p1 and p2 are related to each other if a connection exists.

Format: `listrel p1/CONNECTION_1 [p2/CONNECTION_2]`

Examples:
*  `listrel p1/1`. Shows a list of connections who has a relationship to ID 1 and the description of their relationship.
*  `listrel p1/1 p2/2`. Shows a list of relationships in order to see how connection 1 may be connected to connection 2
via a chain of relationships.

### Editing a relationship : `editrel`

Edits the description of a relationship in NetWise.

Format: `editrel p1/CONNECTION_1 p2/CONNECTION_2 d/DESCRIPTION`

* Edits the relationship between p1 and p2. `editrel` will edit the description of the relationship between p1 and p2.
* All fields must be provided.

Examples:

* `editrel p1/1 p2/2 d/highschool friends`. Edits the description of the relationship between connection ID 1 and 
connection ID 2.

### Deleting a relationship : `deleterel`

Deletes a relationship from NetWise.

Format: `deleterel p1/CONNECTION_1 p2/CONNECTION_2`

* Deletes the relationship between p1 and p2.

Examples:

* `deleterel p1/1 p2/2`. Deletes the relationship between connection ID 1 and connection ID 2.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

NetWise data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Editing the data file

NetWise data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, NetWise will discard all data and start with an
empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
<br>
Furthermore, certain edits can cause NetWise to behave in unexpected ways
(e.g., if a value entered is outside of the acceptable range).
Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that
contains the data of your previous NetWise home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using
only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created
by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the
keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear.
The remedy is to manually restore the minimized Help Window.
--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                    | Format, Examples                                                                                                                                                                       |
|---------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add connection**        | `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG_ID]…​ [r/NOTE]` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/1 t/2 r/owes me lunch` |
| **Clear connection list** | `clear`                                                                                                                                                                                |
| **Delete connection**     | `delete ID`<br> e.g., `delete 3`                                                                                                                                                       |
| **Edit connection**       | `edit ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG_ID]…​ [r/NOTE]`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                   |
| **Find connection**       | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                             |
| **List connection**       | `list`                                                                                                                                                                                 |
| **Add tag**               | `addtag n/NAME [d/DESCRIPTION] [c/RGB_COLOR]` <br> e.g. `addtag n/JC d/JC friends c/23f1cd`                                                                                            |
| **Delete tag**            | `deletetag ID` <br> e.g. `deletetag 2`                                                                                                                                                 |
| **Edit tag**              | `edittag ID [n/NAME] [d/DESCRIPTION] [c/RGB_COLOR]` <br> e.g. `edittag 1 d/my extended family c/099fca`                                                                                |
| **List tag**              | `listtag`                                                                                                                                                                              |
| **Add relationship**      | `addrel p1/CONNECTION_1 p2/CONNECTION_2 d/DESCRIPTION` <br> e.g. `addrel p1/1 p2/2 d/friends`                                                                                          |
| **List relationships**    | `listrel p1/CONNECTION_1 [p2/CONNECTION_2]`  <br> e.g. `listrel p1/1 p2/4`                                                                                                             |
| **Edit relationship**     | `editrel p1/CONNECTION_1 p2/CONNECTION_2 d/DESCRIPTION` <br> e.g. `editrel p1/1 p2/2 d/enemies`                                                                                        |
| **Delete relationship**   | `deleterel p1/CONNECTION_1 p2/CONNECTION_2` <br> e.g. `deleterel p1/1 p2/2`                                                                                                            |
| **Exit program**          | `exit`                                                                                                                                                                                 |
| **Help**                  | `help`                                                                                                                                                                                 |
