package edu.tsu.ge.salesman;

import edu.tsu.ge.salesman.impl.GeneticAlgorithm;
import edu.tsu.ge.salesman.impl.GeneticAlgorithmImpl;
import edu.tsu.ge.salesman.input.InputParser;
import edu.tsu.ge.salesman.input.XmlInputParser;
import edu.tsu.ge.salesman.manager.TourManager;
import edu.tsu.ge.salesman.model.Population;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean elitism = true;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter mutation rate: ");
        double mutationRate = scanner.nextDouble();

        System.out.print("Enter tournament size: ");
        int tournamentSize = scanner.nextInt();

        System.out.println("*******************************");


        InputParser inputParser = new XmlInputParser();
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
