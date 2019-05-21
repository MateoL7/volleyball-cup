package userInterface;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import model.Participant;
import model.Spectator;
import model.VolleyballCup;

public class VolleyController {

	private VolleyballCup vc;

	@FXML
	private TextField fileDestination;
	
	@FXML
    private Label countrySelected;

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
	private Label info;

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

	@FXML
	private BorderPane Canvas;

	@FXML
	private Pane mateo;


	private String fileName;

	private Spectator s;
	private Participant p;

	private long before;
	private long after;

	private Participant countryList;
	private Spectator newSpectator;


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
		info.setText("");
		Canvas = new BorderPane();
	}


	public void exploreFile(ActionEvent event)  {

		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);
		int seleccion = fc.showOpenDialog(null);

		if(seleccion==JFileChooser.APPROVE_OPTION){

			fileName = fc.getSelectedFile().getPath();
			System.out.println(fileName);

			fileDestination.setText(fc.getSelectedFile().toString());
		} 
	}

	public void loadFile(ActionEvent event) {
		try {
			vc.loadInfo(fileName);
			fileMessage.setText("The file has been loaded succesfully");
			vc.createCountryList("China");
			vc.createSpectatorTree("China", fileName);
		} catch (IOException e) {
			fileMessage.setText("There was a problem with loading\nthe file");
		} 
		catch(NullPointerException np) {
			fileMessage.setText("There was a problem with loading\nthe file");
		}

	}

	public void searchSpectator(ActionEvent event) {
		String idSpec = spectatorId.getText();
		before = System.currentTimeMillis();
		if(vc.searchingSpectator(idSpec) != null) {
			s = vc.searchingSpectator(idSpec);
			info.setText("Spectator");
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
		if(vc.searchingParticipant(idPar) != null) {
			p = vc.searchingParticipant(idPar);
			info.setText("Participant");
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

	public void loadCountry(ActionEvent e) {
		try {
			String country = JOptionPane.showInputDialog(null, "Which country would you like to see?");
			countryList =	vc.createCountryList(country);
			newSpectator = vc.createSpectatorTree(country, fileName);
			countrySelected.setText("COUNTRY: " + country.toUpperCase());
		}catch (NumberFormatException nf) {
			JOptionPane.showMessageDialog(null, "Not valid");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void showParticipants(ActionEvent a) {
		Participant temp = countryList;
		double counterx = 87;
		double countery = 257;
		int count = 0;
		while(temp.getNext() != vc.getFirst() && count <= 8) {
			double startx = counterx + 100;
			double distance = startx + 100;
			double starty = countery + 30;
			double starty2 = countery + 90;
			Image i = new Image(temp.getPhoto());
			ImageView v = new ImageView(i);
			v.setFitHeight(100);
			v.setFitWidth(100);
			v.setLayoutX(counterx);
			v.setLayoutY(countery);
			Line l = new Line(startx,starty,distance,starty);
			l.setStrokeWidth(3);
			Line l2 = new Line(startx,starty2,distance,starty2);
			l2.setStrokeWidth(3);
			mateo.getChildren().add(v);
			mateo.getChildren().add(l);
			mateo.getChildren().add(l2);
			temp = temp.getNext();
			count++;
			counterx = startx + distance - counterx - 100;
		}
		Line line3 = new Line(0, 287, 80, 287);
		line3.setStrokeWidth(3);
		Line line4 = new Line(0, (257+90), 80, (257+90));
		line4.setStrokeWidth(3);
		mateo.getChildren().add(line3);
		mateo.getChildren().add(line4);
	}
	public void showSpectators(ActionEvent ae) {
		Spectator temp = newSpectator;
		System.out.println(temp);
		//if(temp != null) {
			Image i = new Image(temp.getPhoto());
			ImageView v = new ImageView(i);
			v.setFitHeight(100);
			v.setFitWidth(100);
			v.setLayoutX(717);
			v.setLayoutY(6);
			mateo.getChildren().add(v);
		///}
	}
}
