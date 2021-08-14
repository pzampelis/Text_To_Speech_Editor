package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import view.TextToSpeechEditorView;

class ApplyThemesTest {
	
	private static TextToSpeechEditorView gui;

	@BeforeAll
	static void init() {
		TextToSpeechEditorView.main(null);
		gui = TextToSpeechEditorView.getSingletonView();
	}

	@Test
	void applyDarkThemeTest() {
		gui.getCommandsFactory().createCommand("ApplyDarkTheme").actionPerformed(null);
		
		Color expectedBackgroundColor = Color.decode("#2b2b2b");
		Color expectedForegroundColor = Color.decode("#f8f8f2");
		Color expectedCaretColor = Color.decode("#f8f8f2");

		Color actualBackgroundColor = gui.getTextArea().getBackground();
		Color actualForegroundColor = gui.getTextArea().getForeground();
		Color actualCaretColor = gui.getTextArea().getCaretColor();

		assertEquals(expectedBackgroundColor, actualBackgroundColor, "The background color for the text area was not set");
		assertEquals(expectedForegroundColor, actualForegroundColor, "The foreground color for the text area was not set");
		assertEquals(expectedCaretColor, actualCaretColor, "The caret color for the text area was not set");
	}
	
	@Test
	void applyLightThemeTest() {
		gui.getCommandsFactory().createCommand("ApplyLightTheme").actionPerformed(null);
		
		Color expectedBackgroundColor = Color.WHITE;
		Color expectedForegroundColor = Color.decode("#333333");
		Color expectedCaretColor = Color.decode("#333333");

		Color actualBackgroundColor = gui.getTextArea().getBackground();
		Color actualForegroundColor = gui.getTextArea().getForeground();
		Color actualCaretColor = gui.getTextArea().getCaretColor();

		assertEquals(expectedBackgroundColor, actualBackgroundColor, "The background color for the text area was not set");
		assertEquals(expectedForegroundColor, actualForegroundColor, "The foreground color for the text area was not set");
		assertEquals(expectedCaretColor, actualCaretColor, "The caret color for the text area was not set");
	}

}
