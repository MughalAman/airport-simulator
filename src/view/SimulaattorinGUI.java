package view;

import java.text.DecimalFormat;

import controller.IKontrolleri;
import controller.Kontrolleri;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;

public class SimulaattorinGUI extends Application implements ISimulaattorinUI{

	//Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleri kontrolleri;

	//Tallenusta varten
	private double loppuaika;
	private int asiakasmaara;
	private double checkaktiiviaika;
	private double turvatarkastus;
	private double oleskeluaikaturvatarkastus;
	private double checkinkayttoaste;
	private double lentokentanteho;
	private double checkinpalveluaika;
	private double turvatarkastusjono;
	private double turvatarkastusjononpituus;

	// Käyttöliittymäkomponentit:
	private TextField aika;
	private TextField viive;
	private Slider sisaankayntiJakauma;
	private Slider checkinJakauma; 
	private Slider infoLkm, manualLkm, autoLkm;
	private Label infoLabel, manualLabel, autoLabel;
	private Label tulos;
	private Label tulos2;
	private Label tulos3;
	private Label tulos4;
	private Label tulos5;
	private Label tulos6;
	private Label tulos7;
	private Label tulos8;
	private Label tulos9;
	private Label tulos10;
	private Label aikaLabel;
	private Label viiveLabel;
	private Label jakauma1Label;
	private Label jakauma2Label;
	private Label tulosLabel;
	private Label tulosLabel2;
	private Label tulosLabel3;
	private Label tulosLabel4;
	private Label tulosLabel5;
	private Label tulosLabel6;
	private Label tulosLabel7;
	private Label tulosLabel8;
	private Label tulosLabel9;
	private Label tulosLabel10;

	private Button kaynnistaButton;
	private Button hidastaButton;
	private Button nopeutaButton;

	private IVisualisointi naytto;


	@Override
	public void init(){

		Trace.setTraceLevel(Level.INFO);

		kontrolleri = new Kontrolleri(this);
	}

	@Override
	public void start(Stage primaryStage) {
		Image image  = new Image("/img/applogo.png");
		primaryStage.getIcons().add(image);
		
		
		
		// Käyttöliittymän rakentaminen
		try {

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});

			primaryStage.setTitle("Simulaattori");

			kaynnistaButton = new Button();
			kaynnistaButton.setText("Käynnistä simulointi");

			kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                kontrolleri.kaynnistaSimulointi();
	                kaynnistaButton.setDisable(true);
	            }
	        });

			//UUS NÄKYMÄ

			Button uus = new Button();
			uus.setText("Historia");

			uus.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                GridPane addPane = new GridPane();
	                ListView<String> tulosList = new ListView<String>();

	                ObservableList<String> tulokset = FXCollections.observableArrayList();
	                System.out.println(kontrolleri.naytaTulokset());
	                kontrolleri.naytaTulokset();
	                for (int i = 0; i < kontrolleri.naytaTulokset().length; i++) {
	    				tulokset.add(kontrolleri.naytaTulokset()[i]);
	    				System.out.println("data: \n"+kontrolleri.naytaTulokset()[i]);
	    			}

	                
	                
	                
	               
	                tulosList.setItems(tulokset);
	    			tulosList.setPrefSize(900, 500);

//	    			ei TARTE päivittyy itestää
	    			//Päivitä GUI
//	    			Button refresh1 = new Button();
//	    			refresh1.setText("Päivitä");
//	    			

	                
	                HBox hBox = new HBox();
	                
	    			VBox tulosBox = new VBox();
	    			tulosBox.getChildren().addAll(new Label("Entiset Tulokset:"), tulosList);
	    			
	    			addPane.add(tulosBox, 0, 0);
	    			
	    			hBox.getChildren().addAll(addPane);

	                Stage addStage = new Stage();
	                Scene addScene = new Scene(hBox);

	            	Image image  = new Image("/img/applogo.png");
	            	addStage.getIcons().add(image);
	        		


	                addStage.setTitle("SQL HISTORIA");
	                addStage.setScene(addScene);
	                addScene.getStylesheets().add("view/style.css");

	                addStage.show();
	                
	                
	                
