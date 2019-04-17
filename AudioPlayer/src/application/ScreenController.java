package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ScreenController implements Initializable {

    public static MediaPlayer mediaPlayer;
    public static File file;
    public static List<String> list = new ArrayList<String>();
    public static List<String> name = new ArrayList<String>();
    public static int musicNumber = -1;

    @FXML
    private TextField txtSource;
    @FXML
    private TextField txtArquive;
    @FXML
    private Label lblStatus;
    @FXML
    private Button btnStop;
    @FXML
    private Button btnForward;
    @FXML
    private Button btnVolMinus;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnVolPlus;
    @FXML
    private Button btnBackward;

    public void updateStatus() {
        lblStatus.setText(musicNumber + "/" + list.size()
                + " - VOL: " + (int) (mediaPlayer.getVolume() * 100) + "%"
        );
        txtArquive.setText(name.get(musicNumber));
    }

    public void btnPlayHandler() {
        if (musicNumber != -1) {
            mediaPlayer.play();
            updateStatus();
        } else {
            lblStatus.setText("NO SOURCE");
        }
    }

    public void btnStopHandler() {
        if (musicNumber != -1) {
            mediaPlayer.stop();
            updateStatus();
        } else {
            lblStatus.setText("NO SOURCE");
        }
    }

    public void btnVolPlusHandler() {
        mediaPlayer.setVolume(mediaPlayer.getVolume() < 1 ? mediaPlayer.getVolume() + 0.1 : 1);
        updateStatus();
    }

    public void btnVolMinusHandler() {
        mediaPlayer.setVolume(mediaPlayer.getVolume() > 0 ? mediaPlayer.getVolume() - 0.1 : 0);
        updateStatus();
    }

    public void btnBackwardHandler() {
        mediaPlayer.stop();
        if (--musicNumber < 0) {
            musicNumber = list.size() - 1;
        }
        mediaPlayer = new MediaPlayer(new Media(list.get(musicNumber)));
        mediaPlayer.play();
        updateStatus();
    }

    public void btnForwardHandler() {
        mediaPlayer.stop();
        if (++musicNumber > list.size() - 1) {
            musicNumber = 0;
        }
        mediaPlayer = new MediaPlayer(new Media(list.get(musicNumber)));
        mediaPlayer.play();
        updateStatus();
    }

    public void btnChangeHandler() {
        try {

            file = new File(txtSource.getText());
            if (file.exists()) {
                list.clear();
                name.clear();
                for (File f : file.listFiles()) {
                    list.add(f.getAbsoluteFile().toURI().toString());
                    name.add(f.getName());
                }
                musicNumber = 0;
                mediaPlayer = new MediaPlayer(new Media(list.get(musicNumber)));
                updateStatus();
            } else {
                lblStatus.setText("SOURCE DO NOT EXISTS");
                musicNumber = -1;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtSource.setText("C:\\Users\\Samanta Meira\\Documents\\Teste");
        btnChangeHandler();
        lblStatus.setText("SOURCE: " + file.toString());
    }

}
