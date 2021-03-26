package squares;

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Implementation of a sequence of square.
 */
public class SquaresSequence implements Iterable<BigInteger> {
  private BigInteger start;
  private BigInteger end;
  private BigInteger increment;

  /**
   * General constructor.
   * 
   * @param start     the starting value for the sequence
   * @param end       the ending value for the sequence
   * @param increment the increment for the sequence
   */
  public SquaresSequence(BigInteger start, BigInteger end, BigInteger increment) {
    this.start = start;
    this.end = end;
    this.increment = increment;
  }

  @Override
  public Iterator<BigInteger> iterator() {
    return new SequenceIterator(start, end, increment);
  }

  /**
   * Implementation of the iterator for this sequence.
   */
  private class SequenceIterator implements Iterator<BigInteger> {
    private BigInteger counter;
    private BigInteger end;
    private BigInteger increment;

    /**
     * Constructor.
     * 
     * @param start     the starting point for this sequence
     * @param end       the ending point for this sequence
     * @param increment the increment for this sequence
     */
    private SequenceIterator(BigInteger start, BigInteger end, BigInteger increment) {
      counter = start;
      this.end = end;
      this.increment = increment;
    }

    @Override
    public BigInteger next() {
      BigInteger result = counter.multiply(counter);
      counter = counter.add(increment);
      return result;
    }

    @Override
    public boolean hasNext() {
      return counter.compareTo(end) <= 0;
    }
  }
}
