package gui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

final class FXApp extends Application {

    private Parent rootElement

    @Override
    void start(Stage stage) {
        final FXMLLoader loader = new FXMLLoader()
        final InputStream stream = getClass().getResourceAsStream(Context.MW_FXML)
        this.rootElement = loader.load(stream)
        stream.close()
        Context.MAINWINDOW_CONTROLLER = loader.getController()
        stage.setScene(new Scene(rootElement, 800, 600))
        stage.show()
    }

    static void main(String[] args) {
        launch(FXApp.class, args)
    }
}