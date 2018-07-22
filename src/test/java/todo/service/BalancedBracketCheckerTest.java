package todo.service;

import org.junit.Assert;
import org.junit.Test;

public class BalancedBracketCheckerTest {

    @Test
    public void test() {
        checkIndividual('(', ')');
        checkIndividual('[', ']');
        checkIndividual('{', '}');

        Assert.assertTrue(BalancedBracketChecker.isBalanced("([{}])"));
        Assert.assertTrue(BalancedBracketChecker.isBalanced("([]{})"));
    }

    protected void checkIndividual(char openChar, char closeChar) {
        Assert.assertTrue(BalancedBracketChecker.isBalanced("" + openChar + closeChar));
        Assert.assertTrue(BalancedBracketChecker.isBalanced("abc" + openChar + closeChar));
        Assert.assertTrue(BalancedBracketChecker.isBalanced("" + openChar + closeChar + "abc"));
        Assert.assertTrue(BalancedBracketChecker.isBalanced("a" + openChar + "b" + closeChar + "c"));
        Assert.assertTrue(BalancedBracketChecker.isBalanced("" + openChar + " xxxxxxx     " + closeChar + " "));

        Assert.assertFalse(BalancedBracketChecker.isBalanced("" + openChar));
        Assert.assertFalse(BalancedBracketChecker.isBalanced("" + closeChar));
        Assert.assertFalse(BalancedBracketChecker.isBalanced("" + openChar +  openChar + closeChar));
        Assert.assertFalse(BalancedBracketChecker.isBalanced("" + openChar +  closeChar + closeChar));
        Assert.assertFalse(BalancedBracketChecker.isBalanced("" + closeChar +  openChar + closeChar));
    }
}
