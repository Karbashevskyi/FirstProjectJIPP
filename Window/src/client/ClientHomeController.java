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
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientHomeController implements Initializable {

//    private int client_id;
//    private String full_name;
    private int selectRoomId = 0;
    private int selectedRoomIdForCancelReservation = 0;

    private List<Room> list = new ArrayList();

    @FXML private Label lb_client_id;
    @FXML private Label lb_selected_room_id;
    @FXML private TableView table_rooms;
    @FXML private TableView table_reservations;
    @FXML private TabPane tab_menu;
    @FXML private Label lb_selected_room_id_for_cancel_reservation;
    private Hotel hotel;
    private Client client;

    private ObservableList getInitialTableData(StatusRoom status) {

        if (list.isEmpty()) {

            list.add(new Room(1));
            list.add(new Room(2));
            list.add(new Room(3));
            list.add(new Room(4));
            list.add(new Room(5));
            list.add(new Room(6));
            list.add(new Room(7));
            list.add(new Room(8));
            list.add(new Room(9));
            list.add(new Room(10));
            list.add(new Room(11));
            list.add(new Room(13));

        }

        List<Room> localList = new ArrayList();

        for (Room room: list) {

            if (room.getStatus() == status) {

                localList.add(room);

            }

        }

        ObservableList data = FXCollections.observableList(localList);

        return data;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {

            lb_client_id.setText("" + client.getFullName());

            getInitTableRooms();
            getInitTableReservations();

            tab_menu.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
                switch (newTab.getText()) {
                    case "All free rooms":
                        System.out.println("Refresh table on: All free rooms");
                        table_rooms.setItems(getInitialTableData(StatusRoom.FREE));
                        break;
                    case "My reservations":
                        System.out.println("Refresh table on: My reservations");
                        table_reservations.setItems(getInitialTableData(StatusRoom.RESERVATION));
                        break;
                }
            });

        });

    }

    private void getInitTableRooms() {

        TableColumn id = new TableColumn("ID");
        TableColumn status = new TableColumn("STATUS");

        status.setMinWidth(100);

        table_rooms.getColumns().addAll(id, status);

        id.setCellValueFactory(new PropertyValueFactory<Room, String>("id"));
        status.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));

        table_rooms.setItems(getInitialTableData(StatusRoom.FREE));

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

        table_reservations.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Room>) (observable, oldValue, newValue) -> {

            if (newValue == null) {
                lb_selected_room_id_for_cancel_reservation.setText("Select id: ");
                return;
            }

            selectedRoomIdForCancelReservation = newValue.getId();
            lb_selected_room_id_for_cancel_reservation.setText("Selected id: " + newValue.getId());

        });

    }

    @FXML
    private void pressBtnLogout(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
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

            for (Room room: list) {

                if (room.getId() == selectRoomId && room.getStatus() == StatusRoom.FREE) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setHeaderText("Great, you make reservation for room id: " + selectRoomId + "!");
                    alert.setHeaderText("Do you sure make reservation for room id: " + selectRoomId + "!");
//                    alert.showAndWait();
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goReservation(client.getId());
                        table_rooms.setItems(getInitialTableData(StatusRoom.FREE));
//                        table_rooms.refresh();

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

            for (Room room: list) {

                if (room.getId() == selectedRoomIdForCancelReservation && room.getStatus() == StatusRoom.RESERVATION) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you sure cancel reservation for room id: " + selectedRoomIdForCancelReservation + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goCancelReservation();
                        table_reservations.setItems(getInitialTableData(StatusRoom.RESERVATION));

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
