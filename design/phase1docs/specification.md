# THIS SPECIFICATION MARKDOWN DOES NOT APPLY ANYMORE

This was the specification we wrote for phase0 for the login system.  We have since
modified the design of our program to be based on the user responding to
actions given in a menu.  As such, most of what is written below no longer applies
to this project.

**Please look at the other documents instead.  This is kept for legacy reasons.**

# Specification

Running the project prompts options to login, signup, or exit the program.

When logged in, the user may access their history, data, or they may logout.

History corresponds to user sign in and log out dates.

data corresponds to information tied to the account, such as ban status.

If the user had admin status, they can access the commands to create, ban, or delete other accounts.


# Commands

`exit` exit the program

`login` log in to the program with an existing username and password

`signup` create an account that will be stored in the program

`logout` log out of the account

`history` access user history, only accessible when logged in

`data` access user data, only accessible when logged in

`create` create a user, only accessible when logged in

`delete` deletes a user, only accessible to admins

`ban` bans a user, only accessible to admins
