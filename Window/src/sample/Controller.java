package sample;

import client.Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML private TextField inp_email;
    @FXML private TextField inp_password;

    private String email = "test@test.test";
    private String password = "1";
    private String role = "ROLE_CLIENT";

    public void pressBtnLogin(ActionEvent event) throws IOException {

        System.out.println("Press login button!");

//        Check data from input.
        if (email.equals(inp_email.getText()) && password.equals(inp_password.getText())) {

            System.out.println("Success!");

//            Path for role.
            String path = null;
            int client_id = 0;

//            Check role.
            if (role.equals("ROLE_CLIENT")) {

//                Initialization path to client path.
                path = "client";
                client_id = 1;

            }

            if (path != null) {

//                Open new window for role.
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/" + path + "/Home.fxml"));
                Parent parent = fxmlLoader.load();
                Home home = fxmlLoader.getController();
                home.setClientId(client_id);
                Scene scene = new Scene(parent);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.setResizable(false);
                appStage.show();

            } else {

                System.out.println("No have role!");

            }

        } else {

            System.out.println("Warning!");

        }

    }

    public void pressBtnRegistration(ActionEvent event) {

        System.out.println("Press registration button!");

    }

}
