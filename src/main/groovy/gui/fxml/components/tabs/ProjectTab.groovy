package gui.fxml.components.tabs

import api.enities.pages.ProjectPage

class ProjectTab<P extends ProjectPage> extends AbstractTab<P> {

    ProjectTab(String name, P page) {
        super(name, page)
        this.closable = false
    }
}
