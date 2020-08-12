Peyton Brakefield
MS3 Jr. Coding Challenge Submission

SUMMARY
-----------------------------------------
For this project, I was tasked with creating a program that could open a CSV file, parse the data, and find records that were valid and insert those records into an SQLite
database. I was able to complete this task with much help from the website https://www.sqlitetutorial.net/ I was completely new to the SQLite database system and also 
fairly new to Java as well. I took this project as a challenge that would help prepare me for potential work I may have at MS3 during my bootcamp or even on site with an
employer I'm consulting for. 

STEPS TO RUN
----------------------------------------
For this project, you'll need to add the provided .jar files in the lib folder to your classpath. Once that is complete, you'll need to make sure the working directory is 
correct and contains the ms3Interview - Jr Challenge 2.csv file. After you've checked that, you should be able to run the program and the correct files should show up in the
working directory. The database file will show up in your C: drive under the sqlite folder and then inside the db folder.

OVERVIEW
---------------------------------------
As stated earlier, I was fairly new to Java and completely new to SQLite. I took to learning via online resources and chose to tackle the project in a way that made sense
to me logically. Within the program, you'll find that I did most of the work in the main method, as it felt like any other extraneous methods might make the code look
more bloated than it needed to. However, the insert method was one where I felt like there was no workaround and was needed to be entered as a method instead of a part of the
main method. Given the fact that I was free to use any tools or open source libraries I could, I chose to use the OpenCSV library in order to robustly parse and write CSV
files. This made my code look much cleaner and simpler, and also helped me wrap my mind around what exactly I was writing when I was beginning to get a bit confused with
the code I had written after trying to look back over it. I added comments where I felt like a new portion of the project was being tackled and addressed what was happening
within the code at that time.