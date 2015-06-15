package gt.edu.kinal.jmonterroso.learngeek_grupo3.beans;

/**
 * Created by JMONTERROSO on 14/06/2015.
 */
public class Lesson {

    private String name;
    private String description;
    private String referencesUrl;
    private String urlVideo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferencesUrl() {
        return referencesUrl;
    }

    public void setReferencesUrl(String referencesUrl) {
        this.referencesUrl = referencesUrl;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }
}