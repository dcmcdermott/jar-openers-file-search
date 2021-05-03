package org.jaropeners;

public class IndexedFile {

    String filepath, last_modified;

    public IndexedFile(String filepath, String last_modified) {
        this.filepath = filepath;
        this.last_modified = last_modified;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }
}
