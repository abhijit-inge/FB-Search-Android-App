<?php 
  //$method = $_SERVER['REQUEST_METHOD'];
  // $request = $_SERVER['QUERY_STRING'];
  // $pos = strpos($request,"&");
  // $request = substr($request,$pos);
   //$serverLink = "http://congress.api.sunlightfoundation.com/".$_GET['opr']."?".$request;
   header('Access-Control-Allow-Origin: *');
   header('Content-Type: application/json');
   //$contents = file_get_contents($serverLink);
   //echo $contents;
 
require_once __DIR__ . '/php-graph-sdk-5.0.0/src/Facebook/autoload.php';
$fb = new \Facebook\Facebook([
        'app_id' => '426418237701010',
        'app_secret' => 'a9a0435d68eb9d240e19d18a0fca6ed6',
        'default_access_token'=>"EAAGD0zuVe5IBAMYZBpKedV26j4ZBYsvFgITCS9yiBYZATgcI2iXZA5AF5cZCdJzHz07WZBlJHMkuVOfl5fpDRsdIZBzmX2Uk5PcaV1nOZB3e8SUgSXUAp9cy9DfruqiBcgLyBMaY7LWWYuXbzSZC8ZCpP0",
        'default_graph_version' => 'v2.8',
    ]);

if($_GET['selType']=="details"){
 $array_pics=[];
 $array_posts=[];
 $details_albums=0;
 $details_post="";    
 $post_time=[];
 $profile_pic="";
 $array_albums=[];
 $array_albums_holder=[];
 $no_pics=[];
$posts=[];
 
$name="";
$size=0;    
$id= $_GET['identity'];
$typo=$_GET['typo'];    
//echo($id);
//$id=" 124984464200434";
if($typo=="event"){    
$details_url="$id?fields=id,name,picture.width(700).height(700),posts.limit(5)"; }   
else{
$details_url="$id?fields=id,name,picture.width(700).height(700),albums.limit(5){name,photos.limit(2){name, picture}},posts.limit(5)";}
//echo $details_url;
$details_json=$fb->get($details_url);
//echo($details_url);
$details_final=$details_json->getDecodedBody();

{
if(isset($details_final["picture"]["data"]["url"]))
{    
$profile_pic=$details_final["picture"]["data"]["url"];
}
$parray=array("profile_pic"=>$profile_pic);
if(isset($details_final["name"])){    
$name=$details_final["name"];
}
    
$narray=array("uname"=>$name);    
$details_albums=1;
if(isset($details_final["albums"]["data"])){     
$size= sizeof($details_final["albums"]["data"]);
}
for($x=0;$x<$size;$x++)
{
    $album_name="";
    $pics=[];

    
if(isset($details_final["albums"]["data"][$x]["name"])){    
    
 $album_name=$details_final["albums"]["data"][$x]["name"];
}
 //$ide="inge".$x;
 if(!isset($details_final["albums"]["data"][$x]["photos"]["data"])){
 
 }else{

  $noPics=sizeof($details_final["albums"]["data"][$x]["photos"]["data"]);
  $no_pics_this=array("no_pics"=>$noPics);
  $no_pics=array_merge_recursive($no_pics_this,$no_pics);
 $pics=[];
     for ($j=0; $j<$noPics ; $j++) {
   $picture1=$details_final["albums"]["data"][$x]["photos"]["data"][$j]["picture"];
   $photoid=$details_final["albums"]["data"][$x]["photos"]["data"][$j]["id"];
   $hurl="$photoid/picture?redirect=false";
  
   $hidef=$fb->get($hurl);
 // echo $malkan;
   $hidefurljson=$hidef->getDecodedBody();
 

   $hidefurl=$hidefurljson["data"]["url"];

  
array_push($pics,$hidefurl);     
$array_holder=["pics_url"=>$pics];
$array_pics=array_merge_recursive($array_pics,$array_holder);
 }
 

$array_albums=array("albums"=>$album_name);
$array_albums_holder=array_merge_recursive($array_albums_holder,$array_albums);
}



}

//$array_albums=array("albums"=>$album_name);
//$array_albums_holder=array_merge_recursive($array_albums_holder,$array_albums);
$count=0;
$messages_number=0;
$array_posts=[];
$posts=[];    
$messagetime=[];    
if(isset($details_final["posts"]["data"])){


$messages_number=sizeof($details_final["posts"]["data"]);
}
for($j=0;$j<$messages_number;$j++){
  if(isset($details_final["posts"]["data"][$j]["message"])||isset($details_final["posts"]["data"][$j]["story"]))
  {
$count=$count+1;
  }
}
if($count==0){
$details_posts=0;
//$posts=[];    

}else{
$details_posts=1;
//$posts=[];    
$messagetime=[];
for($x=0;$x<$messages_number;$x++)
{
  if(!isset($details_final["posts"]["data"][$x]["message"])){
      if(!isset($details_final["posts"]["data"][$x]["story"])){
    $message="";
    array_push($posts,$message);}}
     if(isset($details_final["posts"]["data"][$x]["created_time"])) {
       array_push($messagetime,$details_final["posts"]["data"][$x]["created_time"]);  
      }
    if(!isset($details_final["posts"]["data"][$x]["message"])){
      if(isset($details_final["posts"]["data"][$x]["story"])){
    $message=$details_final["posts"]["data"][$x]["story"];
          array_push($posts,$message);
}
    
  }
  else{
      $message=$details_final["posts"]["data"][$x]["message"];
       
 array_push($posts,$message); 
     

  }
 // echo "<div style='position:relative;'>"; 
  
} 
//$array_posts=array("posts"=>$posts);
$array_time=array("time"=>$messagetime);          
    
}
$array_posts=array("posts"=>$posts);

$details_posts=array("details_posts"=>$details_posts);
$details_albums=array("details_albums"=>$details_albums);
$messagetime=array("messagetime"=>$messagetime);
if(sizeof($no_pics)==0){
    $no_pics=array('no_pics'=>[]);
}
if(sizeof($array_albums_holder)==0){
    $array_albums_holder=array('albums'=>[]);
}    
$details_timepass=array("empty"=>1);
$zynga=$no_pics['no_pics'];
if(gettype($zynga)!="array"){
    $no_pics=array('no_pics'=>[$zynga]);
    
}
$zynga2=$array_albums_holder['albums'];

if(gettype($zynga2)!="array"){
    $array_albums_holder=array('albums'=>[$zynga2]);
    
}
$zynga3=$array_posts['posts'];
if(gettype($zynga3)!="array"){
    $array_posts=array('details_posts'=>[$zynga3]);
    
}
    $final=array_merge_recursive($details_timepass,$array_posts,$array_pics,$no_pics,$details_posts,$array_albums_holder,$parray,$narray,$messagetime);
  echo json_encode($final);


}
}











