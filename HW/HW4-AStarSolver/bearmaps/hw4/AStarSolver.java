package bearmaps.hw4;

import java.util.*;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Memory Efficient A* algorithm - Shortest path solver.
 * Uses a MinPQ to keep track of vertex travel order.
 * Uses a HashMap to record best node distances.
 * @author Andrew Choi
 * Date: 06/11/2019
 */

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome = null;
    private double solutionWeight;
    private List<Vertex> solution;
    private List<Vertex> dequeuedList;
    private double timeSpent;
    private ArrayHeapMinPQ<Vertex> PQ;
    private HashMap<Vertex, Double> distances;
    private HashMap<Vertex, Vertex> origins;
    private int dequeues;

    /** Constructor which finds the solution, computing everything necessary
     *  for all other methods to return their results in constant time.
     *  Note that timeout passed in is in seconds. */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        PQ = new ArrayHeapMinPQ<>();
        distances = new HashMap<>();
        solution = new ArrayList<>();
        dequeuedList = new ArrayList<>();
        origins = new HashMap<>();
        dequeues = 0;
        PQ.add(start, 0.0);
        distances.put(start, 0.0);
        origins.put(start, start);
        Vertex pop = PQ.getSmallest();

        // In case start vertex is the end vertex.
        if (start.equals(end)) {
            solution.add(start);
            solutionWeight = 0;
            outcome = SolverOutcome.SOLVED;
            timeSpent = sw.elapsedTime();
        }

        while (outcome == null) {

            // If PQ is empty, means no possible path from start to end.
            // Problem is unsolvable.
            if (PQ.size() == 0) {
                solution = new ArrayList<>();
                solutionWeight = 0;
                outcome = SolverOutcome.UNSOLVABLE;
                timeSpent = sw.elapsedTime();
            } else {
                // Take the next best vertex from fringe.
                pop = PQ.removeSmallest();
                dequeuedList.add(pop);
                dequeues++;
                solutionWeight = distances.get(pop);
            }

                // Monitoring timeout scenario.
            if (sw.elapsedTime() > timeout) {
                solution = new ArrayList<>();
                solutionWeight = 0;
                outcome = SolverOutcome.TIMEOUT;
                timeSpent = sw.elapsedTime();
            }

            // Iterate through neighbors and record distances, and either
            // add new vertexes to fringe or possibly change a vertex's priority.
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(pop);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                if (dequeuedList.contains(e.to())) { continue; }

                Vertex p = e.from(), q = e.to();
                double w = e.weight();
                double distToP = distances.get(p);
                double distToQ = distToP + w;
                double qPlusH = distToQ + input.estimatedDistanceToGoal(q, end);

                // If the goal vertex is reached, problem is solved.
                if (q.equals(end)) {
                    solutionWeight += e.weight();
                    outcome = SolverOutcome.SOLVED;
                    timeSpent = sw.elapsedTime();

                    // Create the solutions list by iterating through the path
                    // in reverse order using the origins HashMap.
                    solution.add(q);
                    solution.add(p);
                    Vertex path = p;

                    while (!solution.contains(start)) {
                        solution.add(origins.get(path));
                        path = origins.get(path);
                    }
                    Collections.reverse(solution);
                }

                // If explored vertex is new, add to PQ.
                if (!distances.containsKey(q)) {
                    PQ.add(q, qPlusH);
                    distances.put(q, distToQ);
                    origins.put(q, p);
                }

                // Edge relax algorithm.
                else if (distances.get(p) + w < distances.get(q)) {
                    distances.put(q, distToQ);
                    origins.put(q, p);
                    if (PQ.contains(q)) {
                        PQ.changePriority(q, qPlusH);
                    } else {
                        PQ.add(q, qPlusH);
                    }
                }
            }
        }
    }


    /** Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     *  Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     *  UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
     *  You should check to see if you have run out of time every time you dequeue. */
    @Override
    public SolverOutcome outcome()  {
        return outcome;
    }

    /** A list of vertices corresponding to a solution.
     *  Should be empty if result was TIMEOUT or UNSOLVABLE. */
    @Override
    public List<Vertex> solution()  {
        return solution;
    }

    /** The total weight of the given solution, taking into account edge weights.
     *  Should be 0 if result was TIMEOUT or UNSOLVABLE. */
    @Override
    public double solutionWeight()  {
        return solutionWeight;
    }

    /** The total number of priority queue dequeue operations. */
    @Override
    public int numStatesExplored()  {
        return dequeues;
    }
    /** The total time spent in seconds by the constructor. */
    @Override
    public double explorationTime()  {
        return timeSpent;
    }

}
