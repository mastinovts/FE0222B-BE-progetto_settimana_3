package scuola;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Scuola implements Runnable {

    //ATTRIBUTI
    private static final Logger logger = LoggerFactory.getLogger(Scuola.class);
    private List<Studente> scuola;

    //COSTRUTTORE
    public Scuola(List<Studente> scuola) {
        this.scuola = scuola;
    }

    public List<Studente> getScuola() {
        return scuola;
    }

    public void setScuola(List<Studente> scuola) {
        this.scuola = scuola;
    }


    //METODI
    public List<Studente> getPromossi() {
        List<Studente> listaAppoggio = new ArrayList<>();
        Stream<Studente> promossi = getScuola().stream();
        promossi.filter(el -> el.promosso()).forEach(listaAppoggio::add);
        if (listaAppoggio.size() == 0) {
            System.out.println("\nNessuno e' stato promosso!");
        } else {
            System.out.println("\nI promossi sono: ");
        }
        return listaAppoggio;
    }

    public List<Studente> getStudenti() {
        return scuola;
    }

    public Studente getStudenteMigliore() {
        Stream<Studente> studenti = getStudenti().stream();
        return studenti.reduce((max, stud) -> stud.mediaTotale() > max.mediaTotale() ? max = stud : max).get();
    }

    public void salvaStudenti(File file) {
        if (file.exists()) {
            System.out.println();
            try {
                FileUtils.writeStringToFile(file, getStudenti().toString(), "UTF-8", true);
            } catch (IOException e) {
            }
        } else {
            System.out.println("File non trovato!");
        }
    }

    @Override
    public void run() {

        for (Studente studente : getStudenti()) {
            logger.info(studente.toString());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
