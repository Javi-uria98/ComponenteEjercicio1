package com.javier.componente;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TextFieldEspia extends TextField {

    private StringProperty ficheroLog = new SimpleStringProperty();
    private ArrayList<EnPalabraCorrecta> enPalabraCorrecta;

    public TextFieldEspia() {
        enPalabraCorrecta = new ArrayList<EnPalabraCorrecta>();
    }

    public String getFicheroLog() {
        return ficheroLog.get();
    }

    public StringProperty ficheroLogProperty() {
        return ficheroLog;
    }

    public void setFicheroLog(String ficheroLog) {
        this.ficheroLog.set(ficheroLog);
    }

    public void addEnPalabraCorrecta(EnPalabraCorrecta enPalabraCorrecta) {
        this.enPalabraCorrecta.add(enPalabraCorrecta);
    }


    public void iniciar() throws IOException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (ficheroLog != null) {
                    textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                            for (EnPalabraCorrecta e : enPalabraCorrecta) {
                                try {
                                    e.ejecuta("piedra");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                    {
                    }
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 1000, 1000);
    }

    public void aniadirPalabra(String palabra) throws IOException {
        Calendar c = Calendar.getInstance();
        FileWriter fw = new FileWriter(this.ficheroLog.get());
        fw.write(palabra);
        fw.append((char) c.get(Calendar.DATE));
        fw.append((char) c.get(Calendar.HOUR_OF_DAY));

    }
}
