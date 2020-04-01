#### **I missed P3, please grade along with p4. The P3 commit for grading is named "P3 done - readme updated" 

# ProgressTracker 

## Introduction

I found that people nowadays either have few real hobbies or have too many interests for which they don't have the 
time. But for me, a lot of time is wasted every day by being distracted by social media and other mundanities of 
everyday life, time that could be used to better oneself.

The *ProgressTracker* can help in pursuing new interests, improving in their hobbies, or honing their skills. With a
log of weekly updates, and setting short-term and long-term goals for each subject, the *ProgressTracker* provides
organization and planning that allows for us to track how we are doing and improve efficiently and consistently.


## User Stories

### Phase 1

As a user, I want to be able to
- add a subject of interest to my collection based on recency
- remove a subject from my collection
-  set a long-term goal for each subject
- add an update to a list of updates for each subject, with a description of progress and the goal for the next 
update


### Phase 2

As a user, I want to be able to
- save my list of subjects and their progress logs 
- optionally load my subjects and their progress logs

### Phase 3
#### Please see note at the top of the page regarding P3 grading!
#### Instructions for grader:
This is a project based on adding Subject objects to a list in a ListOfSubject object.
- You can generate the first required event by: typing in the name of a subject you want to 
add in the textfield below and then pressing the enter key. This creates a button with that name in the list.
 Empty strings and duplicate names will not be added. You can delete an existing subject as well.
 
- You can generate the second required event by: pressing the button of a subject.
This takes you to the subject's page where you can add an entry to the table of updates.
To add an update, make sure the top and bottom textfields aren't empty and then press 
the update button. This will add an update to the top of the table.

- You can locate my visual component by: next to the word ProgressTracker in the screen,
there is an image of a check mark that I added.

- You can save the state of my application by: pressing the x button to close in the main window.
This prompts a pop-up which asks you if you would like to save to file. The list of subjects is saved
if you click yes.

- You can reload the state of my application by: running the app initially. When you run the app,
a pop-up will ask you if you would like to load from file. Clicking yes will bring up the 
saved list of subjects.
