package com.javier.componente;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TextFieldEspia extends TextField {

    private StringProperty ficheroLog = new SimpleStringProperty();
    private ArrayList<EnPalabraCorrecta> enPalabraCorrecta;
    private ArrayList<String> listaPalabras = new ArrayList<String>();

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

    public ArrayList<String> getListaPalabras() {
        return listaPalabras;
    }

    public void setListaPalabras(ArrayList<String> listaPalabras) {
        this.listaPalabras = listaPalabras;
    }

    public void iniciar() throws IOException {
        if (ficheroLog.get() != null) {
            textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    if (listaPalabras.size() > 0) {
                        for (int i = 0; i < listaPalabras.size(); i++) {
                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                            String fecha = sdf.format(date);
                            try {
                                PrintWriter printWriter = new PrintWriter(ficheroLog.get());
                                printWriter.write(getText() + ". Fecha: " + fecha);
                                printWriter.close();
                                for (EnPalabraCorrecta e : enPalabraCorrecta) {
                                    try {
                                        e.ejecuta();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public void aniadirPalabra(String palabra) throws IOException {
        listaPalabras.add(palabra);
    }
}
