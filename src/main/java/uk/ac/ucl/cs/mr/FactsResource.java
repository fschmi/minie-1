package uk.ac.ucl.cs.mr;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.logging.Logger;

import de.uni_mannheim.minie.MinIE;
import de.uni_mannheim.minie.annotation.AnnotatedPhrase;
import de.uni_mannheim.minie.annotation.AnnotatedProposition;
import de.uni_mannheim.utils.coreNLP.CoreNLPUtils;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Path("/query")
public class FactsResource {

    private final static Logger logger = Logger.getLogger(String.valueOf(Main.class));
    private static final StanfordCoreNLP parser = CoreNLPUtils.StanfordDepNNParser();

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public FactsBean query(String sentence) {
        MinIE minie = new MinIE(sentence, FactsResource.parser, MinIE.Mode.DICTIONARY, DictResource.dictionary);

        List<Fact> facts = new ArrayList<>();

        for (AnnotatedProposition ap: minie.getPropositions()) {

            String sub = ap.getSubject().toString();
            String rel = ap.getRelation().toString();
            String obj = ap.getObject().toString();
            String pol = ap.getPolarity().toString();
            String mod = ap.getModality().toString();

            Fact fact = new Fact(sub, rel, obj, pol, mod);
            facts.add(fact);
        }

        return new FactsBean(facts);
    }
}
