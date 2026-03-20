package iscte.ista;

public class Student {

    private String name;
    private int number;

    // Construtor vazio (obrigatório para Jackson)
    public Student() {
    }

    // Construtor com parâmetros
    public Student(String name, int number) {
        this.name = name;
        this.number = number;
    }

    // Getter e Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // Método opcional (vai dar jeito mais à frente no CSV)
    public String toCSV() {
        return number + "," + name;
    }
}