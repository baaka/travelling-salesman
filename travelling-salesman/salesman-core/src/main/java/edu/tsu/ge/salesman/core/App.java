package edu.tsu.ge.salesman.core;

import edu.tsu.ge.salesman.core.impl.GeneticAlgorithm;
import edu.tsu.ge.salesman.core.impl.GeneticAlgorithmImpl;
import edu.tsu.ge.salesman.core.input.ExcelInputParser;
import edu.tsu.ge.salesman.core.input.InputParser;
import edu.tsu.ge.salesman.core.input.InputType;
import edu.tsu.ge.salesman.core.input.XmlInputParser;
import edu.tsu.ge.salesman.core.manager.TourManager;
import edu.tsu.ge.salesman.core.model.Population;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        try {
            boolean elitism = true;

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter mutation rate: ");
            double mutationRate = scanner.nextDouble();

            System.out.print("Enter tournament size: ");
            int tournamentSize = scanner.nextInt();

            System.out.print("Enter Input Type (0 - xml; 1 - xlsx; ):");
            int inputTypeOrdinal = scanner.nextInt();
            InputType inputType = InputType.values()[inputTypeOrdinal];

            System.out.print("Enter Input File Path: ");
            String inputFilePath = scanner.nextLine();

            File inputFile = new File(inputFilePath);
            FileInputStream fileInputStream = new FileInputStream(inputFile);

            System.out.println("*******************************");


            InputParser inputParser = null;

            switch (inputType) {
                case XML:
                    inputParser = new XmlInputParser(fileInputStream);
                    break;
                case EXCEL:
                    inputParser = new ExcelInputParser(fileInputStream);
                    break;
            }

            inputParser.getCities().forEach(TourManager::addCity);

            // Initialize population
            Population pop = new Population(50, true);
            System.out.println("Initial distance: " + pop.getFittest().getDistance());

            GeneticAlgorithm GA = new GeneticAlgorithmImpl(mutationRate, tournamentSize, elitism);

            // Evolve population for 100 generations
            pop = GA.evolvePopulation(pop);
            for (int i = 0; i < 100; i++) {
                pop = GA.evolvePopulation(pop);
            }

            // Print final results
            System.out.println("Finished");
            System.out.println("Final distance: " + pop.getFittest().getDistance());
            System.out.println("Solution:");
            System.out.println(pop.getFittest());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
