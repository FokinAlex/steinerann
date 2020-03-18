package gui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

final class FXApp extends Application {

    private static final String MW_FXML = "MainWindow.fxml"

    private FXController controller
    private Parent rootElement
    private Scene scene

    @Override
    void start(Stage stage) {
        final FXMLLoader loader = new FXMLLoader()
        final InputStream stream = getClass().getResourceAsStream(MW_FXML)

        this.rootElement = loader.load(stream)
        this.controller = loader.getController()
        this.scene = new Scene(rootElement, 800, 600)

        stage.setScene(this.scene)
        stage.show()
    }

    static void main(String[] args) {
        launch(FXApp.class, args)
    }
}