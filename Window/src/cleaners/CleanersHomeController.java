package cleaners;

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

public class CleanersHomeController {

    private Hotel hotel;
    @FXML private Button btn_cleaning;
    @FXML private Label lb_selected_room_id;
    private int selectRoomId = 0;
    @FXML private TableView table_rooms;


    @FXML private TableColumn tb_id;
    @FXML private TableColumn tb_status;
    @FXML private TableColumn tb_client_full_name;

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

            tb_id.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
            tb_status.setCellValueFactory(new PropertyValueFactory<Room, StatusRoom>("status"));
            tb_client_full_name.setCellValueFactory(new PropertyValueFactory<Room, String>("client_full_name"));

            table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(StatusRoom.NEED_CLEANING, 0, false));

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

    @FXML private void pressBtnCleaning(ActionEvent event) {

        if (selectRoomId == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Need select free room for create reservation!");
            alert.showAndWait();

        } else {

            for (Room room: hotel.theRoomsList) {

                if (room.getId() == selectRoomId && room.getStatus() == StatusRoom.NEED_CLEANING) {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Do you want to check out for room id: " + selectRoomId + "!");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        room.goCleaning();
                        table_rooms.refresh();

                    }

                    return;

                }

            }

        }

    }

}
