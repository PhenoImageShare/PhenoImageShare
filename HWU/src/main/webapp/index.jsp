<html>
<body>
<h2>IQS API summary</h2>
<div>
Base URL is http://lxbisel.macs.hw.ac.uk:8080/IQS/
</div>
<div>
<h3>/getimages</h3>
<h4>parameters</h4>
All parameters are optional
<ol>
<li>phenotype - phenotype ID or term, e.g. MP:0010254 or cataracts</li>
<li>anatomy - anatomy ID or term, e.g. MA:0000261 or eye</li>
<li>gene - gene ID or symbol, e.g. MGI:1891295 or Spns2</li>
<li>num - number of results to be returned, default is 100</li>
<li>start - first result to be returned, default is 0</li>
</ol>

<!--
Examples:
<ol>
<li>http://lxbisel.macs.hw.ac.uk:8080/IQS/getimages?phenotype=MP:0010254</li>
<li>http://lxbisel.macs.hw.ac.uk:8080/IQS/getimages?phenotype=MP:0010254&start=1</li>
<li>http://lxbisel.macs.hw.ac.uk:8080/IQS/getimages?phenotype=MP:0010254&num=1</li>
<li>http://lxbisel.macs.hw.ac.uk:8080/IQS/getimages?phenotype=cataracts</li>
<li>http://lxbisel.macs.hw.ac.uk:8080/IQS/getimages?anatomy=eye</li>
<li>http://lxbisel.macs.hw.ac.uk:8080/IQS/getimages?gene=Spns2</li>
</ol>
-->
</div>

<div>
<h3>/getroi</h3>
<h4>parameters</h4>
Parameter is mandatory
<ol>
<li>id - ROI ID, e.g. komp2_roi_112003_0</li>
</ol>

<!--
Example:
<ol>
<li>http://lxbisel.macs.hw.ac.uk:8080/IQS/getroi?id=komp2_roi_112003_0</li>
</ol>
-->
</div>

<div>
<h3>/getchannel</h3>
<h4>parameters</h4>
Parameter is mandatory
<ol>
<li>id - Channel ID, e.g., komp2_channel_112003_0</li>
</ol>
<!--
Example:
<ol>
<li>http://lxbisel.macs.hw.ac.uk:8080/IQS/getchannel?id=komp2_channel_112003_0</li>
</ol>
-->
</div>

</div>
</body>
</html>
