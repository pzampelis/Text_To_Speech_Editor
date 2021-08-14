package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import view.TextToSpeechEditorView;

class ReadAloudFunctionsTest {
	
	private static TextToSpeechEditorView gui;

	@BeforeAll
	static void init() {
		TextToSpeechEditorView.main(null);
		gui = TextToSpeechEditorView.getSingletonView();
	}
	
	@Test
	void openSpeechEditorTest() {
		gui.getCommandsFactory().createCommand("OpenSpeechEditor").actionPerformed(null);
		
		Boolean expectedSpeechEditorState = true;
		Boolean actualSpeechEditorState = gui.getSpeechEditorFrame().isVisible();

		assertEquals(expectedSpeechEditorState, actualSpeechEditorState, "The speech editor was not opened successfully");
	}
}
