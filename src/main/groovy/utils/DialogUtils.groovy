package utils

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.Label
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import utils.others.Duo

class DialogUtils {

    private static final Dialog<Duo<String, Integer>> GENERATE_GRAPH_DIALOG = new Dialog() {{
        setTitle("Сгенерировать граф")
        GridPane pane = new GridPane()
        pane.setHgap(10)
        pane.setVgap(10)
        pane.setPadding(new Insets(10, 10, 10, 10))

        Label informationLabel = new Label("Введите имя и количество вершин графа")
        pane.add(informationLabel, 0, 0)
        Label nameLabel = new Label("Имя")
        pane.add(nameLabel, 0, 1)
        TextField nameTextField = new TextField()
        pane.add(nameTextField, 1, 1)
        Label numberLabel = new Label("Количество вершин")
        pane.add(numberLabel, 0, 2)
        Spinner<Integer> numberSpinner = new Spinner<>()
        numberSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 100))
        pane.add(numberSpinner, 1, 2)

        ButtonType okType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE)
        getDialogPane().getButtonTypes().addAll( [okType, ButtonType.CANCEL])
        getDialogPane().setContent(pane)
        setResultConverter( { button ->
            okType.equals(button) ?
                    new Duo<>(
                            a: nameTextField.getText(),
                            b: numberSpinner.getValue()) :
                    null
        } )
    }}

    static Duo<String, Integer> showGenerateGraphDialog() {
        Optional<Duo<String, Integer>> optional = GENERATE_GRAPH_DIALOG.showAndWait()
        optional.isPresent() ? optional.get() as Duo<String, Integer> : null as Duo<String, Integer>
    }

}
