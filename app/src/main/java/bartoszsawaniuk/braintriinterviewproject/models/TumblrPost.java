package bartoszsawaniuk.braintriinterviewproject.models;

import java.io.Serializable;

/**
 * Created by bartoszsawaniuk on 05/03/16.
 */
public class TumblrPost implements Serializable{

    String id;//":"81822840008",
    String url;//":"http:\/\/drift-swag.tumblr.com\/post\/81822840008",
    String urlWithSlug;//":"http:\/\/drift-swag.tumblr.com\/post\/81822840008\/formula-drift-long-beach",
    String type;//":"link",
    String format;//":"html",
    String slug;//":"formula-drift-long-beach",
    String linkText;//":"Formula DRIFT Long Beach",
    String linkURL;//":"http:\/\/www.dailymotion.com\/hub\/formula-drift-live?utm_source=meta_header&utm_medium=meta_header&utm_campaign=Formula%20Drift#video=x1dusgf",
    String linkDescription;//":"<p>Watch LIVE now!<\/p>",
    String photoLinkURL;//":"https:\/\/www.flickr.com\/photos\/brandondytangphotography\/13252190433\/",
    String photoURL1280;//":"http:\/\/41.media.tumblr.com\/3eb2fb6ba7bccf283ebcbac1022e43bc\/tumblr_n2nrmj47fj1s945pko1_1280.jpg",
    String photoURL500;//":"http:\/\/40.media.tumblr.com\/3eb2fb6ba7bccf283ebcbac1022e43bc\/tumblr_n2nrmj47fj1s945pko1_500.jpg",
    String photoURL400;//":"http:\/\/36.media.tumblr.com\/3eb2fb6ba7bccf283ebcbac1022e43bc\/tumblr_n2nrmj47fj1s945pko1_400.jpg",
    String photoURL250;
    String photoURL100;
    String photoURL75;

    public TumblrPost(String linkText, String url, String urlWithSlug, String type, String format,
                      String slug, String linkURL, String linkDescription, String id, String photoLinkURL,
                      String photoURL1280, String photoURL500, String photoURL400,String photoURL250,
                      String photoURL100, String photoURL75) {
        this.linkText = linkText;
        this.url = url;
        this.urlWithSlug = urlWithSlug;
        this.type = type;
        this.format = format;
        this.slug = slug;
        this.linkURL = linkURL;
        this.linkDescription = linkDescription;
        this.photoLinkURL = photoLinkURL;
        this.photoURL1280 = photoURL1280;
        this.photoURL500 = photoURL500;
        this.photoURL400 = photoURL400;
        this.photoURL100 = photoURL100;
        this.photoURL75 = photoURL75;
        this.photoURL250 = photoURL250;
        this.id = id;
    }

    public String getPhotoURL250() {
        return photoURL250;
    }

    public String getPhotoURL100() {
        return photoURL100;
    }

    public String getPhotoURL75() {
        return photoURL75;
    }

    public String getPhotoLinkURL() {
        return photoLinkURL;
    }

    public String getPhotoURL1280() {
        return photoURL1280;
    }

    public String getPhotoURL500() {
        return photoURL500;
    }

    public String getPhotoURL400() {
        return photoURL400;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlWithSlug() {
        return urlWithSlug;
    }

    public String getType() {
        return type;
    }

    public String getFormat() {
        return format;
    }

    public String getSlug() {
        return slug;
    }

    public String getLinkText() {
        return linkText;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public String getLinkDescription() {
        return linkDescription;
    }
}
