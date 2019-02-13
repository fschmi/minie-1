package uk.ac.ucl.cs.mr;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class MinIEService extends ResourceConfig {

    public MinIEService() {
        super(FactsResource.class, DictResource.class, JacksonFeature.class);
    }

}
