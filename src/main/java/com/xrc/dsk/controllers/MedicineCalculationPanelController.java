package com.xrc.dsk.controllers;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.KParamDto;
import com.xrc.dsk.model.PanelsStorage;
import com.xrc.dsk.panels.MedicineCalculationPanel;
import com.xrc.dsk.services.MedPanelDataService;
import com.xrc.dsk.services.MedicinePanelService;
import com.xrc.dsk.services.MedicineWindowService;
import com.xrc.dsk.services.PublisherService;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineCalculationPanelController  {
    private MedicinePanelService medicinePanelService;
    private MedicineWindowService medicineWindowService;
    private PanelsStorage panelsStorage = PanelsStorage.getInstance();
    private ConnectionService connectionService;
    private KParamDto kParamDto = new KParamDto();
    private Integer id;
    private MedicineDataViewModel viewModel;
//    private PublisherService publishService = new PublisherService(kParamDto);
    @FXML
    private FontAwesomeIconView addButton;

    @FXML
    private Label additionalProtection;

    @FXML
    private ComboBox<String> analogMaterial;

    @FXML
    private Label analogMaterialThickness;

    @FXML
    private Label attenuationCoefficient;

    @FXML
    private FontAwesomeIconView calculatedParamMark;

    @FXML
    private VBox calculationPanel;

    @FXML
    private FontAwesomeIconView deleteButton;

    @FXML
    private ComboBox<Double> directionCoefficient;

    @FXML
    private TextField distance;

    @FXML
    private Label dmd;

    @FXML
    private VBox exMatStorage;

    @FXML
    private Label leadEquivalent;

    @FXML
    private VBox openingsStorage;

    @FXML
    private ComboBox<String> personalCategory;

    @FXML
    private TextArea roomAssignment;

    @FXML
    private TextArea wallName;

    @FXML
    void addNewPanel(MouseEvent event) {
        panelsStorage.getPanelsStorage().getChildren().add(new MedicineCalculationPanel(++id,viewModel).getRootNode());
        System.out.println("Panel added");

    }

    @FXML
    void deleteRecentPanel(MouseEvent event) {
        panelsStorage.getPanelsStorage().getChildren().remove(calculationPanel);
    }

    @FXML
    void getDmd(ActionEvent event) {
        if (connectionService==null){
            connectionService = new ConnectionService();
        }
        dmd.setText(connectionService.getDmdByCategory(personalCategory.getSelectionModel().getSelectedItem()));
    }

    @FXML
    void getDirectionCoefficient(ActionEvent event) {
        MedPanelDataService service = new MedPanelDataService(id,viewModel);
        service.selectElement(directionCoefficient);
    }

    @FXML
    void setAdjacentRoomPurpose(KeyEvent event) {
        System.out.println("Adjacent room purpose" + event.getText());
    }

    @FXML
    void setWallSign(KeyEvent event) {
        System.out.println("wall sign" + event.getText());
    }

}
