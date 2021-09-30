package com.efa.windoor.core.constants;


public enum FileStoreEnum {

    FileQuestionPhoto("/File_Question_photo"),
    FileCVDocs("/CVs");

    FileStoreEnum(String folder){
        this.folder = folder;
    }

    private String folder;

    public String getFolder() {
        return folder;
    }
}
