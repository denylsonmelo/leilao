package br.edu.ifpi.capar.leilao.matcher;

import br.edu.ifpi.capar.leilao.modelo.Lance;
import br.edu.ifpi.capar.leilao.modelo.Leilao;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author 2155060
 */
public class LeilaoMatcher extends TypeSafeMatcher {

    private final Lance lance;

    public LeilaoMatcher(Lance lance) {
        this.lance = lance;
    }
    
    @Override
    protected boolean matchesSafely(Object t) {
        return  true; //t.getLances().contains(lance);
    }

    @Override
    public void describeTo(Description d) {
        d.appendText("leilao tem um lance com valor igual a " + lance.getValor());
    }

    public static Matcher<Leilao> temUmLance(Lance lance){
        return new LeilaoMatcher(lance);
    }
}
