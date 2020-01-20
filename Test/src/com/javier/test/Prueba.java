package com.javier.test;

import com.javier.componente.EnPalabraCorrecta;
import com.javier.componente.TextFieldEspia;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Prueba extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox=new VBox();
        TextFieldEspia tfe=new TextFieldEspia();
        tfe.setFicheroLog("C:/Users/DAM/Desktop/ficheroLog.txt");
        tfe.addEnPalabraCorrecta(new EnPalabraCorrecta() {
            @Override
            public void ejecuta(String palabra) throws IOException {
                tfe.aniadirPalabra(palabra);
            }
        });

        vBox.getChildren().add(tfe);
        Scene scene=new Scene(vBox);
        stage.setScene(scene);
        stage.show();
        tfe.iniciar();
    }

    public static void main (String[] args){
        launch(args);
    }
}
