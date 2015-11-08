package edu.tsu.ge.salesman;

import edu.tsu.ge.salesman.impl.GeneticAlgorithm;
import edu.tsu.ge.salesman.impl.GeneticAlgorithmImpl;
import edu.tsu.ge.salesman.manager.TourManager;
import edu.tsu.ge.salesman.model.Population;
import edu.tsu.ge.salesman.xml.CityXmlParser;

public class App {
    public static void main(String[] args) {

        CityXmlParser xmlParser = new CityXmlParser();
        xmlParser.getCities().forEach(TourManager::addCity);

        // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        GeneticAlgorithm GA = new GeneticAlgorithmImpl();

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
