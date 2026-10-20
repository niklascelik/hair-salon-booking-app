
package se.niklascelik.hairsalon.app;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        SalonService salon = new SalonService();
        Scanner scanner = new Scanner(System.in);

        SalonMenu menu = new SalonMenu(scanner, salon);
        menu.run();

    }
}
