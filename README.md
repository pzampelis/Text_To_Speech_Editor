# Text To Speech Editor

## Description
This program is a notepad developed in Java, with the additino of some new useful features, such as encoding some selected text and reading it out loud.
The basic editor's features include all the features a user expects from a notepad, from "New", "Open" and "Save As", to "Zoom In", "Zoom Out" and "Word Wrapping".
On some of the more interesting features, the user can encode the whole text or just a selected word / sentence, using the Atbash or Rot13 encoding.
The user can also request from the editor to read out loud the whole text or just a selected word / sentence.
There is also a right click popup menu available, so that the user does not have to visit the menu bar to use some of the basic features the editor provides.
There are two themes available, a light and a dark one for accessibility reasons.
It is also worth mentioning that the user is given the option to change the font size and style by using the font editor provided by the editor, and to also change the volume, the pitch and the rate of the voice reading the text, by using the speech editor that is also provided by the editor.
Last but not least we should mention that this program was mainly developed for people with speech problems in mind, so the logic behind it is that it can be opened and used on the spot, without asking the user to save the text before the editor can be able to read it out loud.

## Developed With
- Java 11
- FreeTTS
- JUnit 5

## How To Run
You can import this project to your IDE of choice and add to the project's referenced libraries all the ".jar" files, that are inside the bin folder of the freetts open source library. This library can be downloaded from [here](https://freetts.sourceforge.io/).

## Known Issues
If you try to undo or redo the application of an encoding, you will need to double undo or double redo it, because the first time a blank space will appear at the place where the encoding was applied. This is due to the use of the setText method of the text area, that is setting the selected text to blank ("") before replacing it with its encoding.
