package uk.ac.ucl.cs.mr;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import de.uni_mannheim.minie.MinIE;
import de.uni_mannheim.minie.annotation.AnnotatedPhrase;
import de.uni_mannheim.minie.annotation.AnnotatedProposition;
import de.uni_mannheim.utils.coreNLP.CoreNLPUtils;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Path("/query/{mode}")
public class FactsResource {

    private final static Logger logger = Logger.getLogger(String.valueOf(Main.class));
    private static final StanfordCoreNLP parser = CoreNLPUtils.StanfordDepNNParser();

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response query(String sentence, @PathParam("mode") String mode) {
        MinIE minie = null;
        if (mode.equals("complete")) {
            minie = new MinIE(sentence, FactsResource.parser, MinIE.Mode.COMPLETE);
        } else if (mode.equals("safe")) {
            minie = new MinIE(sentence, FactsResource.parser, MinIE.Mode.SAFE);
        } else if (mode.equals("dictionary")) {
            minie = new MinIE(sentence, FactsResource.parser, MinIE.Mode.DICTIONARY, DictResource.dictionary);
        } else if (mode.equals("aggressive")) {
            minie = new MinIE(sentence, FactsResource.parser, MinIE.Mode.AGGRESSIVE);
        } else {
            return Response.status(400).entity(new String[]{"Mode not available!"}).build();
        }

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

        return Response.ok(facts).build();
    }
}
