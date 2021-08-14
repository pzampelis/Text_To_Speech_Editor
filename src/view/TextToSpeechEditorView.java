package view;

import commands.CommandsFactory;
import model.Document;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class TextToSpeechEditorView implements ActionListener {

    private final String MYFONTFAMILY = "Arial";
    private final int MYFONTSIZE = 20;

    private final Color MYLIGHTBACKGROUND = Color.decode("#eeeeee");
    private final Color MYLIGHTFOREGROUND = Color.DARK_GRAY;
    private final Color MYDARKBACKGROUND = Color.DARK_GRAY;
    private final Color MYDARKFOREGROUND = Color.WHITE;

    private float volume = 1.0F;
    private int pitch = 150;
    private int rate = 150;

    private static TextToSpeechEditorView gui;
    private final Document currentDocument = new Document();
    private final CommandsFactory commandsFactory = new CommandsFactory();

    private JFrame mainWindowFrame;
    private JTextArea textArea;

    private JMenu fileMenu;

    private JMenu editMenu;
    private UndoManager undoManager = new UndoManager();
    private String copiedOrCutText;

    private JMenu speechMenu;
    private String lineSelectionResponse = null;
    private JFrame speechEditorFrame;
    private JSlider volumeSlider;
    private JSlider pitchSlider;
    private JSlider rateSlider;
    private boolean isSpeechEditorOpen = false;

    private JMenu encodingMenu;

    private JMenu formatMenu;
    private JCheckBoxMenuItem wordWrapCheckBoxItem;
    private JFrame fontEditorFrame;
    private boolean isFontEditorOpen = false;
    private JComboBox<String> fontBox;
    private JSpinner fontSizeSpinner;

    private JMenu themeMenu;

    private JMenu viewMenu;

    private JMenu helpMenu;

    private JPopupMenu rightClickPopupMenu;
    private final ArrayList<JMenuItem> rightClickPopupMenuMenuItems = new ArrayList<>();

    private int confirmationWindowResponse = JOptionPane.CANCEL_OPTION;


    public TextToSpeechEditorView() {
        createFrame();
        createTextArea();
        createTopMenuBar();
        createFileMenu();
        createEditMenu();
        createSpeechMenu();
        createEncodingMenu();
        createFormatMenu();
        createThemeMenu();
        createViewMenu();
        createHelpMenu();
        createRightClickPopupMenu();
        applyRightClickPopupMenuTheme(MYLIGHTBACKGROUND, MYLIGHTFOREGROUND);

        getClipboardText();

        mainWindowFrame.setVisible(true);
    }

    public void createFrame() {
        mainWindowFrame = new JFrame("New Unsaved Document - Text To Speech Editor");
        mainWindowFrame.setSize(900, 600);
        mainWindowFrame.setLocationRelativeTo(null);
        mainWindowFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainWindowFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                commandsFactory.createCommand("ExitProgram").actionPerformed(null);
            }
        });
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
        textArea.setFont(new Font(MYFONTFAMILY, Font.PLAIN, MYFONTSIZE));
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainWindowFrame.add(scrollPane);
    }

    public void createTopMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        mainWindowFrame.setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        speechMenu = new JMenu("Speech");
        menuBar.add(speechMenu);

        encodingMenu = new JMenu("Encoding");
        menuBar.add(encodingMenu);

        formatMenu = new JMenu("Format");
        menuBar.add(formatMenu);

        themeMenu = new JMenu("Theme");
        menuBar.add(themeMenu);

        viewMenu = new JMenu("View");
        menuBar.add(viewMenu);

        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
    }

    public void createFileMenu() {
        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newItem.addActionListener(this);
        newItem.setActionCommand("NewDocument");
        fileMenu.add(newItem);

        JMenuItem openItem = new JMenuItem("Open...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        openItem.addActionListener(this);
        openItem.setActionCommand("OpenDocument");
        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(this);
        saveItem.setActionCommand("SaveDocument");
        fileMenu.add(saveItem);

        JMenuItem saveAsItem = new JMenuItem("Save As...");
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                                 KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        saveAsItem.addActionListener(this);
        saveAsItem.setActionCommand("SaveDocumentAs");
        fileMenu.add(saveAsItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                               KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        exitItem.addActionListener(this);
        exitItem.setActionCommand("ExitProgram");
        fileMenu.add(exitItem);
    }

    public void createEditMenu() {
        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        undoItem.addActionListener(this);
        undoItem.setActionCommand("UndoChange");
        editMenu.add(undoItem);

        JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                                               KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        redoItem.addActionListener(this);
        redoItem.setActionCommand("RedoChange");
        editMenu.add(redoItem);

        editMenu.addSeparator();

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        copyItem.addActionListener(this);
        copyItem.setActionCommand("CopyText");
        editMenu.add(copyItem);

        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        pasteItem.addActionListener(this);
        pasteItem.setActionCommand("PasteText");
        editMenu.add(pasteItem);

        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        cutItem.addActionListener(this);
        cutItem.setActionCommand("CutText");
        editMenu.add(cutItem);

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        deleteItem.addActionListener(this);
        deleteItem.setActionCommand("DeleteText");
        editMenu.add(deleteItem);

        editMenu.addSeparator();

        JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        selectAllItem.addActionListener(this);
        selectAllItem.setActionCommand("SelectAllText");
        editMenu.add(selectAllItem);
    }

    public void createSpeechMenu() {
        JMenuItem allTextToSpeechItem = new JMenuItem("Read All Aloud");
        allTextToSpeechItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        allTextToSpeechItem.addActionListener(this);
        allTextToSpeechItem.setActionCommand("AllTextToSpeech");
        speechMenu.add(allTextToSpeechItem);

        JMenuItem lineToSpeechItem = new JMenuItem("Read Line Aloud...");
        lineToSpeechItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        lineToSpeechItem.addActionListener(this);
        lineToSpeechItem.setActionCommand("LineToSpeech");
        speechMenu.add(lineToSpeechItem);

        JMenuItem selectedTextToSpeechItem = new JMenuItem("Read Selected Aloud");
        selectedTextToSpeechItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                                        KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        selectedTextToSpeechItem.addActionListener(this);
        selectedTextToSpeechItem.setActionCommand("SelectedTextToSpeech");
        speechMenu.add(selectedTextToSpeechItem);

        speechMenu.addSeparator();

        JMenuItem speechSettingsItem = new JMenuItem("Speech Editor...");
        speechSettingsItem.addActionListener(this);
        speechSettingsItem.setActionCommand("OpenSpeechEditor");
        speechMenu.add(speechSettingsItem);
    }

    public void displayLineSelectionWindow() {
        lineSelectionResponse = JOptionPane.showInputDialog("Select a line");
    }

    public void displaySpeechEditor() {
        speechEditorFrame = new JFrame("Speech Editor");
        speechEditorFrame.setLayout(new BorderLayout());

        JPanel volumePanel = new JPanel();
        JLabel volumeLabel = new JLabel("Volume: ");
        volumeLabel.setFont(new Font(MYFONTFAMILY, Font.PLAIN, 16));
        volumeSlider = new JSlider(0, 100, 100);

        volumeSlider.setPreferredSize(new Dimension(200, 200));
        volumeSlider.setMinorTickSpacing(25);
        volumeSlider.setMajorTickSpacing(50);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setFont(new Font(MYFONTFAMILY, Font.PLAIN, 12));

        Hashtable<Integer, JLabel> volumeLabels = new Hashtable<>();
        volumeLabels.put(0, new JLabel("Mute"));
        volumeLabels.put(50, new JLabel("Medium"));
        volumeLabels.put(100, new JLabel("Loud"));
        volumeSlider.setLabelTable(volumeLabels);
        volumeSlider.setPaintLabels(true);

        volumePanel.add(volumeLabel);
        volumePanel.add(volumeSlider);
        volumeSlider.addChangeListener(e -> this.volume = volumeSlider.getValue() * 0.01F);

        JPanel pitchPanel = new JPanel();
        JLabel pitchLabel = new JLabel("Pitch: ");
        pitchLabel.setFont(new Font(MYFONTFAMILY, Font.PLAIN, 16));
        pitchSlider = new JSlider(0, 100, 50);

        pitchSlider.setPreferredSize(new Dimension(200, 200));
        pitchSlider.setMinorTickSpacing(25);
        pitchSlider.setMajorTickSpacing(50);
        pitchSlider.setPaintTicks(true);
        pitchSlider.setFont(new Font(MYFONTFAMILY, Font.PLAIN, 12));

        Hashtable<Integer, JLabel> pitchLabels = new Hashtable<>();
        pitchLabels.put(0, new JLabel("Low"));
        pitchLabels.put(50, new JLabel("Medium"));
        pitchLabels.put(100, new JLabel("High"));
        pitchSlider.setLabelTable(pitchLabels);
        pitchSlider.setPaintLabels(true);

        pitchPanel.add(pitchLabel);
        pitchPanel.add(pitchSlider);
        pitchSlider.addChangeListener(e -> this.pitch = (pitchSlider.getValue() * 2) + 50);

        JPanel ratePanel = new JPanel();
        JLabel rateLabel = new JLabel("Rate: ");
        rateLabel.setFont(new Font(MYFONTFAMILY, Font.PLAIN, 16));
        rateSlider = new JSlider(0, 100, 50);

        rateSlider.setPreferredSize(new Dimension(200, 200));
        rateSlider.setMinorTickSpacing(25);
        rateSlider.setMajorTickSpacing(50);
        rateSlider.setPaintTicks(true);
        rateSlider.setFont(new Font(MYFONTFAMILY, Font.PLAIN, 12));

        Hashtable<Integer, JLabel> rateLabels = new Hashtable<>();
        rateLabels.put(0, new JLabel("Slow"));
        rateLabels.put(50, new JLabel("Normal"));
        rateLabels.put(100, new JLabel("Fast"));
        rateSlider.setLabelTable(rateLabels);
        rateSlider.setPaintLabels(true);

        ratePanel.add(rateLabel);
        ratePanel.add(rateSlider);
        rateSlider.addChangeListener(e -> this.rate = (rateSlider.getValue() * 2) + 50);

        speechEditorFrame.add(volumePanel, BorderLayout.NORTH);
        speechEditorFrame.add(pitchPanel, BorderLayout.CENTER);
        speechEditorFrame.add(ratePanel, BorderLayout.SOUTH);
        speechEditorFrame.setSize(350, 650);
        speechEditorFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                isSpeechEditorOpen = true;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                isSpeechEditorOpen = false;
            }
        });
        speechEditorFrame.setLocationRelativeTo(null);
        speechEditorFrame.setResizable(false);
        speechEditorFrame.setVisible(true);
        speechEditorFrame.setVisible(true);
    }

    public void displayProblemWindow(String message) {
        JOptionPane.showMessageDialog(null, message, "Problem", JOptionPane.WARNING_MESSAGE);
    }

    public void createEncodingMenu() {
        JMenuItem atbashToAllItem = new JMenuItem("Encode All: Atbash");
        atbashToAllItem.addActionListener(this);
        atbashToAllItem.setActionCommand("ApplyAtbashEncodingToAll");
        encodingMenu.add(atbashToAllItem);

        JMenuItem rot13ToAllItem = new JMenuItem("Encode All: Rot 13");
        rot13ToAllItem.addActionListener(this);
        rot13ToAllItem.setActionCommand("ApplyRot13EncodingToAll");
        encodingMenu.add(rot13ToAllItem);

        JMenuItem atbashToSelectedItem = new JMenuItem("Encode Selected: Atbash");
        atbashToSelectedItem.addActionListener(this);
        atbashToSelectedItem.setActionCommand("ApplyAtbashEncodingToSelected");
        encodingMenu.add(atbashToSelectedItem);

        JMenuItem rot13ToSelectedItem = new JMenuItem("Encode Selected: Rot 13");
        rot13ToSelectedItem.addActionListener(this);
        rot13ToSelectedItem.setActionCommand("ApplyRot13EncodingToSelected");
        encodingMenu.add(rot13ToSelectedItem);
    }

    public void createFormatMenu() {
        wordWrapCheckBoxItem = new JCheckBoxMenuItem("Word Wrap");
        wordWrapCheckBoxItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        wordWrapCheckBoxItem.addActionListener(this);
        wordWrapCheckBoxItem.setActionCommand("ToggleWordWrap");
        formatMenu.add(wordWrapCheckBoxItem);

        JMenuItem fontItem = new JMenuItem("Font Editor...");
        fontItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                                               KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        fontItem.addActionListener(this);
        fontItem.setActionCommand("OpenFontEditor");
        formatMenu.add(fontItem);
    }

    public void displayFontEditor() {
        fontEditorFrame = new JFrame("Font Editor");

        JLabel fontSizeLabel = new JLabel("Font Size: ");
        String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox<>(fontFamilies);
        fontBox.addActionListener(this);
        fontBox.setActionCommand("ChangeFontFamily");
        fontBox.setSelectedItem(textArea.getFont().getFamily());

        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        fontSizeSpinner.setValue(textArea.getFont().getSize());
        fontSizeSpinner.addChangeListener(e -> textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN,
                                                                         (int) fontSizeSpinner.getValue())));

        JButton resetFontButton = new JButton("Reset Font To Default");
        resetFontButton.addActionListener(this);
        resetFontButton.setActionCommand("ResetFont");

        fontEditorFrame.add(fontSizeLabel);
        fontEditorFrame.add(fontSizeSpinner);
        fontEditorFrame.add(fontBox);
        fontEditorFrame.add(resetFontButton);
        fontEditorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fontEditorFrame.setSize(500, 80);
        fontEditorFrame.setLayout(new FlowLayout());
        fontEditorFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                isFontEditorOpen = true;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                isFontEditorOpen = false;
            }
        });
        fontEditorFrame.setLocationRelativeTo(null);
        fontEditorFrame.setResizable(false);
        fontEditorFrame.setVisible(true);
    }

    public void createThemeMenu() {
        JMenuItem darkThemeItem = new JMenuItem("Dark");
        darkThemeItem.addActionListener(this);
        darkThemeItem.setActionCommand("ApplyDarkTheme");
        themeMenu.add(darkThemeItem);

        JMenuItem lightThemeItem = new JMenuItem("Light");
        lightThemeItem.addActionListener(this);
        lightThemeItem.setActionCommand("ApplyLightTheme");
        themeMenu.add(lightThemeItem);
    }

    public void createViewMenu() {
        JMenuItem zoomInItem = new JMenuItem("Zoom In");
        zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK));
        zoomInItem.addActionListener(this);
        zoomInItem.setActionCommand("ZoomIn");
        viewMenu.add(zoomInItem);

        JMenuItem zoomOutItem = new JMenuItem("Zoom Out");
        zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        zoomOutItem.addActionListener(this);
        zoomOutItem.setActionCommand("ZoomOut");
        viewMenu.add(zoomOutItem);

        JMenuItem resetZoomItem = new JMenuItem("Reset Zoom");
        resetZoomItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK));
        resetZoomItem.addActionListener(this);
        resetZoomItem.setActionCommand("ResetZoom");
        viewMenu.add(resetZoomItem);
    }

    public void createHelpMenu() {
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(this);
        aboutItem.setActionCommand("OpenAboutWindow");
        helpMenu.add(aboutItem);
    }

    public void displayAboutWindow() {
        String aboutName = " Name:    Text To Speech Editor\n";
        String aboutProject = " Info:       This project was inspired by an assignment for the " +
                              "Software Engineering course at the University of Ioannina\n";
        String aboutAuthor = " Author:   Petros Zampelis (pzampelis at GitHub)";
        JOptionPane.showMessageDialog(null,aboutName + aboutProject + aboutAuthor,
                                  "About this project", JOptionPane.INFORMATION_MESSAGE);
    }

    public void createRightClickPopupMenu() {
        rightClickPopupMenu = new JPopupMenu();

        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.addActionListener(this);
        undoItem.setActionCommand("UndoChange");
        rightClickPopupMenuMenuItems.add(undoItem);
        rightClickPopupMenu.add(undoItem);

        JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.addActionListener(this);
        redoItem.setActionCommand("RedoChange");
        rightClickPopupMenuMenuItems.add(redoItem);
        rightClickPopupMenu.add(redoItem);

        rightClickPopupMenu.addSeparator();

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(this);
        copyItem.setActionCommand("CopyText");
        rightClickPopupMenuMenuItems.add(copyItem);
        rightClickPopupMenu.add(copyItem);

        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.addActionListener(this);
        pasteItem.setActionCommand("PasteText");
        rightClickPopupMenuMenuItems.add(pasteItem);
        rightClickPopupMenu.add(pasteItem);

        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.addActionListener(this);
        cutItem.setActionCommand("CutText");
        rightClickPopupMenuMenuItems.add(cutItem);
        rightClickPopupMenu.add(cutItem);

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(this);
        deleteItem.setActionCommand("DeleteText");
        rightClickPopupMenuMenuItems.add(deleteItem);
        rightClickPopupMenu.add(deleteItem);

        rightClickPopupMenu.addSeparator();

        JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.addActionListener(this);
        selectAllItem.setActionCommand("SelectAllText");
        rightClickPopupMenuMenuItems.add(selectAllItem);
        rightClickPopupMenu.add(selectAllItem);

        rightClickPopupMenu.addSeparator();

        JMenuItem allTextToSpeechItem = new JMenuItem("Read All Aloud");
        allTextToSpeechItem.addActionListener(this);
        allTextToSpeechItem.setActionCommand("AllTextToSpeech");
        rightClickPopupMenuMenuItems.add(allTextToSpeechItem);
        rightClickPopupMenu.add(allTextToSpeechItem);

        JMenuItem lineToSpeechItem = new JMenuItem("Read Line Aloud...");
        lineToSpeechItem.addActionListener(this);
        lineToSpeechItem.setActionCommand("LineToSpeech");
        rightClickPopupMenuMenuItems.add(lineToSpeechItem);
        rightClickPopupMenu.add(lineToSpeechItem);

        JMenuItem selectedTextToSpeechItem = new JMenuItem("Read Selected Aloud");
        selectedTextToSpeechItem.addActionListener(this);
        selectedTextToSpeechItem.setActionCommand("SelectedTextToSpeech");
        rightClickPopupMenuMenuItems.add(selectedTextToSpeechItem);
        rightClickPopupMenu.add(selectedTextToSpeechItem);

        rightClickPopupMenu.addSeparator();

        JMenuItem atbashToAllItem = new JMenuItem("Encode All: Atbash");
        atbashToAllItem.addActionListener(this);
        atbashToAllItem.setActionCommand("ApplyAtbashEncodingToAll");
        rightClickPopupMenuMenuItems.add(atbashToAllItem);
        rightClickPopupMenu.add(atbashToAllItem);

        JMenuItem rot13ToAllItem = new JMenuItem("Encode All: Rot 13");
        rot13ToAllItem.addActionListener(this);
        rot13ToAllItem.setActionCommand("ApplyRot13EncodingToAll");
        rightClickPopupMenuMenuItems.add(rot13ToAllItem);
        rightClickPopupMenu.add(rot13ToAllItem);

        JMenuItem atbashToSelectedItem = new JMenuItem("Encode Selected: Atbash");
        atbashToSelectedItem.addActionListener(this);
        atbashToSelectedItem.setActionCommand("ApplyAtbashEncodingToSelected");
        rightClickPopupMenuMenuItems.add(atbashToSelectedItem);
        rightClickPopupMenu.add(atbashToSelectedItem);

        JMenuItem rot13ToSelectedItem = new JMenuItem("Encode Selected: Rot 13");
        rot13ToSelectedItem.addActionListener(this);
        rot13ToSelectedItem.setActionCommand("ApplyRot13EncodingToSelected");
        rightClickPopupMenuMenuItems.add(rot13ToSelectedItem);
        rightClickPopupMenu.add(rot13ToSelectedItem);

        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e))
                    rightClickPopupMenu.show(textArea, e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public void applyRightClickPopupMenuTheme(Color backgroundColor, Color foregroundColor) {
        for (JMenuItem menuItem: rightClickPopupMenuMenuItems) {
            menuItem.setBackground(backgroundColor);
            menuItem.setForeground(foregroundColor);
            menuItem.setBorder(BorderFactory.createLineBorder(backgroundColor));
        }
        rightClickPopupMenu.setBorder(BorderFactory.createLineBorder(foregroundColor));
    }

    public void getClipboardText() {
        try {
            this.copiedOrCutText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        }
        catch (Exception exception) {
            this.copiedOrCutText = null;
        }
    }

    public void displayConfirmationWindow(String message) {
        confirmationWindowResponse = JOptionPane.showConfirmDialog(null, message);
    }

    public JFrame getMainWindowFrame() {
        return mainWindowFrame;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public String getCopiedOrCutText() {
        return copiedOrCutText;
    }

    public void setCopiedOrCutText(String copiedOrCutText) {
        this.copiedOrCutText = copiedOrCutText;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public void resetUndoManager() {
        undoManager.end();
        undoManager = new UndoManager();
    }

    public String getLineSelectionResponse() {
        return lineSelectionResponse;
    }

    public JFrame getSpeechEditorFrame() {
        return speechEditorFrame;
    }

    public boolean getIsSpeechEditorOpen() {
        return isSpeechEditorOpen;
    }

    public JCheckBoxMenuItem getWordWrapCheckBoxItem() {
        return wordWrapCheckBoxItem;
    }

    public JFrame getFontEditorFrame() {
        return fontEditorFrame;
    }

    public boolean getIsFontEditorOpen() {
        return isFontEditorOpen;
    }

    public JSpinner getFontSizeSpinner() {
        return fontSizeSpinner;
    }

    public JComboBox<String> getFontBox() {
        return fontBox;
    }

    public JPopupMenu getRightClickPopupMenu() {
        return rightClickPopupMenu;
    }

    public ArrayList<JMenuItem> getRightClickPopupMenuMenuItems() {
        return rightClickPopupMenuMenuItems;
    }

    public int getConfirmationWindowResponse() {
        return confirmationWindowResponse;
    }

    public void resetConfirmationWindowResponse() {
        this.confirmationWindowResponse = JOptionPane.CANCEL_OPTION;
    }

    public String getMYFONTFAMILY() {
        return MYFONTFAMILY;
    }

    public int getMYFONTSIZE() {
        return MYFONTSIZE;
    }

    public Color getMYLIGHTBACKGROUND() {
        return MYLIGHTBACKGROUND;
    }

    public Color getMYLIGHTFOREGROUND() {
        return MYLIGHTFOREGROUND;
    }

    public Color getMYDARKBACKGROUND() {
        return MYDARKBACKGROUND;
    }

    public Color getMYDARKFOREGROUND() {
        return MYDARKFOREGROUND;
    }

    public float getVolume() {
        return volume;
    }

    public int getPitch() {
        return pitch;
    }

    public int getRate() {
        return rate;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public CommandsFactory getCommandsFactory() {
        return commandsFactory;
    }

    public static TextToSpeechEditorView getSingletonView() {
        return gui;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        commandsFactory.createCommand(command).actionPerformed(e);
	}

    public static void main(String[] args) {
        gui = new TextToSpeechEditorView();
    }
}
