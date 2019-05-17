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
import model.Participant;
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
	private Participant p;
	
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
			fileMessage.setText("The file has been loaded succesfully");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		catch(NullPointerException np) {
			fileMessage.setText("There was a problem with loading\nthe file");
			np.printStackTrace();
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
			spectatorMessage.setText("Found");
		}
		else {
			spectatorMessage.setText("No spectator found with the id: " + idSpec);
		}
		after = System.currentTimeMillis();
		spectatorTime.setText("Time: " + ((after-before)/1000) + "s");
	}
	public void searchParticipant(ActionEvent event) {
		String idPar = participantId.getText();
		before = System.currentTimeMillis();
		if(vc.searchParticipant(idPar) != null) {
			p = vc.searchParticipant(idPar);
			id.setText("Id:	"+p.getId());
			firstName.setText("First Name:	"+p.getFirstName());
			lastName.setText("Last Name:	"+p.getLastName());
			email.setText("Email:	"+p.getEmail());
			gender.setText("Gender:	"+p.getGender());
			country.setText("Country:	"+p.getCountry());
			birthday.setText("BirthDay:	"+p.getBirthday());
			Image image = new Image(p.getPhoto());
			photo.setImage(image);
			participantMessage.setText("Found");
		}
		else {
			participantMessage.setText("No participant found with the id: " + idPar);
		}
		after = System.currentTimeMillis();
		participantTime.setText("Time: " + ((after-before)/1000) + "s");
	}
}
