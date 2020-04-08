package gui.control

import api.enities.Project
import api.enities.pages.GraphPage
import api.enities.pages.OrlCasePage
import api.enities.pages.Page
import gui.Context
import gui.fxml.components.ProjectPane
import gui.fxml.components.tabs.AbstractTab
import gui.fxml.components.tabs.GraphTab
import gui.fxml.components.tabs.OrlCaseTab
import gui.fxml.components.tabs.ProjectTab

final class ProjectController {

    static Project project
    static ProjectPane projectPane

    static def newProject(String name) {
        projectPane = new ProjectPane()
        project = new Project(name)
        // addNewPage(new ProjectTab<>(name, project.projectPage))
        Context.MAINWINDOW_CONTROLLER.mainPane.setCenter(projectPane)
    }

    static def renameProject(String name) {
        project?.name?.set(name)
    }

    static def closeProject() {
        project = null
        projectPane = null
        // TODO: Clear pane
        // pages.clear()
    }

    static def newPage(OrlCasePage page) {
        OrlCaseTab tab = new OrlCaseTab(page.getName().value, page)
        addNewPage(tab)
    }

    // TODO: move to PageController?
    static def newGraphPage(String name) {
        Page page = new GraphPage(name)
        GraphTab graphPageTab = new GraphTab(name, page)
        addNewPage(graphPageTab)
    }

    private static def addNewPage(AbstractTab pageTab) {
        projectPane + pageTab
        project + pageTab.page
    }
}
