# Text To Speech Editor

## Description
This program is a basically a notepad developed in Java, but it also gives the user the ability to encode the text he/she wrote, and also read that text out loud.
The basic notepad features include all those features that any user expects, from "New", "Open" and "Save As", to "Zoom In", "Zoom Out" and word wrapping.
On some of the more interesting features, the user can encode the whole text or just a selected word/sentence, using the Atbash or Rot13 encoding.
The user can also request from the editor to read out loud the whole text or just a selected word/sentence as well.
There is also available a right click popup menu, so the user does not have to visit the menu bar to do some of the basic actions the editor offers.
There are two themes available, a light and a dark one for accessibility reasons.
It is also worth mentioning that the user is given the ability to change the font size and style by using the font editor provided by this program, and to also change the volume, the pitch and the rate of the voice reading the text, by using the speech editor that is also provided by this program.
Last but not least it should be noted that this program was developed with people with speech problems (and not only) in mind, so the logic behind the program is that it can be opend and used on the spot at any time, without asking the user to save the text or something similar before the editor can be able to read it out loud.

## Developed With
- Java 11
- FreeTTS
- JUnit 5

## How To Run
You can import this project to your IDE of choice and you just need to add to the project's referenced libraries all the .jar files inside the bin folder of the freetts open source library, that you can download from here: [link](https://freetts.sourceforge.io/)

## Known Issues
If you try to undo or redo the application of an encoding, you will need to double undo or double redo it, because the first time a blank space will appear at the place where the encoding was applied. This is due to the use of the setText method of the text area, that is setting the requested text to blank ("") before replacing it with the encoded one.
