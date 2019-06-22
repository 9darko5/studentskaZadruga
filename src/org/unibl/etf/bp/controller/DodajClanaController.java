package org.unibl.etf.bp.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.Date;
import java.util.ResourceBundle;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DAO.ClanDAO;
import org.unibl.etf.bp.model.DTO.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DodajClanaController implements Initializable{

	@FXML
	private TextField ime;

	@FXML
	private ComboBox<String> mjestoComboBox;

	@FXML
	private Button sacuvaj;

	@FXML
	private Button otkazi;

	@FXML
	private TextField brojClanskeKarte;

	@FXML
	private TextField adresa;

	@FXML
	private DatePicker datumIsteka;

	@FXML
	private TextField jmb;

	@FXML
	private TextField prezime;

	@FXML
	private TextField brojLicneKarte;

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
		LocalDate localDate = datumIsteka.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
		String mjesto = mjestoComboBox.getSelectionModel().getSelectedItem();
		MainController.getMjestoZipCode(mjesto);
		if(mjestoComboBox.getSelectionModel().getSelectedItem()==null || MainController.getMjestoZipCode(mjestoComboBox.getSelectionModel().getSelectedItem())==-1 || 
				MainController.getAJMB()==null || jmb.getText().isEmpty() || ime.getText().isEmpty() ||  prezime.getText().isEmpty() || adresa.getText().isEmpty() ||
						brojClanskeKarte.getText().isEmpty() || brojLicneKarte.getText().isEmpty()) {
			ZipAlert();
			return;
		}
		ClanDTO clan = new ClanDTO(jmb.getText(), ime.getText(), prezime.getText(), adresa.getText(),
				MainController.getMjestoZipCode(mjestoComboBox.getSelectionModel().getSelectedItem()),
				Integer.parseInt(brojClanskeKarte.getText()), brojLicneKarte.getText(), date, MainController.getAJMB());
		ClanDAO.addClan(clan);
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
	
	public void ZipAlert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("GRESKA");
        alert.setHeaderText(null);
        alert.setContentText("Morate izabtati datum i mjesto i popuniti sva polja!!!");

        alert.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		insertIntoComboBox();
	}
}
