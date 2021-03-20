# ProgressTracker 
*This interface is a skeleton (not complete!)*
ProgressTracker was a project for a software construction class in 2019. GUI to be updated!



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

This is a project based on adding Subject objects to a ListOfSubjects object.
- You can generate the first required event by: typing in the name of a subject you want to 
add in the textfield below and then pressing the enter key. This creates a button with that name in the list.
 Empty strings and duplicate names will not be added. 
 You can delete an existing subject and its button as well.
 
- You can generate the second required event by: pressing the button of a subject.
This takes you to the subject's page where you can add an entry to the table of updates.
To add an update, make sure both textfields aren't empty and then press 
the update button. This will add an update to the top of the table.

- You can locate my visual component by: next to the word ProgressTracker in the screen,
there is an image of a check mark that I added.

- You can save the state of my application by: when you press the x button to close the main window,
a pop-up will ask you if you would like to save to file. The list of subjects is saved
if you click yes.

- You can reload the state of my application by: running the app initially. When you run the app,
a pop-up will ask you if you would like to load from file. Clicking yes will bring up the 
saved list of subjects and create its buttons.

### Phase 4
Task 2: I have a type hierarchy other than the "Saveable" from P2. I have made this hierarchy while
working on my P3. In the panels package in ui, I have the abstract class "HelperPanel" which 
represents a more complicated panel that will go into a panel in my ProgressTracker class (which
extends JFrame). HelperPanel is extended by ListOfSubjectHelperPanel, which creates and deletes 
buttons for each subject; and SubjectHelperPanel, which is a panel showing parts of the subject
we are viewing, including a table of updates and options to add to it. UpdateTablePanel is a panel
that is a component of SubjectHelperPanel, but I did not make it extend HelperPanel because
it has a different layout and setup from what I wanted HelperPanels to have.


Task 3: One issue I solved is implementing a type hierarchy for HelperPanel, as 
described above. This is an issue I solved before P4 having no knowledge about 
this task, which the instructor allowed.

UML_Design_Diagram.jpg can be found in the root.
I drew out my UML first to identify coupling issues and it wasn't looking great.
I could not refactor my code, but instead I will talk about problems presented by the UML.

