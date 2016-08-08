# README #



### What is this repository for? ###

This contains all the hwu code for phis (PhenoImageShare 2013-2016).  This includes the following:

1. IQS - wrapper for the EBI query interface
2. ISS - wrapper for the EBI submission interface


#### What is the PSDB ####

The Spatial Database was supposed to house all the spatial information that could not be stored by the EBI.  However, the slow progress of the project meant this never really happened.  Instead it now functions as a cache for the annotation tool.

In its role as a cache it stores an aggregation of the information in the EBI (for images and their rois) and the presentation info required by fabric.js.

The code used to fill the cache is available [here](https://www.dropbox.com/s/8iu1fqlazzl1ynd/2016-07-11-EBIDownload.zip?dl=0).
The schema can be found [here](https://www.dropbox.com/s/c79al6nxug4x5u1/2016-07-11-psdb_schema_v1.sql?dl=0).


### Which branch do I use? ###

1. master


### How do I get set up? ###

Start by downloading / cloning the repository.


#### IQS + ISS ####

2. Either edit the pom.xml files in the iqs & iss subdirectories or ask mcleod.kc@gmail.com to do it.  You need to edit or add a profile that contains:
    1. The <host> tag specifies the location of the IQS you wish to use.
    2. The <ebi> tag allows you to chose between *beta* OR *www* (live) EBI endpoints.

3. The ISS security token is stored in *host.properties* so that file is no longer included in this repository.  Please ask mcleod.kc@gmail.com for instructions here.

4. Enter the top level directory and run maven:

        mvn package -P profile_name

    Where *profile_name* is the name of the profile, e.g., *beta*.

    The code is then compiled into the following subdirectories: *iss > target > ISS.war* AND *iqs > target > IQS.war*

5. If you have the IQS and ISS already installed, delete the WAR and exploded directories from the *tomcat > webapps* folder. 

6. Copy the war files into the *tomcat / webapps* folder.

7. You should now have both the IQS and ISS running.



### Who do I talk to? ###

mcleod.kc@gmail.com