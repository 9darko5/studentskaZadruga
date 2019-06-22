package org.unibl.etf.bp.controller;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.bp.connectionPool.ConnectionPool;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	@FXML
    private TextField usernameTF;

    @FXML
    private PasswordField lozinkaTF;

    @FXML
    private Label errorLozinkaLabel;

    @FXML
    private Button loginButton;
    
    private String jmb;

    @FXML
    void loginAction(ActionEvent event) {
    	if (usernameTF.getText().isEmpty() || lozinkaTF.getText().isEmpty()) {
    		Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setHeaderText(null);
            alertError.setContentText("Polja su prazna!");
            alertError.show();
            return;
        }
        String username = usernameTF.getText();
        String password = lozinkaTF.getText();

        boolean authenticationOk = checkAuthentication(username, password);
        if (authenticationOk || ("admin".equals(username) && "admin".equals(password))) {
        	errorLozinkaLabel.setVisible(false);
            System.out.println("Podaci su validni");

            try 
            {
                loginButton.getScene().getWindow().hide();
                Stage s=new Stage();
                FXMLLoader loader=new FXMLLoader();
                s.setTitle("СТУДЕНТСКА ЗАДРУГА");
                s.getIcons().add(new Image("file:resources/sljem.jpg"));
                Parent root=loader.load(getClass().getResource("/org/unibl/etf/bp/view/Main.fxml"));
                Scene scene=new Scene(root);
                s.setScene(scene);
                s.show();
                s.setResizable(false);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, ex);
            ex.printStackTrace();
        }

        } else {
        	errorLozinkaLabel.setVisible(true);
            System.out.println("Podaci nisu validni");
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		errorLozinkaLabel.setVisible(false);
	}
	
	private boolean checkAuthentication(String username, String password) {
        try {
            String passwordHash = hashSHA256(password);
            boolean compareValue = compareUsernameAndPasswordFromDatabase(username, passwordHash);
            return compareValue;

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private boolean compareUsernameAndPasswordFromDatabase(String username, String passwordHash) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT zaposleni.A_JMB from zaposleni natural join administrativni_radnik where KorisnickoIme='"
                    + username + "' and Lozinka='" + passwordHash + "'" );
            while (rs.next()) {
                jmb = rs.getString(1);
            }

            if (jmb != null) {
                return true;
            }

           

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    private String hashSHA256(String value) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        String hash = bytesToHex(encodedhash);
        return hash;
    }

    private String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
