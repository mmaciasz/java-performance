package pl.com.pollub.test;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.pollub.test.collections.CollectionsOperations;
import pl.com.pollub.test.collections.MapOperations;
import pl.com.pollub.test.sort.SortTest;
import pl.com.pollub.test.threads.PingPong;

/**
 * Created by mmaciasz on 2016-12-07.
 */
public class MainBenchmark {

    private static final Logger log;

    static {
        log = LoggerFactory.getLogger(MainBenchmark.class);
    }

    public static void main(String[] args) throws RunnerException {
        log.info("Beginning jmh tests...");
        Options opt = new OptionsBuilder()
                .include(PingPong.class.getSimpleName())
                .include(SortTest.class.getSimpleName())
                .include(CollectionsOperations.class.getSimpleName())
                .include(MapOperations.class.getSimpleName())
//                .include(Test.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(20)
                .build();

        new Runner(opt).run();
    }
/*
    public static void main(String[] args) throws RunnerException {
        // These are our base options. We will mix these options into the
        // measurement runs. That is, all measurement runs will inherit these,
        // see how it's done below.
        Options baseOpts = new OptionsBuilder()
                .include(Test.class.getName())
                .warmupTime(TimeValue.milliseconds(200))
                .measurementTime(TimeValue.milliseconds(200))
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .verbosity(VerboseMode.SILENT)
                .build();

        // Initial population
        Test.Population pop = new Test.Population();
        final int POPULATION = 10;
        for (int c = 0; c < POPULATION; c++) {
            pop.addChromosome(new Test.Chromosome(baseOpts));
        }

        // Make a few rounds of optimization:
        final int GENERATIONS = 20;
        for (int g = 0; g < GENERATIONS; g++) {
            System.out.println("Entering generation " + g);

            // Get the baseline score.
            // We opt to remeasure it in order to get reliable current estimate.
            RunResult runner = new Runner(baseOpts).runSingle();
            Result baseResult = runner.getPrimaryResult();

            // Printing a nice table...
            System.out.println("---------------------------------------");
            System.out.printf("Baseline score: %10.2f %s%n",
                    baseResult.getScore(),
                    baseResult.getScoreUnit()
            );

            for (Test.Chromosome c : pop.getAll()) {
                System.out.printf("%10.2f %s (%+10.2f%%) %s%n",
                        c.getScore(),
                        baseResult.getScoreUnit(),
                        (c.getScore() / baseResult.getScore() - 1) * 100,
                        c.toString()
                );
            }
            System.out.println();

            Test.Population newPop = new Test.Population();

            // Copy out elite solutions
            final int ELITE = 2;
            for (Test.Chromosome c : pop.getAll().subList(0, ELITE)) {
                newPop.addChromosome(c);
            }

            // Cross-breed the rest of new population
            while (newPop.size() < pop.size()) {
                Test.Chromosome p1 = pop.selectToBreed();
                Test.Chromosome p2 = pop.selectToBreed();

                newPop.addChromosome(p1.crossover(p2).mutate());
                newPop.addChromosome(p2.crossover(p1).mutate());
            }

            pop = newPop;
        }

    }*/
}
