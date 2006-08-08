
Steps to build and run GUI from CVS.

- checkout the code from cvs and run ant task # ant all

- copy the file "catrip-config.xml" from conf dir to dir : "C:\Documents and Settings"\<local-user>\.caTRIP 
- update the rootDirectory variable pointing to "conf" dir 
ex: <guiConfiguration rootDirectory="C:\caTRIP\cvs\catrip\codebase\projects\gui\conf">

- copy the file "query_engine_services_config.xml" from conf dir to dir : "C:\Documents and Settings"\<local-user>\.caTRIP 
- update the "clientConfigWsdd" variable pointing to file "qe-client-config.wsdd" in gui's "conf" dir 
<entry key="clientConfigWsdd">C:\\caTRIP\\cvs\\catrip\\codebase\\projects\\gui\\conf\\qe-client-config.wsdd</entry>
- update the service URLs for the CAE and caTissueCore Service.

- run the target # ant run

- Click "Show ALl" on the "Search Services" window.
- check the only service and click "Select"

For amy problems: Please mail to : Sanjeev.Agarwal@SemanticBits.com
