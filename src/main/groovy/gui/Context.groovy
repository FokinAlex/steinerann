package gui

import gui.control.OrlCaseController
import gui.control.ProjectController
import gui.fxml.MainWindowController

final class Context {

    static MainWindowController MAINWINDOW_CONTROLLER = new MainWindowController()

    static final ProjectController PROJECT_CONTROLLER = new ProjectController()
    static final OrlCaseController ORL_CASE_CONTROLLER = new OrlCaseController()
}
