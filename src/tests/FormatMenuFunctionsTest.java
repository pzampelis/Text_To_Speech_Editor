package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import view.TextToSpeechEditorView;

class FormatMenuFunctionsTest {
	
	private static TextToSpeechEditorView gui;

	@BeforeAll
	static void init() {
		TextToSpeechEditorView.main(null);
		gui = TextToSpeechEditorView.getSingletonView();
	}
	
	@Test
	void toggleWordWrapTest() {
		gui.getCommandsFactory().createCommand("ToggleWordWrap").actionPerformed(null);
		
		Boolean expectedWordWrapState = true;
		Boolean actualWordWrapState = gui.getWordWrapCheckBoxItem().isEnabled();
		
		assertEquals(expectedWordWrapState, actualWordWrapState, "The word wrap was not enabled successfully");
	}
	
	@Test
	void openFontEditorTest() {
		gui.getCommandsFactory().createCommand("OpenFontEditor").actionPerformed(null);
		
		Boolean expectedFontEditorState = true;
		Boolean actualFontEditorState = gui.getFontEditorFrame().isVisible();
		
		assertEquals(expectedFontEditorState, actualFontEditorState, "The font editor was not opened successfully");
	}

}
