package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.ConsoleController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.IGameLogic;
import model.UnoLogic;
import model.card.deck.NormalUnoDeck;
import model.card.type.ICard;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;
import model.card.type.*;


/**
 * View of the game. It uses a visual interface to view the game.
 * 
 * @author dcampos
 *
 */
public class javaUNOview extends Application implements Observer {

  private String carta;
  private String color;
  private ImageView cardView;
  private ImageView pilaDescarte;
  private ImageView cartaMano;
  private IGameLogic game;
  private String cartaDescarte;
  private String carta1;
  private String carta2;
  private String carta3;
  private String carta4;
  private String carta5;
  private String carta6;
  private String carta7;
  private ArrayList mano;
  private ConsoleView view;
  ConsoleController ctrl;
  private ICard cartaJugada;
  Map<String, String> cardImages = new HashMap<String, String>();

  /**
   * Imports the card images, depending on the color given
   * 
   * @param color color of the cards to be imported
   */
  public void importarCartas(String color) {

    cardImages.put("carta" + color + "0", "file:assets/UnoCards/" + color + "/0.png");
    cardImages.put("carta" + color + "1", "file:assets/UnoCards/" + color + "/1.png");
    cardImages.put("carta" + color + "2", "file:assets/UnoCards/" + color + "/2.png");
    cardImages.put("carta" + color + "3", "file:assets/UnoCards/" + color + "/3.png");
    cardImages.put("carta" + color + "4", "file:assets/UnoCards/" + color + "/4.png");
    cardImages.put("carta" + color + "5", "file:assets/UnoCards/" + color + "/5.png");
    cardImages.put("carta" + color + "6", "file:assets/UnoCards/" + color + "/6.png");
    cardImages.put("carta" + color + "7", "file:assets/UnoCards/" + color + "/7.png");
    cardImages.put("carta" + color + "8", "file:assets/UnoCards/" + color + "/8.png");
    cardImages.put("carta" + color + "9", "file:assets/UnoCards/" + color + "/9.png");
    cardImages.put("carta" + color + "block", "file:assets/UnoCards/" + color + "/block.png");
    cardImages.put("carta" + color + "draw_2", "file:assets/UnoCards/" + color + "/draw_2.png");
    cardImages.put("carta" + color + "reverse", "file:assets/UnoCards/" + color + "/reverse.png");
    cardImages.put("carta" + color + "star", "file:assets/UnoCards/" + color + "/star.png");

    // cardImages.put("botonDer", "file:assets/UnoCards/botonDer.png");
    // cardImages.put("botonIzq", "file:assets/UnoCards/botonIzq.png");
  }

  /**
   * Imports cards without color and other simbols
   * 
   */
  public void importarOtrasCartas() {

    cardImages.put("cartawild_draw_4", "file:assets/UnoCards/none/wild_draw_4.png");
    cardImages.put("cartawild_star", "file:assets/UnoCards/none/wild_star.png");
    cardImages.put("cartawild", "file:assets/UnoCards/none/wild.png");
    cardImages.put("cartaback", "file:assets/UnoCards/none/back.png");


    cardImages.put("botonDer", "file:assets/UnoCards/botonDer.png");
    cardImages.put("botonIzq", "file:assets/UnoCards/botonIzq.png");

  }

  public static void main(String[] args) {

    launch(args);
  }

  @Override
  public void update(Observable o, Object arg) {
    /*
     * //System.out.println("FirstNewsReader got The news:"); if(arg instanceof ICard){
     * //System.out.println("FirstNewsReader got The news:"); String s = ((ICard)
     * arg).getColor().getName() + ((ICard) arg).getSymbol().getName(); cartaDescarte = "carta" + s;
     * System.out.println(s);
     * 
     * } else if(arg instanceof ArrayList){ System.out.println((String) ((ArrayList) arg).get(0));
     * carta1 = (String) ((ArrayList) arg).get(0); carta2 = (String) ((ArrayList) arg).get(1);
     * carta3 = (String) ((ArrayList) arg).get(2); carta4 = (String) ((ArrayList) arg).get(3);
     * carta5 = (String) ((ArrayList) arg).get(4); carta6 = (String) ((ArrayList) arg).get(5);
     * carta7 = (String) ((ArrayList) arg).get(6);
     * 
     * }
     */
    // ctrl.playTurn();
  }

