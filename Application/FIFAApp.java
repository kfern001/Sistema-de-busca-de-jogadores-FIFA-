package Application;

import DataBase.FIFADataBase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FIFAApp {
    public static void main(String[] args) {
        List<FIFADataBase> base = new ArrayList<FIFADataBase>();

        try (BufferedReader br = new BufferedReader(new FileReader("Players Data Base.txt"))) {
            String line = br.readLine(); // This is the header
            line = br.readLine(); // this is the first line with real information

            while (line != null) {
                String[] vect = line.split(",");
                String playerId = vect[0];
                String playerName = vect[1];
                String playerNationality = vect[2];
                String playerClub = vect[3];
                Byte playerOverall = Byte.parseByte(vect[4]);

                base.add(new FIFADataBase(playerId, playerName, playerNationality, playerClub, playerOverall));

                line = br.readLine();
            }
        } catch (IOException x) {
            System.out.println("Error: " + x);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to search for a player? (s/n) ");
        char answer = sc.next().charAt(0);

        while (answer == 's') {
            System.out.println("What attribute do you want to search the player for? Type one of the" +
                    " following options: ");
            System.out.println("ID - NAME - NATIONALITY - CLUB - OVERALL");
            String attribute = sc.next();
            switch (attribute.toUpperCase()) {
                case "ID":
                    System.out.print("\nEnter the id to search for: ");
                    String id = sc.next();

                    while (!(idCheck(base, id))) {
                        System.out.print("This ID does not exist. Try one from 01 to 18278: ");
                        id = sc.next();
                    }
                    System.out.println(idFound(base, id));
                    break;
                case "NAME":
                    System.out.print("\nEnter the name to search for: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    while (!(nameCheck(base, name))) {
                        System.out.print("This name does not exist. Try another one: ");
                        sc.nextLine();
                        name = sc.nextLine();
                    }

                    System.out.println("\n" + namesFound(base, name).size() + " players found\n");
                    for (FIFADataBase x : namesFound(base, name)) {
                        System.out.println(x.toString());
                    }
                    break;

                case "NATIONALITY":
                    System.out.print("\nEnter the nationality to search for: ");
                    sc.nextLine();
                    String nationality = sc.nextLine();

                    while (!(nationalityCheck(base, nationality))) {
                        System.out.print("This nationality does not exist. Try another one: ");
                        sc.nextLine();
                        nationality = sc.nextLine();
                    }

                    System.out.println("\n" + nationalitiesFound(base, nationality).size() + " players found\n");
                    for (FIFADataBase x : nationalitiesFound(base, nationality)) {
                        System.out.println(x.toString());
                    }
                    break;

                case "CLUB":
                    System.out.print("\nEnter the club to search for: ");
                    sc.nextLine();
                    String club = sc.nextLine();

                    while (!(clubCheck(base, club))) {
                        System.out.print("This club does not exist. Try another one: ");
                        sc.nextLine();
                        nationality = sc.nextLine();
                    }

                    System.out.println("\n" + clubsFound(base, club).size() + " players found\n");
                    for (FIFADataBase x : clubsFound(base, club)) {
                        System.out.println(x.toString());
                    }
                    break;

                case "OVERALL":
                    System.out.print("\nEnter the overall to search for: ");
                    Byte overall = sc.nextByte();

                    while (!(overallCheck(base, overall))) {
                        System.out.print("This overall does not exist. Try another one: ");
                        overall = sc.nextByte();
                    }

                    System.out.println("\n" + overallsFound(base, overall).size() + " players found\n");
                    for (FIFADataBase x : overallsFound(base, overall)) {
                        System.out.println(x.toString());
                    }
                    break;

                default:
                    System.out.println("Attribute not found");
                    break;
            }

            System.out.print("\nDo you want to continue searching? (s/n) ");
            answer = sc.next().charAt(0);
        }

        sc.close();

    }

    public static boolean idCheck(List<FIFADataBase> base, String id) {
        FIFADataBase validate = base.stream().filter(x -> x.getPlayerId().equals(id)).findFirst().orElse(null);
        return validate != null;
    }

    public static boolean nameCheck(List<FIFADataBase> base, String name) {
        List<FIFADataBase> validate = base.stream().filter(x -> x.getPlayerName().toLowerCase().contains(name))
                .collect(Collectors.toList());
        return validate.size() > 0;
    }

    public static boolean nationalityCheck(List<FIFADataBase> base, String nationality) {
        List<FIFADataBase> validate = base.stream()
                .filter(x -> x.getPlayerNationality().toLowerCase().contains(nationality)).collect(Collectors.toList());
        return validate.size() > 0; // this methid also works with a nonlist return
    }

    public static boolean clubCheck(List<FIFADataBase> base, String club) {
        List<FIFADataBase> validate = base.stream().filter(x -> x.getPlayerClub().toLowerCase().contains(club))
                .collect(Collectors.toList());
        return validate.size() > 0;
    }

    public static boolean overallCheck(List<FIFADataBase> base, Byte overall) {
        List<FIFADataBase> validate = base.stream().filter(x -> x.getPlayerOverall() >= overall)
                .collect(Collectors.toList());
        return validate.size() > 0;
    }

    // -/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
    public static FIFADataBase idFound(List<FIFADataBase> base, String id) {
        return base.stream().filter(x -> x.getPlayerId().equals(id)).findFirst().orElse(null);
    }

    public static List<FIFADataBase> namesFound(List<FIFADataBase> base, String name) {
        return base.stream().filter(x -> x.getPlayerName().toLowerCase().contains(name)).collect(Collectors.toList());
    }

    public static List<FIFADataBase> nationalitiesFound(List<FIFADataBase> base, String nationality) {
        return base.stream().filter(x -> x.getPlayerNationality().toLowerCase().contains(nationality))
                .collect(Collectors.toList());
    }

    public static List<FIFADataBase> clubsFound(List<FIFADataBase> base, String club) {
        return base.stream().filter(x -> x.getPlayerClub().toLowerCase().contains(club)).collect(Collectors.toList());
    }

    public static List<FIFADataBase> overallsFound(List<FIFADataBase> base, Byte overall) {
        return base.stream().filter(x -> x.getPlayerOverall() >= overall).collect(Collectors.toList());
    }
}