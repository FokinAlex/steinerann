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
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.Tab
import math.graphs.theory.Graph
import math.metricspaces.MetricSpace

final class ProjectController {

    static Project project
    static ProjectPane projectPane
    static Tab currentTab

    static def newProject(String name) {
        projectPane = new ProjectPane()
        project = new Project(name)

        projectPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    void changed(ObservableValue<? extends Tab> observableValue, Tab oldTab, Tab newTab) {
                        currentTab = newTab
                        Context.MAINWINDOW_CONTROLLER
                                .gui_fxml_AlgorithmsMenu__algorithmsMenu
                                .disableProperty()
                                .set(currentTab == null)
                    }
                }
        )

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

    static def generateGraphPage(String name, int number) {
        Graph graph = new Graph(MetricSpace.EUCLIDEAN)
        for (int i = 0; i < number; i++) {
            graph.newVertex(graph.getMetricSpace().point(Math.random(), Math.random()))
        }
        Page page = new GraphPage(name, graph)
        GraphTab graphPageTab = new GraphTab(name, page)
        addNewPage(graphPageTab)
    }

    private static def addNewPage(AbstractTab pageTab) {
        projectPane + pageTab
        project + pageTab.page
    }
}
