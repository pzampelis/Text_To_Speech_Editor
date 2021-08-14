package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import view.TextToSpeechEditorView;

class ApplyEncodingsTest {

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
	void atbashEncodingToAllTest() {
		gui.getTextArea().setText("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		gui.getCommandsFactory().createCommand("ApplyAtbashEncodingToAll").actionPerformed(null);
		
		String expectedEncodedText = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba";
		String actualEncodedText = gui.getTextArea().getText();
		
		assertEquals(expectedEncodedText, actualEncodedText, "The hole text was not encoded correctly");
	}
	
	@Test
	void rot13EncodingToAllTest() {
		gui.getTextArea().setText("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		gui.getCommandsFactory().createCommand("ApplyRot13EncodingToAll").actionPerformed(null);
		
		String expectedEncodedText = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm";
		String actualEncodedText = gui.getTextArea().getText();
		
		assertEquals(expectedEncodedText, actualEncodedText, "The hole text was not encoded correctly");
	}
	
	@Test
	void atbashEncodingToSelectedTest() {
		gui.getTextArea().setText("this is a test");
		gui.getTextArea().select(10, 14);
		gui.getCommandsFactory().createCommand("ApplyAtbashEncodingToSelected").actionPerformed(null);
		
		String expectedEncodedText = "this is a gvhg";
		String actualEncodedText = gui.getTextArea().getText();
		
		assertEquals(expectedEncodedText, actualEncodedText, "The selected text was not encoded correctly");
	}
	
	@Test
	void rot13EncodingToSelectedTest() {
		gui.getTextArea().setText("this is a test");
		gui.getTextArea().select(10, 14);
		gui.getCommandsFactory().createCommand("ApplyRot13EncodingToSelected").actionPerformed(null);
		
		String expectedEncodedText = "this is a grfg";
		String actualEncodedText = gui.getTextArea().getText();
		
		assertEquals(expectedEncodedText, actualEncodedText, "The selected text was not encoded correctly");
	}

}
