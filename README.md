# gamesys

### Introduction
This is a cucumber project that gets a user's video feed and runs some scenarios on the response. It outputs the responses in the `/output/[resources]/[query_parameter]-[value][timestamp].[contentType]` folder
- It can be run via:
  1. IDE (ex. Eclipse), right click feature file and Run as/Debug as feature
  2. Maven build tool, call `mvn test` inside the project root
  
### Tests
- Scenario 1: 
  - ` Send the Request and Verify the Response code is 200 
     `Get the Response body and extract the following data from the XML feed and display in user friendly format:
     `The <AUTHOR> posted <TITLE> on <PUBLISHED>` for each listing
- Scenario 2:
    - Edit the URI and make the Request fail to achieve:
      `A 400 status code`
- Scenario 3:
    - Edit the URI and make the Request fail to achieve:
      `A 404 status code`
- Scenario 4: 
    - `Verify at least 1 of the authors is called “The Batman”` (this should fail but only display the failure once at the end of the run). 
    
- Scenario 5: 
    - `Verify at least 10 of the authors is called “Jackpotjoy”` (this *should* pass as long as no one deletes 6 videos out of the 15)
    
### Disclaimer
This is basically a day's work.
Some things could have been done much nicer. 
1. Parsing the response as `XML` proved to be difficult, neither `XPathFactory` nor `Document.getElementsByTagName` worked. I have a feeling that it has something to do with the various namespaces associated with the document. Due to lack of time, I ended up parsing the text and modeling just the `<entry>` part that I needed for the tests. Even so, the model could have been cleaner and made use of more abstractions (instead of the dreadfully specific `buildEntries()` in `UserVideos`)
2. **Dependency Injection**. There is only one step definition file and no **DI** library (I had looked into Picocontainer, but it was not behaving properly, that's why there is a `World` class). Ideally, I would split those step definitions based on, well, behaviour :) 
3. Scenarios. `Scenario outline`s could be used for the definition of simpler tests (for example, the code validation could have been a scenario outline with 3 lines for the `Examples`)
4. Prints. The `System.out.println` are there because of the requirements. They would be useless in normal circumstances and if needed, they could be  file outputs archived by Jenkins' post-build steps.
5. Output result. If running in parallel, duplicate naming is quite possible, so a timestamp is not enough. The time-saving thing I did was to keep adding `x`s at the end of the file name, but a better approach would be using a sort of counter that is `synchronized` for the file writer class. Another gimmick would be adding the name of the method calling the file write (easier to understand the archived files for Jenkins builds), which could be done with the `Stack` frame names.
6. There are some placeholder `interfaces/classes` which I didn't get to write, since they required more time to research and design. 
7. I think that maybe `testng` would be stronger for organizing the cucumber tests, but `junit` was faster to write with the archetype generated out of the box.
