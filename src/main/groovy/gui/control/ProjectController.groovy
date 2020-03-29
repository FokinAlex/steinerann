package gui.control

import api.enities.Project
import api.enities.pages.GraphPage
import api.enities.pages.Page
import gui.Context
import gui.fxml.components.ProjectPane
import gui.fxml.components.tabs.AbstractTab
import gui.fxml.components.tabs.GraphTab
import gui.fxml.components.tabs.ProjectTab

final class ProjectController {

    static Project project
    static ProjectPane projectPane

    static def newProject(String name) {
        projectPane = new ProjectPane()
        project = new Project(name)
        newPage(new ProjectTab<>(name, project.projectPage))
        Context.MAINWINDOW_CONTROLLER.mainPane.setCenter(projectPane)

        // TODO: replace
        Context.MAINWINDOW_CONTROLLER.newGraphPage()
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

    // TODO: move to PageController?
    static def newGraphPage(String name) {
        Page page = new GraphPage(name)
        GraphTab graphPageTab = new GraphTab(name, page)
        newPage(graphPageTab)
    }

    private static def newPage(AbstractTab pageTab) {
        projectPane + pageTab
        project + pageTab.page
    }
}
