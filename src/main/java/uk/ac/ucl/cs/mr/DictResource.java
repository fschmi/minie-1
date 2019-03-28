package uk.ac.ucl.cs.mr;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import de.uni_mannheim.minie.MinIE;
import de.uni_mannheim.minie.annotation.AnnotatedPhrase;
import de.uni_mannheim.minie.annotation.AnnotatedProposition;
import de.uni_mannheim.utils.coreNLP.CoreNLPUtils;
import de.uni_mannheim.utils.Dictionary;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Path("/dictionary")
public class DictResource {

    private final static Logger logger = Logger.getLogger(String.valueOf(Main.class));
    protected static Dictionary dictionary = new Dictionary();

    @POST
    public Response addWord(String word) {
        dictionary.addWord(word);
        return Response.ok("Success: " + word).build();
    }

    @DELETE
    public Response resetDictionary() {
        dictionary = new Dictionary();
        return Response.ok("Success").build();
    }
}
