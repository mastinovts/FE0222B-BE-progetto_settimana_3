package scuola;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Studente {

    //ATTRIBUTI
    private int id;
    private String nome;
    private String cognome;
    private char genere;
    private HashMap<String, List<Double>> voti;

    //COSTRUTTORE
    public Studente(int id, String nome, String cognome, char genere) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.genere = genere;
        this.voti = randomMap();
    }


    //GETTER & SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public char getGenere() {
        return genere;
    }

    public void setGenere(char genere) {
        this.genere = genere;
    }

    public HashMap<String, List<Double>> getVoti() {
        return voti;
    }

    public void setVoti(HashMap<String, List<Double>> voti) {
        this.voti = voti;
    }

    @Override
    public String toString() {
        return nome + " " +cognome;
    }

    //METODI
    public double mediaVotoMateria(String materia){
        HashMap<String, List<Double>> votiAppoggio = getVoti();
        List<Double> listaVoti = votiAppoggio.get(materia);
        Double streamListaVoti = listaVoti.stream().reduce(0.0, (subtotale, voto) -> subtotale + voto);
        return streamListaVoti/listaVoti.size();
    }

    public double votoMiglioreMateria(String materia){
        HashMap<String, List<Double>> votiAppoggio = getVoti();
        List<Double> listaVoti = votiAppoggio.get(materia);
        return listaVoti.stream().reduce(0.0, (subtotale, voto) ->  voto > subtotale ? subtotale = voto : subtotale);
    }

    public boolean promosso(){
        HashMap<String, List<Double>> votiAppoggio = getVoti();
        Stream<String> listaMaterie = votiAppoggio.keySet().stream();
        long cont = listaMaterie.filter(materia -> mediaVotoMateria(materia)<6).count();
        return cont < 4;
    }

    private List<Double> randomList(){
        Random random = new Random();
        Double[] arrVotiRndm = new Double[6];
        for (int i = 0; i < arrVotiRndm.length; i++){
            arrVotiRndm[i] = random.nextDouble(2,10);
        }
        Stream<Double> listVotiRndm = Arrays.stream(arrVotiRndm);
        return listVotiRndm.collect(Collectors.toList());
    }

    public HashMap<String,List<Double>> randomMap(){
        HashMap<String,List<Double>> randomMap = new HashMap<>();
        randomMap.put("Italiano",randomList());
        randomMap.put("Matematica",randomList());
        randomMap.put("Inglese",randomList());
        randomMap.put("Scienze",randomList());
        randomMap.put("Arte",randomList());
        randomMap.put("Tecnica",randomList());
        return randomMap;
    }

    public double mediaTotale(){
        Stream<String> materie = getVoti().keySet().stream();
        double tot = materie.mapToDouble(materia -> mediaVotoMateria(materia)).sum();

        return  tot/getVoti().size();
    }
}
