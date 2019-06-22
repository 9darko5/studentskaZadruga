package org.unibl.etf.bp.controller;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DAO.KompanijaDAO;
import org.unibl.etf.bp.model.DAO.PosaoDAO;
import org.unibl.etf.bp.model.DTO.KompanijaDTO;
import org.unibl.etf.bp.model.DTO.PosaoDTO;
import org.unibl.etf.bp.model.DTO.PoslodavacDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajPosaoController implements Initializable {

	    @FXML
	    private Button sacuvaj;

	    @FXML
	    private Button otkazi;

	    @FXML
	    private TextArea opisPosla;

	    @FXML
	    private ComboBox<String> poslodavacComboBox;

	    @FXML
	    private TextField brojPotrebnihRadnika;

	    @FXML
	    private TextField cijenaPoSatu;

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
	    	String p = poslodavacComboBox.getSelectionModel().getSelectedItem();
	    	 System.out.println("poslodavac "+p);
			System.out.println("poslodavacId="+MainController.getIdPoslodavac(p));
			if(poslodavacComboBox.getSelectionModel().getSelectedItem()==null || MainController.getIdPoslodavac(p)==-1 || 
			 cijenaPoSatu.getText().isEmpty() || brojPotrebnihRadnika.getText().isEmpty() || opisPosla.getText().isEmpty() ) {
				ZipAlert();
				return;
			}
			// PosaoDTO(int posaoId, double cijenaPoSatu, int brojPotrebnihRadnika, String opisPosla, String aJmb, int poslodavacId) 
			PosaoDTO fk = new PosaoDTO(Float.parseFloat(cijenaPoSatu.getText()),Integer.parseInt(brojPotrebnihRadnika.getText()), opisPosla.getText(), MainController.getAJMB() ,MainController.getIdPoslodavac(p));
			PosaoDAO.addPosao(fk);
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

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			insertIntoComboBox();
		}
		
		public void insertIntoComboBox() {

			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;
			try {
				connection = ConnectionPool.getInstance().checkOut();
				statement = connection.createStatement();
				rs = statement.executeQuery("select ime, prezime from prikaz_fizickih_lica");
				while (rs.next()) {
					poslodavacComboBox.getItems().add(rs.getString(1)+" "+rs.getString(2));
				}
				rs = statement.executeQuery("select Naziv from prikaz_kompanija");
				while (rs.next()) {
					poslodavacComboBox.getItems().add(rs.getString(1));
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
	        alert.setContentText("Morate popuniti sva polja!!!");

	        alert.showAndWait();
		}
}
