package commands;

import java.awt.event.ActionEvent;

public class LineToSpeech implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        audioManager.setVolume(gui.getVolume());
        audioManager.setPitch(gui.getPitch());
        audioManager.setRate(gui.getRate());

        int requestedLineNumber = getRequestedLineNumber();

        if (gui.getLineSelectionResponse() != null) {
            String requestedLineText = getRequestedLineText(requestedLineNumber);

            if (requestedLineText != null)
                audioManager.play(getRequestedLineText(requestedLineNumber));
            else {
                gui.displayProblemWindow("The line you requested does not exist");
                gui.getCommandsFactory().createCommand("LineToSpeech").actionPerformed(e);
            }
        }
    }

    public String getRequestedLineText(int requestedLineNumber) {
        int lineCounter = 0;

        for (String line : gui.getTextArea().getText().split("\\n")) {
            lineCounter++;
            if (lineCounter == requestedLineNumber)
                return line;
        }
        return null;
    }

    public int getRequestedLineNumber() {
        while (true) {
            gui.displayLineSelectionWindow();

            try {
                return Integer.parseInt(gui.getLineSelectionResponse());
            } catch (Exception ex) {
                if (gui.getLineSelectionResponse() == null)
                    return -1;
                else
                    gui.displayProblemWindow("You must give an integer");
            }
        }
    }

}
