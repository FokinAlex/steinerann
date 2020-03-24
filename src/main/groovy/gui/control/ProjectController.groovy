package gui.control

import gui.Context
import gui.enities.Project
import gui.enities.pages.GraphPage
import gui.enities.pages.Page
import gui.fxml.components.PageTab
import gui.fxml.components.ProjectPane

final class ProjectController {

    static Project project
    static ProjectPane projectPane
    static Map<Page, PageTab> pages = new HashMap<>()

    static def newProject(String name) {
        projectPane = new ProjectPane()
        project = new Project(name)
        newPage(project.projectPage, new PageTab(project.projectPage.name.value))
        Context.MAINWINDOW_CONTROLLER.mainPane.setCenter(projectPane)
    }

    static def renameProject(String name) {
        project?.name?.set(name)
    }

    static def closeProject() {
        project = null
        projectPane = null
        pages.clear()
    }

    // TODO: move to PageController?
    static def newGraphPage(String name) {
        Page page = new GraphPage(name)
        PageTab pageTab = new PageTab(name)
        newPage(page, pageTab)
    }

    private static def newPage(Page page, PageTab pageTab) {
        project + page
        projectPane + pageTab
        pageTab.setContent(page.getContent())
        pages.put(page, pageTab)
    }
}
