Highbrow tribes system is a distributed collaborative learning platform where students can register and form tribes with other students having similar programming interests. The system will create the tribes based on the students liking towards identical programming languages. The system will also store the student's data in a database where all of that student's records will be stored and used for managing the triber system. Furthermore, once the students get allotted to their respective tribes, the system will allow them to communicate and discuss any assignments or tasks they intend to work on. We desired to create this system to enable students to come together and form communities using our system, giving them a helping hand to complete and manage different tasks allocated to them.





To start the application follow the below order of execution
1) Navigate to each of the following modules on terminal window and execute the java package using "mvn exec:java",
 1.1) Persistence System
 1.2) Interests System
 1.3) Communicator system
2) Post all the above modules are up run the Triber Module using "mvn exec:java"
3) Now students can start joining the system, for which we have 4 dummy student modules which can be executed in any order
4) Each of the student's module will intimate the user to input their name and their GitHub ID.
	For ex.
	Name:- Siddharth
	GitHubID:- siddharthucd
Note: GithubId is your Github username which can be extracted from one's github URL. For example if the URL is https://github.com/xyz and the GitHubId is xyz
5) Post registration the user will either be prompted with a list of potential Tribe details or join a tribe by default.
 5.1) If the user is prompted with a list of tribe details the user has to enter the Tribe ID that they wish to       join.
      For ex. the student can be given the following choices
      
     /*
      Tribe Id: 3, Tribe Name: HTML_Tribe, Language: Shell,HTML, Members: ritika
      Tribe Id: 2, Tribe Name: Shell_Tribe, Language: Shell,HTML, Members: ritika
      Tribe Id: 1, Tribe Name: Java_Tribe, Language: Java,JavaScript, Members: siddharth, mansoor
     */

      The user has to type in the Tribe Id of their choice to join that particular tribe. In the above example if         the student wants to join the Java_Tribe they would have to type 1 which is the Tribe ID of Java_Tribe
6) Once the user has joined their desired tribe they are redirected into the chat forum of the tribe where they can
send and receive messages from all the members of the group.
7) Once the student believes that sufficient tribe members are available, they can choose to get the problem to solve which can be invoked by typing the key word "!problem question" without quotes. The Tribe Bot assigns a question for the tribe and every time a tribe member tries to get a using the above keyword they would be prompted the same question until the current question is solved.
8) Once the tribe has solved the question they can let the Tribe Bot know by the keyword "!problem solved" without quotes. This will intimate all the tribe members that the current problem has been solved and any tribe member can use the question keyword again to assign the tribe a new question.