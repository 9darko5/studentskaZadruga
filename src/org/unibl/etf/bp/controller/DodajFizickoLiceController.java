package org.unibl.etf.bp.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DAO.FizickoLiceDAO;
import org.unibl.etf.bp.model.DTO.FizickoLiceDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class DodajFizickoLiceController implements Initializable{
	@FXML
    private TextField ime;

    @FXML
    private TextField prezime;

    @FXML
    private ComboBox<String> mjestoComboBox;

    @FXML
    private Button sacuvaj;

    @FXML
    private Button otkazi;

    @FXML
    private TextField brojLK;

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
    	
    	
    	/*
    	 * FizickoLiceDTO(int zIPcode, String ime, String prezime, String brojLK) {
	super(poslodavacId, zIPcode);
	
	insert_into_fizicko_lice(in ZipKod integer, in ime varchar(50), in prezime varchar(50), in brojLK varchar(20))
    	 */

		String mjesto = mjestoComboBox.getSelectionModel().getSelectedItem();
		MainController.getMjestoZipCode(mjesto);
		if(mjestoComboBox.getSelectionModel().getSelectedItem()==null || MainController.getMjestoZipCode(mjestoComboBox.getSelectionModel().getSelectedItem())==-1 || 
				MainController.getAJMB()==null && ime.getText().isEmpty() || prezime.getText().isEmpty() || brojLK.getText().isEmpty()) {
			ZipAlert();
			return;
		}
		FizickoLiceDTO fk = new FizickoLiceDTO(MainController.getMjestoZipCode(mjestoComboBox.getSelectionModel().getSelectedItem()),ime.getText(), prezime.getText(), brojLK.getText());
		FizickoLiceDAO.addFizickoLice(fk);
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		insertIntoComboBox();
	}
	
}