else{

//
if(isset($_GET["symbol"])){

$text=$_GET["symbol"];
}
$Location="";
$distance="";
$selType=$_GET["selType"];
$array_a=[];
$access_token="EAACEdEose0cBABYfKKqz9dkKR6S5qnI1R9drNVPiQJo1ZB1Ih762GZBGV5E0AI1uXWORNOWZA87Lw1qHrUyVPafSLd0R4KZAu4Y0oF9MTPE2cFZCbZAoBydpmi0ZAgfH8RiZAS2KTxofFaroskibr5WNw9fkg8rShIc8ksn4k5pDtX1lvzn7oV2oEwfvEZCF4ttMZD";
if($_GET["selType"]=="paginate"){
    $url=$_GET["url"];
    $json=file_get_contents($url);
    $seema=json_decode($json,true);
   // echo("maga");
   // echo($url);
    $next="";
    $previous="";
}
else{
if($_GET["selType"]=="Users")
{
	$url="search?q=$text&type=user&fields=id,name,picture.width(700).height(700)&limit=10";
}
else if($_GET["selType"]=="Events"){
	$url="search?q=$text&type=event&fields=id,name,picture.width(700).height(700),place&limit=10";
}
else if($_GET["selType"]=="Pages"){
	$url="search?q=$text&type=page&fields=id,name,picture.width(700).height(700)&limit=10";
}
else if($_GET["selType"]=="Groups"){
      $url="search?q=$text&type=group&fields=id,name,picture.width(700).height(700)&limit=10";

}
else if($_GET["selType"]=="Places")
{ 
  $distance=$_GET["accuracy"];
  if($Location!=""&&$distance!=""){
  
  $lat= $_GET["lat"]; 
  $lng=$_GET["long"];
  
  $url="search?q=$text&type=place&center=$lat,$lng&distance=$distance&fields=
id,name,picture.width(700).height(700)&limit-10";}
else{$url="search?q=$text&type=place&fields=id,name,picture.width(700).height(700)&limit=10";}
}

$inge=$fb->get($url);
$next="";
$previous="";
$uid=[];
$seema=$inge->getDecodedBody();
}
foreach($seema as $key=> $value){
if($key=="paging"){
    if(isset($value["next"])){
        $next=$value["next"];
    }
    if(isset($value["previous"])){
        $previous=$value["previous"];
    }
}    
if($key=="data"){	
foreach ($value as $key1 => $value1) {
$name= $value1['name'];
$id=$value1['id'];
$uid=array("uid"=>$id);    
foreach($value1 as $key2=>$value2){
if($_GET["selType"]=="Events")
{
   if($key2=='place'){
  foreach($value2 as $key4 => $value4)
  { if($key4=='name'){
    $place=$value4;
  }
  }
}
} 
if($key2=='picture'){
foreach ($value2 as $key3 => $value3) {
  $url_profile= $value3["url"];
    $array_b=['name'=>$name,'profile_pic'=>$url_profile,'id'=>$id];
    $array_a=array_merge_recursive($array_a,$array_b);
}
}
}
    
}  
    
}
   
}
$array_c=['next'=>urlencode($next),'previous'=>urlencode($previous)];
if(array_key_exists('name',$array_a)){    
if(gettype($array_a['name'])!="array"){
    $ha=$array_a['name'];
    $array_a['name']=[$ha];
}    
}
    if(array_key_exists('profile_pic',$array_a)){    

if(gettype($array_a['profile_pic'])!="array"){
    $ha=$array_a['profile_pic'];
    $array_a['profile_pic']=[$ha];
}   
    
    }
    if(array_key_exists('id',$array_a)){    

if(gettype($array_a['id'])!="array"){
    $ha=$array_a['id'];
    $array_a['id']=[$ha];
}    
    }
$array_a=array_merge_recursive($array_a,$array_c,$uid);


echo json_encode($array_a);
}
/*$option=2;
if ( $option == 1 ) {
    $data = [ 'jjj', 'b', 'c' ];
    // will encode to JSON array: ["a","b","c"]
    // accessed as example in JavaScript like: result[1] (returns "b")
} else {
    $data = [ 'name' => 'God', 'age' => '-1' ];
    // will encode to JSON object: {"name":"God","age":-1}  
    // accessed as example in JavaScript like: result.name or result['name'] (returns "God")
}
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');

echo json_encode($data);*/
?>