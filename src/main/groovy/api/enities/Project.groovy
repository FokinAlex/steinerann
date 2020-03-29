package api.enities

import api.enities.pages.Page
import api.enities.pages.ProjectPage
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
        this.projectPage.getName().bind(this.name)
    }

    def plus(Page page) {
        pages.add(page)
    }
}
