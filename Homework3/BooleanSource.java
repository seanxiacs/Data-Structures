package Homework3;

/**
 * BooleanSource serves as a class that returns true with the probability that is entered when making a BooleanSource object.
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class BooleanSource {
    private double probability;

    /**
     * Default constructor of the BooleanSource class.
     */
    public BooleanSource() {

    }

    /**
     * Overloaded constructor that sets the probability that requestArrived will return a true value.
     * @param newProbability
     * @throws InvalidProbabilityException
     */
    public BooleanSource(double newProbability) throws InvalidProbabilityException {
        if(newProbability < 0 || newProbability > 1) {
            throw new InvalidProbabilityException("This probability does not lie in the range of 0 and 1 inclusive.\n");
        }
        this.probability = newProbability;
    }

    /**
     * This method sets the probability.
     * @param newProbability
     * @throws InvalidProbabilityException
     */
    public void setProbability(double newProbability) throws InvalidProbabilityException {
        if(newProbability < 0 || newProbability > 1) {
            throw new InvalidProbabilityException("This probability does not lie in the range of 0 and 1 inclusive.\n");
        }
        this.probability = newProbability;
    }

    /**
     * This method gets the probability.
     * @return probability
     */
    public double getProbability() {
        return probability;
    }

    /**
     * This method returns true or false based on the current probability value.
     * @return (Math.random() < probability)
     */
    public boolean requestArrived() {
        return (Math.random() < probability); //Can remove the this.
    }
}
