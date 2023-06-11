# Text To Speech Editor

## Description
This program is a notepad application with the addition of some new useful
features such as encoding and reading text. The core editor’s features consist of
”New”, ”Open” and ”Save As”, up to ”Zoom In”, ”Zoom Out” and ”Word Wrapping”. On
some of the more interesting features, the user can listen to and/or encode the
whole text or just a selected word/sentence. For encoding we use the Atbash and
Rot13 ciphers. There is also a right click popup menu available, so that the user
does not have to visit the menu bar to use the editor’s provided features. There are
two themes available, a light and a dark one for accessibility reasons. Last but not
least it is also worth mentioning that the user is given the option to change the font
size and style by using a font editor, and to change the volume, pitch and rate of
the voice reading the text, by using a speech editor.

## Developed With
- Java 11
- FreeTTS
- JUnit 5

## How To Run
You can import this project to your IDE of choice and add to the project's referenced libraries all the ".jar" files that are inside the bin folder of the freetts open source library. This library can be downloaded from [here](https://freetts.sourceforge.io/).

## Known Issues
If you try to undo or redo the application of an encoding, you will need to double undo/redo it, because the first time a blank space will appear at the place where the encoding was applied. This is probably due to the use of the setText method of the text area that is setting the selected text to blank ("") before replacing it with its encoding.
