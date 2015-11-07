package edu.tsu.ge.salesman.impl;

import edu.tsu.ge.salesman.model.Population;
import edu.tsu.ge.salesman.model.Tour;

public interface GeneticAlgorithm {
    Population evolvePopulation(Population pop);

    Tour crossover(Tour parent1, Tour parent2);

    void mutate(Tour tour);

    Tour tournamentSelection(Population pop);

}
