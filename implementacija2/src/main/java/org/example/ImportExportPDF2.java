package org.example;

public class ImportExportPDF2 extends ImportExport{

    @Override
    public boolean importAction(String fileName, String configPath) {
        return false;
    }

    @Override
    public boolean exportAction(String path) {
        return false;
    }
}
