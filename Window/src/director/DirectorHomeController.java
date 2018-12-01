package director;

import core.Hotel;
import core.Room;
import core.StatusRoom;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;
import java.util.Optional;

public class DirectorHomeController {
    private Hotel hotel;
    @FXML private Label lb_selected_room_id;
    private int selectRoomId = 0;
    @FXML private TableView table_rooms;


    @FXML private TableColumn tb_id;
    @FXML private TableColumn tb_status;

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

            System.out.println(hotel);

            tb_id.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
            tb_status.setCellValueFactory(new PropertyValueFactory<Room, StatusRoom>("status"));

            table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(null, 0, true));

            table_rooms.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Room>) (observable, oldValue, newValue) -> {

                if (newValue == null) {
                    lb_selected_room_id.setText("Selected room id: ");
                    return;
                }

                selectRoomId = newValue.getId();
                lb_selected_room_id.setText("Selected room id: " + newValue.getId());

            });

        });

    }

    @FXML private void pressBtnAddRoom (ActionEvent event) {



    }

    @FXML private void pressBtnLocked (ActionEvent event) {

        if (selectRoomId == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select free room for create reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectRoomId && room.getStatus() != StatusRoom.LOCKED) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you want to locked for room id: " + selectRoomId + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goLocked();
                        table_rooms.refresh();

                    }

                    return;

                }

            }

        }

    }

    @FXML private void pressBtnCancelLocked (ActionEvent event) {

        if (selectRoomId == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select free room for create reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectRoomId && room.getStatus() == StatusRoom.LOCKED) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you want to locked for room id: " + selectRoomId + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goCancelLocked();
                        table_rooms.refresh();

                    }

                    return;

                }

            }

        }

    }

}