  /**
   * Inicializes window
   * 
   * @param stage stage where the window will be inicialized
   */
  public void start(Stage stage) throws Exception {
    carta = "cartawild_star";

    importarCartas("blue");
    importarCartas("green");
    importarCartas("red");
    importarCartas("yellow");
    importarOtrasCartas();
    // cardImages.put("carta1", "file:assets/UnoCards/blue/0.png");
    // cardImages.put("botonIzq", "file:assets/UnoCards/botonIzq.png");
    // cardImages.put("botonDer", "file:assets/UnoCards/botonDer.png");

    IPlayerListBuilder playerBuilder = new UnoPlayerListBuilder();
    IPlayer p1 = new HumanPlayer("Jugador 1");
    IPlayer p2 = new RandomPlayer("CPU 1");
    IPlayer p3 = new RandomPlayer("CPU 2");
    IPlayer p4 = new RandomPlayer("CPU 3");
    playerBuilder.addPlayer(p1);
    playerBuilder.addPlayer(p2);
    playerBuilder.addPlayer(p3);
    playerBuilder.addPlayer(p4);
    game = new UnoLogic(playerBuilder, new NormalUnoDeck());

    ((Observable) game).addObserver(this);

    view = new ConsoleView(game);
    ctrl = new ConsoleController(game, view);
    // ((Observable) ctrl).addObserver(this);
    cartaDescarte = "carta" + game.getCurrentPlayedCard();
    // ctrl.AskForCardFromHand(p1);
    // ctrl.playTurn();
    /*
     * while (!game.hasEnded()) { ctrl.playTurn(); } game.announceWinner(ctrl);
     */
    mano = game.getCurrentPlayer().getHand();

    Text scenetitle = new Text("Current Player");
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    stage.setTitle("JavaUNO GUI!");

    Label player1 = new Label("Player 1");
    Label cant1 = new Label(p1.getHandSize() + " cards");
    Label player2 = new Label("Player 2");
    Label cant2 = new Label(p2.getHandSize() + " cards");
    Label player3 = new Label("Player 3");
    Label cant3 = new Label(p3.getHandSize() + " cards");
    Label player4 = new Label("Player 4");
    Label cant4 = new Label(p4.getHandSize() + " cards");

    Label cantDescarte = new Label(" cards");
    Label cantDeck = new Label("x cards");

    VBox vBox = new VBox();
    vBox.setSpacing(30);
    vBox.setPadding(new Insets(25, 25, 25, 25));

    HBox hBox = new HBox();
    hBox.setSpacing(20);
    hBox.getChildren().addAll(player1, player2, player3, player4);

    HBox hBox2 = new HBox();
    hBox2.setSpacing(20);
    hBox2.getChildren().addAll(cant1, cant2, cant3, cant4);



    HBox hBox3 = new HBox();
    hBox3.setSpacing(50);
    hBox3.getChildren().addAll(makeCardView(pilaDescarte, cartaDescarte),
        makeCardBackButton("cartaback"), makePopUp2(stage), makePopUp1(stage, "Error"),
        makePopUp1(stage, "UNO!"));



    HBox hBox4 = new HBox();
    hBox4.setSpacing(85);
    hBox4.getChildren().addAll(cantDescarte, cantDeck);

    HBox hBox5 = new HBox();
    hBox5.setSpacing(25);
    hBox5.getChildren().addAll(makeArrowButton("botonIzq"));
    for (int i = 0; i < mano.size(); i++) {
      hBox5.getChildren().add(makeCardButton((ICard) mano.get(i)));
    }
    hBox5.getChildren().addAll(makeArrowButton("botonDer"));
    // makeCardButton("carta"), makeCardButton(carta), makeCardButton(carta),
    // makeCardButton(carta), makeCardButton(carta), makeCardButton(carta), makeCardButton(carta),
    // makeArrowButton("botonDer"));



    vBox.getChildren().addAll(scenetitle, hBox, hBox2, hBox3, hBox4, hBox5);



    stage.setScene(new Scene(vBox, 800, 600));
    // game.getCurrentPlayer();
    stage.show();
    // ctrl.playTurn();

  }

