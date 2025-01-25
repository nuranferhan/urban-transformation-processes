package com.example.demo8;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    @FXML
    private TextField idField, addressField, ageField, floorHeightField, wallThicknessField, wallTypeField, floorTypeField, roofTypeField, searchField;
    @FXML
    private ChoiceBox<String> earthquakeRiskBox, greenBox, disasterRiskBox, complianceBox, budgetBox, buildingTypeBox;
    @FXML
    private TableView<Building> buildingTable;
    @FXML
    private TableColumn<Building, String> idColumn, addressColumn, greenColumn, earthquakeRiskColumn, disasterRiskColumn, complianceColumn, budgetColumn;
    @FXML
    private TableColumn<Building, Integer> ageColumn, floorHeightColumn, wallThicknessColumn;
    @FXML
    private TableColumn<Building, String> wallTypeColumn, floorTypeColumn, roofTypeColumn;

    private ObservableList<Building> buildingList;

    public Controller() {
        buildingList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        greenColumn.setCellValueFactory(new PropertyValueFactory<>("green"));
        floorHeightColumn.setCellValueFactory(new PropertyValueFactory<>("floorHeight"));
        wallThicknessColumn.setCellValueFactory(new PropertyValueFactory<>("wallThickness"));
        wallTypeColumn.setCellValueFactory(new PropertyValueFactory<>("wallType"));
        floorTypeColumn.setCellValueFactory(new PropertyValueFactory<>("floorType"));
        roofTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roofType"));
        earthquakeRiskColumn.setCellValueFactory(new PropertyValueFactory<>("earthquakeRisk"));
        disasterRiskColumn.setCellValueFactory(new PropertyValueFactory<>("disasterRisk"));
        complianceColumn.setCellValueFactory(new PropertyValueFactory<>("compliance"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));

        buildingTable.setItems(buildingList);
    }

    @FXML
    private void searchById(KeyEvent event) {
        String searchText = searchField.getText().toLowerCase();
        ObservableList<Building> filteredList = FXCollections.observableArrayList();

        for (Building building : buildingList) {
            if (building.getId().toLowerCase().contains(searchText)) {
                filteredList.add(building);
            }
        }

        buildingTable.setItems(filteredList);
    }

    @FXML
    private void addBuilding() {
        try {
            String id = idField.getText();
            String address = addressField.getText();
            int age = Integer.parseInt(ageField.getText());
            String green = greenBox.getValue();
            int floorHeight = Integer.parseInt(floorHeightField.getText());
            int wallThickness = Integer.parseInt(wallThicknessField.getText());
            String wallType = wallTypeField.getText();
            String floorType = floorTypeField.getText();
            String roofType = roofTypeField.getText();
            String earthquakeRisk = earthquakeRiskBox.getValue();
            String disasterRisk = disasterRiskBox.getValue();
            String compliance = complianceBox.getValue();
            String budget = budgetBox.getValue();
            String buildingType = buildingTypeBox.getValue();

            if (id.isEmpty() || address.isEmpty() || green == null || earthquakeRisk == null || disasterRisk == null || buildingType == null) {
                showAlert("Missing Field", "All fields must be filled!");
                return;
            }

            Building building;
            if ("Residential".equals(buildingType)) {
                building = new ResidentialBuilding(id, address, age, green, floorHeight, wallThickness, wallType, floorType, roofType,
                        earthquakeRisk, disasterRisk, compliance, budget);
            } else {
                building = new CommercialBuilding(id, address, age, green, floorHeight, wallThickness, wallType, floorType, roofType,
                        earthquakeRisk, disasterRisk, compliance, budget);
            }

            buildingList.add(building);

            // Call the displayInfo() method to print the building information to the console
            building.displayInfo();  // Print information of the selected building type
        } catch (NumberFormatException e) {
            showAlert("Error", "Age and floor height must be numerical.");
        }
    }

    @FXML
    private void deleteBuilding() {
        Building selected = buildingTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            buildingList.remove(selected);
        } else {
            showAlert("Delete Error", "No row selected for deletion!");
        }
    }

    @FXML
    private void editBuilding() {
        Building selected = buildingTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setAddress(addressField.getText());
            selected.setAge(Integer.parseInt(ageField.getText()));
            selected.setGreen(greenBox.getValue());
            selected.setFloorHeight(Integer.parseInt(floorHeightField.getText()));
            selected.setWallThickness(Integer.parseInt(wallThicknessField.getText()));
            selected.setWallType(wallTypeField.getText());
            selected.setFloorType(floorTypeField.getText());
            selected.setRoofType(roofTypeField.getText());
            selected.setEarthquakeRisk(earthquakeRiskBox.getValue());
            selected.setDisasterRisk(disasterRiskBox.getValue());
            selected.setCompliance(complianceBox.getValue());
            selected.setBudget(budgetBox.getValue());

            buildingTable.refresh();
        } else {
            showAlert("Edit Error", "No row selected for editing!");
        }
    }

    @FXML
    private void saveToFile() {
        try {
            File file = new File("building_data.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            for (Building building : buildingList) {
                writer.write("ID: " + building.getId() + ", Address: " + building.getAddress() + ", Age: " + building.getAge() + ", Green: " +
                        building.getGreen() + ", Floor Height: " + building.getFloorHeight() + ", Wall Thickness: " + building.getWallThickness() + ", Wall Type: " + building.getWallType() + ", Roof Type: " +
                        building.getFloorType() + ", Floor Type: " + building.getRoofType() + ", Earthquake Risk: " + building.getEarthquakeRisk() + ", Disaster Risk: " +
                        building.getDisasterRisk() + ", Compliance: " + building.getCompliance() + ", Budget: " + building.getBudget() + "\n");
            }
            writer.close();
            showAlert("Success", "Data has been successfully saved.");
        } catch (IOException e) {
            showAlert("Error", "Data could not be saved: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
