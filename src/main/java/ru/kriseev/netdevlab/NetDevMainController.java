package ru.kriseev.netdevlab;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.kriseev.netdevlab.model.Game;

public class NetDevMainController {

    @FXML
    private Button stopButton;
    @FXML
    private Button startButton;
    @FXML
    private Button shootButton;
    @FXML
    private BorderPane mainLayout;
    @FXML
    private Pane fieldPane;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label shotsLabel;

    private final Game game;

    private GameRenderer renderer;
    private Thread gameRunner;
    public NetDevMainController() {
        game = new Game(600, 400);
    }

    public void initialize() {
        renderer = new GameRenderer(fieldPane, game);
        NetDevLabApplication.addStopHandler(() -> {
            if(gameRunner != null)
            {
                gameRunner.interrupt();
                try {
                    gameRunner.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    @FXML
    protected void startGame() {
        if(gameRunner != null) {
            return;
        }
        startButton.setDisable(true);
        stopButton.setDisable(false);
        shootButton.setDisable(false);
        stopButton.setVisible(true);
        shootButton.setVisible(true);
        NetDevLabApplication.getMainStage().setResizable(false);
        renderer.createGame();
        gameRunner = new Thread(() -> {
            synchronized (game) {
                game.setFieldWidth(fieldPane.getWidth());
                game.setFieldHeight(fieldPane.getHeight());
                game.reset();
            }
            Platform.runLater(() -> {
                scoreLabel.setText("Счёт: 0");
                shotsLabel.setText("Выстрелы: 0");
            });
            try {
                while (true) {
                    synchronized (game) {
                        game.step();

                    }
                    Platform.runLater(() -> {
                        synchronized (game) {
                            scoreLabel.setText("Счёт: " + game.getScore());
                            shotsLabel.setText("Выстрелы: " + game.getShotsCount());
                        }
                        renderer.update();
                    });
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                synchronized (game) {
                    game.killArrow();
                }
                gameRunner = null;
                Platform.runLater(this::destroyGame);
                return;
            }
        });
        gameRunner.start();
    }

    @FXML
    protected void stopGame() {
        gameRunner.interrupt();
        renderer.clearGame();
    }

    @FXML
    protected void shoot() {
        synchronized (game) {
            game.shoot();
        }
    }

    protected void destroyGame() {
        stopButton.setDisable(true);
        shootButton.setDisable(true);
        startButton.setDisable(false);
        stopButton.setVisible(false);
        shootButton.setVisible(false);
        NetDevLabApplication.getMainStage().setResizable(true);
    }
}
