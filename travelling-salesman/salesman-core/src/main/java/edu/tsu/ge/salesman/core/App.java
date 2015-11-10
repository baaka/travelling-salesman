package edu.tsu.ge.salesman.core;

import edu.tsu.ge.salesman.core.impl.GeneticAlgorithm;
import edu.tsu.ge.salesman.core.impl.GeneticAlgorithmImpl;
import edu.tsu.ge.salesman.core.input.ExcelInputParser;
import edu.tsu.ge.salesman.core.input.InputParser;
import edu.tsu.ge.salesman.core.input.InputType;
import edu.tsu.ge.salesman.core.input.XmlInputParser;
import edu.tsu.ge.salesman.core.manager.TourManager;
import edu.tsu.ge.salesman.core.model.Population;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        boolean elitism = true;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter mutation rate: ");
        double mutationRate = scanner.nextDouble();

        System.out.print("Enter tournament size: ");
        int tournamentSize = scanner.nextInt();

        System.out.println("*******************************");


        InputParser inputParser = null;
        InputType inputType = InputType.EXCEL;

        switch (inputType) {
            case XML:
                inputParser = new XmlInputParser();
                break;
            case EXCEL:
                inputParser = new ExcelInputParser();
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
    }
}
