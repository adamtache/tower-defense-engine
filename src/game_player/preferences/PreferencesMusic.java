package game_player.preferences;

import java.util.ResourceBundle;

import game_player.GameDataSource;
import game_player.interfaces.IGUIObject;
import game_player.interfaces.IGameView;
import game_player.view.PlayerGUI;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

public class PreferencesMusic implements IGUIObject {
	
	private static final int PANEL_SPACING = 10;
	
	private ResourceBundle myResources;
	private GameDataSource myGameData;
	private IGameView myView;
	private MediaPlayer myMusic;
	
	public PreferencesMusic(ResourceBundle r, GameDataSource gameData, IGameView view, PlayerGUI GUI) {
        myResources = r;
        myGameData = gameData;
        myView = view;
        myMusic = myView.getMusic();
    }

	@Override
	public Node createNode() {
		VBox box = new VBox(PANEL_SPACING);
		Label musicLabel = new Label(myResources.getString("Music"));
		Button switchMusic = new Button();
		switchMusic.setGraphic(new ImageView(new Image(myResources.getString("MusicNote"))));
		switchMusic.setOnAction(e -> switchGameMusic());
		box.getChildren().addAll(musicLabel, switchMusic);
		box.setAlignment(Pos.CENTER);
		return box;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub

	}
	
	private void switchGameMusic() {
		if (myMusic.isAutoPlay()) {
			myMusic.setAutoPlay(false);
			myMusic.pause();
		} else {
			myMusic.setAutoPlay(true);
			myMusic.play();
		}
	}

}