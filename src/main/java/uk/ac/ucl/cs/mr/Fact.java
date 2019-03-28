package uk.ac.ucl.cs.mr;

public class Fact {

    public String subject = null;
    public String predicate = null;
    public String object = null;
    public String polarity = null;
    public String modality = null;

    public Fact(String s, String p, String o) {
        this.subject = s;
        this.predicate = p;
        this.object = o;
    }

    public Fact(String s, String p, String o, String pol, String mod) {
        this.subject = s;
        this.predicate = p;
        this.object = o;
        this.polarity = pol;
        this.modality = mod;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getPolarity() {
        return polarity;
    }

    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }
}
