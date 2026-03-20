package iscte.ista;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class App {

    private static final String FIRSTNAMES_F_FILE = "data/firstnames_f.json";
    private static final String FIRSTNAMES_M_FILE = "data/firstnames_m.json";
    private static final String SURNAMES_FILE = "data/surnames.json";
    private static final String STUDENTS_CVS_FILE = "data/students.csv";
    private static final int INITIAL_NUMBER = 21000;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Quantos alunos quer gerar? ");
        int n = scanner.nextInt();

        ObjectMapper mapper = new ObjectMapper();

        // 📖 Ler ficheiros JSON
        List<String> firstNamesF = Arrays.asList(
                mapper.readValue(getResource(FIRSTNAMES_F_FILE), String[].class)
        );

        List<String> firstNamesM = Arrays.asList(
                mapper.readValue(getResource(FIRSTNAMES_M_FILE), String[].class)
        );

        List<String> surnames = Arrays.asList(
                mapper.readValue(getResource(SURNAMES_FILE), String[].class)
        );

        Random random = new Random();
        Set<String> usedNames = new HashSet<>();
        List<Student> students = new ArrayList<>();

        int number = INITIAL_NUMBER;

        while (students.size() < n) {

            // escolher nome aleatório
            String firstName;
            if (random.nextBoolean()) {
                firstName = firstNamesF.get(random.nextInt(firstNamesF.size()));
            } else {
                firstName = firstNamesM.get(random.nextInt(firstNamesM.size()));
            }

            String surname = surnames.get(random.nextInt(surnames.size()));

            String fullName = firstName + " " + surname;

            // evitar duplicados
            if (!usedNames.contains(fullName)) {
                usedNames.add(fullName);

                Student s = new Student(fullName, number++);
                students.add(s);
            }
        }

        // 💾 Escrever CSV
        File file = new File(STUDENTS_CVS_FILE);
        file.getParentFile().mkdirs(); // cria pasta se não existir

        PrintWriter writer = new PrintWriter(file);

        for (Student s : students) {
            writer.println(s.toCSV());
        }

        writer.close();

        System.out.println("Ficheiro CSV criado com sucesso!");
    }

    // 🔧 Função para ler resources corretamente
    private static InputStream getResource(String path) {
        return App.class.getClassLoader().getResourceAsStream(path);
    }
}