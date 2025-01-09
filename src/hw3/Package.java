//Halice Sachin Falcao
//SBU ID-115437214
//R-01

package hw3;

/**
 * Represents a mail package that is being delivered and picked up from the
 * mailroom.
 */
public class Package {
    private String recipient;
    private int arrivalDate;
    private double weight;

    /**
     * Constructs a Package object with specified recipient, arrival date, and
     * weight.
     * 
     * @param recipient   The recipient of the package.
     * @param arrivalDate The arrival date of the package (starting from day 0).
     * @param weight      The weight of the package.
     */
    public Package(String recipient, int arrivalDate, double weight) {
        this.recipient = recipient;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
    }

    /**
     * Retrieves the recipient of the package.
     * 
     * @return The recipient of the package.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets the recipient of the package.
     * 
     * @param recipient The recipient of the package.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Retrieves the arrival date of the package.
     * 
     * @return The arrival date of the package.
     */
    public int getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the arrival date of the package.
     * 
     * @param arrivalDate The arrival date of the package.
     */
    public void setArrivalDate(int arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Retrieves the weight of the package.
     * 
     * @return The weight of the package.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the package.
     * 
     * @param weight The weight of the package.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
