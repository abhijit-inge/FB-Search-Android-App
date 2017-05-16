package com.example.a12345.fbsearch;

import java.util.List;

/**
 * Created by a12345 on 4/23/17.
 */

public class AlbumsResponse {


    /**
     * empty : 1
     * posts : ["FINAL: USC 4, UCLA 1.\n\nTop-ranked USC Beach Volleyball beats No. 2 UCLA to finish the regular season undefeated and extend its winning streak to 60 matches!\n\n#FightOn #BeatTheBruins","Welcome to the #TrojanFamily, Charles O'Bannon Jr.! The Las Vegas, NV product is USC's first McDonald's All-American since 2008. #ItTakesATeam","Play ball!\n\nUSC Football head coach Clay Helton threw the ceremonial first pitch at tonight's Los Angeles Dodgers game!\n\n#LetsGoDodgers","USC Trojans shared The Players' Tribune's post.","USC sophomore Chimezie Metu announced that he will RETURN for his junior season! #ItTakesATeam"]
     * pics_url : ["https://scontent.xx.fbcdn.net/v/t1.0-9/s720x720/18034282_1496614910370709_2478753578815586968_n.jpg?oh=c8c853a4e70b77bd4e862b1814146681&oe=597C3F79","https://scontent.xx.fbcdn.net/v/t1.0-9/s720x720/18034282_1496614910370709_2478753578815586968_n.jpg?oh=c8c853a4e70b77bd4e862b1814146681&oe=597C3F79","https://scontent.xx.fbcdn.net/v/t1.0-9/s720x720/18032995_1492813534084180_1201927874398016015_n.jpg?oh=636e17c059a2478903cdc3806805d559&oe=5985C3CC","https://scontent.xx.fbcdn.net/v/t1.0-0/p480x480/17800091_1479565238742343_1201289175410253197_n.jpg?oh=08a0974ff162ada00007fc31772eae0c&oe=5991B496","https://scontent.xx.fbcdn.net/v/t1.0-0/p480x480/17800091_1479565238742343_1201289175410253197_n.jpg?oh=08a0974ff162ada00007fc31772eae0c&oe=5991B496","https://scontent.xx.fbcdn.net/v/t1.0-9/s720x720/17799210_1479565222075678_7578066307759069243_n.jpg?oh=e541500c499801fb7253f837ed80e7bf&oe=59805211","https://scontent.xx.fbcdn.net/v/t1.0-9/s720x720/15826283_1383564168342451_8092976519396408045_n.jpg?oh=46d745ab0d892ac4a2fcc0024a1f62f3&oe=597A4C41","https://scontent.xx.fbcdn.net/v/t1.0-9/s720x720/15826283_1383564168342451_8092976519396408045_n.jpg?oh=46d745ab0d892ac4a2fcc0024a1f62f3&oe=597A4C41","https://scontent.xx.fbcdn.net/v/t1.0-9/s720x720/15621699_1370158329683035_7007037210676132495_n.jpg?oh=154c55124cb886f061f83b19dcce0edf&oe=594C83CC","https://scontent.xx.fbcdn.net/v/t1.0-9/14317609_1266608076704728_8902691418088589527_n.jpg?oh=9b540383ac5b9ce5b3ac043b28be7e1c&oe=59751272","https://scontent.xx.fbcdn.net/v/t1.0-9/14317609_1266608076704728_8902691418088589527_n.jpg?oh=9b540383ac5b9ce5b3ac043b28be7e1c&oe=59751272","https://scontent.xx.fbcdn.net/v/t1.0-9/11703350_1014135831951955_3282389641637245168_n.jpg?oh=03cbb5d1b262f9dd9c8be43947b93cbc&oe=59906FD2","https://scontent.xx.fbcdn.net/v/t31.0-8/q83/s720x720/13723920_1231934766838726_8349870493122541488_o.jpg?oh=e929da19cbe4cd037cb4a981f3c012e8&oe=598FA2AA","https://scontent.xx.fbcdn.net/v/t31.0-8/q83/s720x720/13723920_1231934766838726_8349870493122541488_o.jpg?oh=e929da19cbe4cd037cb4a981f3c012e8&oe=598FA2AA","https://scontent.xx.fbcdn.net/v/t31.0-8/s720x720/13680064_1231934770172059_6323709052222106036_o.jpg?oh=f5898ae672627f3990faa3dd027bab5b&oe=5987DBC1"]
     * no_pics : [2,2,2,2,2]
     * details_posts : 1
     * albums : ["Timeline Photos","Mobile Uploads","Cover Photos","Profile Pictures","#USC2RIO"]
     * profile_pic : https://scontent.xx.fbcdn.net/v/t1.0-1/14317609_1266608076704728_8902691418088589527_n.jpg?oh=9bea7c18c9337f032a7e239ce5a431a4&oe=5994A4F8
     * uname : USC Trojans
     * messagetime : ["2017-04-23T01:08:11+0000","2017-04-20T23:40:12+0000","2017-04-20T02:44:16+0000","2017-04-19T16:24:08+0000","2017-04-18T16:20:51+0000"]
     */
    int count;
    private int empty;
    private int details_posts;
    private String profile_pic=null;
    private String uname=null;
    private List<String> posts=null;
    private List<String> pics_url=null;
    private List<Integer> no_pics=null;
    private List<String> albums=null;
    private List<String> messagetime=null;



    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public int getDetails_posts() {
        return details_posts;
    }

    public void setDetails_posts(int details_posts) {
        this.details_posts = details_posts;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }

    public List<String> getPics_url() {
        return pics_url;
    }

    public void setPics_url(List<String> pics_url) {
        this.pics_url = pics_url;
    }

    public List<Integer> getNo_pics() {
        return no_pics;
    }

    public void setNo_pics(List<Integer> no_pics) {
        this.no_pics = no_pics;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<String> getMessagetime() {
        return messagetime;
    }

    public void setMessagetime(List<String> messagetime) {
        this.messagetime = messagetime;
    }
}