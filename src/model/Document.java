package model;

public class Document {

    private String fileName = null;
    private String filePath = null;
    private boolean isDocumentSaved = false;
    private String originalText = "";


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setIsDocumentSaved(boolean isDocumentSaved) {
        this.isDocumentSaved = isDocumentSaved;
    }

    public boolean getIsDocumentSaved() {
        return isDocumentSaved;
    }

    public void setOriginalText(String text) {
        this.originalText = text;
    }

    public String getOriginalText() {
        return originalText;
    }

}
