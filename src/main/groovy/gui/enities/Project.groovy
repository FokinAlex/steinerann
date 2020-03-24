package gui.enities

import gui.enities.pages.Page
import gui.enities.pages.ProjectPage
import javafx.beans.property.SimpleStringProperty

final class Project {

    final SimpleStringProperty name
    final Set<Page> pages
    final ProjectPage projectPage

    Project(String name) {
        this(name, new HashSet<Page>())
    }

    Project(String name, Set<Page> pages) {
        this.name = new SimpleStringProperty(name)
        this.pages = pages
        this.projectPage = new ProjectPage(name)
    }

    def plus(Page page) {
        pages.add(page)
    }
}
