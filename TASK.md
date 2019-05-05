# Layer Backend Engineer Test

## Story

To be able to make spreadsheet sharing easier, we decided to build a tool that allows file owners to specify cells, ranges and tabs that can be shared with **recipients**. Conveniently, spreadsheet users are already familiar with a **selection** notation that is used between all the spreadsheet software. Now we need your help to build an application that this front end can make requests to.

## Task

In your favorite language, write a backend **API** application that receives, stores and list **sharings**. Those sharings consists of **recipient emails** and **selections**. Both are sent as lists, which means you can in a single sharing specify multiple recipients and multiple selections. You are free to manage the data in the code and persist as you feel is more appropriate.

It is important to allow the user to only input **selections** that are possible in the document being shared. The sheet names are the only part to be validated, since the user can select any single cell or range, even if empty. Therefore we compiled a list of the available Sheets in this document:

```
HRReport, Actuals, Assumptions, Dashboard
```

For the sake of simplicity you can assume:
* the whole application is always managing the same single file.
* to share the whole file, the user would specify all the possible selections

Later another developer would also have to create files considering those sharings and make the files available to the defined recipients, but for now, we only want to be able to manage (add, list) the sharing information. 

**Please describe to us:**
* **If you had to generate a new document every time a sharing is added/modified, where would you put that piece of code? What changes would have to be done to your project?**
* **How was your API design process and what was your motivation to on the choice of patterns**

## Examples of valid selections

```
SheetNameWithoutSpacesSingleCell!A1
'Sheet Name With Spaces Single Cell'!B4
OnlyTheSheetNameWithoutQuotes
'SheetNameWithQuotesNoSpaces'
'Sheet Name With Quotes And Spaces'
'Long Sheet Name With Spaces and Range'!B3:B5
SheetNameWithoutSpacesWithRanges!B2:B5
```

You might also want (but is not required to) use the following regular expression for validation:

PCRE
`^((?>(?>'[\w\s]+')|(?>[\w\s]+))(?>![A-Z]{1}[0-9]{1})?(?>:[A-Z]{1}[0-9])?)`

Javascript
`^('[\w\s]+'|[\w\s]+)(![A-Z]+[0-9]+)?(:[A-Z]+[0-9]+)?`

if you do, feel free to criticize and improve the expression if you feel it is needed

## Further instructions
* A new private repository was created only for your test (you can work directly on it, but if you fork, make sure to keep your copy private). When you finish notify fabio@golayer.io.
* Feel free to use any libraries that can be installed from a package manager, but do not use any rapid-development or full-stack frameworks (micro-frameworks or components of full-stack frameworks that can be independently installed and configured are allowed).
* **Consider that you are developing a production-ready service and it has to be maintained for the next 3 years.**
* Include a new Markdown file (or update this one) with your answer for the questions in the task and any further implementation details that you might want to explain. Do not hesitate to include suggestions on how this test could be improved.
* Please provide sample requests (curl commands are not required, but would be appreciated)
* Your application needs to run with as much simplicity as possible. We highly recommend you to use Docker and Docker Compose, but single line commands with few system dependencies would be accepted (in this case, please provide running instructions).
* Given the results fit our minimum acceptance criteria, we will schedule a call and this test will be one of the subjects of the conversation.
* We aim to make a final decision within seven days. Please try to submit the test within 3 to 4 days. If you are unable to provide the results within the given timeline, please let us know.
* Please contact us for any questions you might have during the process.

## Evaluation criteria
* You follow best practices appropriate to your language of choice
* Code is clean and readable.
* Code is maintainable. We will evaluate what that means for you based on your results.
* There is attention to the **domain** expressed in the code.
* There is attention to HTTP communication if that is appropriate in the language/framework used.
