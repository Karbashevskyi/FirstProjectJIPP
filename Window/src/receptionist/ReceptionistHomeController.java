package receptionist;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class ReceptionistHomeController {

    private Hotel hotel;
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

            getInitTableRooms();

        });

    }

    private void getInitTableRooms() {

        tb_status.setMinWidth(100);
        tb_client_full_name.setMinWidth(200);

        tb_id.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
        tb_status.setCellValueFactory(new PropertyValueFactory<Room, StatusRoom>("status"));
        tb_client_full_name.setCellValueFactory(new PropertyValueFactory<Room, String>("client_full_name"));

        table_rooms.setItems(hotel.getObservableRoomListWithSelectedStatus(null));

        table_rooms.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Room>) (observable, oldValue, newValue) -> {

            if (newValue == null) {
                lb_selected_room_id.setText("Select room id: ");
                return;
            }

            selectRoomId = newValue.getId();
            lb_selected_room_id.setText("Selected room id: " + newValue.getId());

        });

    }

    @FXML
    private void pressCheckIn(ActionEvent event) {

    }

    @FXML
    private void pressCheckOut(ActionEvent event) {

    }

    @FXML
    private void pressReservation(ActionEvent event) {

    }

    @FXML
    private void pressCancelReservation(ActionEvent event) {

    }

}
