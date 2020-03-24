package gui

import gui.control.ProjectController
import gui.fxml.MainWindowController

final class Context {

    static final String MW_FXML = "MainWindow.fxml"

    static MainWindowController MAINWINDOW_CONTROLLER = new MainWindowController()

    static final ProjectController PROJECT_CONTROLLER = new ProjectController()
}
