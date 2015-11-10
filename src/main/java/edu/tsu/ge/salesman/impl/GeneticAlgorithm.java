package edu.tsu.ge.salesman.impl;

import edu.tsu.ge.salesman.model.Population;
import edu.tsu.ge.salesman.model.Tour;

public abstract class GeneticAlgorithm {
    protected final double MUTATION_RATE;
    protected final int TOURNAMENT_SIZE;
    protected final boolean ELITISM;

    GeneticAlgorithm(double mutationRate, int tournamentSize, boolean elitism) {
        MUTATION_RATE = mutationRate;
        TOURNAMENT_SIZE = tournamentSize;
        ELITISM = elitism;
    }

    public abstract Population evolvePopulation(Population pop);

    abstract Tour crossover(Tour parent1, Tour parent2);

    abstract void mutate(Tour tour);

    abstract Tour tournamentSelection(Population pop);

}
