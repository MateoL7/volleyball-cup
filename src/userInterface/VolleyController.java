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
	private Label informative;

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
		//info.setText("");
		Canvas = new BorderPane();
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
			info.setText("Spectator".toUpperCase());
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
			JOptionPane.showMessageDialog(null, "Please type another id");
		}
		after = System.currentTimeMillis();
		spectatorTime.setText("Time: " + (after-before) + " ms");
	}
	public void searchParticipant(ActionEvent event) {
		String idPar = participantId.getText();
		before = System.currentTimeMillis();
		if(vc.searchingParticipant(idPar) != null) {
			p = vc.searchingParticipant(idPar);
			info.setText("Participant".toUpperCase());
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
			JOptionPane.showMessageDialog(null, "Please type another id");
		}
		after = System.currentTimeMillis();
		participantTime.setText("Time: " + (after-before) + " ms");
	}

	public void loadCountry(ActionEvent e) {
		mateo.getChildren().clear();
		try {
			String country = JOptionPane.showInputDialog(null, "Which country would you like to see?");
			countryList =	vc.createCountryList(country);
			newSpectator = vc.createSpectatorTree(country, fileName);
			if(countryList != null && newSpectator != null) {
				countrySelected.setText("COUNTRY: " + country.toUpperCase());
			} else {
				countrySelected.setText("Not a valid country");
			}
		}catch (NumberFormatException nf) {
			JOptionPane.showMessageDialog(null, "Not valid");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void showParticipants(ActionEvent a) {
		mateo.getChildren().clear();
		Participant temp = countryList;
		double counterx = 87;
		double countery = 257;
		int count = 0;
		informative.setText("PARTICIPANTS");
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

			Label lbInfo = new Label("Name: " + temp.getFirstName() + "\nId: " + temp.getId() + "\nGender: " + temp.getGender()
			+ "\nCountry: " + temp.getCountry());
			lbInfo.setLayoutX(counterx);
			lbInfo.setLayoutY(countery + 100);


			Line l = new Line(startx,starty,distance,starty);
			l.setStrokeWidth(3);
			Line l2 = new Line(startx,starty2,distance,starty2);
			l2.setStrokeWidth(3);
			mateo.getChildren().add(v);
			mateo.getChildren().add(lbInfo);
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
		mateo.getChildren().clear();
		informative.setText("SPECTATORS");
		int count = 0;
		try {
			Spectator temp = newSpectator;
			double counterx = 720;
			double countery = 6;
			double left = 100;
			double right = 100;
			double downR = 150;
			double downL = 150;


			Image i1 = new Image(temp.getPhoto());
			ImageView v1 = new ImageView(i1);
			v1.setFitHeight(100);
			v1.setFitWidth(100);
			v1.setLayoutX(counterx);
			v1.setLayoutY(countery);

			Label lbInfo = new Label("Name: " + temp.getFirstName() + "\nId: " + temp.getId() + "\nGender: " + temp.getGender()
			+ "\nCountry: " + temp.getCountry());
			lbInfo.setLayoutX(counterx);
			lbInfo.setLayoutY(countery + 100);

			mateo.getChildren().add(lbInfo);
			mateo.getChildren().add(v1);

			while((temp.getRight() != null || temp.getLeft() != null) && count <= 7) {
				if(temp.getRight() != null) {
					Spectator rightS = temp.getRight();
					Image i = new Image(rightS.getPhoto());
					ImageView v = new ImageView(i);
					v.setFitHeight(100);
					v.setFitWidth(100);
					double actualx = counterx + right;
					double actualy = countery + downR;
					v.setLayoutX(actualx);
					v.setLayoutY(actualy);

					Label lbInfo2 = new Label("Name: " + rightS.getFirstName() + "\nId: " + rightS.getId() + "\nGender: " + rightS.getGender()
					+ "\nCountry: " + rightS.getCountry());
					lbInfo2.setLayoutX(actualx);
					lbInfo2.setLayoutY(actualy+ 100);

					mateo.getChildren().add(lbInfo2);
					mateo.getChildren().add(v);

					right += 100;
					downR += 100;

					temp = temp.getRight();
					count++;
				}
				if(temp.getLeft() != null) {
					Spectator leftS = temp.getLeft();
					Image i = new Image(leftS.getPhoto());
					ImageView v = new ImageView(i);
					v.setFitHeight(100);
					v.setFitWidth(100);
					double actualx = counterx - left;
					double actualy = countery + downL;
					v.setLayoutX(actualx);
					v.setLayoutY(actualy);

					Label lbInfo2 = new Label("Name: " + leftS.getFirstName() + "\nId: " + leftS.getId() + "\nGender: " + leftS.getGender()
					+ "\nCountry: " + leftS.getCountry());
					lbInfo2.setLayoutX(actualx);
					lbInfo2.setLayoutY(actualy + 100);

					mateo.getChildren().add(lbInfo2);
					mateo.getChildren().add(v);

					left += 100;
					downL += 100;

					temp = temp.getLeft();
					count++;
				}

			}
		} catch(NullPointerException e) {
			System.out.println("Something inside the code is producing a null pointer");
		}
	}
}
