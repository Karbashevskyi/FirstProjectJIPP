package sample;

import director.DirectorHomeController;
import javafx.scene.control.Alert;
import receptionist.ReceptionistHomeController;
import cleaners.CleanersHomeController;
import client.ClientHomeController;
import core.Client;
import core.Hotel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML private TextField inp_email;
    @FXML private TextField inp_password;

    @FXML ChoiceBox cb_role;

    private String email = "test@test.test";
    private String password = "1";
    private String role = "ROLE_CLIENT";
    private Hotel hotel;

    public void initialize() {

        cb_role.setItems(FXCollections.observableArrayList("Client", "Receptionist", "Director", "Cleaners"));

    }

    public void pressBtnLogin(ActionEvent event) throws IOException {

        System.out.println("Press login button!");

        System.out.println(cb_role.getSelectionModel().getSelectedItem());

        if (cb_role.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select role for login!");
            alert.showAndWait();

        } else {

            switch (cb_role.getSelectionModel().getSelectedItem().toString()) {
                case "Client":
                    authClient(event);
                    break;
                case "Receptionist":
                    authReceptionist(event);
                    break;
                case "Cleaners":
                    authCleaners(event);
                    break;
                case "Director":
                    authDirector(event);
                    break;
            }

        }

    }

    private void authClient(ActionEvent event) throws IOException {

        System.out.println(hotel);

        if (hotel.checkTheRoomsList()) {

            for (Client client : hotel.theClientsList) {

//        Check data from input.
                if (client.getEmail().equals(inp_email.getText()) && client.getPassword().equals(inp_password.getText())) {

//                Open new window for role.
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/ClientHome.fxml"));
                    Parent parent = fxmlLoader.load();
                    ClientHomeController home = fxmlLoader.getController();
                    home.setHotel(hotel);
                    home.setClient(client);
                    Scene scene = new Scene(parent);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.setResizable(false);
                    appStage.show();

                } else {

                    System.out.println("Warning!");

                }

            }

        }

    }

    private void authReceptionist(ActionEvent event) throws IOException {

//        Check data from input.
        if (email.equals(inp_email.getText()) && password.equals(inp_password.getText())) {

//                Open new window for role.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/receptionist/ReceptionistHome.fxml"));
            Parent parent = fxmlLoader.load();
            ReceptionistHomeController home = fxmlLoader.getController();
            home.setHotel(hotel);
            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setResizable(false);
            appStage.show();

        } else {

            System.out.println("Warning!");

        }


    }

    private void authCleaners(ActionEvent event) throws IOException {

//        Check data from input.
        if (email.equals(inp_email.getText()) && password.equals(inp_password.getText())) {

//                Open new window for role.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cleaners/CleanersHome.fxml"));
            Parent parent = fxmlLoader.load();
            CleanersHomeController home = fxmlLoader.getController();
            home.setHotel(hotel);
            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setResizable(false);
            appStage.show();

        } else {

            System.out.println("Warning!");

        }


    }

    private void authDirector(ActionEvent event) throws IOException {

//        Check data from input.
        if (email.equals(inp_email.getText()) && password.equals(inp_password.getText())) {

//                Open new window for role.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/director/DirectorHome.fxml"));
            Parent parent = fxmlLoader.load();
            DirectorHomeController home = fxmlLoader.getController();
            home.setHotel(hotel);
            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setResizable(false);
            appStage.show();

        } else {

            System.out.println("Warning!");

        }


    }

    public void pressBtnRegistration(ActionEvent event) {

        System.out.println("Press registration button!");

    }

    public void setHotel(Hotel hotel) {
        
        this.hotel = hotel;
        
    }
}
