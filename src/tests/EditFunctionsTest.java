package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import view.TextToSpeechEditorView;

class EditFunctionsTest {

	private static TextToSpeechEditorView gui;

	@BeforeAll
	static void init() {
		TextToSpeechEditorView.main(null);
		gui = TextToSpeechEditorView.getSingletonView();
	}
	
	@AfterEach
	void cleanTextArea() {
		gui.getTextArea().setText("");
	}
	
	@Test
	void undoChangeTest() {
		gui.getTextArea().setText("this is a");
		gui.getTextArea().append(" test");
		gui.getCommandsFactory().createCommand("UndoChange").actionPerformed(null);
		
		String expectedUndoneText = "this is a";
		String actualUndoneText = gui.getTextArea().getText();

		assertEquals(expectedUndoneText, actualUndoneText, "The change was not undone successfully");
	}
	
	@Test
	void redoChangeTest() {
		gui.getTextArea().setText("this is a test");
		gui.getTextArea().select(9, 14);
		gui.getCommandsFactory().createCommand("DeleteText").actionPerformed(null);
		gui.getCommandsFactory().createCommand("UndoChange").actionPerformed(null);
		gui.getCommandsFactory().createCommand("RedoChange").actionPerformed(null);
		
		String expectedRedoneText = "this is a";
		String actualRedoneText = gui.getTextArea().getText();

		assertEquals(expectedRedoneText, actualRedoneText, "The change was not redone successfully");
	}

	@Test
	void copyTextTest() {
		gui.getTextArea().setText("this is a test");
		gui.getTextArea().select(0, 4);
		gui.getCommandsFactory().createCommand("CopyText").actionPerformed(null);
		
		String expectedCopiedText = "this";
		String actualCopiedText = gui.getCopiedOrCutText();

		assertEquals(expectedCopiedText, actualCopiedText, "The text was not copied successfully");
	}
	
	@Test
	void pasteTextTest() {
		gui.setCopiedOrCutText("this is a test");
		gui.getCommandsFactory().createCommand("PasteText").actionPerformed(null);
		
		String expectedPastedText = "this is a test";
		String actualPastedText = gui.getTextArea().getText();

		assertEquals(expectedPastedText, actualPastedText, "The text was not pasted successfully");
	}
	
	@Test
	void cutTextTest() {
		gui.getTextArea().setText("this is a test");
		gui.getTextArea().select(0, 4);
		gui.getCommandsFactory().createCommand("CutText").actionPerformed(null);
		
		String expectedCutText = "this";
		String expectedTextAreaText = " is a test";
		
		String actualCutText = gui.getCopiedOrCutText();
		String actualTextAreaText = gui.getTextArea().getText();

		assertEquals(expectedCutText, actualCutText, "The text was not cut successfully");
		assertEquals(expectedTextAreaText, actualTextAreaText, "The text area was not updated correctly after cut");
	}
	
	@Test
	void deleteTextTest() {
		gui.getTextArea().setText("this is a test");
		gui.getTextArea().select(0, 4);
		gui.getCommandsFactory().createCommand("DeleteText").actionPerformed(null);
		
		String expectedRemainingText = " is a test";
		String actualRemainingText = gui.getTextArea().getText();

		assertEquals(expectedRemainingText, actualRemainingText, "The text was not deleted successfully");
	}
	
	@Test
	void selectAllTextTest() {
		gui.getTextArea().setText("this is a test");
		gui.getCommandsFactory().createCommand("SelectAllText").actionPerformed(null);
		
		String expectedSelectedText = "this is a test";
		String actualSelectedText = gui.getTextArea().getSelectedText();

		assertEquals(expectedSelectedText, actualSelectedText, "The hole text was not selected successfully");
	}

}
