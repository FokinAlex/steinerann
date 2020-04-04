package utils

import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser

final class ChooserUtils {

    private static final FileChooser.ExtensionFilter STB_FILTER = new FileChooser.ExtensionFilter("Steinerann-project files (*.sap)", "*.sap")
    private static final FileChooser.ExtensionFilter JSON_FILTER = new FileChooser.ExtensionFilter("Json files (*.json)", "*.json")
    private static final FileChooser.ExtensionFilter TXT_FILTER = new FileChooser.ExtensionFilter("OR-Library cases (*.orl)", "*.orl")
    
    static final FileChooser PROJECT_FILE_CHOOSER = new FileChooser()
    
    static {
        PROJECT_FILE_CHOOSER.getExtensionFilters().addAll(STB_FILTER, JSON_FILTER)
        PROJECT_FILE_CHOOSER.setTitle("Chooser project file")
    }

    static final FileChooser ORL_FILE_CHOOSER = new FileChooser()
    
    static {
        ORL_FILE_CHOOSER.getExtensionFilters().add(TXT_FILTER)
        ORL_FILE_CHOOSER.setTitle("Choose OR-Library file")
    }

    static final DirectoryChooser DIRECTORY_CHOOSER = new DirectoryChooser()
    
    static {
        DIRECTORY_CHOOSER.setTitle("Choose project directory")
    }
}
