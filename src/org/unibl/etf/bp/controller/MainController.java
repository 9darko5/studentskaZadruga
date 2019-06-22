package org.unibl.etf.bp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DAO.ClanDAO;
import org.unibl.etf.bp.model.DAO.ClanPosaoDAO;
import org.unibl.etf.bp.model.DAO.FizickoLiceDAO;
import org.unibl.etf.bp.model.DAO.KompanijaDAO;
import org.unibl.etf.bp.model.DAO.PosaoDAO;
import org.unibl.etf.bp.model.DTO.*;
import org.unibl.etf.bp.controller.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class MainController implements Initializable {

	@FXML
	private Button pretraziPosaoButton;
	
	@FXML
	private Button sviPosloviButton;

	@FXML
	private Button sviClanoviButton;
	
	@FXML
	private Button sviPoslodavciButton;
	
	@FXML
	private TextField pretraziClanaTextField;

	@FXML
	private TableView<KompanijaDTO> tabelaKompanija;

	@FXML
    private TableColumn<KompanijaDTO, String> nazivKompanijaColumn;
	
	@FXML
    private TableColumn<KompanijaDTO, String> JIBColumn;
    
	@FXML
	private ComboBox<String> aktivniPosloviComboBox;
	
	@FXML
	private ComboBox<String> clanoviComboBox;
	
	@FXML
	private TextField brojRadnihSatiTF;
	
	@FXML
	private Button upisiClanPosao;
	
	@FXML
	private Button dodajClanaButton;

	@FXML
	private TableView<PosaoDTO> tabelaPoslovi;
	
	@FXML
	private TableColumn<PosaoDTO, Float> cijenaPoSatuColumn;
	
	@FXML
	private TableColumn<PosaoDTO, Integer> brojPotrebnihRadnikaColumn;

	@FXML
	private TableColumn<PosaoDTO, String> opisPoslaColumn1;
	
	@FXML
	private TableView<FizickoLiceDTO> tabelaFizickaLica;
	
	@FXML
	private TableColumn<FizickoLiceDTO, String> imeFLColumn;
	
	@FXML
	private TableColumn<FizickoLiceDTO, String> prezimeFLColumn;
	
	@FXML
	private TableColumn<FizickoLiceDTO, String> brojLKFLColumn;

	@FXML
	private TextField pretraziPoslodavcaTextField;

	@FXML
	private TextField pretraziPosaoTextField;

	@FXML
	private Button pretraziClanaButton;

	@FXML
	private TableView<ClanDTO> tabelaClanovi;

	@FXML
	private TableColumn<ClanDTO, String> imeClanColumn;
	
	@FXML
	private TableColumn<ClanDTO, String> prezimeClanColumn;
	
	@FXML
	private TableColumn<ClanDTO, Integer> brojClanskeKarteColumn;
	
	@FXML
	private TableColumn<ClanDTO, Date> datumIstekaClanskeKarteColumn;
	
	
	@FXML
	private Button stampajButton;

	@FXML
	private Button dodajPosaoButton;

	@FXML
	private Button pretraziPoslodavcaButton;

	@FXML
	private ComboBox<String> vrstaPoslodavcaComboBox;

	@FXML
	private Button dodajPoslodavcaButton;

	@FXML
	private Button izmijeniButton;

	@FXML
	private TableView<ClanPosaoDTO> tabelaClanPosao;
	
	@FXML
	private TableColumn<ClanPosaoDTO, String> imeClanPosaoColumn;
	
	@FXML
	private TableColumn<ClanPosaoDTO, String> prezimeClanPosaoColumn;
	
	@FXML
	private TableColumn<ClanPosaoDTO, String> brojRadnihSatiClanPosaoColumn;
	
	@FXML
	private TableColumn<ClanPosaoDTO, String> ukupnoZaradjenoClanPosaoColumn;

	@FXML
	private TableColumn<ClanPosaoDTO, String> opisPoslaColumn;
	
	@FXML
	private TableColumn<ClanPosaoDTO, String> datumColumn;
	
	private ObservableList<ClanDTO> clanoviOL = FXCollections.observableArrayList();
	private ObservableList<PosaoDTO>posloviOL = FXCollections.observableArrayList();
	private ObservableList<KompanijaDTO> kompanijeOL = FXCollections.observableArrayList();
	private ObservableList<FizickoLiceDTO> fizickaLicaOL = FXCollections.observableArrayList();
	private ObservableList<ClanPosaoDTO> clanPosaoOL = FXCollections.observableArrayList();
	
	@FXML
	void upisiClanPosaoAction(ActionEvent event) {
		if(aktivniPosloviComboBox.getSelectionModel().getSelectedItem()==null || 
				clanoviComboBox.getSelectionModel().getSelectedItem()==null || brojRadnihSatiTF.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Prvo izaberi posao, clana i upisi broj radnih sati!");
            alert.show();
		}
		else {
			//public ClanPosaoDTO(float brojRadnihSati, float zaradjeno, int posaoId, String cJMB, String opisPosla) 
			ClanPosaoDTO cp=new ClanPosaoDTO(Float.parseFloat(brojRadnihSatiTF.getText()), Float.parseFloat(brojRadnihSatiTF.getText())*PosaoDAO.cijenaPoslaPoSatu(PosaoDAO.getIdPosao(aktivniPosloviComboBox.getSelectionModel().getSelectedItem())),PosaoDAO.getIdPosao(aktivniPosloviComboBox.getSelectionModel().getSelectedItem()), getCJMB(clanoviComboBox.getSelectionModel().getSelectedItem()), ClanPosaoDAO.opisPosla(PosaoDAO.getIdPosao(aktivniPosloviComboBox.getSelectionModel().getSelectedItem())));
			ClanPosaoDAO.addClanPosao(cp);
			popuniTabeluClanPosao();
			popuniTabeluPosao();
			popuniComboBoxAktivniPoslovi();
		}
	}

	@FXML
	void dodajPosaoAction(ActionEvent event) {
		dodajPosaoButton.getScene().getWindow().hide();
		Stage s = new Stage();
		s.setTitle("Dodavanje posla");
		// s.getIcons().add(new Image("file:garaza.jpg"));
		FXMLLoader loader = new FXMLLoader();
		try {
			Parent root;
			root = loader.load(getClass().getResource("/org/unibl/etf/bp/view/DodajPosao.fxml"));

			Scene scene = new Scene(root);
			s.setScene(scene);
			s.show();
			s.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void pretraziPosaoAction(ActionEvent event) {
		if(!pretraziPosaoTextField.getText().isEmpty()) {
			posloviOL.clear();
			posloviOL.addAll(PosaoDAO.pretragaPosla(pretraziPosaoTextField.getText()));
			tabelaPoslovi.setItems(posloviOL);
			sviPosloviButton.setVisible(true);
		}
	}

	@FXML
	void obrisiClanaAction(ActionEvent event) {
		try
		{
            ClanDTO clan=tabelaClanovi.getSelectionModel().getSelectedItem();
			ObservableList<ClanDTO> productSelected, allProducts;
			allProducts=tabelaClanovi.getItems();
			productSelected=tabelaClanovi.getSelectionModel().getSelectedItems();
			productSelected.forEach(allProducts::remove);
                        if(clan!=null)
                        {
                            ClanDAO.deleteClan(clan.getJMB());
                            clanoviOL.remove(clan);
                        }
                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setContentText("Prvo zaberi clana!");
                            alert.show();
                        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void dodajClanaAction(ActionEvent event) {
		dodajPosaoButton.getScene().getWindow().hide();
		Stage s = new Stage();
		s.setTitle("Dodavanje člana");
		// s.getIcons().add(new Image("file:garaza.jpg"));
		FXMLLoader loader = new FXMLLoader();
		try {
			Parent root;
			root = loader.load(getClass().getResource("/org/unibl/etf/bp/view/DodajClana.fxml"));

			Scene scene = new Scene(root);
			s.setScene(scene);
			s.show();
			s.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void pretraziClanaAction(ActionEvent event) {
		if(!pretraziClanaTextField.getText().isEmpty()) {
			clanoviOL.clear();
			clanoviOL.addAll(ClanDAO.pretragaClanova(Integer.parseInt(pretraziClanaTextField.getText())));
			tabelaClanovi.setItems(clanoviOL);
			sviClanoviButton.setVisible(true);
		}
	}

	@FXML
	void sviClanoviAction(ActionEvent event) {
		popuniTabeluClan();
		tabelaClanovi.setItems(clanoviOL);
		sviClanoviButton.setVisible(false);
	}
	
	@FXML
	void pretraziPoslodavcaAction(ActionEvent event) {
		if(!pretraziPoslodavcaTextField.getText().isEmpty()) {
			kompanijeOL.clear();
			kompanijeOL.addAll(KompanijaDAO.pretragaKompanija(pretraziPoslodavcaTextField.getText()));
			tabelaKompanija.setItems(kompanijeOL);
			fizickaLicaOL.clear();
			fizickaLicaOL.addAll(FizickoLiceDAO.pretragaFizickihLica(pretraziPoslodavcaTextField.getText()));
			tabelaFizickaLica.setItems(fizickaLicaOL);
			sviPoslodavciButton.setVisible(true);
		}
	}
	
	@FXML
	void sviPoslodavciAction(ActionEvent event) {
		popuniTabeluKompanija();
		popuniTabeluFizickoLice();
		tabelaKompanija.setItems(kompanijeOL);
		tabelaFizickaLica.setItems(fizickaLicaOL);
		sviPoslodavciButton.setVisible(false);
		
	}

	@FXML
	void dodajPoslodavcaAction(ActionEvent event) {
		if(vrstaPoslodavcaComboBox.getSelectionModel().getSelectedItem()==null){
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("GRESKA");
	        alert.setHeaderText(null);
	        alert.setContentText("Morate izabrati vrstu posloavca!!!");
	        alert.showAndWait();
	        return;
		}
		if("Fizicko lice".equals(vrstaPoslodavcaComboBox.getSelectionModel().getSelectedItem())){
			dodajPoslodavcaButton.getScene().getWindow().hide();
			Stage s = new Stage();
			s.setTitle("Dodavanje fizičkog lica");
			FXMLLoader loader = new FXMLLoader();
			try {
				Parent root;
				root = loader.load(getClass().getResource("/org/unibl/etf/bp/view/DodajFizickoLice.fxml"));
				Scene scene = new Scene(root);
				s.setScene(scene);
				s.show();
				s.setResizable(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			dodajPoslodavcaButton.getScene().getWindow().hide();
			Stage s = new Stage();
			s.setTitle("Dodavanje kompanije");
			FXMLLoader loader = new FXMLLoader();
			try {
				Parent root;
				root = loader.load(getClass().getResource("/org/unibl/etf/bp/view/DodajKompaniju.fxml"));
				Scene scene = new Scene(root);
				s.setScene(scene);
				s.show();
				s.setResizable(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void sviPosloviAction() {
		popuniTabeluPosao();
		tabelaPoslovi.setItems(posloviOL);
		sviPosloviButton.setVisible(false);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sviPosloviButton.setVisible(false);
		sviClanoviButton.setVisible(false);
		sviPoslodavciButton.setVisible(false);
		//tabela KOMPANIJA
		nazivKompanijaColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		JIBColumn.setCellValueFactory(new PropertyValueFactory<>("JIB"));
		
		popuniTabeluKompanija();
		tabelaKompanija.setItems(kompanijeOL);
		tabelaKompanija.setEditable(true);
		
		//tabela POSAO (aktivni i zavrseni)
		cijenaPoSatuColumn.setCellValueFactory(new PropertyValueFactory<>("cijenaPoSatu"));
		brojPotrebnihRadnikaColumn.setCellValueFactory(new PropertyValueFactory<>("brojPotrebnihRadnika"));
		opisPoslaColumn1.setCellValueFactory(new PropertyValueFactory<>("opisPosla"));
		popuniTabeluPosao();
		tabelaPoslovi.setItems(posloviOL);
		tabelaPoslovi.setEditable(true);
		
		cijenaPoSatuColumn.setCellFactory(TextFieldTableCell.<PosaoDTO, Float>forTableColumn(new FloatStringConverter()));
		brojPotrebnihRadnikaColumn.setCellFactory(TextFieldTableCell.<PosaoDTO, Integer>forTableColumn(new IntegerStringConverter()));
		opisPoslaColumn1.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
		//tabela FIZICKO_LICE
		imeFLColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
		prezimeFLColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		brojLKFLColumn.setCellValueFactory(new PropertyValueFactory<>("brojLK"));
		popuniTabeluFizickoLice();
		tabelaFizickaLica.setItems(fizickaLicaOL);
		
		
		//tabela CLAN
		imeClanColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
		prezimeClanColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		brojClanskeKarteColumn.setCellValueFactory(new PropertyValueFactory<>("brojClanskeKarte"));
		datumIstekaClanskeKarteColumn.setCellValueFactory(new PropertyValueFactory<>("datumIstekaClanskeKarte"));
		popuniTabeluClan();
		tabelaClanovi.setItems(clanoviOL);
		
		/*tabela POSAO
		 tabelaPoslovi.setRowFactory(tv -> {
			    TableRow<PosaoDTO> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY ) {

			        	PosaoDTO posao = row.getItem();
			        	opisPoslaTextArea.setText(posao.getOpisPosla());
			        }
			    });
			    return row ;
			});*/
		 vrstaPoslodavcaComboBox.getItems().addAll("Fizicko lice", "Kompanija");
		 
		 //table CLAN_POSAO
		 imeClanPosaoColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
		 prezimeClanPosaoColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		 brojRadnihSatiClanPosaoColumn.setCellValueFactory(new PropertyValueFactory<>("brojRadnihSati"));
		 ukupnoZaradjenoClanPosaoColumn.setCellValueFactory(new PropertyValueFactory<>("zaradjeno"));
		 opisPoslaColumn.setCellValueFactory(new PropertyValueFactory<>("opisPosla"));
		 datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
		 popuniTabeluClanPosao();
		 tabelaClanPosao.setItems(clanPosaoOL);
		 
		 popuniComboBoxAktivniPoslovi();
		 popuniComboBoxClanovi();
		 }
	
	public void changeCijenaPoSatuCellEvent(CellEditEvent edittedCell)
	{
		PosaoDTO posaoSelected=tabelaPoslovi.getSelectionModel().getSelectedItem();
		posaoSelected.setCijenaPoSatu(Float.parseFloat(edittedCell.getNewValue().toString()));
		PosaoDAO.updatePosao(posaoSelected);
	}
	public void changeBrojPotrebnihRadnikCellEvent(CellEditEvent edittedCell)
	{
		PosaoDTO posaoSelected=tabelaPoslovi.getSelectionModel().getSelectedItem();
		posaoSelected.setBrojPotrebnihRadnika(Integer.parseInt(edittedCell.getNewValue().toString()));
		PosaoDAO.updatePosao(posaoSelected);
	}
	
	public void changeOpisPoslaCellEvent(CellEditEvent edittedCell)
	{
		PosaoDTO posaoSelected=tabelaPoslovi.getSelectionModel().getSelectedItem();
		posaoSelected.setOpisPosla(edittedCell.getNewValue().toString());
		PosaoDAO.updatePosao(posaoSelected);
	}

	public void popuniTabeluClan() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from clan");
			clanoviOL.clear();
			while(rs.next())
				clanoviOL.add(new ClanDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), 
							rs.getInt(6), rs.getString(7), rs.getDate(8), rs.getString(9)));

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
	public void popuniTabeluClanPosao() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from pregled_clan_posao");
			clanPosaoOL.clear();
			//ClanPosaoDTO(String ime, String prezime, float ukupanBrojRadnihSati, float ukupnoZaradjeno, String opisPosla)
			//Ime, Prezime, BrojRadnihSati, Zaradjeno
			while(rs.next()) {
				clanPosaoOL.add(new ClanPosaoDTO(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getString(5), rs.getDate(6)));
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
	
	public void popuniTabeluPosao() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from prikaz_poslova");
			posloviOL.clear();
			while(rs.next())
				/*
				 * PosaoDTO( double cijenaPoSatu, int brojPotrebnihRadnika, String opisPosla,
			String aJmb, int poslodavacId) 
				 */
				posloviOL.add(new PosaoDTO(rs.getFloat(3), rs.getInt(6), rs.getString(4), rs.getString(7),  rs.getInt(1)));

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
	
	
	public void popuniTabeluFizickoLice() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from prikaz_fizickih_lica");
			fizickaLicaOL.clear();
			while(rs.next())
				//FizickoLiceDTO(int zIPcode, String ime, String prezime, String brojLK)
				fizickaLicaOL.add(new FizickoLiceDTO(rs.getInt(6), rs.getString(2), rs.getString(3), rs.getString(4)));

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
	
	public void popuniTabeluKompanija() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from prikaz_kompanija");
			kompanijeOL.clear();
			while(rs.next())
				//KompanijaDTO(int zIPcode, String naziv, String adresa, String JIB)
				kompanijeOL.add(new KompanijaDTO(rs.getInt(6), rs.getString(2), rs.getString(3), rs.getString(4)));

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
	
	public static int getMjestoZipCode(String mjesto){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select ZIPkod, Naziv from mjesto");
			while (rs.next()) {
				if(rs.getString(2).equals(mjesto)) {
					return rs.getInt(1);
				}
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
		return -1;
	}
	
	
	
	public static int getIdPoslodavac(String poslodavac){
		String p2=poslodavac.split(" ")[0];
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select Ime, PoslodavacId from prikaz_fizickih_lica");
			while (rs.next()) {
				if(rs.getString(1).equals(p2)) {
					return rs.getInt(2);
				}
			}
			rs = statement.executeQuery("select Naziv, PoslodavacId from prikaz_kompanija");
			while (rs.next()) {
				if(rs.getString(1).equals(poslodavac)) {
					return rs.getInt(2);
				}
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
		return -1;
	}
	
	
	
	public static String getAJMB() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select A_JMB from administrativni_radnik");
			if(rs.next())
				return rs.getString(1);

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
		return null;
	}
	
	public static String getCJMB(String imeIPrezime) {
		String clanIme=imeIPrezime.split(" ")[0];
		String clanPrezime=imeIPrezime.split(" ")[1];

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select Ime, Prezime, C_JMB from clan");
			while (rs.next()) {
				if(rs.getString(1).equals(clanIme) && rs.getString(2).equals(clanPrezime)) {
					return rs.getString(3);
				}
			}
			if(rs.next())
				return rs.getString(1);

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
		return null;
	}
	
	public void popuniComboBoxAktivniPoslovi() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select OpisPosla from posao where zavrsen=false");
			aktivniPosloviComboBox.getItems().clear();
			while (rs.next()) {
				aktivniPosloviComboBox.getItems().add(rs.getString(1));
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
	
	
	public void popuniComboBoxClanovi() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select Ime, Prezime from clan");
			while (rs.next()) {
				clanoviComboBox.getItems().add(rs.getString(1)+" "+rs.getString(2));
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
	

}
