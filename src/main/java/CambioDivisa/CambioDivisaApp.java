package CambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisaApp extends Application {
	private Divisa euro = new Divisa("euro", 1.0);
	private Divisa libra = new Divisa("libra", 0.90);
	private Divisa dolar = new Divisa("dolar", 1.17);
	private Divisa yen = new Divisa("yen", 124.17);
	
	private Divisa[] divisas= { euro ,dolar,libra,yen};
	
	private TextField valorTextA, valorTextB;
	private Button CambiarButton;
	private ComboBox<Divisa> comboBoxA, comboBoxB;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		valorTextA = new TextField();
		valorTextA.setPromptText("");
		valorTextA.setMaxWidth(150);

		valorTextB = new TextField();
		valorTextB.setPromptText("");
		valorTextB.setMaxWidth(150);
		valorTextB.setEditable(false);
		
		CambiarButton = new Button("Cambiar");
		CambiarButton.setDefaultButton(true);
		CambiarButton.setOnAction(e -> cambiarButtonAction(e));
		
		comboBoxA = new ComboBox<>();
		comboBoxA.getItems().addAll(divisas);
		comboBoxA.getSelectionModel().selectFirst();
		
		comboBoxB = new ComboBox<>();
		comboBoxB.getItems().addAll(divisas);
		comboBoxB.getSelectionModel().selectFirst();
		
		HBox primerBox = new HBox();
		primerBox.setSpacing(5);
		primerBox.setAlignment(Pos.CENTER);
		primerBox.getChildren().addAll(valorTextA, comboBoxA);

		HBox segundoBox = new HBox();
		segundoBox.setSpacing(5);
		segundoBox.setAlignment(Pos.CENTER);
		segundoBox.getChildren().addAll(valorTextB, comboBoxB);

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(primerBox, segundoBox, CambiarButton);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Cambio divisa");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	private void cambiarButtonAction(ActionEvent e) {
		Double cantidadOrigen = Double.parseDouble(valorTextA.getText());
		Divisa divisaOrigen = comboBoxA.getSelectionModel().getSelectedItem();
		Divisa divisadestino = comboBoxB.getSelectionModel().getSelectedItem();
		Double cantidadDestino = divisadestino.fromEuro(divisaOrigen.toEuro(cantidadOrigen));
		valorTextB.setText(""+cantidadDestino);
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
	

}


