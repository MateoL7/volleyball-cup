package userInterface;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Spectator;
import model.VolleyballCup;

public class VolleyController {

	private VolleyballCup vc;

	@FXML
	private TextField fileDestination;

	@FXML
	private Button explore;

	@FXML
	private Button load;

	@FXML
	private TextField spectatorId;

	@FXML
	private Button searchSpectator;

	@FXML
	private TextField participantId;

	@FXML
	private Button searchParticipant;

	@FXML
	private Label fileMessage;

	@FXML
	private Label spectatorMessage;

	@FXML
	private Label participantMessage;

	@FXML
	private Label participantTime;

	@FXML
	private Label spectatorTime;

	@FXML
	private ImageView photo;

	@FXML
	private Label firstName;

	@FXML
	private Label id;

	@FXML
	private Label lastName;

	@FXML
	private Label email;

	@FXML
	private Label gender;

	@FXML
	private Label country;

	@FXML
	private Label birthday;

	@FXML
	private Pane pane;
	
	private String fileName;
	
	private Spectator s;
	
	private long before;
	private long after;


	private FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV","csv");
	
	@FXML
	public void initialize() {
		id.setText("");
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		gender.setText("");
		country.setText("");
		birthday.setText("");
		fileMessage.setText("");
		participantMessage.setText("");
		spectatorMessage.setText("");
		vc = new VolleyballCup();
		spectatorTime.setText("");
		participantTime.setText("");
	}
	
	
	public void exploreFile(ActionEvent event)  {

		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);
		int seleccion = fc.showOpenDialog(null);

		if(seleccion==JFileChooser.APPROVE_OPTION){

			fileName = fc.getSelectedFile().getPath();
			
			fileDestination.setText(fc.getSelectedFile().toString());
		} 
	}
	
	public void loadFile(ActionEvent event) {
		try {
			vc.loadInfo(fileName);
			fileMessage.setText("The spectators have been loaded");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		catch(NullPointerException np) {
			fileMessage.setText("There was a problem with loading\nthe spectators. Null Pointer");
		}
	}
	
	public void searchSpectator(ActionEvent event) {
		String idSpec = spectatorId.getText();
		before = System.currentTimeMillis();
		if(vc.searchSpectator(idSpec) != null) {
			s = vc.searchSpectator(idSpec);
			id.setText("Id:	"+s.getId());
			firstName.setText("First Name:	"+s.getFirstName());
			lastName.setText("Last Name:	"+s.getLastName());
			email.setText("Email:	"+s.getEmail());
			gender.setText("Gender:	"+s.getGender());
			country.setText("Country:	"+s.getCountry());
			birthday.setText("BirthDay:	"+s.getBirthday());
			Image image = new Image(s.getPhoto());
			photo.setImage(image);
		}
		else {
			spectatorMessage.setText("No spectator found with that Id");
		}
		after = System.currentTimeMillis();
		spectatorTime.setText("Time: " + ((after-before)/1000) + "s");
	}
}
