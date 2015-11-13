package edu.tsu.ge.salesman.core.impl;

import edu.tsu.ge.salesman.core.input.ExcelInputParser;
import edu.tsu.ge.salesman.core.input.InputParser;
import edu.tsu.ge.salesman.core.input.XmlInputParser;
import edu.tsu.ge.salesman.core.manager.TourManager;
import edu.tsu.ge.salesman.core.model.InputMetaModel;
import edu.tsu.ge.salesman.core.model.Population;
import edu.tsu.ge.salesman.core.model.ResultMetaModel;

public class Evaluator {

    public ResultMetaModel eval(InputMetaModel model) throws Exception {
        ResultMetaModel result = new ResultMetaModel();

        InputParser inputParser = null;

        switch (model.getInputType()) {
            case XML:
                inputParser = new XmlInputParser(model.getInputStream());
                break;
            case EXCEL:
                inputParser = new ExcelInputParser(model.getInputStream());
                break;
        }

        inputParser.getCities().forEach(TourManager::addCity);

        // Initialize population
        Population pop = new Population(50, true);
        result.setInitialDistance(pop.getFittest().getDistance());

        GeneticAlgorithm GA = new GeneticAlgorithmImpl(model.getMutationRate(), model.getTournamentSize(), model.isElitism());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }

        result.setFinalDistance(pop.getFittest().getDistance());
        result.setFittest(pop.getFittest());

        return result;

    }
}
