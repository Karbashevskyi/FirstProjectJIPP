package receptionist;

import core.Client;
import core.Hotel;
import core.Room;
import core.StatusRoom;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;
import java.util.Optional;

public class ReceptionistHomeController {

    private Hotel hotel;
    @FXML private Label lb_selected_room_id;
    private int selectedRoomId = 0;
    private Client selectedClient = null;
    @FXML private TableView table_rooms;


    @FXML private TableColumn tb_id;
    @FXML private TableColumn tb_status;
    @FXML private TableColumn tb_client_full_name;
    @FXML private ChoiceBox<Client> cb_clients;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @FXML
    private void pressBtnLogout(ActionEvent event) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
        Parent parent = fxmlLoader.load();
        Controller home = fxmlLoader.getController();
        home.setHotel(hotel);
        Scene scene = new Scene(parent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();

    }

    public void initialize() {

        Platform.runLater(() -> {

            tb_status.setMinWidth(100);
            tb_client_full_name.setMinWidth(200);

            ObservableList<Client> clients = FXCollections.observableArrayList();

            if (hotel.checkTheClientsList()) {

                for (Client client: hotel.theClientsList) {

                    clients.add(client);

                }

            }

            cb_clients.setItems(clients);

            //
            ChangeListener<Client> changeListener = (observable, oldValue, client) -> {
                if (client != null) {
                    selectedClient = client;
                    System.out.println(selectedClient);
                }
            };
            // Selected Item Changed.
            cb_clients.getSelectionModel().selectedItemProperty().addListener(changeListener);

            tb_id.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
            tb_status.setCellValueFactory(new PropertyValueFactory<Room, StatusRoom>("status"));
            tb_client_full_name.setCellValueFactory(new PropertyValueFactory<Room, String>("client_full_name"));

            table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(null, 0, false));

            table_rooms.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Room>) (observable, oldValue, newValue) -> {

                if (newValue == null) {
                    lb_selected_room_id.setText("Selected room id: ");
                    return;
                }

                selectedRoomId = newValue.getId();
                lb_selected_room_id.setText("Selected room id: " + newValue.getId());

            });

        });

    }

    @FXML
    private void pressCheckIn(ActionEvent event) {

        if (selectedRoomId == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select free room for create reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectedRoomId && room.getStatus() == StatusRoom.RESERVATION) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you want to check in for room id: " + selectedRoomId + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goCheckIn();
                        table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(null, 0, false));
                        table_rooms.refresh();

                    }

                    return;

                }

            }

        }

    }

    @FXML
    private void pressCheckOut(ActionEvent event) {

        if (selectedRoomId == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select free room for create reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectedRoomId && room.getStatus() == StatusRoom.BUSY) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you want to check out for room id: " + selectedRoomId + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goCheckOut();
                        table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(null, 0, false));
                        table_rooms.refresh();

                    }

                    return;

                }

            }

        }

    }

    @FXML
    private void pressReservation(ActionEvent event) {

        if (selectedRoomId == 0 || selectedClient == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select free room or client for create reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectedRoomId && room.getStatus() == StatusRoom.FREE) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you sure make reservation for room id: " + selectedRoomId + ", client: " + selectedClient + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goReservation(selectedClient.getId(), selectedClient.getFullName());
                        table_rooms.refresh();

                    }

                    return;

                }

            }

        }

    }

    @FXML
    private void pressCancelReservation(ActionEvent event) {

        if (selectedRoomId == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select free room for create reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectedRoomId && room.getStatus() == StatusRoom.RESERVATION) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you want to cancel reservation for room id: " + selectedRoomId + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goCancelReservation();
                        table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(null, 0, false));
                        table_rooms.refresh();

                    }

                    return;

                }

            }

        }

    }

}
