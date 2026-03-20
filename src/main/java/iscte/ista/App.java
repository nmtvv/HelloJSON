package iscte.ista;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        // Criar objeto Student
        Student student = new Student("João", 12345);

        // 🔹 Converter objeto → JSON
        String json = mapper.writeValueAsString(student);
        System.out.println("Objeto para JSON:");
        System.out.println(json);

        // 🔹 Converter JSON → objeto
        Student student2 = mapper.readValue(json, Student.class);

        System.out.println("\nJSON para objeto:");
        System.out.println("Nome: " + student2.getName());
        System.out.println("Número: " + student2.getNumber());
    }
}