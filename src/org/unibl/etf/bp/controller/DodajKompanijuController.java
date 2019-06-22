package org.unibl.etf.bp.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DAO.FizickoLiceDAO;
import org.unibl.etf.bp.model.DAO.KompanijaDAO;
import org.unibl.etf.bp.model.DTO.FizickoLiceDTO;
import org.unibl.etf.bp.model.DTO.KompanijaDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DodajKompanijuController implements Initializable {
	@FXML
    private TextField jib;

    @FXML
    private ComboBox<String> mjestoComboBox;

    @FXML
    private Button sacuvaj;

    @FXML
    private Button otkazi;

    @FXML
    private TextField naziv;

    @FXML
    private TextField adresa;

    @FXML
    void otkaziAction(ActionEvent event) {
    	try {
			otkazi.getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/bp/view/Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Opcije");
			// primaryStage.getIcons().add(new Image("file:garaza.jpg"));
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void sacuvajAction(ActionEvent event) {
    	String mjesto = mjestoComboBox.getSelectionModel().getSelectedItem();
		MainController.getMjestoZipCode(mjesto);
		if(mjestoComboBox.getSelectionModel().getSelectedItem()==null || MainController.getMjestoZipCode(mjestoComboBox.getSelectionModel().getSelectedItem())==-1 || 
				MainController.getAJMB()==null && naziv.getText().isEmpty() || adresa.getText().isEmpty() || jib.getText().isEmpty() ) {
			ZipAlert();
			return;
		}
		// KompanijaDTO(int zIPcode, String naziv, String adresa, String JIB)
		KompanijaDTO fk = new KompanijaDTO(MainController.getMjestoZipCode(mjestoComboBox.getSelectionModel().getSelectedItem()),naziv.getText(), adresa.getText(), jib.getText());
		KompanijaDAO.addKompanija(fk);
		try {
			sacuvaj.getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/bp/view/Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Opcije");
			// primaryStage.getIcons().add(new Image("file:garaza.jpg"));
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void ZipAlert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("GRESKA");
        alert.setHeaderText(null);
        alert.setContentText("Morate izabtati datum i mjesto i popuniti sva polja!!!");

        alert.showAndWait();
	}
    public void insertIntoComboBox() {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select Naziv from mjesto");
			while (rs.next()) {
				mjestoComboBox.getItems().add(rs.getString(1));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		insertIntoComboBox();
	}
}
