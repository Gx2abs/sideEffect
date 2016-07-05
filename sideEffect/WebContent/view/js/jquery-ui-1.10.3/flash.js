function makeflash(Url,Width,Height,mainNum,subNum)
{                 
  document.writeln("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0\" width=\"" + Width + "\" height=\"" + Height + "\">"); 
  document.writeln("<param name=\"movie\" value=\"" + Url + "\">"); 
  document.writeln("<param name=\"quality\" value=\"high\" />");     
  document.writeln("<param name=\"wmode\" value=\"transparent\">"); 
  document.write("<param name='menu' value='false'>");
  document.write("<param name=\"FlashVars\" value=\"m="+mainNum+"&s="+subNum+"\">");	
  document.writeln("<embed autostart=\"false\" src=\"" + Url + "\" wmode=\"transparent\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\"" + Width + "\"  height=\"" + Height + "\">"); 
  document.writeln("</object>");     
}
function makeflash_no(Url,Width,Height)
{                 
	alert(Url)
  document.writeln("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0\" width=\"" + Width + "\" height=\"" + Height + "\"  id=\"main_2\">"); 
  document.writeln("<param name=\"movie\" value=\"" + Url + "\">"); 
  document.writeln("<param name=\"quality\" value=\"high\" />");     
  document.writeln("<param name=\"wmode\" value=\"transparent\">"); 
  document.write("<param name='menu' value='false'>");
  document.writeln("<embed autostart=\"false\" src=\"" + Url + "\" wmode=\"transparent\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\"" + Width + "\"  height=\"" + Height + "\">"); 
  document.writeln("</object>");     
}

function flashPlayer(name, sizex, sizey,  swfname) {
 alphavar='Y';

 document.write('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="' + sizex + '" height="' + sizey + '" id="'+swfname+'">');
 document.write('<param name="movie" value="' + name + '">');
 document.write('<param name="quality" value="high">');
 document.write('<param name="menu" value="false">');
 document.write('<param name="allowScriptAccess" value="always">');
 if( alphavar == 'Y'){ 	
     document.write('<param name="wmode" value="transparent">'); 
 }
 document.write('<embed src="' + name + '" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="' + sizex + '" height="' + sizey + '" id="'+swfname+'"');
 document.write('></embed>');
 document.write('</object>');
}

function setPng24(obj) { 
　 　 obj.width=obj.height=1; 
　 　 obj.className=obj.className.replace(/\bpng24\b/i,''); 
　 　 obj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ obj.src +"',sizingMethod='image');" 
　 　 obj.src='';　 
　 　 return ''; 
} 
