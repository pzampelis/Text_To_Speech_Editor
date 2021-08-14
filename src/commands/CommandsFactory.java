package commands;

public class CommandsFactory {

    public ActionListener createCommand(String command) {
        switch (command) {
            case "NewDocument":
                return new NewDocument();
            case "OpenDocument":
                return new OpenDocument();
            case "SaveDocument":
                return new SaveDocument();
            case "SaveDocumentAs":
                return new SaveDocumentAs();
            case "ExitProgram":
                return new ExitProgram();
            case "UndoChange":
                return new UndoChange();
            case "RedoChange":
                return new RedoChange();
            case "CopyText":
                return new CopyText();
            case "PasteText":
                return new PasteText();
            case "CutText":
                return new CutText();
            case "DeleteText":
                return new DeleteText();
            case "SelectAllText":
                return new SelectAllText();
            case "AllTextToSpeech":
                return new AllTextToSpeech();
            case "LineToSpeech":
                return new LineToSpeech();
            case "SelectedTextToSpeech":
                return new SelectedTextToSpeech();
            case "OpenSpeechEditor":
                return new OpenSpeechEditor();
            case "ApplyAtbashEncodingToAll":
                return new ApplyEncodingToAll("Atbash");
            case "ApplyRot13EncodingToAll":
                return new ApplyEncodingToAll("Rot13");
            case "ApplyAtbashEncodingToSelected":
                return new ApplyEncodingToSelected("Atbash");
            case "ApplyRot13EncodingToSelected":
                return new ApplyEncodingToSelected("Rot13");
            case "ToggleWordWrap":
                return new ToggleWordWrap();
            case "ApplyDarkTheme":
                return new ApplyDarkTheme();
            case "ApplyLightTheme":
                return new ApplyLightTheme();
            case "OpenFontEditor":
                return new OpenFontEditor();
            case "ChangeFontFamily":
                return new ChangeFontFamily();
            case "ResetFont":
                return new ResetFont();
            case "ZoomIn":
                return new ZoomIn();
            case "ZoomOut":
                return new ZoomOut();
            case "ResetZoom":
                return new ResetZoom();
            case "OpenAboutWindow":
                return new OpenAboutWindow();
            default:
                return null;
        }
    }

}
