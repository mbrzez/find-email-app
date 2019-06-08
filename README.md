# Find e-mail app
## Description
Find-email-app finds, collects and extracts e-mail addresses from text and binary files (`.doc`, `.docx`, `.pdf`). An e-mail list is stored in output file. In order to speed up the execution time application uses multiple, parallel threads.

If you need to collect e-mail addresses from the any webpage, you can download the content by any website copier and then run find e-mail application.

## Build
Use below maven goal to compile and generate `find-email-app.jar`
```
mvn clean package
```
## Usage
Input and output directories must be provided to run the application.
```
java -jar find-email-app.jar -i <input> -o <output>
```
E-mail list will be generated in the output directory