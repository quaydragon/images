import static org.junit.Assert.assertEquals;

import org.junit.Test;
import squares.SquaresSequence;

import java.math.BigInteger;

/** Test class for the sequence of squares. */
public class SquaresSequenceTest {

  /** Test of the square sequence. */
  @Test
  public void testSquaresSequence() {
    SquaresSequence seq = new SquaresSequence(new BigInteger("" + 0), new BigInteger("1000000000"),
        new BigInteger("" + 100));

    BigInteger counter = new BigInteger("" + 0);

    for (BigInteger result : seq) {
      assertEquals(counter.multiply(counter), result);
      counter = counter.add(new BigInteger("" + 100));
    }
  }
}