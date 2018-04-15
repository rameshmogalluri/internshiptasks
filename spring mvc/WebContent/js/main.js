 document.getElementById("edit").addEventListener("click",main);
  document.getElementById("close").addEventListener("click",close);
  document.getElementById("updatebtn").addEventListener("click",update);
    function main(){
    	var a=document.getElementsByClassName("sessiondata");
    	for(i=0;i<a.length;i++)
          a[i].style.display='none';  
    	var b=document.getElementsByClassName("data")
    	for(i=0;i<b.length;i++)
            b[i].style.display='block';
    	
    }
    function close()
      {
    	var a=document.getElementsByClassName("sessiondata");
    	for(i=0;i<a.length;i++)
          a[i].style.display='block';  
    	var b=document.getElementsByClassName("data")
    	for(i=0;i<b.length;i++)
            b[i].style.display='none'; 
      }
    function update()
      {
    	try{
    	var name=document.getElementById("name").value;
    	var email=document.getElementById("email").value;
    	var phonenumber=document.getElementById("mobileNumber").value;
    	var address=document.getElementById("address").value;
    	var method="POST";
    	var url="/update?name="+name+"&email="+email+"&mobilenumber="+phonenumber+"&address="+address+"";
    	 var xhr=new XMLHttpRequest();	
    	 xhr.open(method,url,true);
    	 xhr.onload = function () {
    		    if (xhr.readyState == 4 && xhr.status == 200) {
    		      
    		    	var data=xhr.response;
    		    	var jsonResponse = JSON.parse(data);
    		    	if(jsonResponse.status === "updated")
    		    		{
    		            document.querySelector("#sessionname").innerHTML=jsonResponse.name;
    		            document.querySelector("#name").value=jsonResponse.name;
    		            document.querySelector("#welcomename").innerHTML=jsonResponse.name;
    		            document.querySelector("#sessionemail").innerHTML=jsonResponse.email;
    		            document.querySelector("#email").value=jsonResponse.email;
    		            document.querySelector("#sessionphoneno").innerHTML=jsonResponse.phoneno;
    		            document.querySelector("#mobileNumber").value=jsonResponse.phoneno;
    		            document.querySelector("#sessionadress").innerHTML=jsonResponse.address;
    		            document.querySelector("#address").value=jsonResponse.address;
    		            document.querySelector("#updated").style.display="block";
    		            document.querySelector("#nochanges").style.display="none";
    		            document.querySelector("#close").click();
    		            setTimeout(function(){ document.querySelector("#updated").style.display="none" }, 4000);
    		            
    		    		}
    		    	else
    		    		{
    		    		document.querySelector("#nochanges").style.display="block";
    		    		document.querySelector("#updated").style.display="none";
    		    		document.querySelector("#close").click();
    		    		setTimeout(function(){ document.querySelector("#nochanges").style.display="none" }, 4000);
    		    		}
    		    }
    		  }
    		  
    		  xhr.send();   
    	}
    	catch(error)
    	   {
    		console.log(error); 
    	   }
      }