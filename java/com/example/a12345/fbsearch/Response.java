package com.example.a12345.fbsearch;

import java.util.List;

/**
 * Created by a12345 on 4/20/17.
 */

public class Response {
    /**
     * name : ["Usc Mezidon Foot","Uschi Graf","Uschi Strobl","Uschi Appel","Uschi Meyer","Apple Usc Lim","Uschi Held","Kbso Usc","Uschi Melly","Uschi Schoenfelder","Usc Pouyastruc","Pamela Uscanga","Uschi Keskin","Apwu Usc Cba","Miguel Angel Uscanga Valdes","Susy Uscanga Dominguez","Uschi Zeiser-Graf","Uschi Gashi","Uschi Fellner","Uschi Hesse","Hilda Uscata Ccaulla","Uscy Nansy Uscy","Edith Usca","Junior Usca Vilca","Uscanga Fernando"]
     * profile_pic : ["https://scontent.xx.fbcdn.net/v/t1.0-1/12294812_182587018755153_5700358192209376194_n.jpg?oh=d8211ee952b03148f11cb67060fe61b2&oe=594DA417","https://scontent.xx.fbcdn.net/v/t1.0-1/c99.29.362.362/228825_102892506480958_7197378_n.jpg?oh=bdde1c889083ec3a9dde006db51d5235&oe=59841E36","https://scontent.xx.fbcdn.net/v/t1.0-1/c0.81.540.540/17634786_1328741943872391_6930947651122337919_n.jpg?oh=61090d11f4ce1cd1c32e263daa7c695a&oe=594D0B37","https://scontent.xx.fbcdn.net/v/t1.0-1/14141839_1373309506031825_4181152479489025060_n.jpg?oh=0d240468f168a76b160704adf4767483&oe=598B4E79","https://scontent.xx.fbcdn.net/v/t1.0-1/c0.0.297.297/15032796_110889202727761_2265148456188172035_n.jpg?oh=a35b482b8652cc8ab50fa093c338590b&oe=5980D402","https://scontent.xx.fbcdn.net/v/t1.0-1/c0.0.720.720/p720x720/13620228_137653143329879_3776760913840205854_n.jpg?oh=b56a616b63ef42e83974eb3af7418770&oe=59884969","https://scontent.xx.fbcdn.net/v/t1.0-1/11896013_523995194418359_6866888962668970013_n.jpg?oh=242323a243beb6cf274b7d874b02a0d6&oe=59923907","https://scontent.xx.fbcdn.net/v/t1.0-1/c41.41.509.509/545143_349607618435700_1356263905_n.jpg?oh=ea48566f78599ed7ce8f733652dfdb55&oe=5988F23B","https://scontent.xx.fbcdn.net/v/t31.0-1/p720x720/17637045_1345899962170087_3786702588756594912_o.jpg?oh=85bf85880520cb3a603adb892c40e8a9&oe=5990C8FC","https://scontent.xx.fbcdn.net/v/t31.0-1/p720x720/17966741_1332879130137867_6134985151248375120_o.jpg?oh=d474b9a3e750b361d7e617cdd813792c&oe=597DED98","https://scontent.xx.fbcdn.net/v/t1.0-1/15822998_1562345380450094_8679072920605621336_n.jpg?oh=da6deb26bedc7fcabcc1b19c14ff51fb&oe=59817485","https://scontent.xx.fbcdn.net/v/t31.0-1/p720x720/17760860_1767370273578144_5017819692815434165_o.jpg?oh=f8bef29eef419271982e325b01eae102&oe=599B5470","https://scontent.xx.fbcdn.net/v/t31.0-1/p720x720/17492995_772607309561096_1193891623642696504_o.jpg?oh=3b91a8b699966536e2c485e3a834e0ec&oe=599A475E","https://scontent.xx.fbcdn.net/v/t1.0-1/14724479_1779215205680163_2846736679359149777_n.jpg?oh=e9e32826661a4a4a72d0922efd845a04&oe=597DFA43","https://scontent.xx.fbcdn.net/v/t1.0-1/15941391_1901968156699553_4657856302743884575_n.jpg?oh=d33e375dbcc398837028cb3b81fbd695&oe=597B268F","https://scontent.xx.fbcdn.net/v/t1.0-1/16472967_10207990129863290_8894927932569714503_n.jpg?oh=62a1bfc3451faab9c5b89dfbf16d7082&oe=597C96DF","https://scontent.xx.fbcdn.net/v/t31.0-1/c0.0.720.720/p720x720/16992456_1557355254304602_1509128847866055456_o.jpg?oh=b09cb3b17e80034bf394aaeacfc619ca&oe=5986992B","https://scontent.xx.fbcdn.net/v/t1.0-1/c22.0.433.433/394671_384749004917857_1169926287_n.jpg?oh=4a4eb44747fefcbbf4381ec6a51c3e3e&oe=594CA92E","https://scontent.xx.fbcdn.net/v/t1.0-1/10898216_840230782708218_6498489422408436472_n.jpg?oh=ec68fa06bf37199652f95eceb904a88c&oe=594DD863","https://scontent.xx.fbcdn.net/v/t31.0-1/c0.0.720.720/p720x720/919150_10201898291156088_1513177407_o.jpg?oh=1a47ac131bbca8ba074edd0979ebdeaf&oe=59880AEE","https://scontent.xx.fbcdn.net/v/t1.0-1/c0.0.646.646/16708349_1639972162975158_3668043998335710381_n.jpg?oh=907e3ab28ba861cbb357bf6cab28d5d2&oe=594FFE5C","https://scontent.xx.fbcdn.net/v/t1.0-1/c1.0.479.479/18056971_287809258310022_6732364401504764565_n.jpg?oh=878fc21b841007284e61be2fa6c6e469&oe=59957C7E","https://scontent.xx.fbcdn.net/v/t31.0-1/c0.0.720.720/p720x720/17855084_292850314468536_7311932981314038589_o.jpg?oh=62f71a73f38a021bf1c31d775eaafc35&oe=597ABCA1","https://scontent.xx.fbcdn.net/v/t1.0-1/p720x720/17757667_10208979898956135_7770782010903110354_n.jpg?oh=546cc4d24a3e0017f62898301d0e0b35&oe=5989DE0B","https://scontent.xx.fbcdn.net/v/t1.0-1/12310614_132313010466841_8828303559363987096_n.jpg?oh=862937abd49ce335fb660a0339f88d7b&oe=597A6DD1"]
     * id : ["423933594620493","888556261247908","1334301449983107","1592010814161692","209854436164570","290671408028051","802750649876144","1447379161991868","1327398460686904","1302270976532016","1652147674803197","1753067051675133","778783615610132","1864517207149962","1933067623589606","10208252824070481","1574786962561431","1361206020605479","1417996454931645","10211792969636866","1651549335150774","283643135393301","290390191381215","10209026750887404","428212757543530"]
     * next : https%3A%2F%2Fgraph.facebook.com%2Fv2.8%2Fsearch%3Ffields%3Did%2Cname%2Cpicture.width%2528700%2529.height%2528700%2529%26type%3Duser%26q%3DUSC%26access_token%3DEAAGD0zuVe5IBAMYZBpKedV26j4ZBYsvFgITCS9yiBYZATgcI2iXZA5AF5cZCdJzHz07WZBlJHMkuVOfl5fpDRsdIZBzmX2Uk5PcaV1nOZB3e8SUgSXUAp9cy9DfruqiBcgLyBMaY7LWWYuXbzSZC8ZCpP0%26limit%3D25%26offset%3D25%26__after_id%3Denc_AdBxvvrUFcaUKEZAEHlc5GpWGbfZCMaInol9bbbztJOK6nXTexLizZABP3e4JQRvGkLYOiryo1wZADgyxeOAudwuF9cZC
     * previous :
     * uid : 428212757543530
     */

    private String next;
    private String previous;
    private String uid;
    private List<String> name;
    private List<String> profile_pic;
    private List<String> id;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(List<String> profile_pic) {
        this.profile_pic = profile_pic;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }
}

