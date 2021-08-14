package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import view.TextToSpeechEditorView;

class ZoomTest {

	private static TextToSpeechEditorView gui;

	@BeforeAll
	static void init() {
		TextToSpeechEditorView.main(null);
		gui = TextToSpeechEditorView.getSingletonView();
		gui.getTextArea().setText("this is a test");
	}
	
	@AfterEach
	void resetFontSize() {
		gui.getTextArea().setFont(new Font(gui.getTextArea().getFont().getFamily(), Font.PLAIN, gui.getMYFONTSIZE()));
	}
	
	@Test
	void zoomInTest() {
		gui.getCommandsFactory().createCommand("ZoomIn").actionPerformed(null);
		int requestedFontIncrease = 10;
		
		int expectedFontSize = gui.getMYFONTSIZE() + requestedFontIncrease;
		int actualFontSize = gui.getTextArea().getFont().getSize();
		
		assertEquals(expectedFontSize, actualFontSize, "The text area was not zoomed in successfully");
	}
	
	@Test
	void zoomOutTest() {
		gui.getCommandsFactory().createCommand("ZoomOut").actionPerformed(null);
		int requestedFontDecrease = 10;
		
		int expectedFontSize = gui.getMYFONTSIZE() - requestedFontDecrease;
		int actualFontSize = gui.getTextArea().getFont().getSize();
		
		assertEquals(expectedFontSize, actualFontSize, "The text area was not zoomed out successfully");
	}
	
	@Test
	void resetZoomTest() {
		gui.getCommandsFactory().createCommand("ResetZoom").actionPerformed(null);
		
		int expectedFontSize = gui.getMYFONTSIZE();
		int actualFontSize = gui.getTextArea().getFont().getSize();
		
		assertEquals(expectedFontSize, actualFontSize, "The text area's zoom was not reseted successfully");
	}

}