  /**
   * Creates a Button that opens a PopUp
   * 
   * @param stage stage where the button is going to be
   * @param s text that's going to be on the popUp
   * @return Node
   */
  private Node makePopUp1(Stage stage, String s) {
    // text puede ser ERROR o UNO
    Button btn = new Button();
    btn.setText("Aviso");
    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        Button okButton = new Button();
        okButton.setText("OK");
        setOnAction2(okButton, dialog);
        dialogVbox.getChildren().addAll(new Text(s), okButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);


        dialog.setScene(dialogScene);
        dialog.show();
      }
    });
    return btn;
  }

  /**
   * Creates a Button that opens a PopUp
   * 
   * @param stage stage where the button is going to be
   * @return Node
   */
  private Node makePopUp2(Stage stage) {
    Button btn = new Button();
    btn.setText("Seleccion De Color");
    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);

        Button nextButton = new Button();
        nextButton.setText("Next");


        ChoiceBox<String> cb = new ChoiceBox<String>(
            FXCollections.observableArrayList("RED", "BLUE", "GREEN", "YELLOW"));

        /*
         * cb.getSelectionModel().selectedIndexProperty() .addListener(new ChangeListener<Number>()
         * { public void changed(ObservableValue ov, Number value, Number new_value) {
         * label.setText(greetings[new_value.intValue()]); } });
         */

        cb.getSelectionModel().select(0);
        // System.out.println(cb.getValue());
        setOnAction3(nextButton, dialog, cb);

        dialogVbox.getChildren().addAll(new Text("ACTION REQUIRED"), nextButton, cb);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);


        dialog.setScene(dialogScene);
        dialog.show();
      }
    });
    return btn;
  }

  /**
   * Creates an image on the window
   * 
   * @param carta ImageView of the card
   * @param key key used to acces the card on the HashMap
   * @return Node
   */
  private Node makeCardView(ImageView carta, String key) {
    String path = cardImages.get(key);
    Image card = new Image(path);
    carta = new ImageView(card);
    carta.setFitWidth(50);
    carta.setFitHeight(100);
    carta.setSmooth(true);
    carta.setCache(true);
    return carta;
  }

  /*
   * private Node makeButtonView(ImageView carta, String key) { String path = cardImages.get(key);
   * Image card = new Image(path); carta = new ImageView(card); carta.setFitWidth(30);
   * carta.setFitHeight(130); carta.setSmooth(true); carta.setCache(true); return carta; }
   */

  /**
   * Defines action of a button once is pressed
   * 
   * @param b Button to be pressed
   * @param stage stage where the button is going to be
   * @param cb Choice Box to be deploided
   */
  private void setOnAction3(Button b, Stage stage, ChoiceBox cb) {
    b.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        color = (String) cb.getValue();
        stage.close();
        System.out.println(color);
      }
    });
  }

  /**
   * Defines action of a button once is pressed
   * 
   * @param b Button to be pressed
   * @param stage stage where the button is going to be
   */
  private void setOnAction2(Button b, Stage stage) {
    b.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        stage.close();
      }
    });
  }

  /**
   * Defines action of a button once is pressed
   * 
   * @param b Button to be pressed
   * @param carta card that was selected
   */
  private void setOnAction1(Button b, ICard carta) {
    b.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        game.cartaJugada(carta);
        cartaDescarte = "carta" + carta;
      }
    });
  }

  /**
   * Creates a Button with an image of a card
   * 
   * @param carta card that's represented by the button
   * @return Node
   */
  private Node makeCardButton(ICard carta) {

    Image imageDecline = new Image(cardImages.get("carta" + carta));
    cardView = new ImageView(imageDecline);
    cardView.setFitWidth(50);
    cardView.setFitHeight(100);
    Button button1 = new Button();
    button1.setGraphic(cardView);

    setOnAction1(button1, carta);

    return button1;
  }

  /**
   * Creates a Button with an image of the back of a card
   * 
   * @param carta key to acces the image from the HasHMap
   * @return Node
   */
  private Node makeCardBackButton(String carta) {

    Image imageDecline = new Image(cardImages.get(carta));
    cardView = new ImageView(imageDecline);
    cardView.setFitWidth(50);
    cardView.setFitHeight(100);
    Button button1 = new Button();
    button1.setGraphic(cardView);

    // setOnAction1(button1, carta);
    return button1;
  }

  /**
   * Creates a Button with an arrow
   * 
   * @param key key to acces the image on the HashMap
   * @return Node
   */
  private Node makeArrowButton(String key) {

    Image imageDecline = new Image(cardImages.get(key));
    cardView = new ImageView(imageDecline);
    cardView.setFitWidth(30);
    cardView.setFitHeight(100);
    Button button1 = new Button();
    button1.setGraphic(cardView);

    button1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        System.out.println("apretado");
      }
    });
    return button1;
  }

}
