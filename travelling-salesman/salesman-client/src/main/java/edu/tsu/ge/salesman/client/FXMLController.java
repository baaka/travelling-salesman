package edu.tsu.ge.salesman.client;

import edu.tsu.ge.salesman.core.impl.Evaluator;
import edu.tsu.ge.salesman.core.input.InputType;
import edu.tsu.ge.salesman.core.model.InputMetaModel;
import edu.tsu.ge.salesman.core.model.ResultMetaModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {
    @FXML
    private TextArea resultTextArea;
    @FXML
    private TextField inputFileTextField;
    @FXML
    private TextField mutationRateTextField;
    @FXML
    private TextField tournamentSizeTextField;
    @FXML
    private CheckBox elitismCheckBox;

    private InputMetaModel inputMetaModel = new InputMetaModel();

    public void calculateButtonAction(ActionEvent event) {

        ResultMetaModel result = null;
        try {
            inputMetaModel.setMutationRate(Double.parseDouble(mutationRateTextField.getText()));
            inputMetaModel.setTournamentSize(Integer.parseInt(tournamentSizeTextField.getText()));
            inputMetaModel.setElitism(elitismCheckBox.isSelected());

            Evaluator evaluator = new Evaluator();
            result = evaluator.eval(inputMetaModel);
        } catch (Throwable t) {
            resultTextArea.setText(t.getMessage() != null ? t.getMessage() : t.toString());
        }

        if (result != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Initial Distance: ")
                    .append(result.getInitialDistance())
                    .append(System.getProperty("line.separator"))
                    .append("Final Distance: ")
                    .append(result.getFinalDistance())
                    .append(System.getProperty("line.separator"))
                    .append(result.getFittest());

            resultTextArea.setText(sb.toString());
        }
    }

    public void chooseInputAction(ActionEvent event) {
        FileChooser inputFileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Choose *.xml or *.xlsx", "*.xml", "*.xlsx");
        inputFileChooser.getExtensionFilters().add(extFilter);

        File inputFile = inputFileChooser.showOpenDialog(null);
        if (inputFile != null) {
            setFileType(inputFile.getName());
            setFileStream(inputFile);
            inputFileTextField.setText(inputFile.getAbsolutePath());
        }
    }

    private void setFileType(String fileName) {
        List<String> val = Arrays.asList(fileName.split("[.]"));
        inputMetaModel.setInputType(InputType.getInputType(val.get(val.size() - 1)));
    }

    private void setFileStream(File file) {
        try {
            inputMetaModel.setInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            resultTextArea.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
