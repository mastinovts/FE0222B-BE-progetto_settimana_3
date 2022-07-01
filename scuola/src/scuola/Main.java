package scuola;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        File fileStud = new File("src/scuola/Studenti.txt");
        DecimalFormat df = new DecimalFormat("0.00");
        Logger logger = LoggerFactory.getLogger(Scuola.class);

        /** SCUOLA 1 **/

        //STUDENTI
        Studente s1 = new Studente(1,"Arro","Tino",'M');
        Studente s2 = new Studente(2,"Lupo","Solitario",'M');
        Studente s3 = new Studente(3,"Crudelia","Demon",'F');
        Studente s4 = new Studente(4,"Mano","Amica",'F');
        Studente s5 = new Studente(5,"Giulia","Veloce",'F');

        //LISTE STUDENTI
        List<Studente> listaScuola1 = new ArrayList<>();
        listaScuola1.add(s1);
        listaScuola1.add(s2);
        listaScuola1.add(s3);
        listaScuola1.add(s4);
        listaScuola1.add(s5);


        /** SCUOLA 2 **/

        //STUDENTI
        Studente s6 = new Studente(1,"Romeo","Alfa",'M');
        Studente s7 = new Studente(2,"Maurizio","Costanzo",'M');
        Studente s8 = new Studente(3,"Maria","DeFilippi",'F');
        Studente s9 = new Studente(4,"Chiara","Scura",'F');
        Studente s0 = new Studente(5,"Mago","Merlino",'M');

        //LISTE STUDENTI
        List<Studente> listaScuola2 = new ArrayList<>();
        listaScuola2.add(s6);
        listaScuola2.add(s7);
        listaScuola2.add(s8);
        listaScuola2.add(s9);
        listaScuola2.add(s0);



        /** EXECUTE **/

        //SCUOLE
        Scuola scuola1 = new Scuola(listaScuola1);
        Scuola scuola2 = new Scuola(listaScuola2);


        //SCUOLA 1
        System.out.println("\n*****************SCUOLA 1**********************");
        System.out.println(scuola1.getPromossi());
        System.out.println("\nLa lista completa degli studenti della scuola e': " + scuola1.getStudenti());
        System.out.println("\nIl voto migliore di Matematica di " + s4.getNome() + " e' " + df.format(s4.votoMiglioreMateria("Matematica")));
        System.out.println("\nLo studente migliore e': " + scuola1.getStudenteMigliore() + " con una media di " + df.format(scuola1.getStudenteMigliore().mediaTotale()));
        scuola1.salvaStudenti(fileStud); logger.info("I dati degli studenti sono stati salvati nel file: " + fileStud);


        //SCUOLA 2
        System.out.println("\n*****************SCUOLA 2**********************");
        System.out.println(scuola2.getPromossi());
        System.out.println("\nLa lista completa degli studenti della scuola e': " + scuola2.getStudenti());
        System.out.println("\nIl voto migliore in Scienze di " + s7.getNome() + " e' " + df.format(s7.votoMiglioreMateria("Matematica")));
        System.out.println("\nLo studente migliore e': " + scuola2.getStudenteMigliore() + " con una media di " + df.format(scuola2.getStudenteMigliore().mediaTotale()));


        //THREAD
        System.out.println("\n*****************THREAD**********************\n");
        Thread thread1 = new Thread(scuola1, "Scuola1");
        Thread thread2 = new Thread(scuola2, "Scuola2");
        thread1.start();
        thread2.start();

    }

}