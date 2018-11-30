package client;

import core.Client;
import core.Hotel;
import core.Room;
import core.StatusRoom;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import sample.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientHomeController implements Initializable {

    private int selectRoomId = 0;
    private int selectedRoomIdForCancelReservation = 0;

    @FXML private Label lb_client_id;
    @FXML private Label lb_selected_room_id;
    @FXML private TableView table_rooms;
    @FXML private TableView table_reservations;
    @FXML private TabPane tab_menu;
    @FXML private Label lb_selected_room_id_for_cancel_reservation;
    @FXML private Tab tp_reservation;
    @FXML private Tab tp_all_free_rooms;
    private Hotel hotel;
    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {

            lb_client_id.setText("" + client.getFullName());

            getCountsInTitleTabPanel();
            getInitTableRooms();
            getInitTableReservations();

            tab_menu.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {

                switch (newTab.getText()) {

                    case "All free rooms":
                        System.out.println("Refresh table on: All free rooms");
                        table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(StatusRoom.FREE, 0));
                        break;
                    case "My reservations":
                        System.out.println("Refresh table on: My reservations");
                        table_reservations.setItems(hotel.getObservableRoomListWithSelectedStatus(StatusRoom.RESERVATION, client.getId()));
                        break;

                }

            });

        });

    }

    private void getCountsInTitleTabPanel() {

        if (hotel.checkTheRoomsList()) {

            tp_reservation.setText("All free rooms (" + hotel.getCountAllRoomsWithStatus(StatusRoom.RESERVATION, client.getId()) + ")");
            tp_all_free_rooms.setText("My reservations (" + hotel.getCountAllRoomsWithStatus(StatusRoom.FREE, 0) + ")");

        }

    }

    private void getInitTableRooms() {

        TableColumn id = new TableColumn("ID");
        TableColumn status = new TableColumn("STATUS");

        status.setMinWidth(100);

        table_rooms.getColumns().addAll(id, status);

        id.setCellValueFactory(new PropertyValueFactory<Room, String>("id"));
        status.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));

        table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(StatusRoom.FREE, 0));

        table_rooms.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Room>) (observable, oldValue, newValue) -> {

            if (newValue == null) {
                lb_selected_room_id.setText("Select id: ");
                return;
            }

            selectRoomId = newValue.getId();
            lb_selected_room_id.setText("Selected id: " + newValue.getId());

        });

    }

    private void getInitTableReservations() {

        TableColumn id = new TableColumn("ID");
        TableColumn status = new TableColumn("STATUS");

        status.setMinWidth(100);

        table_reservations.getColumns().addAll(id, status);

        id.setCellValueFactory(new PropertyValueFactory<Room, String>("id"));
        status.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));

        table_reservations.setItems(hotel.getObservableRoomListWithSelectedStatus(StatusRoom.RESERVATION, client.getId()));

        table_reservations.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Room>) (observable, oldValue, newValue) -> {

            if (newValue == null) {
                lb_selected_room_id.setText("Select id: ");
                return;
            }

            selectRoomId = newValue.getId();
            lb_selected_room_id.setText("Selected id: " + newValue.getId());

        });

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

    @FXML
    private void pressBtnGoReservation(ActionEvent event) {

        if (selectRoomId == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select free room for create reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectRoomId && room.getStatus() == StatusRoom.FREE) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you sure make reservation for room id: " + selectRoomId + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goReservation(client.getId(), client.getFullName());
                        table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(StatusRoom.FREE, 0));
                        getCountsInTitleTabPanel();

                    }

                    return;

                }

            }

        }

    }

    @FXML
    private void pressBtnCancelReservation(ActionEvent event) {

        if (selectedRoomIdForCancelReservation == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select room for cancel reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectedRoomIdForCancelReservation && room.getStatus() == StatusRoom.RESERVATION) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you sure cancel reservation for room id: " + selectedRoomIdForCancelReservation + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goCancelReservation();
                        table_reservations.setItems(hotel.getObservableRoomListWithSelectedStatus(StatusRoom.RESERVATION, client.getId()));
                        getCountsInTitleTabPanel();

                    }

                    return;

                }

            }

        }

    }

    public void setHotel(Hotel hotel) {

        this.hotel = hotel;

    }

    public void setClient(Client client) {

        this.client = client;

    }
}
