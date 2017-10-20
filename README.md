# RealEstate
Main practical work for the Object Oriented Programming class at UFRGS.
## Abstract
TODO



# Programming Help Sheet

## Guide for Javadoc :

JavaDoc comments are always written between `/**` and `*/` and each line has to begin with `*`

- @param : to detail the input parameter of a method
- @return : to detail the return parameter of a method
- @throws : to detail the exceptions thrown by the method if raised
- @author : the author of the class
- @version : the version of the class
- @see : to add a reference to another class
- @since : the version since which the class exists
- @deprecated : indicates that a class is deprecated, since when and replaced by which class.

## Guide for Git :
Add sources to the repo by right-clicking the source, then Git -> Add.
Commit by clicking the "commit" button on the top-right of the IDE.
You can push each commit directly, or push all the day's commit at once (which is usually better and let's you work offline).

## Code syntax :
### Comment keywords
| Keyword | Function                    |
|:-------:|:----------------------------|
| `TODO:` | Things to leave for later   |
| `FIXME:`| Bugs that need to be fixed  |
| `NOTE:` | Notes for other programmers |

Things that you leave for later must be indicated by the keyword `TODO:` in a comment.
Things that don't work have to be indicated by `FIXME:` in the same way.
You can add notes to the other programmers with the keyword `NOTE:`
### Setters, getters and constructors' parameters
The tradition when you use a parameter to initialize a variable is to use the same name for the parameter, preceded by an underscore.

**For example** :
    
    /**
    * sets the name of the character
    * @param _name
    *   name of the character
    */
    public void setName(String _name) {
        name = _name;
    }
It's important because it adds more clarity to the code.