//	                refresh1.setOnAction(e -> {
//		                addStage.close();
//
//
//		                
//		      
//		                addStage.show();
//
//	                });

	                
	            }
	        });


			Button tallenna = new Button();
			tallenna.setText("Tallenna");

			tallenna.setOnAction(e -> kontrolleri.tallennaTulokset(loppuaika, asiakasmaara, checkaktiiviaika, turvatarkastus, oleskeluaikaturvatarkastus, checkinkayttoaste, lentokentanteho, checkinpalveluaika, turvatarkastusjono, turvatarkastusjononpituus));



			hidastaButton = new Button();
			hidastaButton.setText("Hidasta");

			hidastaButton.setOnAction(e -> kontrolleri.hidasta());

			nopeutaButton = new Button();
			nopeutaButton.setText("Nopeuta");

			nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());

			aikaLabel = new Label("Simulointiaika (min):");

	        aika = new TextField("Syötä aika");
	        aika.setPrefWidth(150);

	        viiveLabel = new Label("Viive:");
	        viive = new TextField("Syötä viive");
	        viive.setPrefWidth(150);
	        
	        jakauma1Label = new Label("Sisäänkäynti jakauma (Info/Check-in):");
	        sisaankayntiJakauma = new Slider(0, 1, 0.5);
	        sisaankayntiJakauma.setShowTickMarks(true);
	        sisaankayntiJakauma.setMajorTickUnit(0.25f);
	        sisaankayntiJakauma.setBlockIncrement(0.1f);
	        sisaankayntiJakauma.setShowTickLabels(true);
	        
	        jakauma2Label = new Label("Check-in jakauma (AUTO/MANUAL):");
	        checkinJakauma = new Slider(0, 1, 0.5);
	        checkinJakauma.setShowTickMarks(true);
	        checkinJakauma.setMajorTickUnit(0.25f);
	        checkinJakauma.setBlockIncrement(0.1f);
	        checkinJakauma.setShowTickLabels(true);
	        
	        infoLabel = new Label("Infon palvelupistettein lkm:");
	        infoLkm = new Slider(1, 10, 1);
	        infoLkm.setShowTickMarks(true);
	        infoLkm.setMajorTickUnit(1);
	        infoLkm.setBlockIncrement(1);
	        infoLkm.setShowTickLabels(true);
	        
	        manualLabel = new Label("Manual check-in palvelupisteitten lkm:");
	        manualLkm = new Slider(1, 10, 1);
	        manualLkm.setShowTickMarks(true);
	        manualLkm.setMajorTickUnit(1);
	        manualLkm.setBlockIncrement(1);
	        manualLkm.setShowTickLabels(true);
	        
	        autoLabel = new Label("Auto check-in palvelupisteitten lkm:");
	        autoLkm = new Slider(1, 10, 1);
	        autoLkm.setShowTickMarks(true);
	        autoLkm.setMajorTickUnit(1);
	        autoLkm.setBlockIncrement(1);
	        autoLkm.setShowTickLabels(true);

	        tulosLabel = new Label("Kokonaisaika (min):");
	        tulos = new Label();
	        tulos.setPrefWidth(150);

	        tulosLabel2 = new Label("Asiakas määrä:");
	        tulos2 = new Label();
	        tulos2.setPrefWidth(150);

	        tulosLabel3 = new Label("Check-in aktiiviaika:");
	        tulos3 = new Label();
	        tulos3.setPrefWidth(150);

	        tulosLabel4 = new Label("Turvatarkastuksen läpimenoaika:");
	        tulos4 = new Label();
	        tulos4.setPrefWidth(150);

	        tulosLabel5 = new Label("Turvatarkastuksen oleskeluaika:");
	        tulos5 = new Label();
	        tulos5.setPrefWidth(150);

	        tulosLabel6 = new Label("Check-in käyttöaste:");
	        tulos6 = new Label();
	        tulos6.setPrefWidth(150);

	        tulosLabel7 = new Label("Lentokentän suoritusteho:");
	        tulos7 = new Label();
	        tulos7.setPrefWidth(150);

	        tulosLabel8 = new Label("Check-in keskimääräinen palveluaika:");
	        tulos8 = new Label();
	        tulos8.setPrefWidth(150);

	        tulosLabel9 = new Label("Turvatarkastuksen keskimääräinen läpimenoaika:");
	        tulos9 = new Label();
	        tulos9.setPrefWidth(150);

	        tulosLabel10 = new Label("Turvatarkastuksen keskimääräinen jononpituus:");
	        tulos10 = new Label();
	        tulos10.setPrefWidth(150);

	        HBox hBox = new HBox();
	        hBox.setPadding(new Insets(15, 12, 15, 12)); // marginaalit ylä, oikea, ala, vasen
	        hBox.setSpacing(10);   // noodien välimatka 10 pikseliä

	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setVgap(10);
	        grid.setHgap(5);

	        grid.add(aikaLabel, 0, 0);   // sarake, rivi
	        grid.add(aika, 1, 0);          // sarake, rivi
	        
	        grid.add(viiveLabel, 0, 1);      // sarake, rivi
	        grid.add(viive, 1, 1);           // sarake, rivi
	        
	        grid.add(jakauma1Label, 0, 2);          // sarake, rivi
	        grid.add(sisaankayntiJakauma, 1, 2);          // sarake, rivi
	        
	        grid.add(jakauma2Label, 0, 3);          // sarake, rivi
	        grid.add(checkinJakauma, 1, 3);          // sarake, rivi
	        
	        grid.add(infoLabel, 0, 4);
	        grid.add(infoLkm, 1, 4);
	        
	        grid.add(manualLabel, 0, 5);
	        grid.add(manualLkm, 1, 5);
	        
	        grid.add(autoLabel, 0, 6);
	        grid.add(autoLkm, 1, 6);
	        
	        grid.add(tulosLabel, 0, 7);      // sarake, rivi
	        grid.add(tulosLabel2, 0, 8);      // sarake, rivi
	        grid.add(tulosLabel3, 0, 9);      // sarake, rivi
	        grid.add(tulosLabel4, 0, 10);      // sarake, rivi
	        grid.add(tulosLabel5, 0, 11);      // sarake, rivi
	        grid.add(tulosLabel6, 0, 12);      // sarake, rivi
	        grid.add(tulosLabel7, 0, 13);      // sarake, rivi
	        grid.add(tulosLabel8, 0, 14);      // sarake, rivi
	        grid.add(tulosLabel9, 0, 15);      // sarake, rivi
	        grid.add(tulosLabel10, 0, 16);      // sarake, rivi
	        grid.add(tulos, 1, 7);           // sarake, rivi
	        grid.add(tulos2, 1, 8);           // sarake, rivi
	        grid.add(tulos3, 1, 9);           // sarake, rivi
	        grid.add(tulos4, 1, 10);           // sarake, rivi
	        grid.add(tulos5, 1, 11);           // sarake, rivi
	        grid.add(tulos6, 1, 12);           // sarake, rivi
	        grid.add(tulos7, 1, 13);           // sarake, rivi
	        grid.add(tulos8, 1, 14);           // sarake, rivi
	        grid.add(tulos9, 1, 15);           // sarake, rivi
	        grid.add(tulos10, 1, 16);           // sarake, rivi
	        grid.add(kaynnistaButton,0, 17);  // sarake, rivi
	        grid.add(nopeutaButton, 0, 18);   // sarake, rivi
	        grid.add(hidastaButton, 1, 18);   // sarake, rivi
	        grid.add(uus, 1, 19);   // sarake, rivi

	        grid.add(tallenna, 0, 19);   // sarake, rivi



	        naytto = new Visualisointi((1920/2),(1080/2));


	        // Täytetään boxi:
	        hBox.getChildren().addAll(grid, (Canvas)naytto);




	        Scene scene = new Scene(hBox);

	        scene.getStylesheets().add("view/style.css");



	        primaryStage.setScene(scene);
	        primaryStage.show();



		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	//Käyttöliittymän rajapintametodit (kutsutaan kontrollerista)

	@Override
	public double getAika(){
		return Double.parseDouble(aika.getText());
	}

	@Override
	public long getViive(){
		return Long.parseLong(viive.getText());
	}
	
	@Override
	public double getSisJakauma(){
		return sisaankayntiJakauma.getValue();
	}
	
	@Override
	public double getCheJakauma(){
		return checkinJakauma.getValue();
	}

	@Override
	public double getT() {
		return Double.parseDouble(tulos.getText());
	}

	@Override
	public int getC() {
		return Integer.parseInt(tulos2.getText());
	}

	@Override
	public double getB() {
		return Double.parseDouble(tulos3.getText());
	}

	@Override
	public double getRi() {
		return Double.parseDouble(tulos4.getText());
	}

	@Override
	public double getW() {
		return Double.parseDouble(tulos5.getText());
	}

	@Override
	public double getK() {
		return Double.parseDouble(tulos6.getText());
	}

	@Override
	public double getS() {
		return Double.parseDouble(tulos7.getText());
	}

	@Override
	public double getP() {
		return Double.parseDouble(tulos8.getText());
	}

	@Override
	public double getA() {
		return Double.parseDouble(tulos9.getText());
	}

	@Override
	public double getR() {
		return Double.parseDouble(tulos10.getText());
	}

	@Override
	public void setLoppuaika(double aika){
		 DecimalFormat formatter = new DecimalFormat("#0.00");
		 this.tulos.setText(formatter.format(aika));
		 this.loppuaika = aika;
	}

	@Override
	public void setAsiakasMaara(int C) {
		DecimalFormat formatter = new DecimalFormat("#0");
		this.tulos2.setText(formatter.format(C));
		this.asiakasmaara = C;
	}

	@Override
	public void setCheckAktiiviAika(double B) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos3.setText(formatter.format(B));
		this.checkaktiiviaika = B;
	}

	@Override
	public void setTurvaTarkastus(double Ri) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos4.setText(formatter.format(Ri));
		this.turvatarkastus = Ri;
	}

	@Override
	public void setOleskeluAikaTurvaTarkastus(double W) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos5.setText(formatter.format(W));
		this.oleskeluaikaturvatarkastus = W;
	}

	@Override
	public void setCheckInKayttoaste(double K) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos6.setText(formatter.format(K));
		this.checkinkayttoaste = K;
	}

	@Override
	public void setLentokentanTeho(double S) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos7.setText(formatter.format(S));
		this.lentokentanteho = S;
	}

	@Override
	public void setCheckinPalveluaika(double P) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos8.setText(formatter.format(P));
		this.checkinpalveluaika = P;
	}

	@Override
	public void setTurvatarkastusJono(double A) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos9.setText(formatter.format(A));
		this.turvatarkastusjono = A;
	}

	@Override
	public void setTurvatarkastusJononPituus(double R) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos10.setText(formatter.format(R));
		this.turvatarkastusjononpituus = R;
	}

	@Override
	public IVisualisointi getVisualisointi() {
		 return naytto;
	}

	// JavaFX-sovelluksen (käyttöliittymän) käynnistäminen

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public int getInfoLkm() {
		return (int)infoLkm.getValue();
	}

	@Override
	public int getManualLkm() {
		return (int)manualLkm.getValue();
	}

	@Override
	public int getAutoLkm() {
		return (int)autoLkm.getValue();
	}

}